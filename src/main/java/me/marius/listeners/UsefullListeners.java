package me.marius.listeners;

import me.marius.gamestates.EndingState;
import me.marius.gamestates.LobbyState;
import me.marius.main.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class UsefullListeners implements Listener {

    private Main plugin;

    public UsefullListeners(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState || plugin.getGameStateManager().getCurrentGameState() instanceof EndingState){
            if(p.getGameMode().equals(GameMode.CREATIVE)) {
                e.setCancelled(false);
            }else
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState || plugin.getGameStateManager().getCurrentGameState() instanceof EndingState){
            if(p.getGameMode().equals(GameMode.CREATIVE)){
                e.setCancelled(false);
            }else
                e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState || plugin.getGameStateManager().getCurrentGameState() instanceof EndingState) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPickUpItem(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(false);
        }else
            e.setCancelled(true);
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(false);
        }else
            e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }

}
