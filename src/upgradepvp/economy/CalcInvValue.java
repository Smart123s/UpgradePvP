package upgradepvp.economy;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import upgradepvp.main.ConfigManager;

public class CalcInvValue {
	static ConfigManager config = ConfigManager.getInstance();	
	
	public static int getInvVal(Player player) {
		int value = 0;
		Inventory inv = player.getInventory();
		for (ItemStack item : inv.getContents()) {
			if (item == null) continue;
			value+=calcItemValue(item);	
		}
		return value;
	}
	
	private static int calcNoEnchItemValue(ItemStack item, String priceLoc) {
		try {
			return (int) ((float) (config.getPrice().getInt(priceLoc)*(item.getType().getMaxDurability()-item.getDurability())/item.getType().getMaxDurability()));
		} catch (ArithmeticException e) {
			return config.getPrice().getInt(priceLoc);
		}
		
	}
	
	private static int calcItemValue(ItemStack item) {
		if (item.getType() == Material.ENCHANTED_BOOK) {
			final Enchantment enchOfItem = ConfigLocations.getBookName(item.getItemMeta().getDisplayName().replaceAll("§b", ""));
			return config.getPrice().getInt(ConfigLocations.getEnchLoc(enchOfItem));
		}
		
		String priceLoc = ConfigLocations.getMaterialLoc(item.getType());
		if (priceLoc == null) return 0;
		int valueOfItem = calcNoEnchItemValue(item, priceLoc);
		
		//Check for Enchants
		for (Enchantment ench : item.getEnchantments().keySet()) 
			valueOfItem+=item.getEnchantmentLevel(ench)*config.getPrice().getInt(ConfigLocations.getEnchLoc(ench));
		
		return valueOfItem;
		
	}
	
}
