package com.weswaas.api.functions.scoreboard;

import com.weswaas.api.Main;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Weswas on 09/01/2017.
 */
public abstract class CustomScoreboard {

    private static final List<ChatColor> colors = Arrays.asList(ChatColor.values());
    private static final List<CustomScoreboard> boards = new ArrayList<>();

    private Scoreboard board;
    private Objective objective;
    private Player p;
    private boolean created = false;

    private List<ScoreboardLine> lines = new ArrayList<>();

    public CustomScoreboard(String displayName, Player p){

        this.p = p;
        board = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = board.registerNewObjective("Scoreboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(displayName);
        for(int i = 0; i < colors.size(); i++){
            ChatColor color = colors.get(i);
            Team team = board.registerNewTeam("line"+i);
            team.addEntry(color.toString());
            lines.add(new ScoreboardLine(color, i , team));
        }
        boards.add(this);
    }

    private ScoreboardLine getLine(int line){
        for(ScoreboardLine scLine : this.lines){
            if(scLine.getSlot() == line){
                return scLine;
            }
        }
        return null;
    }

    public void setValue(int line, String value){
        ScoreboardLine scLine = getLine(line);
        scLine.setValue(value);
        Validate.notNull(scLine, "There is not Scoreboard Line with ID " + line);
        Validate.isTrue(value.length() < 33, "The value has to be smaller than 32 chars");

        objective.getScore(scLine.getChatColor().toString()).setScore(line);

        if(value.length() > 16){
            String first = value.substring(0, 16);
            String last = value.substring(16, value.length());
            scLine.getTeam().setPrefix(first);
            scLine.getTeam().setSuffix(last);
        }else{
            scLine.getTeam().setPrefix(value);
        }
    }

    public void removeLine(int line){
        ScoreboardLine scLine = getLine(line);
        Validate.notNull(scLine, "There is not Scoreboard Line with ID " + line);
        board.resetScores(scLine.getChatColor().toString());
    }

    public void display(){
        this.p.setScoreboard(this.board);
        this.created = true;
    }

    public Player getPlayer(){
        return this.p;
    }

    public List<ScoreboardLine> getLines(){
        return this.lines;
    }

    public boolean isCreated(){
        return this.created;
    }

    public static CustomScoreboard getCustomScoreboard(Player p){
        for(CustomScoreboard scoreboard : boards){
            if(scoreboard.getPlayer() == p){
                return scoreboard;
            }
        }
        return null;
    }

    public abstract void refresh();

    public static void update(){
        new BukkitRunnable(){
            @Override
            public void run() {
                for(CustomScoreboard sc : boards){
                    if(sc.isCreated()){
                        sc.refresh();
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 10);
    }

}
