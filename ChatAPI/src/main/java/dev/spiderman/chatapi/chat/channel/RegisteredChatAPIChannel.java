package dev.spiderman.chatapi.chat.channel;

import dev.spiderman.chatapi.chat.input.ChatInputHandler;
import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.meta.ChannelMeta;
import dev.spiderman.chatapi.chat.meta.SendMeta;
import org.bukkit.entity.Player;

public class RegisteredChatAPIChannel implements ChatInputHandler {

	private final ChannelMeta meta;

	public RegisteredChatAPIChannel(ChannelMeta meta) {
		this.meta = meta;
	}

	public ChannelMeta getMeta() {
		return meta;
	}

	@Override
	public void sendMessage(Player sender, String message) {

		// TODO: raw message event & chat message event
		// TODO: global, private filter/emojis

		// create ChatMessage
		ChatMessage chatMessage = ChatMessage.splitMessage(sender, message);
		chatMessage.clean();

		// modify formatter
		meta.getSnippetFormatter().apply(chatMessage);
		meta.getFilter().filter(chatMessage);

		for (Player p : meta.getReceivers().apply(chatMessage)) {
			p.spigot().sendMessage(meta.getMessageFormatter().apply(chatMessage, p));
		}

	}
}
