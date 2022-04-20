package dev.spiderman.chatapi.chat.message.snippet;

import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.message.snippet.content.MessageSnippetContent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;

import java.util.Optional;

public class MessageSnippet <T extends MessageSnippetContent> {

	private T content;

	private String displayText;

	private ChatColor color;
	private Optional<Boolean> underlined = Optional.empty();
	private Optional<Boolean> italic = Optional.empty();
	private Optional<Boolean> strikethrough = Optional.empty();
	private Optional<Boolean> obfuscated = Optional.empty();

	private HoverEvent hoverEvent;
	private ClickEvent clickEvent;

	public MessageSnippet(T content) {
		this.content = content;
		this.displayText = content.asString();
	}

	public BaseComponent[] toBaseComponents() {
		BaseComponent[] components = TextComponent.fromLegacyText(displayText);
		for (BaseComponent component : components) {
			if (color != null) component.setColor(color);
			if (hoverEvent != null) component.setHoverEvent(hoverEvent);
			if (clickEvent != null) component.setClickEvent(clickEvent);

			underlined.ifPresent(component::setUnderlined);
			italic.ifPresent(component::setItalic);
			strikethrough.ifPresent(component::setStrikethrough);
			obfuscated.ifPresent(component::setObfuscated);
		}
		return components;
	}

	public boolean isBlank() {
		return this.displayText.isBlank();
	}

	public boolean isEmpty() {
		return this.displayText.isEmpty();
	}

	//////////////////////////////
	// getters / setters
	//////////////////////////////

	public T getContent() {
		return content;
	}

	public MessageSnippet<T> setContent(T content) {
		this.content = content;
		return this;
	}

	public String getDisplayText() {
		return displayText;
	}

	public MessageSnippet<T> setDisplayText(String displayText) {
		this.displayText = displayText;
		return this;
	}

	public ChatColor getColor() {
		return color;
	}

	public MessageSnippet<T> setColor(ChatColor color) {
		this.color = color;
		return this;
	}

	public boolean isUnderlined() {
		return underlined.orElse(false);
	}

	public MessageSnippet<T> setUnderlined(boolean underlined) {
		this.underlined = Optional.of(underlined);
		return this;
	}

	public boolean isItalic() {
		return italic.orElse(false);
	}

	public MessageSnippet<T> setItalic(boolean italic) {
		this.italic = Optional.of(italic);
		return this;
	}

	public boolean isStrikethrough() {
		return strikethrough.orElse(false);
	}

	public MessageSnippet<T> setStrikethrough(boolean strikethrough) {
		this.strikethrough = Optional.of(strikethrough);
		return this;
	}

	public boolean isObfuscated() {
		return obfuscated.orElse(false);
	}

	public MessageSnippet<T> setObfuscated(boolean obfuscated) {
		this.obfuscated = Optional.of(obfuscated);
		return this;
	}

	public HoverEvent getHoverEvent() {
		return hoverEvent;
	}

	public MessageSnippet<T> setHoverEvent(HoverEvent hoverEvent) {
		this.hoverEvent = hoverEvent;
		return this;
	}

	public ClickEvent getClickEvent() {
		return clickEvent;
	}

	public MessageSnippet<T> setClickEvent(ClickEvent clickEvent) {
		this.clickEvent = clickEvent;
		return this;
	}

}
