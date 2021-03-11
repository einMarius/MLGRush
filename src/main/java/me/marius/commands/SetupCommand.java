package me.marius.commands;

import me.marius.config.ConfigLocationUtil;
import me.marius.gamestates.LobbyState;
import me.marius.main.Main;
import me.marius.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    private Main plugin;

    public SetupCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("mlgrush.setup")){
                if(args.length == 0){
                    Utils.sendHelpMessage(p);

                } else if(args.length == 1){
                    if(args[0].equalsIgnoreCase("lobby")){
                        new ConfigLocationUtil(plugin, p.getLocation(), "Spawns.Lobby").saveLocation();
                        p.sendMessage(Main.prefix + Main.setuplobby);
                    } else if(args[0].equalsIgnoreCase("spectator")) {
                        new ConfigLocationUtil(plugin, p.getLocation(), "Spawns.Spectator").saveLocation();
                        p.sendMessage(Main.prefix + Main.setspectator);
                    }else
                        Utils.sendHelpMessage(p);

                } else if(args.length == 2){
                    if(args[0].equalsIgnoreCase("spawn")){
                        try{
                            int spawnNumber = Integer.parseInt(args[1]);
                            if(spawnNumber > 0 && spawnNumber <= LobbyState.MAX_PLAYERS){
                                ConfigLocationUtil.setSpawnLocation(spawnNumber, p.getLocation());
                                p.sendMessage(Main.prefix + "Der Spawn für §bTeam " + spawnNumber + " §7wurde gesetzt!");

                            } else
                                p.sendMessage(Main.prefix + Main.setspawnnumberwrong);
                        }catch(NumberFormatException ex){
                            p.sendMessage(Main.prefix + Main.wrongvalue);
                        }
                    }else
                        Utils.sendHelpMessage(p);
                }else
                    Utils.sendHelpMessage(p);
            }
        }else
            commandSender.sendMessage("§4Must be a player!");
        return false;
    }
}
