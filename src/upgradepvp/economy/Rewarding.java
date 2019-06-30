package upgradepvp.economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import upgradepvp.main.ConfigManager;
import upgradepvp.main.Main;

public class Rewarding {
	ConfigManager config = ConfigManager.getInstance();	
	
	public void onKill(PlayerDeathEvent e){
		//Get who was killed
		Player dead = Bukkit.getPlayer(e.getEntity().getName());
		//Check if the murder is a player
		if (e.getEntity().getKiller() == null) return;
		//Get who was the murder
		Player murder = Bukkit.getPlayer(e.getEntity().getKiller().getName());
		//Check if both the dead player and the murder are in-game
		if (!Main.inGame.contains(dead) || !Main.inGame.contains(murder)) return;
		//Disable item drops
		e.getDrops().clear();
		//Get the Economy of the dead player
		Economy deadEco = Economy.getEconomyOfPlayer(dead);
		//Get the Economy of the murder
		Economy murderEco = Economy.getEconomyOfPlayer(murder);
		//If the dead player has keepInv, then store their items
		if (deadEco.hasKeepInv()) deadEco.storeKeepInvItems();
		//TODO:Improve Calculation
		//Calculate the award of the murder
		int murderAward = (int) Math.round((deadEco.getCommonMoney() + deadEco.getSafeMoney() + CalcInvValue.getInvVal(dead) - CalcInvValue.getInvVal(murder))*0.5);
		//Calculate the remaining money of the dead person
		int deadAward = (int) Math.round((murderEco.getCommonMoney() + murderEco.getSafeMoney() + CalcInvValue.getInvVal(murder) - CalcInvValue.getInvVal(dead))*0.4);
		//If the dead player doesen't have keepinv, give them more money
		if (!deadEco.hasKeepInv()) deadAward += (int) (Math.round(CalcInvValue.getInvVal(dead)*0.25));
		//If the remaining money of the dead player is below the startingMoney, give them the startingMoney
		if (deadAward < Economy.startingMoney) deadAward = Economy.startingMoney;
		//Add murderAward to the murder's common balance
		murderEco.addCommonMoney(murderAward);
		//Set the dead player's common balance to deadAward
		deadEco.setCommonMoney(deadAward);
		//Send a message to the murder about their award
		murder.sendMessage(Main.prefix + "$" + murderAward + " has been added to your account!");
		//Send a message to the dead player about their remaining money
		dead.sendMessage(Main.prefix + "$" + deadAward + " is your new balance!");
	}
	
}
