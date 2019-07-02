package upgradepvp.economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import upgradepvp.main.Main;

public class Rewarding {
	
	public void onKill(PlayerDeathEvent e){
		//Get who was killed
		Player dead = Bukkit.getPlayer(e.getEntity().getName());
		//Check if the murder is a player
		if (e.getEntity().getKiller() == null) return;
		//Get who was the murder
		Player murder = Bukkit.getPlayer(e.getEntity().getKiller().getName());
		
		//Get the Economy of the dead player
		Economy deadEco = Economy.getEconomy(dead);
		//Get the Economy of the murder
		Economy murderEco = Economy.getEconomy(murder);
		
		//Check if both the dead player and the murder are in-game
		if (deadEco == null || murderEco == null || !deadEco.isInGame() || !murderEco.isInGame()) return;
		//Disable item drops
		e.getDrops().clear();
		//If the dead player has keepInv, then store their items
		if (deadEco.hasKeepInv()) deadEco.storeKeepInvItems();
		
		//Get the value of the inventory of both players
		final int murderInv = CalcInvValue.calc(murder);
		final int deadInv = CalcInvValue.calc(dead);
		//Calculate the award of the murder
		int murderReward = (int) Math.round((deadEco.getCommonMoney() + deadEco.getSafeMoney() + deadInv - murderInv)*0.5);
		//Calculate the remaining money of the dead person
		int deadReward = (int) Math.round((murderEco.getCommonMoney() + murderEco.getSafeMoney() + murderInv - deadInv)*0.4)-deadEco.getSafeMoney();
		//If the dead player doesen't have keepinv, give them more money
		if (!deadEco.hasKeepInv()) deadReward += (int) (Math.round(CalcInvValue.calc(dead)*0.25));
		//If the remaining money of the dead player is below the startingMoney, give them the startingMoney
		if (deadReward < Economy.startingMoney) deadReward = Economy.startingMoney;
		//Make sure the murderRewrad isn't negative
		if (murderReward < 0) murderReward = 0;
		//Add murderReward to the murder's common balance
		murderEco.addCommonMoney(murderReward);
		//Set the dead player's common balance to deadReward
		deadEco.setCommonMoney(deadReward);
		//Send a message to the murder about their Reward
		murder.sendMessage(Main.prefix + "$" + murderReward + " has been added to your account!");
		//Send a message to the dead player about their remaining money
		dead.sendMessage(Main.prefix + "$" + deadReward + " is your new balance!");
	}
	
}
