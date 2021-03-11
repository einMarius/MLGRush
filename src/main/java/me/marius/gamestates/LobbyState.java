package me.marius.gamestates;

import me.marius.countdowns.LobbyCountdown;
import org.bukkit.Bukkit;

public class LobbyState extends GameState{

    public static final int MIN_PLAYERS = 2,
                            MAX_PLAYERS = 2;

    private LobbyCountdown countdown;

    public LobbyState(GameStateManager gameStateManager){
        countdown = new LobbyCountdown(gameStateManager);
    }

    @Override
    public void start() {
        countdown.startIdle();
        System.out.println("[MLGRUSH] LobbyState gestartet!");
    }

    @Override
    public void stop() {
        System.out.println("[MLGRUSH] LobbyState gestoppt!");
        Bukkit.broadcastMessage("Going into the IngameState...");
    }

    public LobbyCountdown getCountdown(){
        return countdown;
    }
}
