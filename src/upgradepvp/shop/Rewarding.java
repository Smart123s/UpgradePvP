package upgradepvp.shop;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import upgradepvp.main.ConfigManager;
import upgradepvp.main.Main;

public class Rewarding {
	ConfigManager config = ConfigManager.getInstance();	
	
	 public void onKill(PlayerDeathEvent e){
	 Player killed = Bukkit.getPlayer(e.getEntity().getName());
	 if (e.getEntity().getKiller() == null) return;
	 Player killer = Bukkit.getPlayer(e.getEntity().getKiller().getName());
	 if (!Main.inGame.contains(killed) || !Main.inGame.contains(killer)) return;
	 e.getDrops().clear();
	 Economy killedEco = Economy.getEconomyOfPlayer(killed);
	 Economy killerEco = Economy.getEconomyOfPlayer(killer);
	 if (killedEco.hasKeepInv()) killedEco.storeKeepInvItems();
	 int killerAward = (int) Math.round(((getInventoryValue(killed)/getInventoryValue(killer)+1*0.2)*(killedEco.getCommonMoney() + killedEco.getSafeMoney() + getInventoryValue(killed))));
	 int killedAward = (int) Math.round((killedEco.getCommonMoney() + killerEco.getCommonMoney() + killerEco.getSafeMoney() + getInventoryValue(killer))*0.4);
	 if (!killedEco.hasKeepInv()) killedAward += (int) (Math.round(getInventoryValue(killed)*0.25));
	 if (killedAward < 250) killedAward = 250;
	 killerEco.addCommonMoney(killerAward);
	 killedEco.setCommonMoney(killedAward);
	 killer.sendMessage(Main.prefix + "$" + killerAward + " has been added to your account!");
	 killed.sendMessage(Main.prefix + "$" + killedAward + " is your new balance!");
	 }
	
	public int getInventoryValue(Player player) {
		int value = 0;
		Inventory inv = player.getInventory();
		for (int i = 0; i < 40; i++) {
			if (inv.getItem(i) == null) continue;
			ItemStack currentItem = inv.getItem(i);

			ItemStack copyCurrentItem = currentItem.clone();
			int valueOfItem = 0;
			
			switch(copyCurrentItem.getType()) {
			case WOOD_SWORD: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Swords.Wooden");
				break;
			case GOLD_SWORD: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Swords.Gold");
				break;
			case STONE_SWORD: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Swords.Stone");
				break;
			case IRON_SWORD: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Swords.Iron");
				break;
			case DIAMOND_SWORD: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Swords.Diamond");
				break;
			case LEATHER_HELMET: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Leather.Helmet");
				break;
			case LEATHER_CHESTPLATE: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Leather.Chestplate");
				break;
			case LEATHER_LEGGINGS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Leather.Leggings");
				break;
			case LEATHER_BOOTS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Leather.Boots");
				break;
			case CHAINMAIL_HELMET: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Chain.Helmet");
				break;
			case CHAINMAIL_CHESTPLATE: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Chain.Chestplate");
				break;
			case CHAINMAIL_LEGGINGS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Chain.Leggings");
				break;
			case CHAINMAIL_BOOTS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Chain.Boots");
				break;
			case GOLD_HELMET: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Gold.Helmet");
				break;
			case GOLD_CHESTPLATE: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Gold.Chestplate");
				break;
			case GOLD_LEGGINGS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Gold.Leggings");
				break;
			case GOLD_BOOTS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Gold.Boots");
				break;
			case IRON_HELMET: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Iron.Helmet");
				break;
			case IRON_CHESTPLATE: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Iron.Chestplate");
				break;
			case IRON_LEGGINGS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Iron.Leggings");
				break;
			case IRON_BOOTS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Iron.Boots");
				break;
			case DIAMOND_HELMET: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Diamond.Helmet");
				break;
			case DIAMOND_CHESTPLATE: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Diamond.Chestplate");
				break;
			case DIAMOND_LEGGINGS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Diamond.Leggings");
				break;
			case DIAMOND_BOOTS: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Armor.Diamond.Boots");
				break;
			case BOW: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Other.Bow");
				break;
			case ARROW: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Other.Arrow")*copyCurrentItem.getAmount();
				break;
			case FISHING_ROD: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Other.FishingRod");
				break;
			case SNOW_BALL: 
				valueOfItem+=calcItemValue(copyCurrentItem, "Other.SnowBall")*copyCurrentItem.getAmount();
				break;
			case ENCHANTED_BOOK: 
				if(copyCurrentItem.getItemMeta().getDisplayName().contains("Unbreaking")) valueOfItem+=config.getPrice().getInt("Enchants.Unbreaking");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Feather Falling")) valueOfItem+=config.getPrice().getInt("Enchants.FallProt");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Fire Protection")) valueOfItem+=config.getPrice().getInt("Enchants.FireProt");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Projlectile Protection")) valueOfItem+=config.getPrice().getInt("Enchants.ProjProt");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Blast Protection")) valueOfItem+=config.getPrice().getInt("Enchants.BlastProt");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Thorns")) valueOfItem+=config.getPrice().getInt("Enchants.Thorns");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Aqua Infinity")) valueOfItem+=config.getPrice().getInt("Enchants.AquaInf");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Respiration")) valueOfItem+=config.getPrice().getInt("Enchants.Respiration");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Sharpness")) valueOfItem+=config.getPrice().getInt("Enchants.Sharpness");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Fire Aspect")) valueOfItem+=config.getPrice().getInt("Enchants.FireAspect");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Knockback")) valueOfItem+=config.getPrice().getInt("Enchants.Knockback");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Power")) valueOfItem+=config.getPrice().getInt("Enchants.Power");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Punch")) valueOfItem+=config.getPrice().getInt("Enchants.Punch");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Flame")) valueOfItem+=config.getPrice().getInt("Enchants.Flame");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Infinity")) valueOfItem+=config.getPrice().getInt("Enchants.Infinity");
				else if(copyCurrentItem.getItemMeta().getDisplayName().contains("Protection")) valueOfItem+=config.getPrice().getInt("Enchants.Protection");

				break;
			default:
				break;
			}
			
							
			//Check for Enchants
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.DURABILITY)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.DURABILITY)*config.getPrice().getInt("Enchants.Unbreaking");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.PROTECTION_FALL)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.PROTECTION_FALL)*config.getPrice().getInt("Enchants.FallProt");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.PROTECTION_FIRE)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)*config.getPrice().getInt("Enchants.FireProt");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.PROTECTION_PROJECTILE)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE)*config.getPrice().getInt("Enchants.ProjProt");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.PROTECTION_EXPLOSIONS)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS)*config.getPrice().getInt("Enchants.BlastProt");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.THORNS)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.THORNS)*config.getPrice().getInt("Enchants.Thorns");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.WATER_WORKER)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.WATER_WORKER)*config.getPrice().getInt("Enchants.AquaInf");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.OXYGEN)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.OXYGEN)*config.getPrice().getInt("Enchants.Respiration");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.DAMAGE_ALL)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.DAMAGE_ALL)*config.getPrice().getInt("Enchants.Sharpness");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.FIRE_ASPECT)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.FIRE_ASPECT)*config.getPrice().getInt("Enchants.FireAspect");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.KNOCKBACK)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.KNOCKBACK)*config.getPrice().getInt("Enchants.Knockback");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.ARROW_DAMAGE)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*config.getPrice().getInt("Enchants.Power");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.ARROW_KNOCKBACK)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK)*config.getPrice().getInt("Enchants.Punch");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.ARROW_FIRE)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.ARROW_FIRE)*config.getPrice().getInt("Enchants.Flame");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.ARROW_INFINITE)*config.getPrice().getInt("Enchants.Infinity");
			if(copyCurrentItem.getEnchantments().containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)) valueOfItem+=copyCurrentItem.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL)*config.getPrice().getInt("Enchants.Protection");

		value+=valueOfItem;	
		}
		return value;
	}
	 
	private int calcItemValue(ItemStack item, String priceLoc) {
		try {
			return (int) ((float) (config.getPrice().getInt(priceLoc)*(item.getType().getMaxDurability()-item.getDurability())/item.getType().getMaxDurability()));
		} catch (ArithmeticException e) {
			return config.getPrice().getInt(priceLoc);
		}
		
	}
	
}
