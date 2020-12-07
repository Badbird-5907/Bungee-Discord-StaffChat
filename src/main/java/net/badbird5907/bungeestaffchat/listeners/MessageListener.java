package net.badbird5907.bungeestaffchat.listeners;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.badbird5907.bungeestaffchat.util.SendAdminChatMessage;
import net.badbird5907.bungeestaffchat.util.SendStaffChatMessage;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class MessageListener implements Listener {
    @EventHandler
    public void onChat(ChatEvent e) {
        if (!(e.getSender() instanceof ProxiedPlayer))
            return;
        if (e.getMessage().startsWith("/"))
            return;
        ProxiedPlayer p = (ProxiedPlayer)e.getSender();
        Configuration config = Messages.getConfig("messages");
        if (BungeeStaffChat.StaffChat.contains(p.getUniqueId())) {
            e.setCancelled(true);
            SendStaffChatMessage.Send(p, e.getMessage());
        }
        if (BungeeStaffChat.AdminChat.contains(p.getUniqueId())) {
            e.setCancelled(true);
            SendAdminChatMessage.Send(p, e.getMessage());
        }
        if(e.getMessage().startsWith("#")){
            e.setCancelled(true);
            String message = e.getMessage().replaceFirst("#", "");
            SendStaffChatMessage.Send(p, message);
        }
    }
}
