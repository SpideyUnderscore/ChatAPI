package dev.spiderman.chatapi.chat.message.modifier.filter;

public class StringReplacement {

	private String replacement;

	public StringReplacement(String replacement) {
		this.replacement = replacement;
	}

	public StringReplacement append(String str) {
		this.replacement = this.replacement + str;
		return this;
	}

	public StringReplacement appendPlaceholder() {
		this.replacement = this.replacement + "{ph}";
		return this;
	}

	public String getRaw() {
		return replacement;
	}

	public String getWith(char placeholder) {
		return replacement.replaceAll("\\{ph}", String.valueOf(placeholder));
	}

	////////////////////////////////////////////////////////

	public static StringReplacement replacingAll(String str) {
		return new StringReplacement(str.replaceAll(".", "{ph}"));
	}

	public static StringReplacement of(String str) {
		return new StringReplacement(str);
	}

}
