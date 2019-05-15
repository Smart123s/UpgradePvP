package upgradepvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import upgradepvp.main.Main;
import upgradepvp.shop.Economy;
import upgradepvp.shop.OpenShopItem;

public class UpgradePvpTest implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		OpenShopItem openShopItem = new OpenShopItem();
		Player player = Bukkit.getPlayer(sender.getName());
		if (args[0] == null) return false;
		else if (args[0].equalsIgnoreCase("join")) {
			if (Main.inGame.contains(player)){
				player.sendMessage(Main.prefixError + "You are allready ingame.");
				return true;
			}
			Main.inGame.add(player);
			Economy.createEconomyOfPlayer(player);
			Economy.getEconomyOfPlayer(player).addCommonMoney(Economy.startingMoney);
			openShopItem.openShopItem(player);
		}
		else if (args[0].equalsIgnoreCase("leave")) Main.inGame.remove(player);
		
		
		return true;
	}

}
