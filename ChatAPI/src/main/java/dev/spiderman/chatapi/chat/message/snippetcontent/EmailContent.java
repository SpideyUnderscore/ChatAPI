package dev.spiderman.chatapi.chat.message.snippetcontent;

public class EmailContent implements MessageSnippetContent<String> {

	private String email;

	public EmailContent(String email) {
		this.email = email;
	}

	@Override
	public String get() {
		return this.email;
	}

	@Override
	public void set(String email) {
		this.email = email;
	}

	@Override
	public String asString() {
		return email;
	}

}
