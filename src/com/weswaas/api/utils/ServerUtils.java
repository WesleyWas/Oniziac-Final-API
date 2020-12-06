package com.weswaas.api.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.weswaas.api.Main;
import org.bukkit.entity.Player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Weswas on 10/01/2017.
 */
public class ServerUtils {

    public String getMotd(String ip, String port) {
        try {
            Socket sock = new Socket(ip, Integer.valueOf(port));

            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
            DataInputStream in = new DataInputStream(sock.getInputStream());

            out.write(0xFE);

            int b;
            StringBuffer str = new StringBuffer();
            while ((b = in.read()) != -1)
                if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) str.append((char) b);

            String[] data = str.toString().split("§");
            String serverMotd = data[0];
            return serverMotd;
        } catch (Exception ex) {
            return null;
        }
    }

    public static void sendToServer(Player p, String server){

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);

        p.sendMessage("§b§lConnexion...");
        p.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());

    }

}
