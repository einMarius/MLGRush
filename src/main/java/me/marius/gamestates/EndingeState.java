package me.marius.gamestates;

public class EndingeState extends GameState{


    @Override
    public void start() {
        System.out.println("[MLGRUSH] EndingState gestartet!");
    }

    @Override
    public void stop() {
        System.out.println("[MLGRUSH] EndingState gestoppt!");
    }
}
