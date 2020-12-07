package net.badbird5907.bungeestaffchat.commands;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.badbird5907.bungeestaffchat.util.Permission;
import net.badbird5907.bungeestaffchat.util.SendStaffChatMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class StaffChat extends Command {
    public StaffChat() {
        super("sc", Permission.STAFF_CHAT.node, new String[] { "staffchat" });
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            Configuration messages = Messages.getConfig("messages");
            if (args.length == 0) {
                if (p.hasPermission(Permission.STAFF_CHAT.node)) {
                    if(BungeeStaffChat.AdminChat.contains(p.getUniqueId())){
                        BungeeStaffChat.AdminChat.remove(p.getUniqueId());
                        p.sendMessage(new TextComponent(
                                ChatColor.translateAlternateColorCodes('&', 
                                        messages.getString("Messages.staff-chat-switched-to-admin"))
                        ));
                    }
                    if (BungeeStaffChat.StaffChat.contains(p.getUniqueId())) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.staffchatoff"))));
                        BungeeStaffChat.StaffChat.remove(p.getUniqueId());
                    } else {
                        BungeeStaffChat.StaffChat.add(p.getUniqueId());
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.staffchaton"))));
                    }
                } else {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.perms"))));
                }
            } else if (p.hasPermission(Permission.STAFF_CHAT.node)) {
                String msg = "";
                for (int i = 0; i < args.length; i++)
                    msg = msg + args[i] + " ";
                SendStaffChatMessage.Send(p, msg);
            } else {
                p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.perms"))));
            }
        }
    }
}
