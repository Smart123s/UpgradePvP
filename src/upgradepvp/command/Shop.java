package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.economy.Economy;
import upgradepvp.main.Main;
import upgradepvp.shop.OpenShopItem;

public class Shop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Main.prefixPlain + "Only players can execute this command!");
			return true;
		}
		Player player = Bukkit.getPlayer(sender.getName());
		if (Economy.getEconomy(player) == null || !Economy.getEconomy(player).isInGame()) {
			player.sendMessage(Main.prefixError + "You are not ingame.");
			return true;
		}
		OpenShopItem.give(player);
		player.sendMessage(Main.prefix + "Your shop item has been succesfully restored.");
		return true;
	}
}
