package dev.spiderman.chatapi.chat.message.snippet.content;

public interface MessageSnippetContent<T> {

	T get();

	void set(T t);

	String asString();

}
