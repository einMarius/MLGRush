package me.marius.gamestates;

import me.marius.config.ConfigLocationUtil;
import me.marius.main.Main;
import me.marius.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;

public class IngameState extends GameState{

    private Main plugin;
    private ArrayList<Player> players;

    public IngameState(Main plugin){
        this.plugin = plugin;
    }


    @Override
    public void start() {
        System.out.println("[MLGRUSH] IngameState gestartet!");
        Collections.shuffle(plugin.getPlayers());
        ConfigLocationUtil.loadSpawnLocations();
        players = plugin.getPlayers();
        for(int i = 0; i < players.size(); i++){
            players.get(i).teleport(ConfigLocationUtil.getSpawnLocations()[i]);
        }
        for(Player players : players){
            Utils.setIngameitems(players);
        }
    }

    @Override
    public void stop() {
        System.out.println("[MLGRUSH] IngameState gestoppt!");
    }
}
