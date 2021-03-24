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

import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;

import upgradepvp.main.Main;

public class UKeepInventory {
	public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
		Economy eco = Economy.getEconomy(e.getPlayer());
		
		if (eco == null || !eco.hasKeepInv()) return;
		
		Inventory pInv = e.getPlayer().getInventory();
		Inventory kInv = eco.getKeepInvItems();
		
		for (int i = 0; i <= 40; i++) {
			try {
				if (kInv.getItem(i) == null) continue;
				pInv.setItem(i, kInv.getItem(i));
			} catch (ArrayIndexOutOfBoundsException ex) {	
			} catch (NullPointerException ex) {}
		}
		
		
		eco.clearKeepInvStorage();
		
		e.getPlayer().sendMessage(Main.prefix + "Your inventory has been restored, because you had KeepInventory.");
		
	}
	
}
