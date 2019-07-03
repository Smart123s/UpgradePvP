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

import org.bukkit.entity.Player;

import upgradepvp.main.Main;
import upgradepvp.map.UPvPMap;

public class StartCommand {

	public static void run(String[] args, Player player) {
		if (args.length == 2) {
			if (!UPvPMap.exists(args[1])) {
				player.sendMessage(Main.prefixError + "Map " + args[1] + " does not exist!");
				return;
			}
			UPvPMap.get(args[1]).startGame();
		} else {
			player.sendMessage(Main.prefixError + "/UPvP start <map>");
		}
	}
	
}
