package com.weswaas.api.commands;

import com.weswaas.api.Main;
import com.weswaas.api.commands.commands.*;
import com.weswaas.api.managers.ConversationManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import java.util.ArrayList;

/**
 * Created by Weswas on 09/01/2017.
 */
public class CommandManager implements CommandExecutor{

    private ArrayList<OniziacCommand> cmds = new ArrayList<>();

    private ConversationManager msg;

    public CommandManager(ConversationManager msg){
        this.msg = msg;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        OniziacCommand cmd = getCommand(command.getName());

        if(cmd == null){
            return true;
        }

        if(!sender.hasPermission(cmd.getPermission())){
            return true;
        }

        try{
            if(!cmd.execute(sender, args)){

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public OniziacCommand getCommand(String name){
        for(OniziacCommand cmd : this.cmds){
            if(cmd.getName().equalsIgnoreCase(name)){
                return cmd;
            }
        }
        return null;
    }

    public void registerCommands(){

        registerCommand(new ColorCommand());
        registerCommand(new ReportCommand());
        registerCommand(new LagCommand());
        registerCommand(new ListCommand());
        registerCommand(new HubCommand());
        registerCommand(new MeCommand());
        registerCommand(new com.weswaas.api.commands.commands.PluginCommand());
        registerCommand(new MessageCommand(msg));
        registerCommand(new ReplyCommand(msg));
        registerCommand(new PollCommand());
        registerCommand(new DiscordCommand());

        for(OniziacCommand cmd : cmds){
            PluginCommand pCmd = Main.getInstance().getCommand(cmd.getName());

            if(pCmd == null){
                Bukkit.broadcastMessage("§cError with command: " + pCmd.getName());
                continue;
            }

            pCmd.setExecutor(this);

        }

    }

    public void registerCommand(OniziacCommand command){
        this.cmds.add(command);
    }

}
