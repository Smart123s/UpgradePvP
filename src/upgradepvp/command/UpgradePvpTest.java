package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.economy.Economy;
import upgradepvp.main.Main;

public class UpgradePvpTest implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = Bukkit.getPlayer(sender.getName());
		if (args.length == 0) {
			player.sendMessage(Main.prefixError + "Not enough arguemnts.");
			return true;
		}
		else if (args[0].equalsIgnoreCase("join")) {
			//TODO: Create join
		}
		else if (args[0].equalsIgnoreCase("leave")) ;//TODO: Create leave
		else if (args[0].equalsIgnoreCase("addMoney")) 
			Economy.getEconomy(player).addCommonMoney(Integer.valueOf(args[1]));
		
		
		return true;
	}

}
