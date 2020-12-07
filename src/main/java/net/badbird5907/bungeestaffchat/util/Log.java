package net.badbird5907.bungeestaffchat.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

public class Log {
    public static String prefix = "§8[§bBungeeSC§8] ";
    public static void info(String message){
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(prefix + message));
    }
}
