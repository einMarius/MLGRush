//This file was created in 2021

package me.marius.listeners;

import me.marius.config.ConfigLocationUtil;
import me.marius.countdowns.LobbyCountdown;
import me.marius.gamestates.LobbyState;
import me.marius.main.Main;
import me.marius.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLobbyConnectionListener implements Listener {

    private Main plugin;

    public PlayerLobbyConnectionListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
        Player p = e.getPlayer();
        plugin.getPlayers().add(p);
        String lobbyjoin = Main.lobbyjoin.replace("%PLAYER%", p.getName());
        e.setJoinMessage(Main.prefix + lobbyjoin);
        Utils.clearPlayer(p);
        Utils.setLobbyItems(p);

        ConfigLocationUtil locationUtil = new ConfigLocationUtil(plugin, "Spawns.Lobby");
        try {
            if(locationUtil.loadLocation() != null)
                p.teleport(locationUtil.loadLocation());
            }catch(IllegalArgumentException ex){
                Bukkit.getConsoleSender().sendMessage("[MLGRush] Der Lobby-Spawn wurde noch nicht gesetzt!");
        }

        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
        LobbyCountdown countdown = lobbyState.getCountdown();
        if(plugin.getPlayers().size() == LobbyState.MIN_PLAYERS){
            if(!countdown.isRunning()){
                countdown.stopIdle();
                countdown.start();
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();

        if(!(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState)) return;
        plugin.getPlayers().remove(p);
        String lobbyquit = Main.lobbyquit.replace("%PLAYER%", p.getName());
        e.setQuitMessage(Main.prefix + lobbyquit);

        if(plugin.getPlayers().size() < LobbyState.MIN_PLAYERS){
            LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();
            LobbyCountdown countdown = lobbyState.getCountdown();
            if(countdown.isRunning()){
                countdown.stop();
                countdown.startIdle();
            }
        }
    }
}