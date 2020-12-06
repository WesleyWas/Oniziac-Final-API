package com.weswaas.api.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 09/01/2017.
 */
public abstract class OniziacCommand {

    private String name;
    private String usage;
    private String permission;

    public OniziacCommand(String name, String usage, String permission){
        this.name = name;
        this.usage = usage;
        this.permission = permission;
    }

    public String getName(){
        return this.name;
    }

    public String getUsage(){
        return this.usage;
    }

    public String getPermission(){
        return this.permission;
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public void sendSynthaxError(Player p){
        p.sendMessage("§cSynthax error. Please try with " + usage);
    }

}
