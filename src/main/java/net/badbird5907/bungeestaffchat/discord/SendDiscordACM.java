package net.badbird5907.bungeestaffchat.discord;

import net.badbird5907.bungeestaffchat.util.Config;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class SendDiscordACM {
    public static void send(ProxiedPlayer player , String message){
        Configuration config = Config.getConfig("config");
        Configuration messages = Config.getConfig("messages");
        String pname = player.getDisplayName();
        String sentmessage = messages.getString("Discord-Messages.sent-admin-chat-msg")
                .replaceAll("%player%", pname)
                .replaceAll("%server%", player.getServer().getInfo().getName())
                .replaceAll("%message%", message);
        if(config.getBoolean("admin-chat"))
            sendmsg.sendmsg(sentmessage, config.getString("Discord.adminchat"));
    }
}
