package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import com.weswaas.api.utils.ServerUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 12/01/2017.
 */
public class HubCommand extends OniziacCommand{

    public HubCommand() {
        super("hub", "/hub", "player.permission");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

            ServerUtils.sendToServer((Player)sender, "hub");

        }
        return false;
    }
}
