package dev.spiderman.chatapi.chat.message;

import dev.spiderman.chatapi.chat.message.snippetcontent.EmailContent;
import dev.spiderman.chatapi.chat.message.snippetcontent.LinkContent;
import dev.spiderman.chatapi.chat.message.snippetcontent.MentionContent;
import dev.spiderman.chatapi.chat.message.snippetcontent.TextContent;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChatMessage {

	private final Player sender;
	private MessageSnippet<?>[] snippets;

	public ChatMessage(Player sender, MessageSnippet<?>[] snippets) {
		this.sender = sender;
		this.snippets = snippets;
	}

	public void clean() {
		this.snippets = Arrays.stream(snippets).filter(messageSnippet -> !messageSnippet.isEmpty()).toArray(MessageSnippet<?>[]::new);
	}

	public BaseComponent[] toBaseComponents() {
		if (snippets.length == 1) {
			return snippets[0].toBaseComponents();
		}

		if (snippets.length > 1) {
			return Arrays.stream(snippets).map(MessageSnippet::toBaseComponents).flatMap(Stream::of).toArray(BaseComponent[]::new);
		}

		return TextComponent.fromLegacyText("");
	}

	public String getRaw() {
		StringBuilder builder = new StringBuilder();
		for (MessageSnippet<?> snippet : snippets) {
			builder.append(snippet.getDisplayText());
		}
		return builder.toString();
	}

	public MessageSnippet<?>[] getSnippets() {
		return snippets;
	}

	public Player getSender() {
		return sender;
	}

	////////////////////////
	// static
	////////////////////////

	public static ChatMessage plainTextChatMessage(Player sender, String content) {
		return new ChatMessage(sender, new MessageSnippet[] {new MessageSnippet<TextContent>(new TextContent(content))});
	}

	// TODO: update splitting od message to snippets
	public static ChatMessage splitMessage(Player sender, String message) {

		// delimiter
		String delimiter = UUID.randomUUID().toString().substring(0, 5);
		// urls
		Pattern urlPattern = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
		for (String result : urlPattern.matcher(message).results().map(MatchResult::group).distinct().collect(Collectors.toList())) {
			System.out.println(result);
			message = message.replaceAll(result, delimiter + result + delimiter);
		}
		// emails
		Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
		for (String result : emailPattern.matcher(message).results().map(MatchResult::group).distinct().collect(Collectors.toList())) {
			System.out.println(result);
			message = message.replaceAll(result, delimiter + result + delimiter);
		}
		// mentions
		Pattern mentionPattern = Pattern.compile("(^|\s)@[a-zA-Z_]{3,16}(\s|$)");
		for (String result : mentionPattern.matcher(message).results().map(MatchResult::group).distinct().collect(Collectors.toList())) {
			System.out.println(result);
			message = message.replaceAll(result, delimiter + result + delimiter);
		}
		// raw snippets
		String[] rawSnippets = message.split(delimiter);
		// convert to MessageSnippets
		MessageSnippet<?>[] snippets = Arrays.stream(rawSnippets).map(str -> {
			if (urlPattern.matcher(str).matches()) return new MessageSnippet<>(new LinkContent(str));
			if (emailPattern.matcher(str).matches()) return new MessageSnippet<>(new EmailContent(str));
			if (mentionPattern.matcher(str).matches()) return new MessageSnippet<>(new MentionContent(str));
			return new MessageSnippet<>(new TextContent(str));
		}).toArray(MessageSnippet<?>[]::new);
		// return ChatMessage
		ChatMessage result = new ChatMessage(sender, snippets);
		result.clean();
		return result;

	}

}
