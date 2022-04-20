package dev.spiderman.chatapi.chat;

import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.message.modifier.filter.MessageFilter;
import dev.spiderman.chatapi.chat.message.modifier.format.MessageFormatter;
import dev.spiderman.chatapi.chat.message.modifier.format.SnippetFormatter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Predicate;

public final class ChannelMeta extends SendMeta {

	@NotNull private String name;
	private String[] aliases;

	private Function<ChatMessage, Player[]> receivers;

	private Predicate<Player> requirement;

	public ChannelMeta(@NotNull String name) {
		this.name = name;

		setAliases(new String[0]);
		setFilter(MessageFilter.identity());
		setMessageFormatter((message, player) -> new MessageFormatter(message).appendSenderName().append(": ").appendMessage().format());
		setSnippetFormatter(new SnippetFormatter(messageSnippet -> messageSnippet.setColor(ChatColor.WHITE)));
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}

	public String[] getAliases() {
		return aliases;
	}

	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}

	public Function<ChatMessage, Player[]> getReceivers() {
		return receivers;
	}

	public void setReceivers(Function<ChatMessage, Player[]> receivers) {
		this.receivers = receivers;
	}

	public Predicate<Player> getRequirement() {
		return requirement;
	}

	public void setRequirement(Predicate<Player> requirement) {
		this.requirement = requirement;
	}
}
