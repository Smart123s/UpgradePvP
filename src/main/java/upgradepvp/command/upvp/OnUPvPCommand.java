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
package upgradepvp.command.upvp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.main.Main;

public class OnUPvPCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.prefixPlain + "Only players can execute this command!");
			return true;
		} else if (args.length == 0) {
			Bukkit.getPlayer(sender.getName()).sendMessage(Main.prefixError + "/UPvP <join|leave|start|create|setspawn|setlobby>");
		}
		Player player = Bukkit.getPlayer(sender.getName());

		for (UPvPCommand cmd : UPvPCommand.getCommands()) {
			if (cmd.getName().equalsIgnoreCase(args[0])) {
				cmd.run(args, player);
				break;
			}
		}
		
		return true;
	}
	
}
