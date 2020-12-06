package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import com.weswaas.api.gui.ColorGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 09/01/2017.
 */
public class ColorCommand extends OniziacCommand{

    public ColorCommand() {
        super("color", "/color", "color.use");
    }


    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 0){
                new ColorGUI().open(p);
            }else{
                sendSynthaxError(p);
            }
        }
        return false;
    }
}
