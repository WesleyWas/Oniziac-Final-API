package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import com.weswaas.api.functions.OniziacPlayer;
import com.weswaas.api.managers.ConversationManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 19/04/2020.
 */
public class ReplyCommand extends OniziacCommand{

    private ConversationManager msg;

    public ReplyCommand(ConversationManager msg){
        super("reply", "/reply <message>", "oniziac.player");
        this.msg = msg;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

            Player messager = (Player) sender;
            if(msg.getReplyTarget(messager) == null){
                messager.sendMessage("§cYou have no one to reply !");
                return true;
            }

            Player receiver = msg.getReplyTarget(messager);
            if(receiver.isOnline()){

                String nameMessager = (messager.getDisplayName());
                String nameReceiver = (receiver.getDisplayName());

                String message = "";
                for(int i = 0; i < args.length; i++){
                    message += " " + args[i];
                }

                messager.sendMessage("§8[§b" + nameMessager + " §8» §3" + nameReceiver + "§8]:§7" + message);
                receiver.sendMessage("§8[§3" + nameMessager + " §8» §b" + nameReceiver + "§8]:§7" + message);
                return true;
            }else{
                messager.sendMessage("§cPlayer is not online.");
            }

        }
        return false;
    }

}
