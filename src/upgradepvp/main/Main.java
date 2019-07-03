/*
    This file is part of UpgradePvP.
	Copyright (C) 2019 Péter Tombor

    UpgradePvP is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UpgradePvP is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UpgradePvP.  If not, see <https://www.gnu.org/licenses/>.
*/
package upgradepvp.main;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import upgradepvp.command.*;
import upgradepvp.command.upvp.UPvPBaseCommand;
import upgradepvp.config.*;
import upgradepvp.map.UPvPMap;

public class Main extends JavaPlugin{
public static String prefixPlain = "[UpgradePvP] ";
public static String prefix = "§b[UpgradePvP] §3";
public static String prefixError = "§c[UpgradePvP] §4";
public static ArrayList<UPvPMap> maps = new ArrayList<UPvPMap>();
Logger log = Bukkit.getLogger();

	public void onEnable() {
		
		log.info(prefixPlain + "Starting UpgradePvP version " + getDescription().getVersion().toString());
		
		//Initialize ConfigFile.plugin
		ConfigFile.plugin = this;
		
		//Create Config Files
		new ConfigFile("config");
		new ConfigFile("price");
		
		//Register commands
		getCommand("UpgradePvpTest").setExecutor(new UpgradePvpTest());
		getCommand("Shop").setExecutor(new Shop());
		getCommand("Balance").setExecutor(new Balance());
		getCommand("Safe").setExecutor(new Safe());
		getCommand("UPvP").setExecutor(new UPvPBaseCommand());
		
		//Register events
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		
		//Add available maps to maps variable
		File mapsFolder = new File(this.getDataFolder().getPath().replace('\\', '/') + "/maps");
		if (!mapsFolder.exists()) mapsFolder.mkdir();
		for (File file : mapsFolder.listFiles())
			maps.add(new UPvPMap(file.getName().replaceAll(".yml", "")));

		log.info(prefixPlain + "Successfully started UpgradePvP version " + getDescription().getVersion().toString());
	}
	
	public void onDisable() {
		log.info(prefixPlain + "Disabling UpgradePvP version " + getDescription().getVersion().toString());
		
		log.info(prefixPlain + "Successfully disabled UpgradePvP version " + getDescription().getVersion().toString());
	}
}
