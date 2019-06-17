package upgradepvp.shop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import upgradepvp.economy.ConfigLocations;
import upgradepvp.main.ConfigManager;

public class ShopInventory{
	ConfigManager config = ConfigManager.getInstance();	
	
	public final static Inventory start = generateStartInv();
	public final static Inventory swords = generateSwordsInv();
	public final static Inventory armor = generateArmorInv();
	public final static Inventory ench = generateEnchInv();
	public final static Inventory other = generateOtherInv();
	
	private static Inventory generateStartInv() {
		Inventory inv = Bukkit.createInventory(null, 9, "UpgradePvp Shop > Main");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(0, shopInv.createCategoryItem(Material.WOOD_SWORD, "Swords"));
		inv.setItem(1, shopInv.createCategoryItem(Material.CHAINMAIL_CHESTPLATE, "Armor"));
		inv.setItem(2, shopInv.createCategoryItem(Material.ENCHANTED_BOOK, "Enchantments"));
		inv.setItem(3, shopInv.createCategoryItem(Material.FISHING_ROD, "Other"));

		inv.setItem(7, shopInv.createFinishMyGame());
		
		inv.setItem(8, shopInv.createGoBackItem());
		
		shopInv=null;
		return inv;
	}
	
	private static Inventory generateSwordsInv() {
		Inventory inv = Bukkit.createInventory(null, 9, "UpgradePvp Shop > Swords");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(8, shopInv.createGoBackItem());
		
		inv.setItem(0, shopInv.createBasicItem(Material.WOOD_SWORD));
		inv.setItem(1, shopInv.createBasicItem(Material.GOLD_SWORD));
		inv.setItem(2, shopInv.createBasicItem(Material.STONE_SWORD));
		inv.setItem(3, shopInv.createBasicItem(Material.IRON_SWORD));
		inv.setItem(4, shopInv.createBasicItem(Material.DIAMOND_SWORD));
		
		shopInv=null;
		return inv;
	}
	
	private static Inventory generateArmorInv() {
		Inventory inv = Bukkit.createInventory(null, 9*3, "UpgradePvp Shop > Armor");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(26, shopInv.createGoBackItem());
		
		inv.setItem(0, shopInv.createBasicItem(Material.LEATHER_BOOTS));
		inv.setItem(1, shopInv.createBasicItem(Material.LEATHER_LEGGINGS));
		inv.setItem(2, shopInv.createBasicItem(Material.LEATHER_CHESTPLATE));
		inv.setItem(3, shopInv.createBasicItem(Material.LEATHER_HELMET));
		
		inv.setItem(5, shopInv.createBasicItem(Material.CHAINMAIL_BOOTS));
		inv.setItem(6, shopInv.createBasicItem(Material.CHAINMAIL_LEGGINGS));
		inv.setItem(7, shopInv.createBasicItem(Material.CHAINMAIL_CHESTPLATE));
		inv.setItem(8, shopInv.createBasicItem(Material.CHAINMAIL_HELMET));
		
		inv.setItem(9, shopInv.createBasicItem(Material.GOLD_BOOTS));
		inv.setItem(10, shopInv.createBasicItem(Material.GOLD_LEGGINGS));
		inv.setItem(11, shopInv.createBasicItem(Material.GOLD_CHESTPLATE));
		inv.setItem(12, shopInv.createBasicItem(Material.GOLD_HELMET));
		
		inv.setItem(14, shopInv.createBasicItem(Material.IRON_BOOTS));
		inv.setItem(15, shopInv.createBasicItem(Material.IRON_LEGGINGS));
		inv.setItem(16, shopInv.createBasicItem(Material.IRON_CHESTPLATE));
		inv.setItem(17, shopInv.createBasicItem(Material.IRON_HELMET));
		
		inv.setItem(18, shopInv.createBasicItem(Material.DIAMOND_BOOTS));
		inv.setItem(19, shopInv.createBasicItem(Material.DIAMOND_LEGGINGS));
		inv.setItem(20, shopInv.createBasicItem(Material.DIAMOND_CHESTPLATE));
		inv.setItem(21, shopInv.createBasicItem(Material.DIAMOND_HELMET));
		
		shopInv=null;
		return inv;
	}
	
	private static Inventory generateEnchInv() {
		Inventory inv = Bukkit.createInventory(null, 9*2, "UpgradePvp Shop > Enchantments");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(17, shopInv.createGoBackItem());
		
		inv.setItem(0, createEnchantedBook("Unbreaking"));
		inv.setItem(1, createEnchantedBook("Protection"));
		inv.setItem(2, createEnchantedBook("Feather Falling"));
		inv.setItem(3, createEnchantedBook("Fire Protection"));
		inv.setItem(4, createEnchantedBook("Blast Protection"));
		inv.setItem(5, createEnchantedBook("Projlectile Protection"));
		inv.setItem(6, createEnchantedBook("Thorns"));
		inv.setItem(7, createEnchantedBook("Aqua Infinity"));
		inv.setItem(8, createEnchantedBook("Respiration"));
		inv.setItem(9, createEnchantedBook("Sharpness"));
		inv.setItem(10, createEnchantedBook("Fire Aspect"));
		inv.setItem(11, createEnchantedBook("Knockback"));
		inv.setItem(12, createEnchantedBook("Power"));
		inv.setItem(13, createEnchantedBook("Punch"));
		inv.setItem(14, createEnchantedBook("Flame"));
		inv.setItem(15, createEnchantedBook("Infinity"));
		
		shopInv=null;
		return inv;
	}
	
	private static Inventory generateOtherInv() {
		Inventory inv = Bukkit.createInventory(null, 9, "UpgradePvp Shop > Others");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(8, shopInv.createGoBackItem());
		
		inv.setItem(0, shopInv.createBasicItem(Material.BOW));
		inv.setItem(1, shopInv.createBasicItem(Material.ARROW));
		inv.setItem(2, shopInv.createBasicItem(Material.FISHING_ROD));
		inv.setItem(3, shopInv.createBasicItem(Material.SNOW_BALL));
		
		inv.setItem(4, generateKeepInvItem());
	
		shopInv=null;
		return inv;
	}
	
	private static ItemStack generateKeepInvItem() {
		ShopInventory shopInv = new ShopInventory();
		ItemStack keepInvItem = shopInv.createItemWithName(Material.EXP_BOTTLE, "Other.KeepInventory",ChatColor.AQUA + "Keep Inventory");
		List<String> lore = keepInvItem.getItemMeta().getLore();
		lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Permament");
		ItemMeta meta = keepInvItem.getItemMeta();
		meta.setLore(lore);
		keepInvItem.setItemMeta(meta);
		return keepInvItem;
	}

	private ItemStack createBasicItem(Material material) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.YELLOW + config.getPrice().getInt(ConfigLocations.getMaterialLoc(material)) + "$");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;		
	}
	
	private ItemStack createGoBackItem() {
		ItemStack item = new ItemStack(Material.BARRIER, 1);
		ItemMeta meta = item.getItemMeta();
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED + "Go Back!");
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack createItemWithName(Material material, String priceLoc, String name) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.YELLOW + config.getPrice().getInt(priceLoc) + "$");
		meta.setLore(lore);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack createCategoryItem(Material material, String name) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<String>();
		lore.add("Open Category...");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack createFinishMyGame() {
		ItemStack item = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName("Finish My Game!");
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.YELLOW + config.getPrice().getInt("Win") + "$");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack createEnchantedBook(String ench) {
		ShopInventory shopInv = new ShopInventory();
		final String priceLoc = ConfigLocations.getEnchLoc(ConfigLocations.getBookName(ench));
		return shopInv.createItemWithName(Material.ENCHANTED_BOOK, priceLoc, ChatColor.AQUA + ench);
	}
}
