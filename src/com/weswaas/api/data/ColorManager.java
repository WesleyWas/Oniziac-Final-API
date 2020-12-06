package com.weswaas.api.data;

import com.weswaas.api.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;

/**
 * Created by Weswas on 09/01/2017.
 */
public class ColorManager implements Listener{

    private HashMap<Player, String> colors;
    private HashMap<Byte, String> bytes;

    public ColorManager(){
        colors = new HashMap<>();
        bytes = new HashMap<>();
        register();
    }

    public String getColor(Player p){
        if(!colors.containsKey(p)){
            String color = Main.getInstance().getSQL().getTable("api").getValue("color", "uuid", p.getUniqueId().toString());
            colors.put(p, color);
            return color;
        }else{
            return colors.get(p);
        }
    }

    private void register(){

        bytes.put(Byte.valueOf("14"), "§c");
        bytes.put(Byte.valueOf("1"), "§6");
        bytes.put(Byte.valueOf("4"), "§e");
        bytes.put(Byte.valueOf("13"), "§2");
        bytes.put(Byte.valueOf("5"), "§a");
        bytes.put(Byte.valueOf("3"), "§b");
        bytes.put(Byte.valueOf("9"), "§3");
        bytes.put(Byte.valueOf("11"), "§9");
        bytes.put(Byte.valueOf("6"), "§d");
        bytes.put(Byte.valueOf("10"), "§5");

    }

}
