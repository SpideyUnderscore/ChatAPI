package dev.spiderman.chatapi.chat;

import org.bukkit.entity.Player;

public final class ConversationMeta extends SendMeta {

	private Player receiver;

	public Player getReceiver() {
		return receiver;
	}

	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}

}
