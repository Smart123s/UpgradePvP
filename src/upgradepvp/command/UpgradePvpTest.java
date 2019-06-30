package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.economy.Economy;
import upgradepvp.main.Main;
import upgradepvp.shop.OpenShopItem;

public class UpgradePvpTest implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		OpenShopItem openShopItem = new OpenShopItem();
		Player player = Bukkit.getPlayer(sender.getName());
		if (args.length == 0) {
			player.sendMessage(Main.prefixError + "Not enough arguemnts.");
			return true;
		}
		else if (args[0].equalsIgnoreCase("join")) {
			if (Main.inGame.contains(player)){
				player.sendMessage(Main.prefixError + "You are allready ingame.");
				return true;
			}
			Main.inGame.add(player);
			Economy eco = new Economy(player);
			eco.setCommonMoney(Economy.startingMoney);
			openShopItem.give(player);
		}
		else if (args[0].equalsIgnoreCase("leave")) Main.inGame.remove(player);
		else if (args[0].equalsIgnoreCase("addMoney")) 
			Economy.getEconomyOfPlayer(player).addCommonMoney(Integer.valueOf(args[1]));
		
		
		return true;
	}

}
