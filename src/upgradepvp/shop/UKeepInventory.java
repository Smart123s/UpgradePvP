package upgradepvp.shop;

import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;

import upgradepvp.main.Main;

public class UKeepInventory {
	public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
		Economy eco = Economy.getEconomyOfPlayer(e.getPlayer());
		
		
		
		if (!eco.hasKeepInv()) return;
		
		Inventory pInv = e.getPlayer().getInventory();
		Inventory kInv = eco.getKeepInvItems();
		//Items
		for (int i = 0; i <= 35; i++) {
			pInv.setItem(i, kInv.getItem(i));
		}
		
		//Armor
		for (int i = 100; i <= 103; i++) {
			pInv.setItem(i, kInv.getItem(i));
		}
		
		eco.clearKeepInvStorage();
		
		Log.info(Main.prefix + "Your inventory has been restored, because you had KeepInventory.");
		
	}
	
}
