package net.badbird5907.bungeestaffchat;

import net.badbird5907.bungeestaffchat.commands.Hush;
import net.badbird5907.bungeestaffchat.discord.listeners.ACListener;
import net.badbird5907.bungeestaffchat.discord.listeners.SCListener;
import net.badbird5907.bungeestaffchat.listeners.MessageListener;
import net.badbird5907.bungeestaffchat.listeners.ServerListener;
import net.badbird5907.bungeestaffchat.spigotmc_metrics.Metrics;
import net.badbird5907.bungeestaffchat.spigotmc_metrics.update_check;
import net.badbird5907.bungeestaffchat.util.Config;
import net.badbird5907.bungeestaffchat.util.Log;
import net.badbird5907.bungeestaffchat.util.Messages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import org.apache.log4j.BasicConfigurator;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class BungeeStaffChat extends Plugin {
    /**
     * @author Badbird5907
     */
    public static List<UUID> StaffChat = new ArrayList<>();
    public static List<UUID> AdminChat = new ArrayList<>();
    public static List<UUID> Hush = new ArrayList<>();
    private JDA jda;
    private static BungeeStaffChat instance;
    public static BungeeStaffChat getInstance() {
        return instance;
    }
    /*
    public static String version_raw1 = BungeeStaffChat.getInstance().getDescription().getVersion();
    public static String version_raw = version_raw1
            .replaceAll("[^\\d.]", "");
    */
    public static String version_raw = "1.0";
    int spigotpluginid;
    int PluginID;
    @Override
    public void onEnable() {
        instance = this;
        Log.info("Starting BungeeSC Version: " + version_raw);
        long start = System.currentTimeMillis();
        BasicConfigurator.configure();
        Messages.createFile("messages");
        Config.createFile("config");
        Log.info("Registering commands");
        getProxy().getInstance().getPluginManager().registerCommand(this, new net.badbird5907.bungeestaffchat.commands.StaffChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new net.badbird5907.bungeestaffchat.commands.AdminChat());
        getProxy().getInstance().getPluginManager().registerCommand(this, new Hush());
        Log.info("Registering listeners");
        getProxy().getPluginManager().registerListener(this, new ServerListener());
        getProxy().getPluginManager().registerListener(this, new MessageListener());
        Log.info("Connecting to discord");
        buildJDA();
        Log.info("Startup Finished. Took " + (System.currentTimeMillis() - start) + "ms.");
        spigotpluginid = 85774;
        update_check.check(spigotpluginid, version_raw);
        PluginID = 86510;
        Metrics metrics = new Metrics(this, PluginID);
    }

    @Override
    public void onDisable() {
        Log.info("Disabling BungeeSC. Thank you for using this plugin.");
    }
    private void buildJDA(){
        Configuration conf1 = Config.getConfig("config");
        try{
            jda = JDABuilder
                    .createDefault(conf1.getString("Discord.token"))
                    .addEventListeners(new SCListener())
                    .addEventListeners(new ACListener())
                    .build();
        }catch (LoginException e){
            e.printStackTrace();
        }
    }
    public static JDA getJDA() {
        return BungeeStaffChat.getInstance().jda;
    }
}
