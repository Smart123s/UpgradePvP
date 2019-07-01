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

import upgradepvp.config.ConfigFile;
import upgradepvp.config.ConfigLocations;

public class ShopInventory{
	static ConfigFile priceConfig = ConfigFile.get("price");	
	
	//Inventories of the shop
	public final static Inventory start = generateStartInv();
	public final static Inventory swords = generateSwordsInv();
	public final static Inventory armor = generateArmorInv();
	public final static Inventory ench = generateEnchInv();
	public final static Inventory other = generateOtherInv();
	
	//Item which opens the previous inventory
	public final static ItemStack goBackItem = createGoBackItem();
	
	
	private static Inventory generateStartInv() {
		//Create a new Inventory
		Inventory inv = Bukkit.createInventory(null, 9, "UpgradePvp Shop > Main");
		
		//Fill up the inventory with its items
		inv.setItem(0, createCategoryItem(Material.WOOD_SWORD, "Swords"));
		inv.setItem(1, createCategoryItem(Material.CHAINMAIL_CHESTPLATE, "Armor"));
		inv.setItem(2, createCategoryItem(Material.ENCHANTED_BOOK, "Enchantments"));
		inv.setItem(3, createCategoryItem(Material.FISHING_ROD, "Other"));
		
		//Add Finish Game diamond
		inv.setItem(7, createFinishMyGame());
		
		//Add Go Back item
		inv.setItem(8, createGoBackItem());
		
		return inv;
	}
	
	private static Inventory generateSwordsInv() {
		//Create a new Inventory
		Inventory inv = Bukkit.createInventory(null, 9, "UpgradePvp Shop > Swords");
		
		//Add Go Back item
		inv.setItem(8, createGoBackItem());
		
		//Fill up the inventory with its items
		inv.setItem(0, createBasicItem(Material.WOOD_SWORD));
		inv.setItem(1, createBasicItem(Material.GOLD_SWORD));
		inv.setItem(2, createBasicItem(Material.STONE_SWORD));
		inv.setItem(3, createBasicItem(Material.IRON_SWORD));
		inv.setItem(4, createBasicItem(Material.DIAMOND_SWORD));
		
		return inv;
	}
	
	private static Inventory generateArmorInv() {
		//Create a new Inventory
		Inventory inv = Bukkit.createInventory(null, 9*3, "UpgradePvp Shop > Armor");
		
		//Add Go Back item
		inv.setItem(26, createGoBackItem());
		
		//Fill up the inventory with its items
		
		//Leather Armor
		inv.setItem(0, createBasicItem(Material.LEATHER_BOOTS));
		inv.setItem(1, createBasicItem(Material.LEATHER_LEGGINGS));
		inv.setItem(2, createBasicItem(Material.LEATHER_CHESTPLATE));
		inv.setItem(3, createBasicItem(Material.LEATHER_HELMET));
		
		//Chain Armor
		inv.setItem(5, createBasicItem(Material.CHAINMAIL_BOOTS));
		inv.setItem(6, createBasicItem(Material.CHAINMAIL_LEGGINGS));
		inv.setItem(7, createBasicItem(Material.CHAINMAIL_CHESTPLATE));
		inv.setItem(8, createBasicItem(Material.CHAINMAIL_HELMET));
		
		//Gold Armor
		inv.setItem(9, createBasicItem(Material.GOLD_BOOTS));
		inv.setItem(10, createBasicItem(Material.GOLD_LEGGINGS));
		inv.setItem(11, createBasicItem(Material.GOLD_CHESTPLATE));
		inv.setItem(12, createBasicItem(Material.GOLD_HELMET));
		
		//Iron Armor
		inv.setItem(14, createBasicItem(Material.IRON_BOOTS));
		inv.setItem(15, createBasicItem(Material.IRON_LEGGINGS));
		inv.setItem(16, createBasicItem(Material.IRON_CHESTPLATE));
		inv.setItem(17, createBasicItem(Material.IRON_HELMET));
		
		//Diamond Armor
		inv.setItem(18, createBasicItem(Material.DIAMOND_BOOTS));
		inv.setItem(19, createBasicItem(Material.DIAMOND_LEGGINGS));
		inv.setItem(20, createBasicItem(Material.DIAMOND_CHESTPLATE));
		inv.setItem(21, createBasicItem(Material.DIAMOND_HELMET));
		
		return inv;
	}
	
	private static Inventory generateEnchInv() {
		//Create a new Inventory
		Inventory inv = Bukkit.createInventory(null, 9*2, "UpgradePvp Shop > Enchantments");
		
		//Add Go Back item
		inv.setItem(17, createGoBackItem());
		
		//Fill up the inventory with its items
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
		
		return inv;
	}
	
	private static Inventory generateOtherInv() {
		//Create a new Inventory
		Inventory inv = Bukkit.createInventory(null, 9, "UpgradePvp Shop > Others");
		
		//Add Go Back item
		inv.setItem(8, createGoBackItem());
		
		//Fill up the inventory with its items
		inv.setItem(0, createBasicItem(Material.BOW));
		inv.setItem(1, createBasicItem(Material.ARROW));
		inv.setItem(2, createBasicItem(Material.FISHING_ROD));
		inv.setItem(3, createBasicItem(Material.SNOW_BALL));
		
		//Add Keep Inventory item
		inv.setItem(4, generateKeepInvItem());
	
		return inv;
	}
	
	private static ItemStack generateKeepInvItem() {
		ItemStack keepInvItem = createItemWithName(Material.EXP_BOTTLE, "Other.KeepInventory",ChatColor.AQUA + "Keep Inventory");
		List<String> lore = keepInvItem.getItemMeta().getLore();
		lore.add(ChatColor.BLUE + "" + ChatColor.ITALIC + "Permament");
		ItemMeta meta = keepInvItem.getItemMeta();
		meta.setLore(lore);
		keepInvItem.setItemMeta(meta);
		return keepInvItem;
	}

	private static ItemStack createBasicItem(Material material) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.YELLOW + priceConfig.get().getInt(ConfigLocations.getMaterialLoc(material)) + "$");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;		
	}
	
	private static ItemStack createGoBackItem() {
		ItemStack item = new ItemStack(Material.BARRIER, 1);
		ItemMeta meta = item.getItemMeta();
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED + "Go Back!");
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack createItemWithName(Material material, String priceLoc, String name) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.YELLOW + priceConfig.get().getInt(priceLoc) + "$");
		meta.setLore(lore);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack createCategoryItem(Material material, String name) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		List<String> lore = new ArrayList<String>();
		lore.add("Open Category...");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack createFinishMyGame() {
		ItemStack item = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		meta.setDisplayName("Finish My Game!");
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.YELLOW + priceConfig.get().getInt("Win") + "$");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack createEnchantedBook(String ench) {
		final String priceLoc = ConfigLocations.getEnchLoc(ConfigLocations.getBookName(ench));
		return createItemWithName(Material.ENCHANTED_BOOK, priceLoc, ChatColor.AQUA + ench);
	}
}