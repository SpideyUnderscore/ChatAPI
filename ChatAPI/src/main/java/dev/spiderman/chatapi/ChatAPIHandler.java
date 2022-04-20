package dev.spiderman.chatapi;

import dev.spiderman.chatapi.chat.PlayerChatData;
import dev.spiderman.chatapi.chat.ChannelMeta;
import dev.spiderman.chatapi.chat.channel.RegisteredChatAPIChannel;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ChatAPIHandler {

	public static ChatAPIHandler instance;

	private final HashMap<UUID, PlayerChatData> playerChatDataHashMap = new HashMap<>();
	private final List<RegisteredChatAPIChannel> registeredChannels = new ArrayList<>();

	protected void safelyAddPlayer(Player player) {
		if (playerChatDataHashMap.containsKey(player.getUniqueId())) {
			return;
		}

		// TODO: other handler
		playerChatDataHashMap.put(player.getUniqueId(), new PlayerChatData(player));
	}

	public PlayerChatData getPlayerChatData(Player player) {
		return playerChatDataHashMap.get(player.getUniqueId());
	}

	public static ChatAPIHandler getInstance() {
		if (instance == null) {
			instance = new ChatAPIHandler();
		}

		return instance;
	}

	public void register(ChannelMeta meta) {
		registeredChannels.add(new RegisteredChatAPIChannel(meta));
	}

	/////////////////////////////////////
	// getters / setters
	/////////////////////////////////////

	public List<RegisteredChatAPIChannel> getRegisteredChannels() {
		return registeredChannels;
	}

}
