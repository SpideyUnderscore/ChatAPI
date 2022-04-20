package dev.spiderman.chatapi.chat.message.snippetcontent;

public interface MessageSnippetContent<T> {

	T get();

	void set(T t);

	String asString();

}
