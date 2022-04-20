package dev.spiderman.chatapi.chat.message;

import dev.spiderman.chatapi.chat.message.snippetcontent.EmailContent;
import dev.spiderman.chatapi.chat.message.snippetcontent.LinkContent;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

public class SnippetFormatter {

	private UnaryOperator<MessageSnippet<?>> textFormat;
	private UnaryOperator<MessageSnippet<LinkContent>> linkFormat;
	private UnaryOperator<MessageSnippet<EmailContent>> emailFormat;

	public SnippetFormatter() {
		this(messageSnippet -> {
			messageSnippet.setColor(ChatColor.WHITE);
			return messageSnippet;
		});
	}

	public SnippetFormatter(@NotNull UnaryOperator<MessageSnippet<?>> textFormat) {
		this.textFormat = textFormat;
	}

	public SnippetFormatter formatsText(UnaryOperator<MessageSnippet<?>> textFormat) {
		this.textFormat = textFormat;
		return this;
	}

	public SnippetFormatter formatsLink(UnaryOperator<MessageSnippet<LinkContent>> linkFormat) {
		this.linkFormat = linkFormat;
		return this;
	}

	public SnippetFormatter formatsEmail(UnaryOperator<MessageSnippet<EmailContent>> emailFormat) {
		this.emailFormat = emailFormat;
		return this;
	}

	//////////////////////////////////////////////////////////////////////////

	public UnaryOperator<MessageSnippet<?>> getTextFormat() {
		return textFormat;
	}

	public UnaryOperator<MessageSnippet<LinkContent>> getLinkFormat() {
		return linkFormat;
	}

	public UnaryOperator<MessageSnippet<EmailContent>> getEmailFormat() {
		return emailFormat;
	}

	public void apply(final ChatMessage message) {
		for (int i = 0; i < message.getSnippets().length; i++) {
			if (message.getSnippets()[i].getContent() instanceof LinkContent && this.linkFormat != null) {
				message.getSnippets()[i] = linkFormat.apply((MessageSnippet<LinkContent>) message.getSnippets()[i]);
				continue;
			}
			if (message.getSnippets()[i].getContent() instanceof EmailContent && this.emailFormat != null) {
				message.getSnippets()[i] = emailFormat.apply((MessageSnippet<EmailContent>) message.getSnippets()[i]);
				continue;
			}
			message.getSnippets()[i] = textFormat.apply(message.getSnippets()[i]);
		}
	}

}
