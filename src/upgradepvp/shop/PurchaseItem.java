package upgradepvp.shop;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import upgradepvp.config.ConfigFile;
import upgradepvp.economy.Economy;
import upgradepvp.main.Main;

public class PurchaseItem {
	static ConfigFile priceConfig = ConfigFile.get("price");	
	
	public static void buy(Player player, ItemStack item, int price) {
		Economy eco = Economy.getEconomy(player);
		if (!eco.hasEnough(price)) {
			player.sendMessage(Main.prefixError + "You don't have enough money to purchase this product!");
			return;
		}
			eco.removeMoney(price);
			player.getInventory().addItem(item);
	}
	
	public static ItemStack removeMeta(ItemStack item, boolean keepName) {
		if (item.getType() == Material.AIR) return null;
		ItemStack item2 = new ItemStack(Material.COBBLESTONE); 
		ItemMeta meta = item2.getItemMeta();
		item2 = item.clone();
		String name = item.getItemMeta().getDisplayName();
		if (keepName) meta.setDisplayName(name);
		item2.setItemMeta(meta);
		return item2;
	}
	
	public static int getPrice(ItemStack item) {
		if (item.getType() == Material.AIR) return 0;
		item = item.clone();
		return Integer.valueOf(item.getItemMeta().getLore().get(0).replaceAll("Cost: ", "").replaceAll("§6§e", "").replace("$", ""));
	}
	
	public static void buyRaw(Player player, ItemStack item, boolean keepName) {
		if (item.getType() == Material.AIR) return;
		item = item.clone();
		buy(player, PurchaseItem.removeMeta(item.clone(), keepName), PurchaseItem.getPrice(item.clone()));
	}
	
	public static void buyKeepInv(Player player) {
		Economy eco = Economy.getEconomy(player);
		final int price = priceConfig.get().getInt("Other.KeepInventory");
		if (eco.hasKeepInv()) {
			player.sendMessage(Main.prefixError + "You allready own this product!");
			return;
		} else if (!eco.hasEnough(price)) {
			player.sendMessage(Main.prefixError + "You don't have enough money to purchase this product!");
			return;
		}
		eco.removeMoney(price);
		player.sendMessage(Main.prefix + "You successfully purchased KeepInventory for this round.");
		eco.addKeepInv();	
	}
	
	public static void buyWin(Player player) {
		Economy eco = Economy.getEconomy(player);
		int price = priceConfig.get().getInt("Win");
		if (!eco.hasEnough(price)) {
			player.sendMessage(Main.prefixError + "You do not have enough money to buy that!");
		} else {
			eco.removeMoney(price);
			player.setGameMode(GameMode.SPECTATOR);
			Bukkit.broadcastMessage(Main.prefix + "Player " + player.getName() + " has just finished the game! Place: " + (Main.winners.indexOf(player)+1));
			//TODO: Remove the player from the Map's ingame list && add to it's winners list
		}
	}
	
}
