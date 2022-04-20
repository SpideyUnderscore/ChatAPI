package dev.spiderman.chatapi.chat.message;

import java.util.HashMap;

public class MessageFilter {

	private char placeHolder = '*';
	private final HashMap<String, StringReplacement> replacements = new HashMap<>();

	public MessageFilter() {
	}

	public MessageFilter(char placeHolder) {
		this.placeHolder = placeHolder;
	}

	public char getPlaceHolder() {
		return placeHolder;
	}

	public void setPlaceHolder(char placeHolder) {
		this.placeHolder = placeHolder;
	}

	public MessageFilter replaces(String str, StringReplacement replacement) {
		replacements.put(str, replacement);
		return this;
	}

	public MessageFilter replaces(String str, String replacement) {
		replacements.put(str, StringReplacement.of(str));
		return this;
	}

	public MessageFilter replaces(String str) {
		replacements.put(str, StringReplacement.replacingAll(str));
		return this;
	}

	public MessageFilter replacesAll(String... strings) {
		for (String str : strings) {
			replacements.put(str, StringReplacement.replacingAll(str));
		}
		return this;
	}

	public HashMap<String, StringReplacement> getReplacements() {
		return replacements;
	}

	public static MessageFilter identity() {
		return new MessageFilter() {
			@Override
			public boolean filter(ChatMessage message) {
				return false;
			}
		};
	}

	public boolean filter(ChatMessage message) {
		String raw = message.getRaw();

		for (MessageSnippet<?> snippet : message.getSnippets()) {
			snippet.setDisplayText(filterString(snippet.getDisplayText()));
		}

		return raw.equals(message.getRaw());
	}

	private String filterString(String str) {
		for (String match : replacements.keySet()) {
			str = str.replaceAll("(?i)" + match, replacements.get(match).getWith(placeHolder));
		}
		return str;
	}

	public MessageFilter and(MessageFilter other) {
		final MessageFilter result = this;
		result.setPlaceHolder(other.getPlaceHolder());
		result.getReplacements().putAll(other.getReplacements());
		return result;
	}

}
