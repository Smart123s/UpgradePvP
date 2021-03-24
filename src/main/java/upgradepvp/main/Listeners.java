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
package upgradepvp.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import upgradepvp.economy.Economy;
import upgradepvp.economy.Rewarding;
import upgradepvp.economy.UKeepInventory;
import upgradepvp.map.PlayerDamageEvent;
import upgradepvp.map.UPvPMap;
import upgradepvp.shop.AddEnchantment;
import upgradepvp.shop.OpenShopItem;
import upgradepvp.shop.ShopInventoryListener;

public class Listeners implements Listener{

	private OpenShopItem openShopItem = new OpenShopItem();
	private ShopInventoryListener shopInventoryListener = new ShopInventoryListener();
	private Rewarding rewarding = new Rewarding();
	private AddEnchantment addEnchantment = new AddEnchantment();
	private UKeepInventory uKeepInventory = new UKeepInventory();
	private PlayerDamageEvent playerDamageEvent = new PlayerDamageEvent();
	
	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		shopInventoryListener.onInventoryClickEvent(e);
		//openShopItem.onInventoryCLickEvent(e);
		addEnchantment.onInventoryClickEvent(e);
	}
	
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {
		openShopItem.onPlayerInteractEvent(e);
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent e) {
		rewarding.onKill(e);
	}
	
	//Disables Weather
	@EventHandler
	public void onWeatherChangeEvent(WeatherChangeEvent e) {
		e.setCancelled(true);
	}
	
	//Disables Hunger
	@EventHandler
	public void onFoodLevelChangeEvent(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerRespavnEvent(PlayerRespawnEvent e) {
		uKeepInventory.onPlayerRespawnEvent(e);
		openShopItem.onPlayerRespawnEvent(e);
		UPvPMap map = Economy.getEconomy(e.getPlayer()).getCurrentMap();
		if (map != null) map.performSpawnProtectionActions(e.getPlayer());
	}
	
	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		playerDamageEvent.onEntityDamageByEntityEvent(e);
	}
}
