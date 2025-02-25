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
package upgradepvp.shop;

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
		player.getInventory().addItem(item);
		eco.removeMoney(price);
		eco.updateAllBalanceScoreboard();
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
			eco.getCurrentMap().playerFinish(player);
		}
	}
	
}
