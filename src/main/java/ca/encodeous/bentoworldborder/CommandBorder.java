package ca.encodeous.bentoworldborder;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandsManager;

public class CommandBorder implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try{
            Player player = Bukkit.getPlayer(args[0]);
            if(player != null){
                try{
                    int size = Integer.parseInt(args[1]);
                    for(World world : WorldBorder.worldSet){
                        IslandsManager ism = WorldBorder.bentoBox.getIslands();
                        Island is = ism.getIsland(world,player.getUniqueId());
                        if(size > is.getMaxEverProtectionRange()){
                            sender.sendMessage("Size is invalid.");
                        }else{
                            is.setProtectionRange(size);
                        }
                    }
                    WorldBorder.UpdateUserIslandBorder(player,player.getLocation());
                    sender.sendMessage("Updated border for player "+player);
                }catch(Exception e){
                    sender.sendMessage("Size is invalid.");
                    return false;
                }
                return true;
            }else{
                sender.sendMessage("Player is either offline or non-existent.");
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
