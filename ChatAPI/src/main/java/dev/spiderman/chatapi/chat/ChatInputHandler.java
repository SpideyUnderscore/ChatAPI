package dev.spiderman.chatapi.chat;

import org.bukkit.entity.Player;

public interface ChatInputHandler {

	void sendMessage(Player sender, String message);

}
