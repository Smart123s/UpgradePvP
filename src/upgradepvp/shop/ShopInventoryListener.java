package upgradepvp.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopInventoryListener{
ShopInventory invs = new ShopInventory();

	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) return;
		Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
		
		if (e.getClickedInventory().getName() == "UpgradePvp Shop > Main") {
			e.setCancelled(true);
			
			if (e.getCurrentItem().getType() == Material.WOOD_SWORD && e.getCurrentItem().getItemMeta().getDisplayName() == "Swords") {
				player.openInventory(ShopInventory.swords);
			}
			else if (e.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE && e.getCurrentItem().getItemMeta().getDisplayName() == "Armor") {
				player.openInventory(ShopInventory.armor);
			}
			else if (e.getCurrentItem().getType() == Material.ENCHANTED_BOOK && e.getCurrentItem().getItemMeta().getDisplayName() == "Enchantments") {
				player.openInventory(ShopInventory.ench);
			}
			else if (e.getCurrentItem().getType() == Material.FISHING_ROD && e.getCurrentItem().getItemMeta().getDisplayName() == "Other") {
				player.openInventory(ShopInventory.other);
			}
			else if (e.getCurrentItem().getType() == Material.DIAMOND && e.getCurrentItem().getItemMeta().getDisplayName() == "Finish My Game!") {
				PurchaseItem.buyWin(player);
			}
			else if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.start.getItem(8).getItemMeta().getDisplayName()) {
				player.closeInventory();
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Swords") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.swords.getItem(8).getItemMeta().getDisplayName()) {
				player.openInventory(ShopInventory.start);
			} else {
				PurchaseItem.buyRaw(player, e.getCurrentItem().clone(), false);
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Armor") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.armor.getItem(26).getItemMeta().getDisplayName()) {
				player.openInventory(ShopInventory.start);
			} else {
				PurchaseItem.buyRaw(player, e.getCurrentItem().clone(), false);
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Enchantments") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.ench.getItem(17).getItemMeta().getDisplayName()) {
				player.openInventory(ShopInventory.start);
			} else {
				PurchaseItem.buyRaw(player, e.getCurrentItem().clone(), true);
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Others") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.other.getItem(8).getItemMeta().getDisplayName()) {
				player.openInventory(ShopInventory.start);
			} else if (e.getCurrentItem().getType() == Material.EXP_BOTTLE) {
				PurchaseItem.buyKeepInv(player);
			} else {
				PurchaseItem.buyRaw(player, e.getCurrentItem().clone(), false);
			}
		}
	}
	
}
