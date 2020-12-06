package com.weswaas.api.listeners;

import com.weswaas.api.functions.OniziacPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Created by Weswas on 09/01/2017.
 */
public class InventoryListener implements Listener{

    @EventHandler
    public void onClick(InventoryClickEvent event){
        OniziacPlayer player = OniziacPlayer.getPlayer((Player)event.getWhoClicked());
        if(event.getCurrentItem() == null)return;
        if(player.getInventory() != null)
            player.getInventory().onClick(event);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event){
        OniziacPlayer player = OniziacPlayer.getPlayer((Player)event.getWhoClicked());
        if(player.getInventory() != null)
            player.getInventory().onDrag(event);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        OniziacPlayer player = OniziacPlayer.getPlayer((Player)event.getPlayer());
        if(player.getInventory() != null){
            player.getInventory().onClose(event);
            player.setInventory(null);
        }
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event){
        OniziacPlayer player = OniziacPlayer.getPlayer((Player)event.getPlayer());
        if(player.getInventory() != null){
            player.getInventory().onOpen(event);
        }
    }

}
