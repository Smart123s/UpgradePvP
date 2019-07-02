package upgradepvp.command.upvp;

import org.bukkit.entity.Player;

import upgradepvp.main.Main;
import upgradepvp.map.UPvPMap;

public class JoinCommand {

	public static void run(String[] args, Player player) {
		if (args.length == 2) {
			if (!UPvPMap.exists(args[1])) {
				player.sendMessage(Main.prefixError + "Map " + args[1] + " does not exist!");
				return;
			}
			UPvPMap.get(args[1]).join(player);
		} else {
			player.sendMessage(Main.prefixError + "/UPvP join <map>");
		}
	}
	
}
