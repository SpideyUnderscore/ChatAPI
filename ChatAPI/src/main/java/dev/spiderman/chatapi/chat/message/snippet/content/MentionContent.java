package dev.spiderman.chatapi.chat.message.snippet.content;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MentionContent implements MessageSnippetContent<Player> {

	private Player player;

	public MentionContent(String str) {
		this.player = Bukkit.getPlayer(str);
	}

	@Override
	public Player get() {
		return player;
	}

	@Override
	public void set(Player player) {
		this.player = player;
	}

	@Override
	public String asString() {
		return "@" + player.getName();
	}
}
