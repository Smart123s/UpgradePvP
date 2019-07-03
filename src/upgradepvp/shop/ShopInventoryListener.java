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
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopInventoryListener{

	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) return;
		Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
		
		switch (e.getClickedInventory().getName()) {
		case "UpgradePvp Shop > Main" :
			e.setCancelled(true);
			
			switch(e.getCurrentItem().getType()) {
			case WOOD_SWORD : 
				player.openInventory(ShopInventory.swords);
				break;
			case CHAINMAIL_CHESTPLATE : 
				player.openInventory(ShopInventory.armor);
				break;
			case ENCHANTED_BOOK : 
				player.openInventory(ShopInventory.ench);
				break;
			case FISHING_ROD : 
				player.openInventory(ShopInventory.other);
				break;
			case DIAMOND:
				PurchaseItem.buyWin(player);
				break;
			case BARRIER:
				player.closeInventory();
				break;
			default:
				break;
			}
		break;	
		
		case "UpgradePvp Shop > Swords" :
		case "UpgradePvp Shop > Armor" :
		case "UpgradePvp Shop > Others" :	
		case "UpgradePvp Shop > Enchantments" :
		
			
			e.setCancelled(true);
			if (isGoBackItem(e.getCurrentItem())) 
				player.openInventory(ShopInventory.start);
			else if (e.getCurrentItem().getType() == Material.EXP_BOTTLE) 
				PurchaseItem.buyKeepInv(player);
			else 
				PurchaseItem.buyRaw(player, e.getCurrentItem().clone(), e.getClickedInventory().getName().equalsIgnoreCase("UpgradePvp Shop > Enchantments"));
			break;
		}
					
	}
	
	private boolean isGoBackItem(ItemStack item) {
		return item.getType() == Material.BARRIER 
				&& item.getItemMeta().getDisplayName().equalsIgnoreCase(ShopInventory.goBackItem.getItemMeta().getDisplayName());
	}
	
}
