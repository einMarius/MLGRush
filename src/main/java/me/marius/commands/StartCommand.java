package me.marius.commands;

import me.marius.gamestates.LobbyState;
import me.marius.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {

    private static final int START_SECONDS = 5;

    private Main plugin;

    public StartCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] args) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("mlgrush.start")){
                if(args.length == 0){

                    if(plugin.getGameStateManager().getCurrentGameState() instanceof LobbyState){

                        LobbyState lobbyState = (LobbyState) plugin.getGameStateManager().getCurrentGameState();

                        if(lobbyState.getCountdown().isRunning() && lobbyState.getCountdown().getSeconds() > START_SECONDS){
                            lobbyState.getCountdown().setSeconds(START_SECONDS);
                            p.sendMessage(Main.prefix + Main.gamestart);
                        }
                    }else
                        p.sendMessage(Main.prefix + Main.onlyinlobby);
                }else
                    p.sendMessage(Main.prefix + Main.lobbystartwrong);
            }else
                p.sendMessage(Main.prefix + Main.noperms);
        }else
            commandSender.sendMessage("§4Must be a player!");

        return false;
    }
}
