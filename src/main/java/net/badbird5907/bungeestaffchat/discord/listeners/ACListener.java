package net.badbird5907.bungeestaffchat.discord.listeners;

import net.badbird5907.bungeestaffchat.util.Config;
import net.badbird5907.bungeestaffchat.util.Log;
import net.badbird5907.bungeestaffchat.util.SendAdminChatMessage;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.md_5.bungee.config.Configuration;

public class ACListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        Configuration config = Config.getConfig("config");
        if(event.getChannel().getIdLong() == Long.parseLong(config.getString("Discord.adminchat").replaceAll("L", ""))){
            if (event.getAuthor().isBot()) return;
            Message message = event.getMessage();
            String content = message.getContentRaw();
            String name = event.getAuthor().getName();
            SendAdminChatMessage.DiscordSend(name, content);
            Log.info(   "Discord Admin Chat > " + name + ": " + content);
        }
        else return;
    }
}
