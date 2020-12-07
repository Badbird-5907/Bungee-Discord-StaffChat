package net.badbird5907.bungeestaffchat.util;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static void createFile(String fileName) {
        if (!BungeeStaffChat.getInstance().getDataFolder().exists())
            BungeeStaffChat.getInstance().getDataFolder().mkdir();
        File file = new File(BungeeStaffChat.getInstance().getDataFolder(), fileName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                if (fileName.equals("config")) {
                    Configuration config = getConfig(fileName);
                    config.set("Discord.token", "your token here MUST BE IN QUOTES");
                    config.set("Discord.staffchat", "staff chat channel id here MUST BE IN QUOTES");
                    config.set("Discord.adminchat", "admin chat channel id here MUST BE IN QUOTES");

                    config.set("admin-chat", true);
                    config.set("staff-chat", true);
                    config.set("broadcast-join", true);
                    config.set("broadcast-leave", true);
                    config.set("broadcast-switch", true);
                    saveConfig(config, fileName);
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
