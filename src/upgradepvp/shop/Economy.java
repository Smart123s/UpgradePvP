package upgradepvp.shop;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Economy {

	public static int startingMoney = 250;
	private int commonBalance = 0;
	private int safeBalance = 0;
	private boolean hasKeepInv = false;
	private static HashMap<Player, Economy> eco = new HashMap<Player, Economy>();
	private Inventory keepInvStorage;
	private Player playerOfEco;
	
	
	public Economy() {
		this.commonBalance = 0;
	}
	
	public int getCommonMoney() {
		return this.commonBalance;
	}
	
	public boolean hasEnough(int value) {
		if (this.commonBalance+this.safeBalance >= value) return true;
		return false;
	}
	
	public void addCommonMoney(int amount) {
		this.commonBalance += amount;
	}
	
	public void removeCommonMoney(int amount) {
		this.commonBalance -= amount;
	}
	
	public void setCommonMoney(int amount) {
		this.commonBalance = amount;
	}
	
	public boolean moveToSafeMoney(int amount) {
		if (!(this.commonBalance >= amount)) return false; 
			this.removeCommonMoney(amount);
			this.addSafeMoney((int) (amount*0.7));
		return true;
	}
	
	public void addSafeMoney(int amount) {
		this.safeBalance+=amount;
	}
	public boolean hasEnoughCommon(int amount) {
		if (this.commonBalance >= amount) return true;
		return false;
	}
	
	public int getSafeMoney() {
		return this.safeBalance;
	}
	
	public void removeSafeMoney(int amount) {
		this.safeBalance-=amount;
	}
	
	public void removeMoney(int amount) {
		if (this.commonBalance >= amount) {
			this.removeCommonMoney(amount);
		} else {
			int commonBalCopy = this.commonBalance;
			this.removeCommonMoney(this.getCommonMoney());
			amount-=commonBalCopy;
			this.removeSafeMoney(amount);
		}
	}
	
	public void setEconomyOfPlayer(Player player) {
		eco.put(player, this);
		this.playerOfEco = player;
	}
	
	public void addKeepInv() {
		this.hasKeepInv = true;
	}
	
	public boolean hasKeepInv() {
		return this.hasKeepInv;
	}
	
	public static void createEconomyOfPlayer(Player player) {
		Economy nEco = new Economy();
		nEco.setEconomyOfPlayer(player);
	}
	
	public static Economy getEconomyOfPlayer(Player player) {
		return eco.get(player);
	}
	
	public void storeKeepInvItems() {
		Inventory cInv = this.playerOfEco.getInventory();
		keepInvStorage = Bukkit.createInventory(null, 45);
		for (int i = 0; i <= 40; i++) {
			try {
				if (cInv.getItem(i) == null) continue;
				this.keepInvStorage.setItem(i, cInv.getItem(i));
			} catch (ArrayIndexOutOfBoundsException ex) {	
			} catch (NullPointerException ex) {}	
		}
	}
	
	public Inventory getKeepInvItems() {
		return this.keepInvStorage;
	}
	
	public void clearKeepInvStorage() {
		this.keepInvStorage = null;
	}
	
}
