package dev.spiderman.chatapi.chat.channel;

import dev.spiderman.chatapi.ChatAPIHandler;
import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.message.modifier.filter.MessageFilter;
import dev.spiderman.chatapi.chat.message.modifier.format.SnippetFormatter;
import dev.spiderman.chatapi.chat.meta.ChannelMeta;
import dev.spiderman.chatapi.chat.meta.SendMeta;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class ChatAPIChannel {

	private ChannelMeta meta;

	public ChatAPIChannel(@NotNull String name) {
		this.meta = new ChannelMeta(name);
	}

	public ChatAPIChannel withAliases(String... aliases) {
		this.meta.setAliases(aliases);
		return this;
	}

	public ChatAPIChannel withReceivers(Function<ChatMessage, Player[]> receivers) {
		this.meta.setReceivers(receivers);
		return this;
	}

	public ChatAPIChannel withFilter(MessageFilter filter) {
		this.meta.setFilter(filter);
		return this;
	}

	public ChatAPIChannel formatsMessage(Function<ChatMessage, BaseComponent[]> messageFormatter) {
		return formatsMessage((message, player) -> messageFormatter.apply(message));
	}

	public ChatAPIChannel formatsMessage(BiFunction<ChatMessage, Player, BaseComponent[]> messageFormatter) {
		this.meta.setMessageFormatter(messageFormatter);
		return this;
	}

	public ChatAPIChannel withSnippetFormatter(SnippetFormatter snippetFormatter) {
		this.meta.setSnippetFormatter(snippetFormatter);
		return this;
	}

	public ChatAPIChannel withRequirement(Predicate<Player> requirement) {
		this.meta.setRequirement(this.meta.getRequirement().and(requirement));
		return this;
	}

	public void register() {
		ChatAPIHandler.getInstance().register(meta);
	}

}
