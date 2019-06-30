package upgradepvp.economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import upgradepvp.main.ConfigManager;
import upgradepvp.main.Main;

public class Rewarding {
	ConfigManager config = ConfigManager.getInstance();	
	
	public void onKill(PlayerDeathEvent e){
		//Gets who was killed
		Player dead = Bukkit.getPlayer(e.getEntity().getName());
		//Checks, if the murder is a player
		if (e.getEntity().getKiller() == null) return;
		//Gets who was the murder
		Player murder = Bukkit.getPlayer(e.getEntity().getKiller().getName());
		//Checks if both the dead player and the murder are ingame
		if (!Main.inGame.contains(dead) || !Main.inGame.contains(murder)) return;
		//Disables Itemdrops
		e.getDrops().clear();
		//Gets the Economy of the dead player
		Economy deadEco = Economy.getEconomyOfPlayer(dead);
		//Gets the Economy of the murder
		Economy murderEco = Economy.getEconomyOfPlayer(murder);
		//If the dead player has keepInv, then stores their items
		if (deadEco.hasKeepInv()) deadEco.storeKeepInvItems();
		//TODO:Improve Calculation
		//Calculates the award of the murder
		int murderAward = (int) Math.round((((float)(CalcInvValue.getInvVal(dead)+1)/(float)(CalcInvValue.getInvVal(murder)+1)*0.2)*(deadEco.getCommonMoney() + deadEco.getSafeMoney() + CalcInvValue.getInvVal(dead))));
		//Calculates the remaining money of the dead person
		int deadAward = (int) Math.round((deadEco.getCommonMoney() + murderEco.getCommonMoney() + murderEco.getSafeMoney() + CalcInvValue.getInvVal(murder))*0.4);
		//If the dead player doesen't have keepinv, it gives them more money
		if (!deadEco.hasKeepInv()) deadAward += (int) (Math.round(CalcInvValue.getInvVal(dead)*0.25));
		//If the remaining money of the dead player is below the startingMoney, it gives them the startingMoney
		if (deadAward < Economy.startingMoney) deadAward = Economy.startingMoney;
		//Adds murderAward to the murder's common balance
		murderEco.addCommonMoney(murderAward);
		//Sets the dead player's common balance to deadAward
		deadEco.setCommonMoney(deadAward);
		//Sends a message to the murder about their award
		murder.sendMessage(Main.prefix + "$" + murderAward + " has been added to your account!");
		//Sends a message to the dead player about their remaining money
		dead.sendMessage(Main.prefix + "$" + deadAward + " is your new balance!");
	}
	
}
