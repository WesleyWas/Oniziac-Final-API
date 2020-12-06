package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 10/07/2019.
 */
public class PluginCommand extends OniziacCommand{

    public PluginCommand() {
        super("plugins", "/plugins", "oniziac.player");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            p.sendMessage("§3Our plugins are all homemade by Weswaas.");
        }
        return false;
    }

}
