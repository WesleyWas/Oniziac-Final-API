package com.weswaas.api.utils.server_pinging;

import com.weswaas.api.utils.PacketUtils;
import org.bukkit.craftbukkit.libs.com.google.gson.Gson;
import org.bukkit.craftbukkit.libs.com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by Weswas on 10/01/2017.
 */
final class ServerPinger3
        extends ServerPinger
{
    private static final Gson gson = new Gson();

    public StatusResponse fetchData(String host, int port)
    {
        Socket socket = null;
        DataOutputStream dout = null;
        DataInputStream din = null;
        try
        {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 20);
            dout = new DataOutputStream(socket.getOutputStream());
            din = new DataInputStream(socket.getInputStream());
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            DataOutputStream handshake = new DataOutputStream(bOut);
            handshake.write(0);
            PacketUtils.writeVarInt(handshake, 4);
            PacketUtils.writeString(handshake, host, PacketUtils.UTF8);
            handshake.writeShort(port);
            PacketUtils.writeVarInt(handshake, 1);
            byte[] b = bOut.toByteArray();
            PacketUtils.writeVarInt(dout, b.length);
            dout.write(b);
            b = new byte[] { 0 };
            PacketUtils.writeVarInt(dout, b.length);
            dout.write(b);

            PacketUtils.readVarInt(din);
            PacketUtils.readVarInt(din);
            byte[] responseData = new byte[PacketUtils.readVarInt(din)];
            din.readFully(responseData);
            String jsonString = new String(responseData, PacketUtils.UTF8);

            socket.close();
            return (StatusResponse)gson.fromJson(jsonString, StatusResponse.class);
        }
        catch (JsonSyntaxException e) {}catch (SocketTimeoutException e) {}catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (dout != null) {
                try
                {
                    dout.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (din != null) {
                try
                {
                    din.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try
                {
                    socket.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
