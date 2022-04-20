package dev.spiderman.chatapi.chat.message.snippet.content;

public class TextContent implements MessageSnippetContent<String> {

	private String text;

	public TextContent(String text) {
		this.text = text;
	}

	@Override
	public String get() {
		return text;
	}

	@Override
	public void set(String s) {
		this.text = text;
	}

	@Override
	public String asString() {
		return this.text;
	}

}
