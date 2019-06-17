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

import upgradepvp.main.ConfigManager;

public class ShopInventory{
	ConfigManager config = ConfigManager.getInstance();	
	
	//TODO: Improve with ConfigLocations
	
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
		
		inv.setItem(0, shopInv.createBasicItem(Material.WOOD_SWORD, "Swords.Wooden"));
		inv.setItem(1, shopInv.createBasicItem(Material.GOLD_SWORD, "Swords.Gold"));
		inv.setItem(2, shopInv.createBasicItem(Material.STONE_SWORD, "Swords.Stone"));
		inv.setItem(3, shopInv.createBasicItem(Material.IRON_SWORD, "Swords.Iron"));
		inv.setItem(4, shopInv.createBasicItem(Material.DIAMOND_SWORD, "Swords.Diamond"));
		
		shopInv=null;
		return inv;
	}
	
	private static Inventory generateArmorInv() {
		Inventory inv = Bukkit.createInventory(null, 9*3, "UpgradePvp Shop > Armor");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(26, shopInv.createGoBackItem());
		
		inv.setItem(0, shopInv.createBasicItem(Material.LEATHER_BOOTS, "Armor.Leather.Boots"));
		inv.setItem(1, shopInv.createBasicItem(Material.LEATHER_LEGGINGS, "Armor.Leather.Leggings"));
		inv.setItem(2, shopInv.createBasicItem(Material.LEATHER_CHESTPLATE, "Armor.Leather.Chestplate"));
		inv.setItem(3, shopInv.createBasicItem(Material.LEATHER_HELMET, "Armor.Leather.Helmet"));
		
		inv.setItem(5, shopInv.createBasicItem(Material.CHAINMAIL_BOOTS, "Armor.Chain.Boots"));
		inv.setItem(6, shopInv.createBasicItem(Material.CHAINMAIL_LEGGINGS, "Armor.Chain.Leggings"));
		inv.setItem(7, shopInv.createBasicItem(Material.CHAINMAIL_CHESTPLATE, "Armor.Chain.Chestplate"));
		inv.setItem(8, shopInv.createBasicItem(Material.CHAINMAIL_HELMET, "Armor.Chain.Helmet"));
		
		inv.setItem(9, shopInv.createBasicItem(Material.GOLD_BOOTS, "Armor.Gold.Boots"));
		inv.setItem(10, shopInv.createBasicItem(Material.GOLD_LEGGINGS, "Armor.Gold.Leggings"));
		inv.setItem(11, shopInv.createBasicItem(Material.GOLD_CHESTPLATE, "Armor.Gold.Chestplate"));
		inv.setItem(12, shopInv.createBasicItem(Material.GOLD_HELMET, "Armor.Gold.Helmet"));
		
		inv.setItem(14, shopInv.createBasicItem(Material.IRON_BOOTS, "Armor.Iron.Boots"));
		inv.setItem(15, shopInv.createBasicItem(Material.IRON_LEGGINGS, "Armor.Iron.Leggings"));
		inv.setItem(16, shopInv.createBasicItem(Material.IRON_CHESTPLATE, "Armor.Iron.Chestplate"));
		inv.setItem(17, shopInv.createBasicItem(Material.IRON_HELMET, "Armor.Iron.Helmet"));
		
		inv.setItem(18, shopInv.createBasicItem(Material.DIAMOND_BOOTS, "Armor.Diamond.Boots"));
		inv.setItem(19, shopInv.createBasicItem(Material.DIAMOND_LEGGINGS, "Armor.Diamond.Leggings"));
		inv.setItem(20, shopInv.createBasicItem(Material.DIAMOND_CHESTPLATE, "Armor.Diamond.Chestplate"));
		inv.setItem(21, shopInv.createBasicItem(Material.DIAMOND_HELMET, "Armor.Diamond.Helmet"));
		
		shopInv=null;
		return inv;
	}
	
	private static Inventory generateEnchInv() {
		Inventory inv = Bukkit.createInventory(null, 9*2, "UpgradePvp Shop > Enchantments");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(17, shopInv.createGoBackItem());
		
		inv.setItem(0, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Unbreaking", ChatColor.AQUA + "Unbreaking"));
		inv.setItem(1, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Protection", ChatColor.AQUA + "Protection"));
		inv.setItem(2, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.FallProt", ChatColor.AQUA + "Feather Falling"));
		inv.setItem(3, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.FireProt", ChatColor.AQUA + "Fire Protection"));
		inv.setItem(4, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.BlastProt", ChatColor.AQUA + "Blast Protection"));
		inv.setItem(5, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.ProjProt", ChatColor.AQUA + "Projlectile Protection"));
		inv.setItem(6, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Thorns", ChatColor.AQUA + "Thorns"));
		inv.setItem(7, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.AquaInf", ChatColor.AQUA + "Aqua Infinity"));
		inv.setItem(8, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Respiration", ChatColor.AQUA + "Respiration"));
		inv.setItem(9, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Sharpness", ChatColor.AQUA + "Sharpness"));
		inv.setItem(10, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.FireAspect", ChatColor.AQUA + "Fire Aspect"));
		inv.setItem(11, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Knockback", ChatColor.AQUA + "Knockback"));
		inv.setItem(12, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Power", ChatColor.AQUA + "Power"));
		inv.setItem(13, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Punch", ChatColor.AQUA + "Punch"));
		inv.setItem(14, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Flame", ChatColor.AQUA + "Flame"));
		inv.setItem(15, shopInv.createItemWithName(Material.ENCHANTED_BOOK, "Enchants.Infinity", ChatColor.AQUA + "Infinity"));
		
		shopInv=null;
		return inv;
	}
	
	private static Inventory generateOtherInv() {
		Inventory inv = Bukkit.createInventory(null, 9, "UpgradePvp Shop > Others");
		ShopInventory shopInv = new ShopInventory();
		
		inv.setItem(8, shopInv.createGoBackItem());
		
		inv.setItem(0, shopInv.createBasicItem(Material.BOW, "Other.Bow"));
		inv.setItem(1, shopInv.createBasicItem(Material.ARROW, "Other.Arrow"));
		inv.setItem(2, shopInv.createBasicItem(Material.FISHING_ROD, "Other.FishingRod"));
		inv.setItem(3, shopInv.createBasicItem(Material.SNOW_BALL, "Other.SnowBall"));
		
		
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

	private ItemStack createBasicItem(Material material, String priceLoc) {
		ItemStack item = new ItemStack(material, 1);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GOLD + "Cost: " + ChatColor.YELLOW + config.getPrice().getInt(priceLoc) + "$");
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
}
