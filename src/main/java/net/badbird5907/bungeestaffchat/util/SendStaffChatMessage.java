package net.badbird5907.bungeestaffchat.util;


import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.badbird5907.bungeestaffchat.discord.SendDiscordSCM;
import net.badbird5907.bungeestaffchat.discord.sendmsg;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

public class SendStaffChatMessage {
    public static void Send(ProxiedPlayer p, String message){
        Configuration config = Messages.getConfig("messages");
        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
            if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staffchat")
                        .replaceAll("%message%", message)
                        .replaceAll("%player%", p.getDisplayName())
                        .replaceAll("%server%", p.getServer().getInfo().getName())));
                if(BungeeStaffChat.Hush.contains(staff.getUniqueId())) { }
                else{staff.sendMessage(cp);}
            }
        }
        SendDiscordSCM.send(p, message);
    }
    public static void SendCustom(String sender,String server_null_if_none ,String message, Boolean discord){
        Configuration config = Messages.getConfig("messages");
        for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
            if (staff.hasPermission(Permission.STAFF_CHAT.node)) {
                if(server_null_if_none == null){
                    TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staffchat")
                            .replaceAll("%message%", message)
                            .replaceAll("%player%", sender)
                            .replace("(%server%)", "")
                            .replaceFirst("%server%", "")
                            .replaceAll("%server%", ""))
                    );
                    if(BungeeStaffChat.Hush.contains(staff.getUniqueId())) { }
                    else{staff.sendMessage(cp);}
                }
                else{
                    TextComponent cp = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staffchat")
                            .replaceAll("%message%", message)
                            .replaceAll("%player%", sender)
                            .replaceAll("%server%", server_null_if_none)));
                    if(BungeeStaffChat.Hush.contains(staff.getUniqueId())) { }
                    else{staff.sendMessage(cp);}
                }
            }
        }
        Configuration conf = Config.getConfig("config");
        if(discord)
            sendmsg.sendmsg(message, conf.getString("Discord.staffchat"));
    }
    public static void DiscordSend(String sender, String message){
        Configuration config = Messages.getConfig("messages");
        for(ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()){
            TextComponent textComponent = new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Discord-Messages.discordsc")
                    .replaceAll("%user%", sender)
                    .replaceAll("%message%", message)
            ));
            if(BungeeStaffChat.Hush.contains(staff.getUniqueId())) { }
            else{staff.sendMessage(textComponent);}
        }
    }
}
