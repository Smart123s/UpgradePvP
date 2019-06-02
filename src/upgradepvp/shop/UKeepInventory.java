package upgradepvp.shop;

import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;

import upgradepvp.main.Main;

public class UKeepInventory {
	public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
		Economy eco = Economy.getEconomyOfPlayer(e.getPlayer());
		
		
		
		if (!eco.hasKeepInv()) return;
		
		Inventory pInv = e.getPlayer().getInventory();
		Inventory kInv = eco.getKeepInvItems();
		
		
		//TODO: FIX THIS
		for (int i = 0; i <= 40; i++) {
			try {
				if (pInv.getItem(i) == null) continue;
				pInv.setItem(i, kInv.getItem(i));
			} catch (ArrayIndexOutOfBoundsException ex) {}
		}
		
		
		eco.clearKeepInvStorage();
		
		e.getPlayer().sendMessage(Main.prefix + "Your inventory has been restored, because you had KeepInventory.");
		
	}
	
}