/*
    This file is part of UpgradePvP.
	Copyright (C) 2019 Péter Tombor

    UpgradePvP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UpgradePvP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UpgradePvP.  If not, see <https://www.gnu.org/licenses/>.
*/
package upgradepvp.map;

import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitScheduler;

import upgradepvp.config.ConfigFile;
import upgradepvp.economy.Economy;
import upgradepvp.main.Main;
import upgradepvp.shop.OpenShopItem;

public class UPvPMap {

	public static Plugin plugin;
	private String name;
	private Location lobby;
	private Location spawn;
	private ConfigFile storage;
	private ArrayList<Player> inGame = new ArrayList<Player>();
	private static HashMap<String, UPvPMap> mapName = new HashMap<String, UPvPMap>();
	private ArrayList<Player> winners = new ArrayList<Player>();
	private static int respawnProt = new ConfigFile("config").get().getInt("respawn-protection");
	private int databaseGameId;
	
	public UPvPMap(String name) {
		plugin = Main.plugin;
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

		//Add the player to the Map's player list
		inGame.add(player);

		//Actions with the player
		//Teleport to the waiting area
		player.teleport(lobby);

		//Reset player
		this.resetPlayer(player);

		//Notify the player about the successful join
		player.sendMessage(Main.prefix + "Successfully joined game " + name);

	}

	public void startGame() {
		ArrayList<Runnable> databaseStatements = new ArrayList<>();

		// Add game to database
		databaseStatements.add(() -> {
			try {
				Connection conn = Main.getDatabaseConnection();
				String sql = "INSERT INTO game () VALUES ()";

				PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int affectedRows = preparedStatement.executeUpdate();
				if (affectedRows > 0) {
					try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
						if (generatedKeys.next()) {
							databaseGameId = generatedKeys.getInt(1);
						}
					}
				}
				conn.close();
			} catch (SQLException exception) {
				exception.printStackTrace();
			}
		});

		for (Player player : inGame) {
			//Reset player
			this.resetPlayer(player);
			//Teleport to spawn
			player.teleport(spawn);
			//Give an openShopItem
			OpenShopItem.give(player);
			//Tell them that the game has started
			player.sendMessage(Main.prefix + "The game has started!");
		}

		this.updateAllBalanceScoreboard();
		for (Player player : inGame) {
			Economy eco = Economy.getEconomy(player);
			if (eco == null) continue;
			eco.getCurrentMap().updateAllBalanceScoreboard();

			databaseStatements.add(() -> {
				try {
					Connection conn = Main.getDatabaseConnection();
					String sql;
					PreparedStatement preparedStatement;

					// Add game player to database
					sql = "INSERT IGNORE INTO player (player_uuid, player_name) VALUES (?, ?)";
					preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, player.getUniqueId().toString());
					preparedStatement.setString(2, player.getName());
					preparedStatement.executeUpdate();

					// Add game player to database
					sql = "INSERT INTO game_player (game_id, player_uuid) VALUES (?, ?)";
					preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setInt(1, databaseGameId); // databaseGameId is accessible here
					preparedStatement.setString(2, player.getUniqueId().toString());
					preparedStatement.executeUpdate();
					conn.close();

				} catch (SQLException exception) {
					exception.printStackTrace();
				}
			});
		}

		// Create and run a single task for all database operations
		Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
			for (Runnable statement : databaseStatements) {
				statement.run();
			}
		});
	}

	public void playerFinish(Player player) {
		this.winners.add(player);
		player.setGameMode(GameMode.SPECTATOR);
		sendMessageAll(Main.prefix + "Player " + player.getName() + " has just finished the game! Place: " + winners.size());
	}

	public void reset() {
		this.inGame.clear();
		this.winners.clear();
	}

	public void sendMessageAll(String message) {
		for (Player player : inGame)
			player.sendMessage(message);
	}

	public static boolean exists(String name) {
		for (UPvPMap map : Main.maps)
			if (map.getName().equalsIgnoreCase(name))
				return true;
		return false;
	}
	//TODO: Implement gameState

	public void performSpawnProtectionActions(Player player) {
		Economy eco = Economy.getEconomy(player);
		if (eco == null) return;
		eco.setInvulnerable(true);
		player.sendMessage(Main.prefix + "You are now invulnerable for " + respawnProt + " seconds");

		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				if (!eco.isInvulnerable()) return;
				eco.setInvulnerable(false);
				player.sendMessage(Main.prefix + "Your invulnerability has expired");
			}
		}, respawnProt*20L);

	}

	public void updateAllBalanceScoreboard() {
		for (Player player : this.getInGame()) {
			Economy eco = Economy.getEconomy(player);
			for (Player player2 : this.getInGame()) {
				Economy playerEco = Economy.getEconomy(player2);
				eco.updateBalanceScoreboard(playerEco);
			}
		}
	}

	public ArrayList<Player> getInGame() {
		return inGame;
	}

	private void resetPlayer(Player player) {
		//Get the economy of the player
		Economy eco = Economy.getEconomy(player);
		//Check if eco exists
		if (eco == null) eco = new Economy(player);

		//Reset the player money, keepInv, etc.
		eco.reset();
		//Set the map to this in the eco
		eco.setCurrentMap(this);

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
	}

	public int getDatabaseGameId() {
		return databaseGameId;
	}
	
}
