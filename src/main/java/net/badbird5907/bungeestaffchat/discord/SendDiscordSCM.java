package net.badbird5907.bungeestaffchat.discord;

import net.badbird5907.bungeestaffchat.util.Config;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class SendDiscordSCM {
    public static void send(ProxiedPlayer player , String message){
        Configuration config = Config.getConfig("config");
        Configuration messages = Messages.getConfig("messages");
        String pname = player.getDisplayName();
        String sentmessage = messages.getString("Discord-Messages.sent-staff-chat-msg")
                .replaceAll("%player%", pname)
                .replaceAll("%server%", player.getServer().getInfo().getName())
                .replaceAll("%message%", message);
        if(config.getBoolean("staff-chat"))
            sendmsg.sendmsg(sentmessage, config.getString("Discord.staffchat"));
    }
}
