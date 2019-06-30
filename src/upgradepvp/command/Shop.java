package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.main.Main;
import upgradepvp.shop.OpenShopItem;

public class Shop implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		OpenShopItem openShopItem = new OpenShopItem();
		Player player = Bukkit.getPlayer(sender.getName());
		if (!Main.inGame.contains(player)) {
			player.sendMessage(Main.prefixError + "You are not ingame.");
			return true;
		}
		openShopItem.give(player);
		player.sendMessage(Main.prefix + "Your shop item has been succesfully restored.");
		return true;
	}
}
