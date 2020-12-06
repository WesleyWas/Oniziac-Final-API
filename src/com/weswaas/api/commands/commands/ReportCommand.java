package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import com.weswaas.api.gui.ReportGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 09/01/2017.
 */
public class ReportCommand extends OniziacCommand{

    public ReportCommand() {
        super("report", "/report <player>", "oniziac.player");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target != null && target.isOnline()){
                    new ReportGUI(target).open(p);
                }else{
                    p.sendMessage("§cThis player is not online");
                }
            }else{
                sendSynthaxError(p);
            }
        }
        return false;
    }
}
