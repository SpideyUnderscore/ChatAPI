package dev.spiderman.chatapi;

import dev.spiderman.chatapi.chat.channel.RegisteredChatAPIChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ChatAPI {

	protected static Logger logger;

	/**
	 * Gets the logger for ChatAPI
	 * This will be the plugins logger by default or a
	 * child of the Bukkit logger if not in the plugins folder
	 *
	 * @return
	 */
	public static Logger getLogger() {
		if (logger == null) {
			logger = new Logger("ChatAPI", (String) null) {
				{
					this.setParent(Bukkit.getServer().getLogger());
					this.setLevel(Level.ALL);
				}

				public void log(LogRecord logRecord) {
					logRecord.setMessage("[ChatAPI] " + logRecord.getMessage());
					super.log(logRecord);
				}
			};
		}
		return logger;
	}

	/**
	 * Logs a message to the console using Logger.info() if the configuration has
	 * verbose logging enabled
	 *
	 * @param message the message to log to the console
	 */
	public static void logInfo(String message) {
		// TODO: silent logs & verbose output
		getLogger().info(message);
	}

	/**
	 * Logs a message from the ChatAPI. If silent logs are enabled, this message
	 * is not logged.
	 *
	 * @param message the message to log
	 */
	public static void logNormal(String message) {
		// TODO: silent logs
		getLogger().fine(message);
	}

	/**
	 * Logs a warning from the ChatAPI. If silent logs are enabled, this warning
	 * is not logged.
	 *
	 * @param message the message to log as a warning
	 */
	public static void logWarning(String message) {
		// TODO: silent logs
		getLogger().warning(message);
	}

	/**
	 * Logs an error from the ChatAPI. This always gets logged, even if silent
	 * logs are enabled.
	 *
	 * @param message the message to log as an error
	 */
	public static void logError(String message) {
		getLogger().severe(message);
	}

	@Nullable
	public static RegisteredChatAPIChannel getChannel(String name) {
		for (RegisteredChatAPIChannel channel : ChatAPIHandler.getInstance().getRegisteredChannels()) {
			if (channel.getMeta().getName().equals(name)) {
				return channel;
			}
		}
		for (RegisteredChatAPIChannel channel : ChatAPIHandler.getInstance().getRegisteredChannels()) {
			for (String alias : channel.getMeta().getAliases()) {
				if (alias.equals(name)) {
					return channel;
				}
			}
		}
		return null;
	}

	public static void onEnable(Plugin plugin) {

		Bukkit.getPluginManager().registerEvents(new Listener() {

			@EventHandler(priority = EventPriority.LOWEST)
			public void onPlayerJoin(ServerLoadEvent event) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					ChatAPIHandler.getInstance().safelyAddPlayer(p);
				}
			}

		}, plugin);

		Bukkit.getPluginManager().registerEvents(new Listener() {

			@EventHandler(priority = EventPriority.LOWEST)
			public void onPlayerJoin(PlayerJoinEvent event) {
				ChatAPIHandler.getInstance().safelyAddPlayer(event.getPlayer());
			}

		}, plugin);

		Bukkit.getPluginManager().registerEvents(new Listener() {

			@EventHandler
			public void onPlayerChat(AsyncPlayerChatEvent event) {

				// handle cancelled
				// TODO: test
				if (event.isCancelled()) {
					return;
				}

				event.setCancelled(true);

				// ChatAPIPreProcessPlayerChatEvent
				/*ChatAPIRawMessageSendEvent chatAPIRawMessageSendEvent = new ChatAPIRawMessageSendEvent(event.getPlayer(), event.getMessage());
				Bukkit.getPluginManager().callEvent(chatAPIRawMessageSendEvent);
				if (chatAPIRawMessageSendEvent.isCancelled()) {
					return;
				}*/

				final Player sender = event.getPlayer();
				//final String message = chatAPIRawMessageSendEvent.getMessage();
				final String message = event.getMessage();

				ChatAPIHandler.getInstance().getPlayerChatData(sender).getChatMessageSendHandler().sendMessage(sender, message);

			}

		}, plugin);
	}

}
