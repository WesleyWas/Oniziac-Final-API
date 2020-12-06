package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 10/01/2017.
 */
public class ListCommand extends OniziacCommand{

    public ListCommand() {
        super("list", "/list", "oniziac.player");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            if(args.length == 0){
                Player p = (Player)sender;

                int staff = 0;
                int count = Bukkit.getServer().getOnlinePlayers().size();

                for(Player pls : Bukkit.getOnlinePlayers()){
                    if(pls.hasPermission("api.staff")){
                        staff++;
                    }
                }

                if(staff == 0 && count > 3){
                    staff = 1;
                }

                p.sendMessage(ChatColor.DARK_GRAY + "§m------------------------");
                p.sendMessage(ChatColor.DARK_AQUA + "Online Players" + ChatColor.DARK_GRAY + " » " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size());
                p.sendMessage("§a");
                p.sendMessage(ChatColor.DARK_AQUA + "Online Staff" + ChatColor.DARK_GRAY + " » " + ChatColor.AQUA + staff);
                p.sendMessage(ChatColor.DARK_GRAY + "§m------------------------");

            }else{
                sendSynthaxError((Player)sender);
            }
        }
        return false;
    }
}
