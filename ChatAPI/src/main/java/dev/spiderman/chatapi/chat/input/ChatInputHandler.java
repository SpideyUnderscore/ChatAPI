package dev.spiderman.chatapi.chat.input;

import org.bukkit.entity.Player;

public interface ChatInputHandler {

	void sendMessage(Player sender, String message);

}
