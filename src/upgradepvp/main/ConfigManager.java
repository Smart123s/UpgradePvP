package upgradepvp.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class ConfigManager {
	 
       private ConfigManager() { }
      
       static ConfigManager instance = new ConfigManager();
      
       public static ConfigManager getInstance() {
               return instance;
       }
      
       Plugin p;
      
       FileConfiguration config;
       File cfile;
      
       FileConfiguration price;
       File pfile;
      
       public void setup(Plugin p) {
               cfile = new File(p.getDataFolder(), "config.yml");
               config = p.getConfig();
               //config.options().copyDefaults(true);
               //saveConfig();
               
               price.options().copyDefaults(true);
               savePrice();
              
               if (!p.getDataFolder().exists()) {
                       p.getDataFolder().mkdir();
               }
              
               pfile = new File(p.getDataFolder(), "price.yml");
              
               if (!pfile.exists()) {
                       try {
                               pfile.createNewFile();
                       }
                       catch (IOException e) {
                               Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create price.yml!");
                       }
               }
              
               price = YamlConfiguration.loadConfiguration(pfile);
       }
      
       public FileConfiguration getPrice() {
               return price;
       }
      
       public void savePrice() {
               try {
                       price.save(pfile);
               }
               catch (IOException e) {
                       Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save price.yml!");
               }
       }
      
       public void reloadPrice() {
               price = YamlConfiguration.loadConfiguration(pfile);
       }
      
       public FileConfiguration getConfig() {
               return config;
       }
      
       public void saveConfig() {
               try {
                       config.save(cfile);
               }
               catch (IOException e) {
                       Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
               }
       }
      
       public void reloadConfig() {
               config = YamlConfiguration.loadConfiguration(cfile);
       }
      
       public PluginDescriptionFile getDesc() {
               return p.getDescription();
       }

}