package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.economy.Economy;
import upgradepvp.main.Main;

public class Safe implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = Bukkit.getServer().getPlayer(sender.getName());
		if (!Main.inGame.contains(player)) {
			player.sendMessage(Main.prefixError + "You are not ingame.");
			return true;
		}
		Economy eco = Economy.getEconomyOfPlayer(player);
		if (args[0].equalsIgnoreCase("all")) {
			eco.moveToSafeMoney(eco.getCommonMoney());
			player.sendMessage(Main.prefix + "$" + eco.getCommonMoney() + " has been taken from your Common Balance.");
			player.sendMessage(Main.prefix + "$" + (int) (eco.getCommonMoney()*0.6) + " has been added to your Safe Balance.");
			return true;
		}
		else if (!eco.hasEnoughCommon(Integer.valueOf(args[0]))) {
			player.sendMessage(Main.prefixError + "You do not have enough money to do that!");
			return true;
		}
		eco.moveToSafeMoney(Integer.valueOf(args[0]));
		player.sendMessage(Main.prefix + "$" + args[0] + " has been taken from your Common Balance.");
		player.sendMessage(Main.prefix + "$" + (int) (Integer.valueOf(args[0])*0.6) + " has been added to your Safe Balance.");
		return true;
	}

}
