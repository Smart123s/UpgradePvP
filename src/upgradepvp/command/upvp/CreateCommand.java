package upgradepvp.command.upvp;

import org.bukkit.entity.Player;

import upgradepvp.main.Main;
import upgradepvp.map.UPvPMap;

public class CreateCommand {
	
	public static void run(String[] args, Player player) {
		if (args.length == 2) {
			if (UPvPMap.exists(args[1])) {
				player.sendMessage(Main.prefixError + "A map with the same name allready exists!");
				return;
			}
			Main.maps.add(new UPvPMap(args[1]));
			player.sendMessage(Main.prefix + "Successfully created the Map called " + args[1]);
		} else {
			player.sendMessage(Main.prefixError + "/UPvP create <name>");
		}
	}
	
}
