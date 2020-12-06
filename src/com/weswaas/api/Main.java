package com.weswaas.api;

import com.weswaas.api.commands.CommandManager;
import com.weswaas.api.data.sql.SQLManager;
import com.weswaas.api.listeners.InventoryListener;
import com.weswaas.api.listeners.PlayerListener;
import com.weswaas.api.managers.ConversationManager;
import com.weswaas.api.utils.TPS;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Weswas on 09/01/2017.
 */
public class Main extends JavaPlugin{

    private static Main instance;

    private SQLManager sql;
    private CommandManager command;
    private ConversationManager msg;

    @Override
    public void onEnable() {
        instances();

        registerListeners(new InventoryListener(), new PlayerListener());
        command.registerCommands();
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new TPS(), 100L, 1L);
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

    }

    private void instances(){
        instance = this;
        sql = new SQLManager("jdbc:mysql://", "localhost", "oniziac", "weswas", "wqa159");
        msg = new ConversationManager();
        command = new CommandManager(msg);
    }

    private void registerListeners(Listener... list){
        PluginManager pm = Bukkit.getPluginManager();
        for(Listener listener : list){
            pm.registerEvents(listener, this);
        }
    }

    public static Main getInstance(){
        return instance;
    }

    public SQLManager getSQL(){
        return this.sql;
    }

}
