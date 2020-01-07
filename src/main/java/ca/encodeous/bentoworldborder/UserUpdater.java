package ca.encodeous.bentoworldborder;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class UserUpdater implements Listener {
    @EventHandler
    public void OnPlayerMove(PlayerMoveEvent e){
        if(!e.isCancelled()){
            WorldBorder.UpdateUserIslandBorder(e.getPlayer(),e.getTo());
        }
    }
}
