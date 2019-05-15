package upgradepvp.shop;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import upgradepvp.main.ConfigManager;
import upgradepvp.main.Main;

public class ShopInventoryListener{
ShopInventory invs = new ShopInventory();

	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) return;

		if (e.getClickedInventory().getName() == "UpgradePvp Shop > Main") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.WOOD_SWORD && e.getCurrentItem().getItemMeta().getDisplayName() == "Swords") {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.swords);
			}
			else if (e.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE && e.getCurrentItem().getItemMeta().getDisplayName() == "Armor") {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.armor);
			}
			else if (e.getCurrentItem().getType() == Material.ENCHANTED_BOOK && e.getCurrentItem().getItemMeta().getDisplayName() == "Enchantments") {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.ench);
			}
			else if (e.getCurrentItem().getType() == Material.FISHING_ROD && e.getCurrentItem().getItemMeta().getDisplayName() == "Other") {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.other);
			}
			else if (e.getCurrentItem().getType() == Material.DIAMOND && e.getCurrentItem().getItemMeta().getDisplayName() == "Finish My Game!") {
				Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
				Economy eco = Economy.getEconomyOfPlayer(player);
				int price = ConfigManager.getInstance().getPrice().getInt("Win");
				if (!eco.hasEnough(price)) {
					player.sendMessage(Main.prefixError + "You do not have enough money to buy that!");
				} else {
					eco.removeMoney(price);
					Main.winners.add(player);
					player.setGameMode(GameMode.SPECTATOR);
					Bukkit.broadcastMessage(Main.prefix + "Player " + player.getName() + " has just finished the game! Place: " + (Main.winners.indexOf(player)+1));
					Main.inGame.remove(player);
				}
			}
			else if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.start.getItem(8).getItemMeta().getDisplayName()) {
				Bukkit.getPlayer(e.getWhoClicked().getName()).closeInventory();
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Swords") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.swords.getItem(8).getItemMeta().getDisplayName()) {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.start);
			} else {
				PurchaseItem.buyRaw(Bukkit.getPlayer(e.getWhoClicked().getName()), e.getCurrentItem().clone(), false);
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Armor") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.armor.getItem(26).getItemMeta().getDisplayName()) {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.start);
			} else {
				PurchaseItem.buyRaw(Bukkit.getPlayer(e.getWhoClicked().getName()), e.getCurrentItem().clone(), false);
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Enchantments") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.ench.getItem(17).getItemMeta().getDisplayName()) {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.start);
			} else {
				PurchaseItem.buyRaw(Bukkit.getPlayer(e.getWhoClicked().getName()), e.getCurrentItem().clone(), true);
			}
		}
		else if (e.getClickedInventory().getName() == "UpgradePvp Shop > Others") {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() == Material.BARRIER && e.getCurrentItem().getItemMeta().getDisplayName() == ShopInventory.other.getItem(8).getItemMeta().getDisplayName()) {
				Bukkit.getPlayer(e.getWhoClicked().getName()).openInventory(ShopInventory.start);
			} else if (e.getCurrentItem().getType() == Material.EXP_BOTTLE) {
				//TODO: KeepInventory purchase
			} else {
				PurchaseItem.buyRaw(Bukkit.getPlayer(e.getWhoClicked().getName()), e.getCurrentItem().clone(), false);
			}
		}
	}
	
}
