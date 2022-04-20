package dev.spiderman.chatapi.chat.meta;

import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.message.modifier.filter.MessageFilter;
import dev.spiderman.chatapi.chat.message.modifier.format.MessageFormatter;
import dev.spiderman.chatapi.chat.message.modifier.format.SnippetFormatter;
import dev.spiderman.chatapi.exception.ChatChannelNameException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class SendMeta {

	private MessageFilter filter;

	private BiFunction<ChatMessage, Player, BaseComponent[]> messageFormatter;
	private SnippetFormatter snippetFormatter;

	public SendMeta() {
		setFilter(MessageFilter.identity());
		setMessageFormatter((message, player) -> new MessageFormatter(message).appendSenderName().append(": ").appendMessage().format());
		setSnippetFormatter(new SnippetFormatter(messageSnippet -> messageSnippet.setColor(ChatColor.WHITE)));
	}

	public MessageFilter getFilter() {
		return filter;
	}

	public void setFilter(MessageFilter filter) {
		this.filter = filter;
	}

	public BiFunction<ChatMessage, Player, BaseComponent[]> getMessageFormatter() {
		return messageFormatter;
	}

	public void setMessageFormatter(BiFunction<ChatMessage, Player, BaseComponent[]> messageFormatter) {
		this.messageFormatter = messageFormatter;
	}

	public SnippetFormatter getSnippetFormatter() {
		return snippetFormatter;
	}

	public void setSnippetFormatter(SnippetFormatter snippetFormatter) {
		this.snippetFormatter = snippetFormatter;
	}

}
