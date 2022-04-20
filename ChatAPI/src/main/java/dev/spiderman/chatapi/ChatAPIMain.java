package dev.spiderman.chatapi;

import dev.spiderman.chatapi.chat.channel.ChatAPIChannel;
import dev.spiderman.chatapi.chat.message.modifier.filter.MessageFilter;
import dev.spiderman.chatapi.chat.message.modifier.filter.StringReplacement;
import dev.spiderman.chatapi.chat.message.modifier.format.MessageFormatter;
import dev.spiderman.chatapi.chat.message.modifier.format.SnippetFormatter;
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
		getCommand("testcomp").setExecutor(new TestComponentCommand());

		// test
		Bukkit.broadcastMessage("]----- Enabled ChatAPI -----[");

		new ChatAPIChannel("TEST")
				.withAliases("TESTING", "THE_TEST")
				.withFilter(new MessageFilter('*')
						.replaces("asshole")
						.replaces("idiot", StringReplacement.of("id**t")))
				.formatsMessage(message -> new MessageFormatter(message).appendSenderName().append(": ").appendMessage().format())
				.withSnippetFormatter(new SnippetFormatter()
						.formatsText(messageSnippet -> messageSnippet.setColor(ChatColor.YELLOW))
						.formatsLink(messageSnippet -> messageSnippet
								.setColor(ChatColor.WHITE)
								.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(ChatColor.GRAY + "Click to open " + ChatColor.BLUE + messageSnippet.getContent().asString())))
								.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, messageSnippet.getContent().asString()))))
				.register();

		Bukkit.broadcastMessage("]----- Tested ChatAPI -----[");

	}

	@Override
	public void onDisable() {

	}
}
