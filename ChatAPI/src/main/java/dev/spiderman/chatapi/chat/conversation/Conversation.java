package dev.spiderman.chatapi.chat.conversation;

import dev.spiderman.chatapi.chat.ChatInputHandler;
import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.ConversationMeta;
import org.bukkit.entity.Player;

public class Conversation implements ChatInputHandler {

	private ConversationMeta meta;

	public Conversation(ConversationMeta meta) {
		this.meta = meta;
	}

	@Override
	public void sendMessage(Player sender, String message) {

		ChatMessage chatMessage = ChatMessage.splitMessage(sender, message);

		meta.getSnippetFormatter().apply(chatMessage);
		meta.getFilter().filter(chatMessage);

		sender.spigot().sendMessage(meta.getMessageFormatter().apply(chatMessage, sender));
		meta.getReceiver().spigot().sendMessage(meta.getMessageFormatter().apply(chatMessage, meta.getReceiver()));
	}

}
