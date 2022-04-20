package dev.spiderman.chatapi.event.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChatInputEvent extends Event {

	private static final HandlerList HANDLERS_LIST = new HandlerList();

	private final Object data;
	private final Player sender;

	public ChatInputEvent(@NotNull Player sender, @Nullable Object data) {
		this.data = data;
		this.sender = sender;
	}

	public Object getData() {
		return data;
	}

	public Player getSender() {
		return sender;
	}

	@NotNull
	@Override
	public HandlerList getHandlers() {
		return HANDLERS_LIST;
	}

	public static HandlerList getHandlerList() {
		return HANDLERS_LIST;
	}

}
