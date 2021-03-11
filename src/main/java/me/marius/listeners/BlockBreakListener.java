package me.marius.listeners;

import me.marius.gamestates.LobbyState;
import me.marius.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private Main plugin;

    public BlockBreakListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState){
            e.setCancelled(true);
        }
    }
}
