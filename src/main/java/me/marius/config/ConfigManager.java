//This file was created in 2021

package me.marius.config;

import java.io.File;
import java.io.IOException;

import com.sun.jdi.connect.Connector;
import me.marius.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import net.md_5.bungee.api.ChatColor;

public class ConfigManager {

    public static File file = new File("plugins/MLGRush", "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    public void register() {
        config.options().copyDefaults(true);
        config.addDefault("Information", "Sonderzeichen werden derzeit nicht unterstuezt!");
        config.addDefault("Einstellungen.Prefix", "&b&lMLG&f&lRush &8» &7");
        config.addDefault("Einstellungen.NoPerms", "Dazu hast du keine Berechtigung!");
        config.addDefault("Einstellungen.WrongValue", "Du musst eine &bZahl &7angeben!");
        config.addDefault("Lobby.Join", "&b%PLAYER% &7ist dem Spiel beigetreten.");
        config.addDefault("Lobby.Leave", "&b%PLAYER% &7hat das Spiel verlassen.");
        config.addDefault("Lobby.NeededPlayers", "Es fehlt noch &bein &7Spieler zum starten des &bSpiels&7!");
        config.addDefault("Lobby.Countdown", "Das Spiel startet in &b%COUNTDOWN% &7Sekunden.");
        config.addDefault("Lobby.StartWrong", "Benutze /&bstart");
        config.addDefault("Lobby.OnlyInLobby", "Die funktioniert nur in der &bLobby&7!");
        config.addDefault("Lobby.GameStart", "Du hast das &bSpiel &7gestartet.");
        config.addDefault("Lobby.Spectator", "Du bist als &bSpectator &7verbunden!");
        config.addDefault("Setup.SetLobbyWrong", "Benutze /&bsetup &7<&bLobby&7>");
        config.addDefault("Setup.SetLobby", "Der Lobby-Spawn wurde &berfolgreich &7gesetzt!");
        config.addDefault("Setup.SetSpectatorWrong", "Benutze /&bsetup &7<&bSpectator&7>");
        config.addDefault("Setup.SetSpectator", "Der Spectator-Spawn wurde &berfolgreich &7gesetzt!");
        config.addDefault("Setup.SetSpawnsWrong", "Benutze /&bsetup &7<&bSpawn&7> <&bNumber&7>");
        config.addDefault("Setup.SetSpawnsWrongNumber", "Die Zahl darf nicht größer als 2 sein!");

        saveCfg();

        Main.prefix = config.getString("Einstellungen.Prefix");
        Main.prefix = ChatColor.translateAlternateColorCodes('&', Main.prefix);

        Main.noperms = config.getString("Einstellungen.NoPerms");
        Main.noperms = ChatColor.translateAlternateColorCodes('&', Main.noperms);

        Main.wrongvalue = config.getString("Einstellungen.WrongValue");
        Main.wrongvalue = ChatColor.translateAlternateColorCodes('&', Main.wrongvalue);

        Main.lobbyjoin = config.getString("Lobby.Join");
        Main.lobbyjoin = ChatColor.translateAlternateColorCodes('&', Main.lobbyjoin);

        Main.lobbyquit = config.getString("Lobby.Leave");
        Main.lobbyquit = ChatColor.translateAlternateColorCodes('&', Main.lobbyquit);

        Main.neededplayers = config.getString("Lobby.NeededPlayers");
        Main.neededplayers = ChatColor.translateAlternateColorCodes('&', Main.neededplayers);

        Main.lobbycountdown = config.getString("Lobby.Countdown");
        Main.lobbycountdown = ChatColor.translateAlternateColorCodes('&', Main.lobbycountdown);

        Main.lobbystartwrong = config.getString("Lobby.StartWrong");
        Main.lobbystartwrong = ChatColor.translateAlternateColorCodes('&', Main.lobbystartwrong);

        Main.onlyinlobby = config.getString("Lobby.OnlyInLobby");
        Main.onlyinlobby = ChatColor.translateAlternateColorCodes('&', Main.onlyinlobby);

        Main.gamestart = config.getString("Lobby.GameStart");
        Main.gamestart = ChatColor.translateAlternateColorCodes('&', Main.gamestart);

        Main.spectator = config.getString("Lobby.Spectator");
        Main.spectator = ChatColor.translateAlternateColorCodes('&', Main.spectator);

        Main.setuplobbywrong = config.getString("Setup.SetLobbyWrong");;
        Main.setuplobbywrong = ChatColor.translateAlternateColorCodes('&', Main.setuplobbywrong);

        Main.setuplobby = config.getString("Setup.SetLobby");
        Main.setuplobby = ChatColor.translateAlternateColorCodes('&', Main.setuplobby);

        Main.setspectatorwrong = config.getString("Setup.SetSpectatorWrong");
        Main.setspectatorwrong = ChatColor.translateAlternateColorCodes('&', Main.setspectatorwrong);

        Main.setspectator = config.getString("Setup.SetSpectator");
        Main.setspectator = ChatColor.translateAlternateColorCodes('&', Main.setspectator);

        Main.setspawnwrong = config.getString("Setup.SetSpawnsWrong");
        Main.setspawnwrong = ChatColor.translateAlternateColorCodes('&', Main.setspawnwrong);

        Main.setspawnnumberwrong = config.getString("Setup.SetSpawnsWrongNumber");
        Main.setspawnnumberwrong = ChatColor.translateAlternateColorCodes('&', Main.setspawnnumberwrong);
    }

    public void saveCfg() {
        try {
            config.save(file);
        } catch (IOException e) {
            System.out.println("Es gab einen Fehler beim Speichern der config.yml!");
        }
    }
}
