package dev.spiderman.chatapi.chat.meta;

import org.bukkit.entity.Player;

public class ConversationMeta extends SendMeta {

	private Player receiver;

	public Player getReceiver() {
		return receiver;
	}

	public void setReceiver(Player receiver) {
		this.receiver = receiver;
	}

}
