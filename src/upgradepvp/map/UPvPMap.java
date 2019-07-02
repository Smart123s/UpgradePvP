package upgradepvp.map;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import upgradepvp.config.ConfigFile;
import upgradepvp.economy.Economy;
import upgradepvp.main.Main;
import upgradepvp.shop.OpenShopItem;

public class UPvPMap {

	private String name;
	private Location lobby;
	private Location spawn;
	private ConfigFile storage;
	private ArrayList<Player> inGame = new ArrayList<Player>();
	private static HashMap<String, UPvPMap> mapName = new HashMap<String, UPvPMap>();
	
	public UPvPMap(String name) {
		this.name = name;
		mapName.put(name, this);
		//Creates config file for long-term storage
		this.storage = new ConfigFile("maps/" + name);
		this.lobby = (Location) storage.get().get("location.lobby");
		this.spawn = (Location) storage.get().get("location.spawn");
	}
	
	public void setLobby(Location loc) {
		this.lobby = loc;
		storage.get().set("location.lobby", loc);
		storage.save();
	}
	
	public void setSpawn(Location loc) {
		this.spawn = loc;
		storage.get().set("location.spawn", loc);
		storage.save();
	}
	
	public boolean isInGame(Player player) {
		return inGame.contains(player);
	}
	
	public String getName() {
		return name;
	}
	
	public static UPvPMap get(String name) {
		return mapName.get(name);
	}
	
	public void join(Player player) {
		//Disallow double join
		if (isInGame(player)) {
			player.sendMessage(Main.prefixError + "You are allready ingame!");
			return;
		}
		
		//Get the economy of the player
		Economy eco = Economy.getEconomy(player);
		//Check if eco exists
		if (eco == null) eco = new Economy(player);
		
		//Reset the player money, keepInv, etc.
		eco.reset();
		//Set the map to this in the eco
		eco.setCurrentMap(this);
		
		//Add the player to the Map's player list
		inGame.add(player);
		
		//Actions with the player
		//Teleport to the waiting area
		player.teleport(lobby);
		//Clear inventory
		player.getInventory().clear();
		//Set gamemode to adventure
		player.setGameMode(GameMode.ADVENTURE);
		//Reset health and saturation
		player.setHealth(20);
		player.setSaturation(20);
		//Remove all potion effects
		for (PotionEffect effect : player.getActivePotionEffects())
			player.removePotionEffect(effect.getType());
		//Set spawn point
		player.setBedSpawnLocation(spawn, true);
		
		//Notify the player about the successful join
		player.sendMessage(Main.prefix + "Successfully joined game " + name);
		
		//TODO: Add openShopItem when the game starts
	}
	
	public void startGame() {
		for (Player player : inGame) {
			player.teleport(spawn);
			OpenShopItem.give(player);
			player.sendMessage(Main.prefix + "The game has started!");
		}
	}
	
	public static boolean exists(String name) {
		for (UPvPMap map : Main.maps)
			if (map.getName().equalsIgnoreCase(name))
				return true;
		return false;
	}
	//TODO: Implement gameState
}
