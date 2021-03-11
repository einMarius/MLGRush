package me.marius.gamestates;

import me.marius.countdowns.LobbyCountdown;

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
    }

    public LobbyCountdown getCountdown(){
        return countdown;
    }
}
