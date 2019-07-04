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
package upgradepvp.map;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import upgradepvp.economy.Economy;
import upgradepvp.main.Main;

public class PlayerDamageEvent {

	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
		
		if(e.getDamager().getType() != EntityType.PLAYER || e.getEntity().getType() != EntityType.PLAYER) return;
		Player damager = Bukkit.getPlayer(e.getDamager().getName());
		Player damaged = Bukkit.getPlayer(e.getEntity().getName());
		Economy damagerEco = Economy.getEconomy(damager);
		if (damagerEco == null) return;
		Economy damagedEco = Economy.getEconomy(damaged);
		if (damagedEco == null) return;
		
		if (damagedEco.isInvulnerable()) {
			e.setCancelled(true);
			damager.sendMessage(Main.prefixError + "This player is still invulnerable");
			return;
		}
		
		if (damagerEco.isInvulnerable()) {
			damagerEco.setInvulnerable(false);
			damager.sendMessage(Main.prefix + "Your invulnerability has expired");
		}
		//TODO: Slowness on hit perk
		
	}
	
}
