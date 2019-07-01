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
		//Get the player who executed the command
		Player player = Bukkit.getPlayer(sender.getName());
		//Check if the player is in-game
		if (!Main.inGame.contains(player)) {
			//Notify the player that they are not in-game
			player.sendMessage(Main.prefixError + "You are not ingame.");
			return true;
		}
		//Send messages about the player's common balance
		player.sendMessage(Main.prefix + "Your current balance: $" + Economy.getEconomyOfPlayer(player).getCommonMoney());
		//Send messages about the player's safe balance
		player.sendMessage(Main.prefix + "Your current safe balance: $" + Economy.getEconomyOfPlayer(player).getSafeMoney());
		//Send messages about the player's inventory's value
		player.sendMessage(Main.prefix + "Worth of your inventory: $" + CalcInvValue.calc(player));
		return true;
	}

}
