package dev.spiderman.chatapi.chat.message.snippetcontent;

public class LinkContent implements MessageSnippetContent<String> {

	private String link;

	public LinkContent(String link) {
		this.link = link;
	}

	@Override
	public String get() {
		return link;
	}

	@Override
	public void set(String link) {
		this.link = link;
	}

	@Override
	public String asString() {
		return link;
	}
}
