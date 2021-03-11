//This file was created in 2021

package me.marius.listeners;

import me.marius.config.ConfigLocationUtil;
import me.marius.gamestates.IngameState;
import me.marius.main.Main;
import me.marius.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerIngameConnectionListener implements Listener {

    private Main plugin;

    public PlayerIngameConnectionListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(!(plugin.getGameStateManager().getCurrentGameState() instanceof IngameState)) return;
        Player p = e.getPlayer();
        e.setJoinMessage("");
        Utils.setSpectatorItems(p);
        p.sendMessage(Main.prefix + Main.spectator);

        for(Player all : plugin.getPlayers()){
            all.hidePlayer(p);
        }

        ConfigLocationUtil locationUtil = new ConfigLocationUtil(plugin, "Spawns.Spectator");
        try {
            if(locationUtil.loadLocation() != null) {
                p.teleport(locationUtil.loadLocation());
            } else
                Bukkit.getConsoleSender().sendMessage("[MLGRush] Der Spectator-Spawn wurde noch nicht gesetzt!");
        }catch(IllegalArgumentException ex){
            Bukkit.getConsoleSender().sendMessage("[MLGRush] Der Spectator-Spawn wurde noch nicht gesetzt!");
        }
    }

}