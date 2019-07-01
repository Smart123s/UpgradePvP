package upgradepvp.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import upgradepvp.config.ConfigLocations;
import upgradepvp.main.Main;

public class AddEnchantment {

	public void onInventoryClickEvent(InventoryClickEvent e) {
		
		//Check if we should start the enchanting process
		if (e.getClickedInventory() == null
		 || !Main.inGame.contains(Bukkit.getPlayer(e.getWhoClicked().getName()))
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
