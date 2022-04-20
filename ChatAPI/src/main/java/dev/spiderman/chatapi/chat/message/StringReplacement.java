package dev.spiderman.chatapi.chat.message;

public class StringReplacement {

	public static final String PLACEHOLDER = "{ph}";

	private String replacement;

	public StringReplacement(String replacement) {
		this.replacement = replacement;
	}

	public StringReplacement append(String str) {
		this.replacement = this.replacement + str;
		return this;
	}

	public StringReplacement appendPlaceholder() {
		this.replacement = this.replacement + PLACEHOLDER;
		return this;
	}

	public String getRaw() {
		return replacement;
	}

	public String getWith(char placeholder) {
		return replacement.replaceAll(PLACEHOLDER, String.valueOf(placeholder));
	}

	////////////////////////////////////////////////////////

	public static StringReplacement replacingAll(String str) {
		return new StringReplacement(str.replaceAll(".", PLACEHOLDER));
	}

	public static StringReplacement of(String str) {
		return new StringReplacement(str);
	}

}
