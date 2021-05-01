/*
    This file is part of UpgradePvP.
	Copyright (C) 2019 Péter Tombor

    UpgradePvP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UpgradePvP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UpgradePvP.  If not, see <https://www.gnu.org/licenses/>.
*/
package upgradepvp.economy;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import upgradepvp.map.UPvPMap;

public class Economy {
	
	private static HashMap<Player, Economy> eco = new HashMap<Player, Economy>();
	public static int startingMoney = 250;
	
	private Player playerOfEco;
	private UPvPMap currentMap;
	
	private int commonBalance = startingMoney;
	private int safeBalance = 0;
	
	private boolean hasKeepInv = false;
	private Inventory keepInvStorage;
	
	private boolean invulnerable = false;
	
	
	public Economy(Player player) {
		eco.put(player, this);
		this.playerOfEco = player;
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
			this.addSafeMoney((int) (amount*0.05));
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
	
	public void addKeepInv() {
		this.hasKeepInv = true;
	}
	
	public boolean hasKeepInv() {
		return this.hasKeepInv;
	}
	
	public static Economy getEconomy(Player player) {
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
	
	public void setCurrentMap(UPvPMap map) {
		this.currentMap = map;
	}
	
	public UPvPMap getCurrentMap() {
		return currentMap;
	}
	
	public boolean isInGame() {
		if (currentMap == null) return false;
		return currentMap.isInGame(playerOfEco);
	}
	
	public void reset() {
		this.commonBalance = startingMoney;
		this.safeBalance = 0;
		this.hasKeepInv = false;
		this.keepInvStorage = null;
		this.currentMap = null;
	}
	
	public Player getPlayer() {
		return this.playerOfEco;
	}
	
	public boolean isInvulnerable() {
		return this.invulnerable;
	}
	
	public void setInvulnerable(boolean val) {
		this.invulnerable = val;
	}
	
}
