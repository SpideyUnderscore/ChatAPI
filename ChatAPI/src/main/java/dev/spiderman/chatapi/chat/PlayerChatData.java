package dev.spiderman.chatapi.chat;

import dev.spiderman.chatapi.ChatAPI;
import org.bukkit.entity.Player;

public class PlayerChatData {

	private final Player player;

	private ChatInputHandler currentChannel;

	public PlayerChatData(Player player) {
		this.player = player;
		this.currentChannel = ChatAPI.getDefaultChannel() == null ? ChatInputHandler.empty() : ChatAPI.getDefaultChannel();
	}

	public Player getPlayer() {
		return player;
	}

	public ChatInputHandler getCurrentChannel() {
		return currentChannel;
	}

	public void setCurrentChannel(ChatInputHandler currentChannel) {
		this.currentChannel = currentChannel;
	}

}
