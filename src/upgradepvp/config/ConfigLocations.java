package upgradepvp.config;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class ConfigLocations {

	private final static HashMap<Enchantment, String> enchLoc = generateEnchLoc();
	private final static HashMap<String, Enchantment> bookName = generateBookName();
	private final static HashMap<Material, String> materialLoc = generateMaterialLoc();
	
	public static String getEnchLoc(Enchantment ench) {
		return enchLoc.get(ench);
	}
	
	public static Enchantment getBookName(String name) {
		return bookName.get(name);
	}
	
	public static String getMaterialLoc(Material material) {
		return materialLoc.get(material);
	}
	
	private static HashMap<Enchantment, String> generateEnchLoc(){
		HashMap<Enchantment, String> tmp = new HashMap<Enchantment, String>();
		tmp.put(Enchantment.DURABILITY, "Enchants.Unbreaking");
		tmp.put(Enchantment.PROTECTION_FALL, "Enchants.FallProt");
		tmp.put(Enchantment.PROTECTION_FIRE, "Enchants.FireProt");
		tmp.put(Enchantment.PROTECTION_PROJECTILE, "Enchants.ProjProt");
		tmp.put(Enchantment.PROTECTION_EXPLOSIONS, "Enchants.BlastProt");
		tmp.put(Enchantment.THORNS, "Enchants.Thorns");
		tmp.put(Enchantment.WATER_WORKER, "Enchants.AquaInf");
		tmp.put(Enchantment.OXYGEN, "Enchants.Respiration");
		tmp.put(Enchantment.DAMAGE_ALL, "Enchants.Sharpness");
		tmp.put(Enchantment.FIRE_ASPECT, "Enchants.FireAspect");
		tmp.put(Enchantment.KNOCKBACK, "Enchants.Knockback");
		tmp.put(Enchantment.ARROW_DAMAGE, "Enchants.Power");
		tmp.put(Enchantment.ARROW_KNOCKBACK, "Enchants.Punch");
		tmp.put(Enchantment.ARROW_FIRE, "Enchants.Flame");
		tmp.put(Enchantment.ARROW_INFINITE, "Enchants.Infinity");
		tmp.put(Enchantment.PROTECTION_ENVIRONMENTAL, "Enchants.Protection");
		return tmp;
	}
	
	private static HashMap<String, Enchantment> generateBookName(){
		HashMap<String, Enchantment> tmp = new HashMap<String, Enchantment>();
		tmp.put("Unbreaking", Enchantment.DURABILITY);
		tmp.put("Feather Falling", Enchantment.PROTECTION_FALL);
		tmp.put("Fire Protection", Enchantment.PROTECTION_FIRE);
		tmp.put("Blast Protection", Enchantment.PROTECTION_EXPLOSIONS);
		tmp.put("Projlectile Protection", Enchantment.PROTECTION_PROJECTILE);
		tmp.put("Thorns", Enchantment.THORNS);
		tmp.put("Aqua Infinity", Enchantment.WATER_WORKER);
		tmp.put("Respiration", Enchantment.OXYGEN);
		tmp.put("Sharpness", Enchantment.DAMAGE_ALL);
		tmp.put("Fire Aspect", Enchantment.FIRE_ASPECT);
		tmp.put("Knockback", Enchantment.KNOCKBACK);
		tmp.put("Power", Enchantment.ARROW_DAMAGE);
		tmp.put("Punch", Enchantment.ARROW_KNOCKBACK);
		tmp.put("Flame", Enchantment.ARROW_FIRE);
		tmp.put("Infinity", Enchantment.ARROW_INFINITE);	
		tmp.put("Protection", Enchantment.PROTECTION_ENVIRONMENTAL);
		return tmp;
	}
	
	private static HashMap<Material, String> generateMaterialLoc(){
		HashMap<Material, String> tmp = new HashMap<Material, String>();
		tmp.put(Material.WOOD_SWORD, "Swords.Wooden");		
		tmp.put(Material.GOLD_SWORD, "Swords.Gold");		
		tmp.put(Material.STONE_SWORD, "Swords.Stone");		
		tmp.put(Material.IRON_SWORD, "Swords.Iron");		
		tmp.put(Material.DIAMOND_SWORD, "Swords.Diamond");		
		tmp.put(Material.LEATHER_HELMET, "Armor.Leather.Helmet");		
		tmp.put(Material.LEATHER_CHESTPLATE, "Armor.Leather.Chestplate");		
		tmp.put(Material.LEATHER_LEGGINGS, "Armor.Leather.Leggings");		
		tmp.put(Material.LEATHER_BOOTS, "Armor.Leather.Boots");		
		tmp.put(Material.CHAINMAIL_HELMET, "Armor.Chain.Helmet");		
		tmp.put(Material.CHAINMAIL_CHESTPLATE, "Armor.Chain.Chestplate");		
		tmp.put(Material.CHAINMAIL_LEGGINGS, "Armor.Chain.Leggings");		
		tmp.put(Material.CHAINMAIL_BOOTS, "Armor.Chain.Boots");		
		tmp.put(Material.GOLD_HELMET, "Armor.Gold.Helmet");		
		tmp.put(Material.GOLD_CHESTPLATE, "Armor.Gold.Chestplate");		
		tmp.put(Material.GOLD_LEGGINGS, "Armor.Gold.Leggings");		
		tmp.put(Material.GOLD_BOOTS, "Armor.Gold.Boots");		
		tmp.put(Material.IRON_HELMET, "Armor.Iron.Helmet");		
		tmp.put(Material.IRON_CHESTPLATE, "Armor.Iron.Chestplate");		
		tmp.put(Material.IRON_LEGGINGS, "Armor.Iron.Leggings");		
		tmp.put(Material.IRON_BOOTS, "Armor.Iron.Boots");		
		tmp.put(Material.DIAMOND_HELMET, "Armor.Diamond.Helmet");		
		tmp.put(Material.DIAMOND_CHESTPLATE, "Armor.Diamond.Chestplate");		
		tmp.put(Material.DIAMOND_LEGGINGS, "Armor.Diamond.Leggings");		
		tmp.put(Material.DIAMOND_BOOTS, "Armor.Diamond.Boots");		
		tmp.put(Material.BOW, "Other.Bow");		
		tmp.put(Material.ARROW, "Other.Arrow");		
		tmp.put(Material.FISHING_ROD, "Other.FishingRod");		
		tmp.put(Material.SNOW_BALL, "Other.SnowBall");
		return tmp;
	}
	
}
