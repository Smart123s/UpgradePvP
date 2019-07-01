package upgradepvp.main;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import upgradepvp.command.Balance;
import upgradepvp.command.Safe;
import upgradepvp.command.Shop;
import upgradepvp.command.UpgradePvpTest;

public class Main extends JavaPlugin{
public static String prefixPlain = "[UpgradePvP] ";
public static String prefix = "§b[UpgradePvP] §3";
public static String prefixError = "§c[UpgradePvP] §4";
ConfigManager config = ConfigManager.getInstance();	
public static ArrayList<Player> inGame = new ArrayList<Player>();
public static ArrayList<Player> winners = new ArrayList<Player>();
Logger log = Bukkit.getLogger();

	public void onEnable() {
		
		log.info(prefixPlain + "Starting UpgradePvP version " + getDescription().getVersion().toString());
		
		config.setup(this);
		
		//Register commands
		getCommand("UpgradePvpTest").setExecutor(new UpgradePvpTest());
		getCommand("Shop").setExecutor(new Shop());
		getCommand("Balance").setExecutor(new Balance());
		getCommand("Safe").setExecutor(new Safe());
		
		//Register events
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);

		log.info(prefixPlain + "Successfully started UpgradePvP version " + getDescription().getVersion().toString());
	}
	
	public void onDisable() {
		log.info(prefixPlain + "Disabling UpgradePvP version " + getDescription().getVersion().toString());
		
		log.info(prefixPlain + "Successfully disabled UpgradePvP version " + getDescription().getVersion().toString());
	}
}
