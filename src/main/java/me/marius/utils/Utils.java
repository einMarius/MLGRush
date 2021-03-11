package me.marius.utils;

import me.marius.main.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static void setItem(Material material, String displayname, int amount, int slot, Player p){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        p.getInventory().setItem(slot, itemStack);
        p.updateInventory();
    }

    public static void clearPlayer(Player p){
        p.getInventory().clear();
        p.setGameMode(GameMode.SURVIVAL);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.setLevel(0);
        p.getInventory().setArmorContents(null);
    }

    public static void setLobbyItems(Player p){
        setItem(Material.CHEST, "§8§l» §b§lInventar§f§lsortierung", 1, 1, p);
        setItem(Material.WATCH, "§8§l» §e§lLobby", 1, 7, p);
    }

    public static void setSpectatorItems(Player p){
        setItem(Material.COMPASS, "§8§l» §b§lTeleporter", 1, 1, p);
        setItem(Material.WATCH, "§8§l» §e§lLobby", 1, 7, p);
        p.setAllowFlight(true);
    }

    public static void sendHelpMessage(Player p){
        p.sendMessage("");
        p.sendMessage(Main.prefix + "----------=" + Main.prefix + "=----------");
        p.sendMessage(Main.prefix + Main.setuplobbywrong);
        p.sendMessage(Main.prefix + Main.setspectatorwrong);
        p.sendMessage(Main.prefix + Main.setspawnwrong);
        p.sendMessage(Main.prefix + "----------=" + Main.prefix + "=----------");
    }

}
