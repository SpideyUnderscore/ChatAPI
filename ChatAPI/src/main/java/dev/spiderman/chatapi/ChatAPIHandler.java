package dev.spiderman.chatapi;

import dev.spiderman.chatapi.chat.ChannelMeta;
import dev.spiderman.chatapi.chat.PlayerChatData;
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

	private RegisteredChatAPIChannel defaultChannel;

	protected void safelyAddPlayer(Player player) {
		playerChatDataHashMap.putIfAbsent(player.getUniqueId(), new PlayerChatData(player));
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

	public void registerChannel(ChannelMeta meta, boolean asDefault) {
		RegisteredChatAPIChannel channel = new RegisteredChatAPIChannel(meta);
		registeredChannels.add(channel);
		if (asDefault) ChatAPI.setDefaultChannel(channel);
	}

	/////////////////////////////////////
	// getters / setters
	/////////////////////////////////////

	public List<RegisteredChatAPIChannel> getRegisteredChannels() {
		return registeredChannels;
	}

	public RegisteredChatAPIChannel getDefaultChannel() {
		return defaultChannel;
	}

	public void setDefaultChannel(RegisteredChatAPIChannel defaultChannel) {
		this.defaultChannel = defaultChannel;
	}
}
