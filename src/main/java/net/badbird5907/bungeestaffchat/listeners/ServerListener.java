package net.badbird5907.bungeestaffchat.listeners;


import net.badbird5907.bungeestaffchat.discord.sendmsg;
import net.badbird5907.bungeestaffchat.util.Config;
import net.badbird5907.bungeestaffchat.util.CreateEmbed;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

import java.awt.*;

public class ServerListener implements Listener {
    @EventHandler
    public void OnServerDisconnect (ServerDisconnectEvent event){
        String ServerName = event.getTarget().getName();
        Configuration config = Config.getConfig("config");
        Configuration messages = Messages.getConfig("messages");
        String rawmsg = messages.getString("Server-Log.server-down").replaceAll("%server%", ServerName);
        MessageEmbed msg = CreateEmbed.create("Server Log", rawmsg, Color.RED);
        sendmsg.sendmsg(msg, config.getString("Server-Log.channel"));
    }
    @EventHandler
    public void OnServerConnect (ServerConnectEvent event){
        String ServerName = event.getTarget().getName();
        Configuration config = Config.getConfig("config");
        Configuration messages = Messages.getConfig("messages");
        String rawmsg = messages.getString("Server-Log.server-up").replaceAll("%server%", ServerName);
        MessageEmbed msg = CreateEmbed.create("Server Log", rawmsg, Color.GREEN);
        sendmsg.sendmsg(msg, config.getString("Server-Log.channel"));
    }
}