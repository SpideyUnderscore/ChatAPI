package dev.spiderman.chatapi.chat;

import dev.spiderman.chatapi.ChatAPI;
import org.bukkit.entity.Player;

public class PlayerChatData {

	private final Player player;

	private ChatInputHandler chatMessageSendHandler;

	public PlayerChatData(Player player) {
		this.player = player;
		this.chatMessageSendHandler = ChatAPI.getChannel("TEST");
	}

	public Player getPlayer() {
		return player;
	}

	public ChatInputHandler getChatMessageSendHandler() {
		return chatMessageSendHandler;
	}

	public void setChatMessageSendHandler(ChatInputHandler chatMessageSendHandler) {
		this.chatMessageSendHandler = chatMessageSendHandler;
	}

}
