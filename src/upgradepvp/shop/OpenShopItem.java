package upgradepvp.shop;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import upgradepvp.main.Main;

public class OpenShopItem {
	final static ItemStack shopItem = generateItem();
	
	private static ItemStack generateItem() {
		ItemStack shopItemHere = new ItemStack(Material.CHEST, 1);
		ItemMeta meta = shopItemHere.getItemMeta();
		meta.setDisplayName(ChatColor.RESET + "" + ChatColor.GOLD + "Open Shop " + ChatColor.GRAY + "" + ChatColor.ITALIC + "(Right Click)");
		shopItemHere.setItemMeta(meta);
		return shopItemHere;
	}
	
	public void openShopItem(Player player) {
		player.getInventory().setItem(8, shopItem);
	}
	
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		if (!Main.inGame.contains(e.getPlayer())) return;
		try {
		if (e.getItem().getItemMeta().getDisplayName() == shopItem.getItemMeta().getDisplayName() && e.getItem().getType() == Material.CHEST) {
			e.setCancelled(true);
			e.getPlayer().getInventory().setItem(8, shopItem);
			e.getPlayer().openInventory(ShopInventory.start);
		}
		} catch(NullPointerException exception) {
			return;
		}
	}
	
	//DISABLED IN THE Listeners CLASS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void onInventoryCLickEvent(InventoryClickEvent e) {
		if (e.getClickedInventory() == null) return;
		if (!Main.inGame.contains(Bukkit.getPlayer(e.getWhoClicked().getName()))) return;
		if (e.getClickedInventory().contains(shopItem) && e.getSlot() == 8) {
			//if (!e.getWhoClicked().getOpenInventory().getTitle().contains("UpgradePvp Shop >")) e.getInventory().setItem(8, shopItem);
			e.setCancelled(true);
			
		}
	}
	
}
