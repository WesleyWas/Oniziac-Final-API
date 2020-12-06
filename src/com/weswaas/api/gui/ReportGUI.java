package com.weswaas.api.gui;

import com.weswaas.api.functions.GUI;
import com.weswaas.api.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.HashMap;

/**
 * Created by Weswas on 09/01/2017.
 */
public class ReportGUI extends GUI{

    private HashMap<Material, String> reasons = new HashMap<>();

    private Player reported;

    public ReportGUI(Player reported) {
        super(null, 9*2, ChatColor.AQUA + reported.getName());
        register();
        this.reported = reported;
    }

    @Override
    public void update() {

        setItem(0, new ItemBuilder(Material.IRON_SWORD).name("§aAimbot").build());
        setItem(1, new ItemBuilder(Material.DIAMOND_SWORD).name("§aKill-Aura").build());
        setItem(2, new ItemBuilder(Material.LEATHER_BOOTS).name("§aAnti-Knockback").build());
        setItem(3, new ItemBuilder(Material.IRON_BOOTS).name("§aSpeed-Hack").build());
        setItem(4, new ItemBuilder(Material.DIAMOND_BOOTS).name("§aFly").build());
        setItem(5, new ItemBuilder(Material.BOW).name("§aFast-Bow").build());
        setItem(6, new ItemBuilder(Material.IRON_PICKAXE).name("§aCave-Finder").build());
        setItem(7, new ItemBuilder(Material.GOLD_PICKAXE).name("§aIllegal-Mining").build());
        setItem(8, new ItemBuilder(Material.DIAMOND_PICKAXE).name("§aX-Ray").build());
        setItem(9, new ItemBuilder(Material.ANVIL).name("§aGlitch-Abuse").build());
        setItem(10, new ItemBuilder(Material.PAPER).name("§aDisrespect").build());
        setItem(11, new ItemBuilder(Material.SHEARS).name("§aBad Sportsmanship").build());
        setItem(12, new ItemBuilder(Material.FLINT_AND_STEEL).name("§aIndirect-PvP (iPvP)").build());
        setItem(17, new ItemBuilder(Material.ENDER_PEARL).name("§aOther").build());

    }

    @Override
    public void onClick(InventoryClickEvent e) {
        e.setCancelled(true);

        Material mat = e.getCurrentItem().getType();
        String reason = this.reasons.get(mat);
        if(reason == null){
            reason = "Other";
        }


        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.hasPermission("oniziac.staff")){
                p.sendMessage("§8[§4Report§8]§c§l" + reported.getName() + " §r§7has been reported by §a" + getPlayer().getName() + " §7for §e" + reason);
            }
        }

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

    private void register(){

        reasons.put(Material.IRON_SWORD, "Aimbot");
        reasons.put(Material.DIAMOND_SWORD, "Kill-Aura");
        reasons.put(Material.DIAMOND_BOOTS, "Fly");
        reasons.put(Material.LEATHER_BOOTS, "Anti-Knockback");
        reasons.put(Material.IRON_BOOTS, "Speed-Hack");
        reasons.put(Material.BOW, "Fast-Bow");
        reasons.put(Material.IRON_PICKAXE, "Cave-Finder");
        reasons.put(Material.GOLD_PICKAXE, "Illegal Mining");
        reasons.put(Material.DIAMOND_PICKAXE, "X-Ray");
        reasons.put(Material.ANVIL, "Glitch Abuse");
        reasons.put(Material.PAPER, "Disrespect");
        reasons.put(Material.SHEARS, "Bad Sportsmanship");
        reasons.put(Material.FLINT_AND_STEEL, "Indirect PvP (iPvP)");
        reasons.put(Material.ENDER_PEARL, "Other");

    }

}
