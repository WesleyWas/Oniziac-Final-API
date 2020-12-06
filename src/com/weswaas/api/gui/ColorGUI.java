package com.weswaas.api.gui;

import com.weswaas.api.functions.GUI;
import com.weswaas.api.functions.OniziacPlayer;
import com.weswaas.api.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Created by Weswas on 09/01/2017.
 */
public class ColorGUI extends GUI{

    enum ColorType{

        WHITE(ChatColor.WHITE, "§fWhite", 0, "§f"),
        ORANGE(ChatColor.GOLD, "§6Orange", 1, "§6"),
        PINK(ChatColor.LIGHT_PURPLE, "§dPink", 2, "§d"),
        BLUE(ChatColor.BLUE, "§9Blue", 3, "§9"),
        YELLOW(ChatColor.YELLOW, "§eYellow", 4, "§e"),
        GREEN(ChatColor.GREEN, "§aGreen", 5, "§a"),
        GRAY(ChatColor.GRAY, "§7Gray", 7, "§7"),
        AQUA(ChatColor.AQUA, "§bAqua", 9, "§b"),
        PURPLE(ChatColor.DARK_PURPLE, "§5Purple", 10, "§5"),
        DARKGREEN(ChatColor.DARK_GREEN, "§2Dark Green", 13, "§2"),
        RED(ChatColor.RED, "§cRed", 14, "§c");

        public ChatColor color;
        public String name;
        public int data;
        private String c;

        ColorType(ChatColor color, String name, int data, String c){
            this.color = color;
            this.name = name;
            this.data = data;
            this.c = c;
        }

        public int getData(){
            return data;
        }

        public String getColor(){
            return this.c;
        }

        public static ColorType getByID(int id){
            for(ColorType type : ColorType.values()){
                if(type.getData() == id){
                    return type;
                }
            }
            return ColorType.BLUE;
        }

    }

    public ColorGUI() {
        super(null, 9*2, "§aColor Chooser §8»");
    }

    public void update(){

        Player player = getPlayer();

        if(player != null){
            ItemBuilder ib = new ItemBuilder(Material.WOOL);

            for(ColorType type : ColorType.values()){
                ib.clearLore();
                addItem(ib.name(type.color + type.name).data((byte)type.data).lore("§8» " + type.color + player.getName()).build());
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onClick(InventoryClickEvent e) {
        e.setCancelled(true);
        OniziacPlayer oniziacPlayer = OniziacPlayer.getPlayer((Player)e.getWhoClicked());

        ColorType type = ColorType.getByID(e.getCurrentItem().getData().getData());

        ((Player) e.getWhoClicked()).getPlayer().sendMessage("§aYour color has been changed to " + type.name.toLowerCase());

        oniziacPlayer.setColor(type.getColor());

        getPlayer().closeInventory();
    }

    @Override
    public void onClose(InventoryCloseEvent e) {

    }

    @Override
    public void onOpen(InventoryOpenEvent e) {

    }

    @Override
    public void onDrag(InventoryDragEvent e) {

    }

}
