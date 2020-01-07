package ca.encodeous.bentoworldborder;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class UserBorder {
    public static HashMap<UUID,Integer> borderMap = new HashMap<>();
    public static void SetUserBorder(Player user, Integer size, Location centre){
        if(!borderMap.getOrDefault(user.getUniqueId(), -1).equals(size)){
            // update cache size
            borderMap.put(user.getUniqueId(),size);
            // change size
            WorldBorder.wbapi.setBorder(user,size,centre);
        }
    }
    public static void RemoveUserBorder(Player user){
        if(borderMap.containsKey(user.getUniqueId())){
            WorldBorder.wbapi.resetWorldBorderToGlobal(user);
            borderMap.remove(user.getUniqueId());
        }
    }
}
