package dev.spiderman.chatapi.chat.message.snippet.content;

import dev.spiderman.chatapi.chat.message.snippet.MessageSnippet;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
