package com.weswaas.api.managers;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Weswas on 19/04/2020.
 */
public class ConversationManager {

    HashMap<Player, Player> conversations = new HashMap<>();

    public void setReplyTarget(Player messager, Player reciever){
        conversations.put(messager, reciever);
        conversations.put(reciever, messager);
    }

    public Player getReplyTarget(Player messager){
        return conversations.get(messager);
    }

}
