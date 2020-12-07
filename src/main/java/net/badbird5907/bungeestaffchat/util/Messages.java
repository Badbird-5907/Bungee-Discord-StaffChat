package net.badbird5907.bungeestaffchat.util;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Messages {
    public static void createFile(String fileName) {
        if (!BungeeStaffChat.getInstance().getDataFolder().exists()) {
            BungeeStaffChat.getInstance().getDataFolder().mkdir();
        }
        File file = new File(BungeeStaffChat.getInstance().getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                if (fileName.equals("messages")) {
                    Configuration messages = getConfig(fileName);
                    messages.set("Messages.staffchaton", "&8&7Staff chat is now &aenabled&7.");
                    messages.set("Messages.staffchatoff", "&8&7Staff chat is now &cdisabled&7.");
                    messages.set("Messages.staffchat", "&6&lStaffChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
                    messages.set("Messages.adminchaton", "&8&7Admin chat is now &aenabled&7.");
                    messages.set("Messages.adminchatoff", "&8&7Admin chat is now &cdisabled&7.");
                    messages.set("Messages.adminchat", "&c&lAdminChat&r&8 »&r&7 (%server%)&r %player%&r: %message%");
                    messages.set("Messages.perms", "&cYou do not have permissions to do this!&8 &oNO_PERMISSIONS");
                    messages.set("Messages.staffjoin", "&aStaff &8» &f%player% &aConnected");
                    messages.set("Messages.staffleave", "&aStaff &8» &f%player% &cDisconnected");
                    messages.set("Messages.staffswitch", "&aStaff &8 » &f%player% &aSwitched from %from% to %to%");
                    messages.set("Messages.staff-chat-switched-to-admin", "&aStaff Chat was on so it was turned off!");
                    messages.set("Messages.admin-chat-switched-to-staff", "&aAdmin Chat was on so it was turned off!");
                    messages.set("Messages.hush-on", "&8&7Hush is now &aenabled.");
                    messages.set("Messages.hush-off", "&8&7Hush is now &cdisabled.");
                    messages.set("Messages.discordsc", "&6&lDiscordSC »&r %user%&r: %message%");
                    messages.set("Messages.discordac", "&c&lDiscordAC »&r %user%&r: %message%");
                    messages.set("Discord-Messages.sent-staff-chat-msg", "```css\nStaffChat > (%server%) %player%: %message%\n```");
                    messages.set("Discord-Messages.sent-admin-chat-msg", "```css\nAdminChat > (%server%) %player%: %message%\n```");
                    saveConfig(messages, fileName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration getConfig(String fileName) {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(BungeeStaffChat.getInstance().getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveConfig(Configuration config, String fileName) {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(BungeeStaffChat.getInstance().getDataFolder(), fileName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
