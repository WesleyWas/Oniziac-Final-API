package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 01/05/2020.
 */
public class DiscordCommand extends OniziacCommand{

    public DiscordCommand() {
        super("discord", "/discord", "player.permission");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

            Player p = (Player)sender;
            p.sendMessage("§3Join us on Discord §8» §bhttps://discord.gg/SRn4Ap");

        }
        return false;
    }

}
