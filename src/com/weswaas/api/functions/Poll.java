package com.weswaas.api.functions;

import com.weswaas.api.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

/**
 * Created by Weswas on 26/04/2020.
 */
public class Poll {

    public static ArrayList<Poll> polls = new ArrayList<>();

    private String option1;
    private String option2;
    private String name;

    private BukkitTask task;

    private ArrayList<String> voters1;
    private ArrayList<String> voters2;

    public Poll(String option1, String option2, String name){
        this.option1 = option1;
        this.option2 = option2;
        this.voters1 = new ArrayList<>();
        this.voters2 = new ArrayList<>();
        this.name = name;
        polls.add(this);
        timer();
    }

    public String getName(){
        return this.name;
    }

    public String getOption1(){
        return this.option1;
    }

    public String getOption2() {
        return this.option2;
    }

    public void cancel(){
        finish(false);
    }

    public void vote(String option, Player voter){
        if(!voters1.contains(voter.getName()) && !voters2.contains(voter.getName())){
            if(option.equalsIgnoreCase(option1)){
                voters1.add(voter.getName());
                voter.sendMessage("§3You voted for §b" + option);
            }else if(option.equalsIgnoreCase(option2)){
                voters2.add(voter.getName());
                voter.sendMessage("§3You voted for §b" + option);
            }
        }else{
            voter.sendMessage("§cYou have already voted.");
        }
    }

    public void timer(){
        task = new BukkitRunnable(){
            @Override
            public void run() {
                finish(true);
            }
        }.runTaskLater(Main.getInstance(), 1200);
    }

    public void finish(boolean say){

        if(say){
            Bukkit.broadcastMessage("§7[§bPOLL§7] §8» §3The poll is now finished.");
            String winner = option1;
            if(voters1.size() > voters2.size()){
                winner = option1;
            }
            else if(voters2.size() > voters1.size()){
                winner = option2;
            }
            else{
                winner = null;
            }

            Bukkit.broadcastMessage("§8» §3" + option1 + ": §b" + voters1.size());
            Bukkit.broadcastMessage("§8» §3" + option2 + ": §b" + voters2.size());
            Bukkit.broadcastMessage("§8» §bThe winner is " + (winner == null ? "nobody ! Equality !" : winner));
        }

        this.task.cancel();

        Poll.polls.remove(this);

    }

}
