package dev.spiderman.chatapi.event.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EnableChatInputModeEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList HANDLERS_LIST = new HandlerList();
	private boolean cancel;

	private final Plugin plugin;
	private final Object data;
	private String message;

	public EnableChatInputModeEvent(@NotNull Player who, @NotNull Plugin plugin, @Nullable Object data, @Nullable String message) {
		super(who);
		this.cancel = false;
		this.plugin = plugin;
		this.data = data;
		this.message = message;
	}

	public Plugin getPlugin() {
		return plugin;
	}

	@Nullable
	public Object getData() {
		return data;
	}

	@Nullable
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean isCancelled() {
		return this.cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	@NotNull
	@Override
	public HandlerList getHandlers() {
		return null;
	}
}
