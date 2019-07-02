package upgradepvp.main;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import upgradepvp.command.*;
import upgradepvp.command.upvp.UPvPBaseCommand;
import upgradepvp.config.*;
import upgradepvp.map.UPvPMap;

public class Main extends JavaPlugin{
public static String prefixPlain = "[UpgradePvP] ";
public static String prefix = "§b[UpgradePvP] §3";
public static String prefixError = "§c[UpgradePvP] §4";
public static ArrayList<Player> winners = new ArrayList<Player>();
public static ArrayList<UPvPMap> maps = new ArrayList<UPvPMap>();
Logger log = Bukkit.getLogger();

	public void onEnable() {
		
		log.info(prefixPlain + "Starting UpgradePvP version " + getDescription().getVersion().toString());
		
		//Initialize ConfigFile.plugin
		ConfigFile.plugin = this;
		
		//Create Config Files
		new ConfigFile("config");
		new ConfigFile("price");
		
		//Register commands
		getCommand("UpgradePvpTest").setExecutor(new UpgradePvpTest());
		getCommand("Shop").setExecutor(new Shop());
		getCommand("Balance").setExecutor(new Balance());
		getCommand("Safe").setExecutor(new Safe());
		getCommand("UPvP").setExecutor(new UPvPBaseCommand());
		
		//Register events
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		
		//Add available maps to maps variable
		File mapsFolder = new File(this.getDataFolder().getPath().replace('\\', '/') + "/maps");
		if (!mapsFolder.exists()) mapsFolder.mkdir();
		for (File file : mapsFolder.listFiles())
			maps.add(new UPvPMap(file.getName().replaceAll(".yml", "")));

		log.info(prefixPlain + "Successfully started UpgradePvP version " + getDescription().getVersion().toString());
	}
	
	public void onDisable() {
		log.info(prefixPlain + "Disabling UpgradePvP version " + getDescription().getVersion().toString());
		
		log.info(prefixPlain + "Successfully disabled UpgradePvP version " + getDescription().getVersion().toString());
	}
}
