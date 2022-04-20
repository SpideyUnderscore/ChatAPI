package dev.spiderman.chatapi;

import dev.spiderman.chatapi.chat.channel.ChatAPIChannel;
import dev.spiderman.chatapi.chat.message.MessageFilter;
import dev.spiderman.chatapi.chat.message.StringReplacement;
import dev.spiderman.chatapi.chat.message.MessageFormatter;
import dev.spiderman.chatapi.chat.message.SnippetFormatter;
import dev.spiderman.chatapi.test.components.TestComponentCommand;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChatAPIMain extends JavaPlugin {

	@Override
	public void onLoad() {
		this.saveDefaultConfig();
		ChatAPI.logger = this.getLogger();
	}

	@Override
	public void onEnable() {
		ChatAPI.onEnable(this);
	}

	@Override
	public void onDisable() {

	}
}
