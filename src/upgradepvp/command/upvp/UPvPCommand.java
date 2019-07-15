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

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class UPvPCommand{

	private static ArrayList<UPvPCommand> commands = new ArrayList<UPvPCommand>();
	private String name;
	
	protected UPvPCommand(String name) {
		this.name = name;
		commands.add(this);
	}
	
	public static void setup() {
		new CreateCommand();
		new JoinCommand();
		new SetLobbyCommand();
		new SetSpawnCommand();
		new StartCommand();
	}
	
	public String getName() {
		return name;
	}
	
	public void run(String[] args, Player player){ }
	
	public static ArrayList<UPvPCommand> getCommands() {
		return commands;
	}
	
}
