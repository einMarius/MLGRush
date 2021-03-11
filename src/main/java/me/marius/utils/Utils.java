package me.marius.utils;

import me.marius.main.Main;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {

    public static void setEntchantedItem(Material material, Enchantment enchantment, int strenght, String displayname, int amount, int slot, Player p){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemStack.setAmount(amount);
        itemMeta.addEnchant(enchantment, strenght, false);
        itemStack.setItemMeta(itemMeta);
        p.getInventory().setItem(slot, itemStack);
        p.updateInventory();
    }

    public static void setItem(Material material, String displayname, int amount, int slot, Player p){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        p.getInventory().setItem(slot, itemStack);
        p.updateInventory();
    }

    public static void setItem2(Material material, Enchantment enchantment, int strenght, int amount, int slot, Player p){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemStack.setAmount(amount);
        itemStack.setItemMeta(itemMeta);
        itemMeta.addEnchant(enchantment, strenght, false);
        p.getInventory().setItem(slot, itemStack);
        p.updateInventory();
    }

    public static void setItem3(Material material, int amount, int slot, Player p){
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
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
        clearPlayer(p);
        setItem(Material.CHEST, "§8§l» §b§lInventar§f§lsortierung", 1, 1, p);
        setItem(Material.WATCH, "§8§l» §e§lLobby", 1, 7, p);
    }

    public static void setSpectatorItems(Player p){
        clearPlayer(p);
        setItem(Material.COMPASS, "§8§l» §b§lTeleporter", 1, 1, p);
        setItem(Material.WATCH, "§8§l» §e§lLobby", 1, 7, p);
        p.setAllowFlight(true);
    }

    public static void setIngameitems(Player p){
        clearPlayer(p);

        //BLÖCKE
        ItemStack sandstone = new ItemStack(Material.SANDSTONE);
        sandstone.setAmount(64);
        p.getInventory().setItem(0, sandstone);

        //STICK
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta stickmeta = stick.getItemMeta();
        stick.setAmount(1);
        stickmeta.addEnchant(Enchantment.KNOCKBACK, 2, false);
        stick.setItemMeta(stickmeta);
        p.getInventory().setItem(1, stick);

        //SPITZHACKE
        ItemStack spitzhacke = new ItemStack(Material.WOOD_PICKAXE);
        ItemMeta axemeta = spitzhacke.getItemMeta();
        axemeta.spigot().setUnbreakable(true);
        spitzhacke.setAmount(1);
        spitzhacke.setDurability((short) 0);
        spitzhacke.setItemMeta(axemeta);
        p.getInventory().setItem(8, spitzhacke);

        p.updateInventory();
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
