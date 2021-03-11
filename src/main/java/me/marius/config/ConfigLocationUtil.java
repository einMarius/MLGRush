package me.marius.config;

import me.marius.gamestates.LobbyState;
import me.marius.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigLocationUtil {

    private static Main plugin;
    private Location location;
    private String root;
    public static Location[] spawnLocations = new Location[LobbyState.MAX_PLAYERS];

    public ConfigLocationUtil(Main plugin, Location location, String root){
        this.plugin = plugin;
        this.location = location;
        this.root = root;
    }

    public ConfigLocationUtil(Main plugin, String root){
        this(plugin, null, root);
    }

    public static void setSpawnLocation(int spawnnumber, Location location){
        spawnLocations[spawnnumber-1] = location;
        new ConfigLocationUtil(plugin, location, "Spawns.Team " + spawnnumber).saveLocation();
    }

    public static void loadSpawnLocations(){
        for(int i = 0; i < spawnLocations.length; i++){
            spawnLocations[i] = new ConfigLocationUtil(plugin, "Spawns.Team " + (i + 1)).loadLocation();
        }
    }

    public void saveLocation(){
        FileConfiguration config = ConfigManager.config;
        config.set(root + ".World", location.getWorld().getName());
        config.set(root + ".X", location.getX());
        config.set(root + ".Y", location.getY());
        config.set(root + ".Z", location.getZ());
        config.set(root + ".Yaw", location.getYaw());
        config.set(root + ".Pitch", location.getPitch());
        Main.cm.saveCfg();
    }
    public Location loadLocation(){
        FileConfiguration config = ConfigManager.config;
        if(config.contains(root)) {
            World world = Bukkit.getWorld(config.getString(root + ".World"));
            double x = config.getDouble(root + ".X"),
                   y = config.getDouble(root + ".Y"),
                   z = config.getDouble(root + ".Z");
            float yaw = (float) config.getDouble(root + ".Yaw"),
                    pitch = (float) config.getDouble(root + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        } else
            return null;
    }

    public static Location[] getSpawnLocations() {
        return spawnLocations;
    }
}
