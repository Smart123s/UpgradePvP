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

public class Safe implements CommandExecutor{

	private final static float SAFE_MULTIPLIER = 0.2f;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.prefixPlain + "Only players can execute this command!");
			return true;
		}
		Player player = Bukkit.getServer().getPlayer(sender.getName());
		//Permission check
		if (!player.hasPermission("upgradepvp.safe")) {
			player.sendMessage(Main.prefixError + "You do not have permission to execute this command");
			return true;
		} else if (args.length != 1) {
			player.sendMessage(Main.prefixError + "/safe <amount|all>");
			return true;
		}
		
		
		Economy eco = Economy.getEconomy(player);
		if (eco == null || !eco.isInGame()) {
			player.sendMessage(Main.prefixError + "You are not ingame.");
			return true;
		}
		if (args[0].equalsIgnoreCase("all")) {
			player.sendMessage(Main.prefix + "$" + eco.getCommonMoney() + " has been taken from your Common Balance.");
			player.sendMessage(Main.prefix + "$" + (int) (eco.getCommonMoney()*SAFE_MULTIPLIER) + " has been added to your Safe Balance.");
			eco.moveToSafeMoney((int) (eco.getCommonMoney()*SAFE_MULTIPLIER));
			eco.getCurrentMap().updateAllBalanceScoreboard();
			return true;
		}
		else if (!eco.hasEnoughCommon(Integer.valueOf(args[0]))) {
			player.sendMessage(Main.prefixError + "You do not have enough money to do that!");
			return true;
		}
		eco.moveToSafeMoney((int) ((Integer.valueOf(args[0]))*SAFE_MULTIPLIER));
		player.sendMessage(Main.prefix + "$" + args[0] + " has been taken from your Common Balance.");
		player.sendMessage(Main.prefix + "$" + (int) (Integer.valueOf(args[0])*SAFE_MULTIPLIER) + " has been added to your Safe Balance.");
		return true;
	}

}
