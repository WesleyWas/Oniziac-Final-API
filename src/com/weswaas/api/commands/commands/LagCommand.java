package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import com.weswaas.api.functions.OniziacPlayer;
import com.weswaas.api.utils.TPS;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

/**
 * Created by Weswas on 10/01/2017.
 */
public class LagCommand extends OniziacCommand{

    public LagCommand() {
        super("lag", "/lag", "oniziac.player");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            OniziacPlayer oniziacPlayer = OniziacPlayer.getPlayer((Player)sender);

            int ping = oniziacPlayer.getPing();
            double tps = TPS.getTPS();

            oniziacPlayer.getPlayer().sendMessage(ChatColor.AQUA + "Lag-Evaluation" + ChatColor.DARK_GRAY + " » " + ChatColor.DARK_AQUA + oniziacPlayer.getPlayer().getName());
            oniziacPlayer.getPlayer().sendMessage(ChatColor.AQUA + "Date" + ChatColor.DARK_GRAY + " » " + ChatColor.DARK_AQUA + new Date(System.currentTimeMillis()));
            oniziacPlayer.getPlayer().sendMessage(ChatColor.AQUA + "Ping" + ChatColor.DARK_GRAY + " » " + ChatColor.DARK_AQUA + ping);
            oniziacPlayer.getPlayer().sendMessage(ChatColor.AQUA + "Server TPS" + ChatColor.DARK_GRAY + " » " + ChatColor.DARK_AQUA + tps);

        }
        return false;
    }
}
