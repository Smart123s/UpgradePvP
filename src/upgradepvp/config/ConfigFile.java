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
package upgradepvp.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import upgradepvp.main.Main;

public class ConfigFile {

	public static Plugin plugin;
	private FileConfiguration config;
    private File file;
	private String name;
	final private static String extension = ".yml";
	private static HashMap<String, ConfigFile> configs = new HashMap<String, ConfigFile>();
	
	public ConfigFile(String name) {
		plugin = Main.plugin;
		this.name = name;
		configs.put(name, this);
		setup();
	}
	
	public static ConfigFile get(String name) {
		return configs.get(name);
	}
	
	public void setup() {
        this.file = new File(plugin.getDataFolder(), name + extension);
        this.config = YamlConfiguration.loadConfiguration(file);
        config.options().copyDefaults(true);
        save();
        
        createPluginDataFolder();
        createConfigFile();

	}
	
	public FileConfiguration get() {
		return config;
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(Main.prefix + "An error has occured while saving " + name + extension);
		}
	}
	
	public void reload() {
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	private void createPluginDataFolder() {
		if (!plugin.getDataFolder().exists()) {
        	plugin.getDataFolder().mkdir();
        }
	}
	
	private void createConfigFile() {
		if (!file.exists()) {
        	try {
            	file.createNewFile();
            }
            catch (IOException e) {
            	Bukkit.getServer().getLogger().severe(Main.prefix + "An error has occured while creating " + name + extension);
            }
        }
	}
	
}
