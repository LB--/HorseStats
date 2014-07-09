package me.bdubz4552.horsestats.translate;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.bdubz4552.horsestats.HorseStatsMain;

public class Translate {

	private File file;
	private MemoryConfiguration translateFile;

	private static ConfigurationSection adminNotificationListener;
	private static ConfigurationSection horseStatsCommand;
	private static ConfigurationSection horseStatsEventListener;
	private static ConfigurationSection message;
	private static ConfigurationSection setStat;
	private static ConfigurationSection hspawn;
	private static ConfigurationSection setOwner;

	/**
	 * Constructor for translation. Instntiates everything.
	 * @param main - HorseStatsMain
	 * @throws IOException - Thrown if translate.yml is not found.
	 */
	public Translate(HorseStatsMain main) throws IOException{
		this.file = new File("plugins/horsestats/translate.yml");

		if (file.exists()) {
			YamlConfiguration yc = YamlConfiguration.loadConfiguration(this.file);
			this.translateFile = yc;
			adminNotificationListener = translateFile.getConfigurationSection("AdminNotificationListener");
			horseStatsCommand = translateFile.getConfigurationSection("HorseStatsCommand");
			horseStatsEventListener = translateFile.getConfigurationSection("HorseStatsEventListener");
			message = translateFile.getConfigurationSection("Message");
			setStat = translateFile.getConfigurationSection("SetStat");
			setOwner = translateFile.getConfigurationSection("SetOwner");
			hspawn = translateFile.getConfigurationSection("Hspawn");
		} else {
			main.saveResource("translate.yml", false);
			throw new IOException("translate file not found");
		}
	}

	/**
	 * Returns the translated String for AdminNotificationListener
	 * @param path - The YAML header to check
	 * @return The translated String at the specified YAML header
	 */
	public static final String admin(String path) {
		return adminNotificationListener.getString(path);
	}

	/**
	 * Returns the translated String for HorseStatsCommand
	 * @param path - The YAML header to check
	 * @return The translated String at the specified YAML header
	 */
	public static final String command(String path) {
		return horseStatsCommand.getString(path);
	}

	/**
	 * Returns the translated String for HorseStatsEventListener
	 * @param path - The YAML header to check
	 * @return The translated String at the specified YAML header
	 */
	public static final String event(String path) {
		return horseStatsEventListener.getString(path);
	}

	/**
	 * Returns the translated String for Message
	 * @param path - The YAML header to check
	 * @return The translated String at the specified YAML header
	 */
	public static final String message(String path) {
		String translation = message.getString(path);
		return translation != null ? translation : ("Missing translation \""+path+"\"");
	}

	/**
	 * Returns the translated String for SetStat
	 * @param path - The YAML header to check
	 * @return The translated String at the specified YAML header
	 */
	public static final String setstat(String path) {
		return setStat.getString(path);
	}

	/**
	 * Returns the translated String for SetOwner
	 * @param path - The YAML header to check
	 * @return The translated String at the specified YAML header
	 */
	public static final String setowner(String path) {
		return setOwner.getString(path);
	}

	/**
	 * Returns the translated String for Hspawn
	 * @param path - The YAML header to check
	 * @return The translated String at the specified YAML header
	 */
	public static final String hspawn(String path) {
		return hspawn.getString(path);
	}
}
