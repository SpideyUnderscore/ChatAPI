package dev.spiderman.chatapi.chat;

import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.message.MessageFilter;
import dev.spiderman.chatapi.chat.message.MessageFormatter;
import dev.spiderman.chatapi.chat.message.SnippetFormatter;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;

import java.util.function.BiFunction;

public sealed class SendMeta permits ChannelMeta, ConversationMeta {

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
