package net.badbird5907.bungeestaffchat.discord;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

public class sendmsg {
    private static JDA jda;
    public static void sendmsg(String message, String channelID){
        long id = Long.parseLong(channelID.replaceAll("L", ""));
        jda = BungeeStaffChat.getJDA();
        TextChannel channel = jda.getTextChannelById(id);
        channel.sendMessage(message).queue();
    }
}
