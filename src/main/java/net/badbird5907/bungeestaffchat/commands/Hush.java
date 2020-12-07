package net.badbird5907.bungeestaffchat.commands;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.badbird5907.bungeestaffchat.util.Permission;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

public class Hush extends Command {
    public Hush() {
        super("hush", Permission.STAFF_CHAT.node, new String[] { "hidesc" });
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer p = (ProxiedPlayer)sender;
            Configuration messages = Messages.getConfig("messages");
            if(!BungeeStaffChat.Hush.contains(p.getUniqueId())){
                if(BungeeStaffChat.StaffChat.contains(p.getUniqueId())) {
                    BungeeStaffChat.StaffChat.remove(p.getUniqueId());
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                            messages.getString("Messages.staffchatoff")
                            )));
                }
                if(BungeeStaffChat.AdminChat.contains(p.getUniqueId())) {
                    BungeeStaffChat.AdminChat.remove(p.getUniqueId());
                    p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                            messages.getString("Messages.adminchatoff")
                    )));
                }
                BungeeStaffChat.Hush.add(p.getUniqueId());
                p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.hush-on"))));
            }
            else{
                BungeeStaffChat.Hush.remove(p.getUniqueId());
                p.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.hush-off"))));
            }
        }
    }
}
