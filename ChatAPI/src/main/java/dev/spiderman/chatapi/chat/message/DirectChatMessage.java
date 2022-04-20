package dev.spiderman.chatapi.chat.message;

import dev.spiderman.chatapi.chat.message.snippet.MessageSnippet;
import org.bukkit.entity.Player;

public class DirectChatMessage extends ChatMessage {

	private Player receiver;

	public DirectChatMessage(Player sender, Player receiver, MessageSnippet<?>[] snippets) {
		super(sender, snippets);
		this.receiver = receiver;
	}

}
