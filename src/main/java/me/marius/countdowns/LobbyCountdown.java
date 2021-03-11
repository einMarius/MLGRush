package me.marius.countdowns;

import me.marius.gamestates.GameState;
import me.marius.gamestates.GameStateManager;
import me.marius.main.Main;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown{

    private static final int COUNTDOWN_TIME = 20, IDLE_TIME = 10;

    private int seconds;
    private int idleID;
    private boolean isIdling;
    private boolean isRunning;

    private GameStateManager gameStateManager;

    public LobbyCountdown(GameStateManager gameStateManager){
        this.gameStateManager = gameStateManager;
        seconds = COUNTDOWN_TIME;
    }

    @Override
    public void start() {
        isRunning = true;
        taskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                switch (seconds){
                    case 20: case 10: case 5: case 3: case 2:
                        Bukkit.broadcastMessage(Main.prefix + "Das Spiel startet in §b" + seconds + " §7Sekunden.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(Main.prefix + "Das Spiel startet in §beiner §7Sekunde.");
                        break;
                    case 0:
                        gameStateManager.setGameState(GameState.INGAME_STATE);
                        break;

                    default:
                        break;
                }
                seconds--;
            }
        }, 0, 20);

    }

    @Override
    public void stop() {
        if(isRunning){
            Bukkit.getScheduler().cancelTask(taskID);
            isRunning = false;
            seconds = COUNTDOWN_TIME;
        }
    }

    public void startIdle(){
        isIdling = true;
        idleID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(gameStateManager.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(Main.prefix + Main.neededplayers);
            }
        }, 0, 20 * IDLE_TIME);
    }

    public void stopIdle(){
        if(isIdling){
            Bukkit.getScheduler().cancelTask(idleID);
            isIdling = false;
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
