package com.weswaas.api.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * Created by Weswas on 10/01/2017.
 */
public class BukkitUtil {

    public static void clear(Player p){

        p.getInventory().setHelmet(null);
        p.getInventory().setChestplate(null);
        p.getInventory().setLeggings(null);
        p.getInventory().setBoots(null);

        p.setHealth(20.0D);
        p.setFoodLevel(20);
        p.getInventory().clear();
        p.setGameMode(GameMode.SURVIVAL);

        for(PotionEffect pe : p.getActivePotionEffects()){
            p.removePotionEffect(pe.getType());
        }

    }

}
