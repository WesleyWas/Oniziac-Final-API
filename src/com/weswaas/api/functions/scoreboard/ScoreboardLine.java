package com.weswaas.api.functions.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

/**
 * Created by Weswas on 09/01/2017.
 */
public class ScoreboardLine {

    private final ChatColor color;
    private final int slot;
    private final Team team;
    private String value;

    public ScoreboardLine(ChatColor color, int slot, Team team){
        this.color = color;
        this.slot = slot;
        this.team = team;
    }

    public ChatColor getChatColor(){
        return this.color;
    }

    public int getSlot(){
        return this.slot;
    }

    public Team getTeam(){
        return this.team;
    }

    public String getValue(){
        return this.value;
    }

    public void setValue(String value){
        this.value = value;
    }

}
