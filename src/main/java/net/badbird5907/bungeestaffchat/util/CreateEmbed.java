package net.badbird5907.bungeestaffchat.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class CreateEmbed {
    public static MessageEmbed create(String title, String desc, String colorstr){
        Color color = Color.getColor(colorstr);
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(color);
        eb.setTitle(title);
        eb.setDescription(desc);
        return eb.build();
    }
    public static MessageEmbed create(String title, String desc, Color color){
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(color);
        eb.setTitle(title);
        eb.setDescription(desc);
        return eb.build();
    }
}
