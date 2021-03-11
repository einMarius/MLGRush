package me.marius.gamestates;

import me.marius.main.Main;

public class IngameState extends GameState{

    private Main plugin;

    public IngameState(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public void start() {
        System.out.println("[MLGRUSH] IngameState gestartet!");
    }

    @Override
    public void stop() {
        System.out.println("[MLGRUSH] IngameState gestoppt!");
    }
}
