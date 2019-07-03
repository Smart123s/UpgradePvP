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

import upgradepvp.economy.CalcInvValue;
import upgradepvp.economy.Economy;
import upgradepvp.main.Main;

public class Balance implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.prefixPlain + "Only players can execute this command!");
			return true;
		}
		//Get the player who executed the command
		Player player = Bukkit.getPlayer(sender.getName());
		
		//Create an economy
		Economy eco = null;
		//Your or the name of the requested player in genitive
		String dispNameGen = "";
		//You or the name of the requested player plus be
		String dispNameBe = "";
		
		//Initialize the variables according to whose balance you are requesting
		if (args.length == 0) {
			eco = Economy.getEconomy(player);
			dispNameGen = "Your";
			dispNameBe = "You are";
			
		} else if (args.length == 1) {
			eco = Economy.getEconomy(Bukkit.getPlayer(args[0]));
			dispNameGen = args[0] + "'s";
			dispNameBe = args[0] + " is";
		} else {
			player.sendMessage(Main.prefixError + "/balance [player]");
		}
		
		//Check if the player is in-game
		if (eco == null || !eco.isInGame()) {
			//Notify the player that they are not in-game
			player.sendMessage(Main.prefixError + dispNameBe + " not ingame.");
			return true;
		}

		//Send messages about the player's common balance
		player.sendMessage(Main.prefix + dispNameGen + " current balance: $" + eco.getCommonMoney());
		//Send messages about the player's safe balance
		player.sendMessage(Main.prefix + dispNameGen + " current safe balance: $" + eco.getSafeMoney());
		//Send messages about the player's inventory's value
		player.sendMessage(Main.prefix + "Worth of " + dispNameGen + " inventory: $" + CalcInvValue.calc(eco.getPlayer()));
		return true;
	}

}
