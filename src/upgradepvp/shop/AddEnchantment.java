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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import upgradepvp.config.ConfigLocations;
import upgradepvp.economy.Economy;
import upgradepvp.main.Main;

public class AddEnchantment {

	public void onInventoryClickEvent(InventoryClickEvent e) {
		
		//Check if we should start the enchanting process
		if (e.getClickedInventory() == null
		 || Economy.getEconomy(Bukkit.getPlayer(e.getWhoClicked().getName())) == null
		 || !Economy.getEconomy(Bukkit.getPlayer(e.getWhoClicked().getName())).isInGame()
		 || e.getClickedInventory().getName().contains("UpgradePvp Shop > ")
		 || e.getCurrentItem().getType() != Material.ENCHANTED_BOOK
		 || Bukkit.getPlayer(e.getWhoClicked().getName()).getItemOnCursor().getType() == Material.AIR) //TODO: Fix a glitch, when this returns true in the players inventory (opened by pressing e)
			return;
		
		Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
		Enchantment ench = ConfigLocations.getBookName(e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§b", ""));
		if (!ench.canEnchantItem(player.getItemOnCursor())) {
			player.sendMessage(Main.prefixError + "You cannot enchant this item with this enchantment!");
			e.setCancelled(true);
			return;
		}
		
		else if (ench.getMaxLevel() <= player.getItemOnCursor().getEnchantmentLevel(ench)) {
			player.sendMessage(Main.prefixError + "You cannot increase the level of this enchant anymore!");
			e.setCancelled(true);
			return;
		}
		player.getItemOnCursor().addEnchantment(ench, player.getItemOnCursor().getEnchantmentLevel(ench) + 1);
		e.getCurrentItem().setType(Material.FEATHER);
		e.getCurrentItem().setItemMeta((new ItemStack(Material.COBBLESTONE).getItemMeta()));
		player.sendMessage(Main.prefix + "Item enchanted successfully!");	
		
	}
	
}
