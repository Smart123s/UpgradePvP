package upgradepvp.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import upgradepvp.main.Main;

public class AddEnchantment {

	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) return;
		if (!Main.inGame.contains(Bukkit.getPlayer(e.getWhoClicked().getName()))) return;
		if (e.getClickedInventory().getName().contains("UpgradePvp Shop > ")) return;
		
		Player player = Bukkit.getPlayer(e.getWhoClicked().getName());
		
		if(e.getCurrentItem().getType() == Material.ENCHANTED_BOOK) {
			if (player.getItemOnCursor().getType() == Material.AIR) return;
			Enchantment ench = nameToEnch(e.getCurrentItem().getItemMeta().getDisplayName());
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
	
	private Enchantment nameToEnch(String name){
		Enchantment ench = null;
		if (name.contains("Unbreaking")) ench = Enchantment.DURABILITY;
		else if (name.contains("Feather Falling")) ench = Enchantment.PROTECTION_FALL;
		else if (name.contains("Fire Protection")) ench = Enchantment.PROTECTION_FIRE;
		else if (name.contains("Blast Protection")) ench = Enchantment.PROTECTION_EXPLOSIONS;
		else if (name.contains("Projlectile Protection")) ench = Enchantment.PROTECTION_PROJECTILE;
		else if (name.contains("Thorns")) ench = Enchantment.THORNS;
		else if (name.contains("Aqua Infinity")) ench = Enchantment.WATER_WORKER;
		else if (name.contains("Respiration")) ench = Enchantment.OXYGEN;
		else if (name.contains("Sharpness")) ench = Enchantment.DAMAGE_ALL;
		else if (name.contains("Fire Aspect")) ench = Enchantment.FIRE_ASPECT;
		else if (name.contains("Knockback")) ench = Enchantment.KNOCKBACK;
		else if (name.contains("Power")) ench = Enchantment.ARROW_DAMAGE;
		else if (name.contains("Punch")) ench = Enchantment.ARROW_KNOCKBACK;
		else if (name.contains("Flame")) ench = Enchantment.ARROW_FIRE;
		else if (name.contains("Infinity")) ench = Enchantment.ARROW_INFINITE;	
		else if (name.contains("Protection")) ench = Enchantment.PROTECTION_ENVIRONMENTAL;
		return ench;
	}
	
}
