package com.weswaas.api.functions;

import com.weswaas.api.Main;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Weswas on 09/01/2017.
 */
public class OniziacPlayer{

    public static HashMap<String, OniziacPlayer> players = new HashMap<>();

    private Player player;
    private String name;

    private String color = "§9";
    private boolean hasWin = false;
    private GUI inventory = null;

    public OniziacPlayer(Player player){
        this.name = player.getName();
        this.color = Main.getInstance().getSQL().getTable("api").getValue("color", "uuid", player.getUniqueId().toString());
        String wins = Main.getInstance().getSQL().getTable("uhc").getValue("wins", "uuid", player.getUniqueId().toString());
        int realWins = (wins == null ? 0 : Integer.valueOf(wins));
        this.hasWin = realWins > 0;
        players.put(this.name, this);
        this.player = player;
    }

    public static OniziacPlayer getPlayer(Player player){
        Validate.notNull(player, "Player cannot be null!");
        return getPlayer(player.getName());
    }

    public static OniziacPlayer getPlayer(String name){
        if(players.containsKey(name)){
            return players.get(name);
        }else{
            Player p = Bukkit.getPlayer(name);
            if(p != null && p.isOnline()){
                OniziacPlayer oniziacPlayer = new OniziacPlayer(p);
                return oniziacPlayer;
            }
        }
        return null;
    }

    public int getPing(){
        return ((CraftPlayer)player).getHandle().ping;
    }

    public boolean hasWin(){
        return this.hasWin;
    }

    public Player getPlayer(){
        return player;
    }

    public void setInventory(GUI gui){
        this.inventory = gui;
    }

    public GUI getInventory(){
        return this.inventory;
    }

    public void setColor(String color){
        this.color = color;

        try{

            PreparedStatement sts = Main.getInstance().getSQL().getConnection().prepareStatement("UPDATE api SET color = ? WHERE uuid = ?");
            sts.setString(1, color);
            sts.setString(2, Bukkit.getPlayer(name).getUniqueId().toString());
            sts.executeUpdate();
            sts.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public String getColor(){
        return this.color;
    }

}
