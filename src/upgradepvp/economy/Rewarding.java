package upgradepvp.economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import upgradepvp.main.ConfigManager;
import upgradepvp.main.Main;

public class Rewarding {
	ConfigManager config = ConfigManager.getInstance();	
	
	 public void onKill(PlayerDeathEvent e){
	 Player dead = Bukkit.getPlayer(e.getEntity().getName());
	 if (e.getEntity().getKiller() == null) return;
	 Player murder = Bukkit.getPlayer(e.getEntity().getKiller().getName());
	 if (!Main.inGame.contains(dead) || !Main.inGame.contains(murder)) return;
	 e.getDrops().clear();
	 Economy deadEco = Economy.getEconomyOfPlayer(dead);
	 Economy murderEco = Economy.getEconomyOfPlayer(murder);
	 if (deadEco.hasKeepInv()) deadEco.storeKeepInvItems();
	 //TODO:Improve Calculation
	 int murderAward = (int) Math.round((((float)(CalcInvValue.getInvVal(dead)+1)/(float)(CalcInvValue.getInvVal(murder)+1)*0.2)*(deadEco.getCommonMoney() + deadEco.getSafeMoney() + CalcInvValue.getInvVal(dead))));
	 int deadAward = (int) Math.round((deadEco.getCommonMoney() + murderEco.getCommonMoney() + murderEco.getSafeMoney() + CalcInvValue.getInvVal(murder))*0.4);
	 if (!deadEco.hasKeepInv()) deadAward += (int) (Math.round(CalcInvValue.getInvVal(dead)*0.25));
	 if (deadAward < Economy.startingMoney) deadAward = Economy.startingMoney;
	 murderEco.addCommonMoney(murderAward);
	 deadEco.setCommonMoney(deadAward);
	 murder.sendMessage(Main.prefix + "$" + murderAward + " has been added to your account!");
	 dead.sendMessage(Main.prefix + "$" + deadAward + " is your new balance!");
	 }
	
}
