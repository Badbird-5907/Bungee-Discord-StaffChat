package net.badbird5907.bungeestaffchat.commands;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.badbird5907.bungeestaffchat.util.Permission;
import net.badbird5907.bungeestaffchat.util.SendAdminChatMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class AdminChat extends Command {
    public AdminChat() {
        super("adminchat", Permission.ADMIN_CHAT.node, new String[] { "ac" });
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            Configuration messages = Messages.getConfig("messages");
            if (args.length == 0) {
                if (p.hasPermission(Permission.ADMIN_CHAT.node)) {
                    if(BungeeStaffChat.StaffChat.contains(p.getUniqueId())){
                        BungeeStaffChat.StaffChat.remove(p.getUniqueId());
                        p.sendMessage(new TextComponent(
                                ChatColor.translateAlternateColorCodes('&',
                                        messages.getString("Messages.admin-chat-switched-to-staff"))
                        ));
                    }
                    if (BungeeStaffChat.AdminChat.contains(p.getUniqueId())) {
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.adminchatoff"))));
                        BungeeStaffChat.AdminChat.remove(p.getUniqueId());
                    } else {
                        BungeeStaffChat.AdminChat.add(p.getUniqueId());
                        p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.adminchaton"))));
                    }
                } else {
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.perms"))));
                }
            } else if (p.hasPermission(Permission.ADMIN_CHAT.node)) {
                String msg = "";
                for (int i = 0; i < args.length; i++)
                    msg = msg + args[i] + " ";
                SendAdminChatMessage.Send(p, msg);
            } else {
                p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.perms"))));
            }
        }
    }
}
