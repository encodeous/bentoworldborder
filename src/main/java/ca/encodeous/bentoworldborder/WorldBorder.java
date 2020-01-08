package ca.encodeous.bentoworldborder;

import com.github.yannicklamprecht.worldborder.api.BorderAPI;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.IslandWorldManager;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class WorldBorder extends JavaPlugin {
    public static IslandWorldManager iwm;
    public static BentoBox bentoBox;
    public static WorldBorderApi wbapi;
    public static Set<World> worldSet = new HashSet<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        bentoBox = BentoBox.getInstance();
        wbapi = BorderAPI.getApi();
        iwm = bentoBox.getIWM();
        worldSet = iwm.getWorlds();

        getServer().getPluginManager().registerEvents(new UserUpdater(),this);
        getCommand("setislandsize").setExecutor(new CommandBorder());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static void UpdateUserIslandBorder(Player player, Location loc){
        Optional<Island> is = bentoBox.getIslands().getIslandAt(loc);
        if(is.isPresent()){
            int size = is.get().getProtectionRange();
            UserBorder.SetUserBorder(player,size,is.get().getCenter());
            if(!is.get().getProtectionBoundingBox().contains(loc.toVector()) && player.getGameMode() != GameMode.SPECTATOR){
                player.sendMessage("You were teleported to spawn because you were outside the island.");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"spawn "+player.getName());
            }
        }else{
            UserBorder.RemoveUserBorder(player);
        }
    }
}
