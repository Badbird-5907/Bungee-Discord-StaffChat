package net.badbird5907.bungeestaffchat.spigotmc_metrics;

import net.badbird5907.bungeestaffchat.BungeeStaffChat;
import net.badbird5907.bungeestaffchat.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class update_check {
    public static void check(int id, String versionnum){
        try {
            String update = getText("https://api.spigotmc.org/legacy/update.php?resource=" + id);
            double newest_version = Double.parseDouble(update);
            double current_version = Double.parseDouble(versionnum);
            if(newest_version > current_version){
                Log.info("This version of BungeeSC is outdated! Please update to the new version!");
                Log.info("New version: " + newest_version);
                Log.info("Your current version: " + current_version);
                Log.info("Update Link: https://www.spigotmc.org/resources/bungeesc.86510/");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getText(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        //add headers to the connection, or check the status if desired..

        // handle error response code it occurs
        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null)
            response.append(currentLine);

        in.close();

        return response.toString();
    }
}
