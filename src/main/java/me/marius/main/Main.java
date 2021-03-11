//This file was created in 2021

package me.marius.main;

import me.marius.commands.SetupCommand;
import me.marius.commands.StartCommand;
import me.marius.config.ConfigManager;
import me.marius.gamestates.GameState;
import me.marius.gamestates.GameStateManager;
import me.marius.listeners.PlayerIngameConnectionListener;
import me.marius.listeners.PlayerLobbyConnectionListener;
import me.marius.listeners.UsefullListeners;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public static ConfigManager cm;
    private GameStateManager gameStateManager;

    public ArrayList<Player> players;

    public static String prefix;
    public static String noperms;
    public static String wrongvalue;
    public static String lobbyjoin;
    public static String lobbyquit;
    public static String neededplayers;
    public static String lobbycountdown;
    public static String lobbystartwrong;
    public static String onlyinlobby;
    public static String gamestart;
    public static String spectator;
    public static String setuplobby;
    public static String setuplobbywrong;
    public static String setspectatorwrong;
    public static String setspectator;
    public static String setspawnwrong;
    public static String setspawnnumberwrong;

    private boolean configisrunning;
    private Thread ConfigThread;

    public void onEnable() {

        gameStateManager = new GameStateManager(this);
        players = new ArrayList<>();

        gameStateManager.setGameState(GameState.LOBBY_STATE);

        cm = new ConfigManager();
        cm.register();

        configisrunning = !configisrunning;

        if (configisrunning) {
            new Thread() {
                @Override
                public void run() {

                    super.run();

                    while (configisrunning) {

                        try {
                            Thread.sleep(100);
                        }catch(InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("[ReportSystem] Thread was interrupted, Failed to complete operation");
                        }

                        ConfigThread = Thread.currentThread();

                        cm.register();

                        ConfigThread.interrupt();
                        configisrunning = false;
                    }
                }
            }.start();
        }

        getCommand("setup").setExecutor(new SetupCommand(this));
        getCommand("start").setExecutor(new StartCommand(this));
        Bukkit.getPluginManager().registerEvents(new PlayerLobbyConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new UsefullListeners(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerIngameConnectionListener(this), this);

// -------------------------------
        System.out.println("----------[MLGRush]----------");
        System.out.println("Plugin aktiviert");
        System.out.println("Version: 1.0");
        System.out.println("Author: einMarius");
        System.out.println("----------[MLGRush]----------");
// -------------------------------
    }

    public void onDisable() {

// -------------------------------
        System.out.println("----------[MLGRush]----------");
        System.out.println("Plugin deaktiviert");
        System.out.println("Version: 1.0");
        System.out.println("Author: einMarius");
        System.out.println("----------[MLGRush]----------");
// -------------------------------

    }

    public GameStateManager getGameStateManager(){
        return gameStateManager;
    }
    public ArrayList<Player> getPlayers(){
        return players;
    }

}