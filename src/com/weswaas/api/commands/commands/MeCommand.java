package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 08/07/2019.
 */
public class MeCommand extends OniziacCommand{

    public MeCommand() {
        super("me", "/me", "oniziac.command.me");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

        }
        return false;
    }


}
