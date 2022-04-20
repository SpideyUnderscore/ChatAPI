package dev.spiderman.chatapi.chat.message;

import dev.spiderman.chatapi.chat.message.snippet.MessageSnippet;
import dev.spiderman.chatapi.chat.message.snippet.content.TextContent;
import org.bukkit.entity.Player;

public class MessageBuilder {

	private final Player sender;
	private final String message;

	public MessageBuilder(Player sender, String message) {
		this.sender = sender;
		this.message = message;
	}

	public MessageBuilder split() {

		return this;
	}

	public ChatMessage create() {
		// TODO: create Message
		// when creating snippets use ChatColor.getLastColors from string before and pass to next snippet
		// example: ChatColor.RED + "Hello click here: " as first snippet, then "https://whatever.com"
		//                                                                     ^ here input ChatColor.getLastColors(ChatColor.RED + "Hello click here: ")
		// todoEnd

		// Emojis
		// Link
		// Mentions
		// Emails

		return new ChatMessage(sender, new MessageSnippet[] {new MessageSnippet<>(new TextContent("Hello how are you shit swear frick ")), new MessageSnippet<>(new TextContent(" is happening here???"))});
	}

}
