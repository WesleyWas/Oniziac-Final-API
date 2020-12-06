package com.weswaas.api.commands.commands;

import com.weswaas.api.commands.OniziacCommand;
import com.weswaas.api.functions.Poll;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;
import net.minecraft.server.v1_7_R4.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static com.weswaas.api.functions.Poll.polls;

/**
 * Created by Weswas on 26/04/2020.
 */
public class PollCommand extends OniziacCommand{

    public PollCommand() {
        super("poll", "/poll <option1/option2>", "oniziac.player");
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
                if(args.length > 0){
                    if(args[0].equalsIgnoreCase("answer")){
                        if(!Poll.polls.isEmpty()){
                            Poll poll = Poll.polls.get(0);
                            if(args[1].equalsIgnoreCase(poll.getOption1()) || args[1].equalsIgnoreCase(poll.getOption2())){
                                poll.vote(args[1], p);
                            }else{
                                p.sendMessage("§cPlease answer correctly to this poll.");
                            }
                        }else{
                            p.sendMessage("§cThere is currently no polls.");
                        }
                    }else{
                        if(p.hasPermission("oniziac.staff")){

                            if(!args[0].contains("/")){
                                p.sendMessage("§cWrong synthax. Try with /poll option1/option2");
                                return true;
                            }

                            String[] options = args[0].split("/");
                            String option1 = options[0];
                            String option2 = options[1];
                            String name = args[0];

                            for(Poll po : polls){
                                po.cancel();
                            }

                            new Poll(option1, option2, name);

                            for(Player pls : Bukkit.getOnlinePlayers()){
                                pls.sendMessage("§7[§bPOLL§7] §8» §3What would you prefer between §b" + option1 + " §3and §b" + option2 + " §3? (Click to vote) (Ending in 60 seconds)");
                                sendRawMessage(pls, option1, option2);
                            }
                        }
                    }

                }else{
                    p.sendMessage("§cJ'ai pas fait ça encore laisse le temps zebi");
                }
        }
        return false;
    }

    private void sendRawMessage(Player p, String option1, String option2){
        PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;
        PacketPlayOutChat packet = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + "§8» §b" + option1 + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/poll answer " + option1 + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"§bClick here to vote for " + option1 + "\",\"color\":\"aqua\"}]}}}"));
        PacketPlayOutChat packet2 = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + "§8» §b" + option2 + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/poll answer " + option2 + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"§bClick here to vote for " + option2 + "\",\"color\":\"aqua\"}]}}}"));
        connection.sendPacket(packet);
        connection.sendPacket(packet2);
    }

}
