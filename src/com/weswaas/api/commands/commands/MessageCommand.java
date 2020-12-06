package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import com.weswaas.api.functions.OniziacPlayer;
import com.weswaas.api.managers.ConversationManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Weswas on 19/04/2020.
 */
public class MessageCommand extends OniziacCommand{

    private ConversationManager msg;

    public MessageCommand(ConversationManager msg){
        super("message", "/message <message>", "oniziac.player");
        this.msg = msg;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){

            Player messager = (Player) sender;

            if(args.length > 1){
                Player receiver = Bukkit.getOfflinePlayer(args[0]).getPlayer();
                if(receiver == null){
                    receiver = Bukkit.getPlayer(args[0]);
                }


                if(receiver != null){
                    if(receiver.isOnline()){

                        String nameMessager = (messager.getDisplayName());
                        String nameReceiver = (receiver.getDisplayName());

                        if(!messager.getDisplayName().equalsIgnoreCase(receiver.getDisplayName())){
                            msg.setReplyTarget(messager, receiver);
                            args[0] = "";
                            String message = "";
                            for(int i = 0; i < args.length; i++){
                                message += " " + args[i];
                            }
                            messager.sendMessage("§8[§b" + nameMessager + " §8» §3" + nameReceiver + "§8]:§7" + message);
                            receiver.sendMessage("§8[§3" + nameMessager + " §8» §b" + nameReceiver + "§8]:§7" + message);
                            return true;
                        }

                    }else{
                        messager.sendMessage("§cPlayer is not online.");
                    }
                }else{
                    messager.sendMessage("§cPlayer is not online.");
                }
            }else{
                messager.sendMessage("§cWrong synthax. Please try with /msg <message>.");
            }

        }
        return false;
    }

}
