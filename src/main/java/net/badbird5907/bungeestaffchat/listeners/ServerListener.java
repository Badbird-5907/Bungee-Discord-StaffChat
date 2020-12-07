package net.badbird5907.bungeestaffchat.listeners;


import net.badbird5907.bungeestaffchat.util.Config;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.badbird5907.bungeestaffchat.util.Permission;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.event.EventHandler;

public class ServerListener implements Listener {
    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = Messages.getConfig("messages");
        Configuration conf = Config.getConfig("config");
        if(conf.getBoolean("broadcast-leave")){
            if (p.hasPermission(Permission.BROADCAST_LEAVE.node) &&
                    config.getBoolean("Config.enable-leave-message"))
                for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                    if (staff.hasPermission(Permission.STAFF_CHAT.node))
                        staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-leave-network")
                                .replaceAll("%player%", p.getDisplayName()))));
                }
        }
    }

    @EventHandler
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = Messages.getConfig("messages");
        Configuration conf = Config.getConfig("config");
        if(conf.getBoolean("broadcast-join")){
            if (p.hasPermission(Permission.BROATCAST_JOIN.node) &&
                    config.getBoolean("Config.enable-join-message"))
                for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                    if (staff.hasPermission(Permission.STAFF_CHAT.node))
                        staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-join-network")
                                .replaceAll("%player%", p.getDisplayName()))));
                }
        }
    }
    @EventHandler
    public void onSwitch(ServerSwitchEvent e) {
        ProxiedPlayer p = e.getPlayer();
        Configuration config = Messages.getConfig("messages");
        if (p.hasPermission(Permission.BROADCAST_SWITCH.node) &&
                config.getBoolean("Config.enable-switch-messages"))
            for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
                if (staff.hasPermission(Permission.STAFF_CHAT.node) &&
                        e.getFrom() != null) {
                    String sserver = e.getFrom().getName().toString();
                    String dserver = e.getPlayer().getServer().getInfo().getName();
                    staff.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', config.getString("Messages.staff-switch-server")
                            .replaceAll("%from%", sserver)
                            .replaceAll("%to%", dserver)
                            .replaceAll("%player%", p.getDisplayName()))));
                }
            }
    }
}
