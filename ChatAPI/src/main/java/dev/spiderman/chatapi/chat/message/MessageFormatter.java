package dev.spiderman.chatapi.chat.message;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageFormatter {

	private final ChatMessage message;
	private final List<BaseComponent> components = new ArrayList<>();

	public MessageFormatter(ChatMessage message) {
		this.message = message;
	}

	public MessageFormatter append(String str) {
		Collections.addAll(components, TextComponent.fromLegacyText(str));
		return this;
	}

	public MessageFormatter appendMessage() {
		Collections.addAll(components, message.toBaseComponents());
		return this;
	}

	public MessageFormatter appendSenderName() {
		append(message.getSender().getName());
		return this;
	}

	public MessageFormatter appendSenderDisplayName() {
		append(message.getSender().getDisplayName());
		return this;
	}

	public BaseComponent[] format() {
		return components.toArray(BaseComponent[]::new);
	}

}
