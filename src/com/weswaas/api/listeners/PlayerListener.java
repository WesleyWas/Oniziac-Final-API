package com.weswaas.api.listeners;

import com.weswaas.api.Main;
import com.weswaas.api.data.sql.Table;
import com.weswaas.api.functions.OniziacPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Weswas on 09/01/2017.
 */
public class PlayerListener implements Listener{

    public ArrayList<String> cooldown = new ArrayList<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        if(e.isCancelled()){
            return;
        }

        final String name = e.getPlayer().getName();

        if(cooldown.contains(name) && !e.getPlayer().hasPermission("api.chat.bypass")){
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cPlease wait before writing another message.");
        }

        Player p = e.getPlayer();
        OniziacPlayer player = OniziacPlayer.getPlayer(e.getPlayer());
        PermissionUser user = PermissionsEx.getUser(e.getPlayer().getName());
        String color = player.getColor();

        if(user.getPrefix().equalsIgnoreCase("&f")){
            color = "§9";
        }



        e.setFormat((player.hasWin() ? "§6[W]" : "") + user.getPrefix().replace('&', '§') + color + player.getPlayer().getName() + " §8» §r" + e.getMessage());

        cooldown.add(name);
        new BukkitRunnable(){
            public void run(){
                cooldown.remove(name);
            }

        }.runTaskLater(Main.getInstance(), 30);

    }



    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        OniziacPlayer player = OniziacPlayer.getPlayer(e.getPlayer());

        Table table = Main.getInstance().getSQL().getTable("api");

        if(!table.isRegistered("uuid", "uuid", player.getPlayer().getUniqueId().toString())){
            HashMap<String, String> map = new HashMap<>();
            map.put("uuid", player.getPlayer().getUniqueId().toString());
            map.put("name", player.getPlayer().getName());
            map.put("color", "§9");
            map.put("ip", player.getPlayer().getAddress().getHostName());
            table.insert(map);
            player.setColor("§9");
        }
        else{
            String color = "§9";
            try{
                PreparedStatement sts = Main.getInstance().getSQL().getConnection().prepareStatement("SELECT color FROM api WHERE uuid = ?");
                sts.setString(1, e.getPlayer().getUniqueId().toString());
                ResultSet rs = sts.executeQuery();
                if(!rs.next()){
                    color = "§9";
                }
                color = rs.getString("color");
                sts.close();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            player.setColor(color);
        }

    }

}
