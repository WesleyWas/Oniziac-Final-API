package com.weswaas.api.functions;

import com.weswaas.api.Main;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftInventoryCustom;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

/**
 * Created by Weswas on 09/01/2017.
 */
public abstract class GUI extends CraftInventoryCustom implements CraftingInventory, Inventory {

    private Player player;

    public GUI(InventoryHolder owner, int size, String title) {
        super(owner, size, title);
        allConstructor();
    }

    public GUI(InventoryHolder owner, int size) {
        super(owner, size);
        allConstructor();
    }

    public GUI(InventoryHolder owner, InventoryType type, String title) {
        super(owner, type, title);
        allConstructor();
    }

    public GUI(InventoryHolder owner, InventoryType type) {
        super(owner, type);
        allConstructor();
    }

    public void allConstructor(){}

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract void onClick(InventoryClickEvent e);

    public abstract void onClose(InventoryCloseEvent e);

    public void close(){
        OniziacPlayer oniziacPlayer = OniziacPlayer.getPlayer(player);
        if(oniziacPlayer != null)
            player.closeInventory();
        setPlayer(null);
        oniziacPlayer.setInventory(null);
    }

    public abstract void onOpen(InventoryOpenEvent e);

    public abstract void onDrag(InventoryDragEvent e);

    public void open(Player player){
        if(player != null){
            setPlayer(player);
            if(player.getOpenInventory() != null)
                player.closeInventory();
            player.openInventory(this);
            OniziacPlayer oniziacPlayer = OniziacPlayer.getPlayer(player);
            oniziacPlayer.setInventory(this);
            final GUI gui = this;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable(){

                public void run() {
                    gui.update();
                }

            }, 1L);
        }
    }

    public abstract void update();

    public ItemStack[] getMatrix() {return null;}

    public Recipe getRecipe() {return null;}

    public ItemStack getResult() {return null;}

    public void setMatrix(ItemStack[] arg0) {}

    public void setResult(ItemStack arg0) {}

}
