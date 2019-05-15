package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.main.Main;
import upgradepvp.shop.Economy;
import upgradepvp.shop.Rewarding;

public class Balance implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = Bukkit.getPlayer(sender.getName());
		if (!Main.inGame.contains(player)) {
			player.sendMessage(Main.prefixError + "You are not ingame.");
			return true;
		}
		player.sendMessage(Main.prefix + "Your current balance: $" + Economy.getEconomyOfPlayer(player).getCommonMoney());
		player.sendMessage(Main.prefix + "Your current safe balance: $" + Economy.getEconomyOfPlayer(player).getSafeMoney());
		player.sendMessage(Main.prefix + "Worth of your inventory: $" + new Rewarding().getInventoryValue(player));
		return true;
	}

}
