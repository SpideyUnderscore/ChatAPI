package dev.spiderman.chatapi.test.components;

import dev.spiderman.chatapi.chat.message.ChatMessage;
import dev.spiderman.chatapi.chat.message.MessageBuilder;
import dev.spiderman.chatapi.chat.message.modifier.filter.MessageFilter;
import dev.spiderman.chatapi.chat.message.modifier.filter.StringReplacement;
import dev.spiderman.chatapi.chat.message.MessageSnippet;
import dev.spiderman.chatapi.chat.message.snippetcontent.TextContent;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TestComponentCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

		MessageSnippet<TextContent> messageSnippet2 = new MessageSnippet<>(new TextContent(ChatColor.RED + "TEST SNIPPET 3 " + ChatColor.UNDERLINE + " (multiple colors)"));
		messageSnippet2.setItalic(true);
		messageSnippet2.setStrikethrough(true);
		messageSnippet2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(messageSnippet2.getContent().asString())));

		sender.spigot().sendMessage(messageSnippet2.toBaseComponents());

		////////////////////////////////////////////////////

		sender.spigot().sendMessage(new ComponentBuilder(ChatColor.RED + "red " + ChatColor.YELLOW + "yellow").italic(true).create());

		////////////////////////////////////////////////////

		MessageFilter filter = new MessageFilter()
				.replaces("wtf", StringReplacement.replacingAll("wtf"))
				.replaces("shit", StringReplacement.of("sh*t"));
		ChatMessage msg = new MessageBuilder((Player) sender, "Hello how are you shit swear wtf is happening here???").create();
		sender.sendMessage("without filter:");
		sender.spigot().sendMessage(msg.toBaseComponents());
		sender.sendMessage("w/ filter:");
		filter.filter(msg);
		sender.spigot().sendMessage(msg.toBaseComponents());

		

		return true;
	}

}
