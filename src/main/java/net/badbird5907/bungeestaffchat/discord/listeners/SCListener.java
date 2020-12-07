package net.badbird5907.bungeestaffchat.discord.listeners;

import net.badbird5907.bungeestaffchat.util.Config;
import net.badbird5907.bungeestaffchat.util.Log;
import net.badbird5907.bungeestaffchat.util.SendStaffChatMessage;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.config.Configuration;

public class SCListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Configuration config = Config.getConfig("config");
        if(event.getChannel().getIdLong() == Long.parseLong(config.getString("Discord.staffchat").replaceAll("L", ""))){
            if (event.getAuthor().isBot()) return;
            Message message = event.getMessage();
            String content = message.getContentRaw();
            String name = event.getAuthor().getName();
            SendStaffChatMessage.DiscordSend(name, content);
            Log.info("Discord Staff Chat > " + name + ": " + content);
        }
        else return;
    }
}
