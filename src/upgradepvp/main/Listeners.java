package upgradepvp.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import upgradepvp.shop.AddEnchantment;
import upgradepvp.shop.OpenShopItem;
import upgradepvp.shop.Rewarding;
import upgradepvp.shop.ShopInventoryListener;

public class Listeners implements Listener{

	private OpenShopItem openShopItem = new OpenShopItem();
	private ShopInventoryListener shopInventoryListener = new ShopInventoryListener();
	private Rewarding rewarding = new Rewarding();
	private AddEnchantment addEnchantment = new AddEnchantment();
	
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
}
