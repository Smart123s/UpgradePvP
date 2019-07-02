package upgradepvp.command.upvp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.main.Main;

public class UPvPBaseCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.prefixPlain + "Only players can execute this command!");
			return true;
		} else if (args.length == 0) {
			Bukkit.getPlayer(sender.getName()).sendMessage(Main.prefixError + "/UPvP <create|setspawn|setlobby>");
		}
		//TODO: Check permissions
		Player player = Bukkit.getPlayer(sender.getName());
		if (args[0].equalsIgnoreCase("create")) 
			CreateCommand.run(args, player);
		else if (args[0].equalsIgnoreCase("setspawn")) 
			SetSpawnCommand.run(args, player);
		else if (args[0].equalsIgnoreCase("setlobby")) 
			SetLobbyCommand.run(args, player);
		else if (args[0].equalsIgnoreCase("join")) 
			JoinCommand.run(args, player);
		else if (args[0].equalsIgnoreCase("start")) 
			StartCommand.run(args, player);
		
		
		return true;
	}
}
