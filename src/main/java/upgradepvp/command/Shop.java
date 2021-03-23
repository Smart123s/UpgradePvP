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
package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.economy.Economy;
import upgradepvp.main.Main;
import upgradepvp.shop.OpenShopItem;

public class Shop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.prefixPlain + "Only players can execute this command!");
			return true;
		}
		Player player = Bukkit.getPlayer(sender.getName());
		//Permission check
		if (!player.hasPermission("upgradepvp.shop")) {
			player.sendMessage(Main.prefixError + "You do not have permission to execute this command");
			return true;
		}
		if (Economy.getEconomy(player) == null || !Economy.getEconomy(player).isInGame()) {
			player.sendMessage(Main.prefixError + "You are not ingame.");
			return true;
		}
		OpenShopItem.give(player);
		player.sendMessage(Main.prefix + "Your shop item has been succesfully restored.");
		return true;
	}
}
