package me.bdubz4552.horsestats;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.bdubz4552.horsestats.commands.*;
import me.bdubz4552.horsestats.event.*;
import me.bdubz4552.horsestats.translate.*;
import net.gravitydevelopment.updater.*;
import net.gravitydevelopment.updater.Updater.*;

public class HorseStatsMain extends JavaPlugin {

	protected Logger log;
	public Translate translate;

	/**
	 * True if an update is available.
	 */
	public boolean updateAvailable = false;
	public String updateName;

	/**
	 * If HorseStats cannot retrieve speed due to mismatched Bukkit versions, this will be true.
	 */
	public boolean noSpeedMode = false;

	/**
	 * Set to true if the config is out of date.
	 */
	public boolean outofdateConfig = false;

	/**
	 * Checks if a player has the global override permission.
	 * @param p - The player to check permissions for.
	 * @return True if has permission, false if not.
	 */
	public boolean hasGlobalOverride(Player p) {
		if (p.hasPermission("HorseStats.globaloverride")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Called on plugin start.
	 */
	public void onEnable() {
		this.log = this.getLogger();
		this.saveDefaultConfig();
		try {
			this.translate = new Translate(this);
		} catch (IOException e) {
			//If the IOException is from the missing file
			if (e.getMessage().equalsIgnoreCase("Translate file not found")) {
				log.info("Creating new 'translate.yml' in '<server root>/plugins/horsestats'");
			//Any other IOException
			} else {
				e.printStackTrace();
			}
		}

		registerCommands();

		getServer().getPluginManager().registerEvents(new AdminNotificationListener(this), this);

		getServer().getPluginManager().registerEvents(new HorseStatsHorseInteractListener(this), this);

		/**
		 * Check if CraftBukkit matches. Warn if not. Register proper event listener based on outcome.
		 */
		if (checkVersion() == false) {
			noSpeedMode = true;
			log.warning("The version of CraftBukkit on this server does not match that of HorseStats.");
			log.warning("The HorseStats config file is reporting that this version of HorseStats, " +
			this.getDescription().getVersion() + " is using " + this.getConfig().getConfigurationSection("information").getString("HorseStats Is Running"));
			log.warning("To avoid full plugin failure, the speed value in the stat display will be disabled.");
			log.warning("To fix this issue, get a HorseStats build that is made for your version of CraftBukkit.");
			getServer().getPluginManager().registerEvents(new HorseStatsNoSpeedEventListener(this), this);
		} else {
			getServer().getPluginManager().registerEvents(new HorseStatsEventListener(this), this);
		}

		/**
		 * Check if config is outdated. Warn if so.
		 *
		 * Config version in config.yml and HorseStats version do not need to match exactly;
		 * if the config is updated, the config version will be updated to match HorseStats version.
		 * Otherwise it will remain the same.
		 */
		if (this.getConfig().getString("information.configVersion") == null || this.getConfig().getDouble("information.configVersion") != 3.03) {
			outofdateConfig = true;
			log.warning("It appears your HorseStats configuration file is out of date.");
			log.warning("Please take note of the settings you have in it, and delete it.");
			log.warning("A new configuration with new settings will generate next time you start or reload your server.");
		}

		/**
		 * Update notifier; only notifies, does not download.
		 */
		if (configBoolean("allowUpdateChecks") == true) {
			Updater updater = new Updater(this, 62378, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
				updateAvailable = true;
				updateName = updater.getLatestName();
				log.info("A new build of HorseStats is available!");
				log.info("You can find " + updater.getLatestName() + " at: https://dev.bukkit.org/bukkit-plugins/horsestats");
			}
		}
	}

	/**
	 * Checks if the server version matches the plugin version.
	 * If classes are found, no problem.
	 * If not, no speed mode is activated.
	 *
	 * When CraftBukkit updates:
	 * 1) Replace imports in Event Handlers <del>and SetStat command</del>
	 * 2) Replace class strings below
	 *
	 * @return Boolean value indicating whether or not CB builds match.
	 */
	private boolean checkVersion() {
		try {
			Class.forName("net.minecraft.server.v1_7_R3.NBTBase");
			Class.forName("org.bukkit.craftbukkit.v1_7_R3.entity.CraftHorse");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	/**
	 * Registers all commands.
	 */
	private void registerCommands() {
		getCommand("horsestats").setExecutor(new Horsestats(this));
		getCommand("htp").setExecutor(new Htp(this, new HorseStatsListenerBase(this)));
		getCommand("setowner").setExecutor(new SetOwner(this));
		getCommand("untame").setExecutor(new Untame(this));
		getCommand("delchest").setExecutor(new Delchest(this));
		getCommand("delname").setExecutor(new Delname(this));
		getCommand("slayhorse").setExecutor(new Slayhorse(this));
		getCommand("hspawn").setExecutor(new Hspawn(this));
		getCommand("setstyle").setExecutor(new SetStyle(this));
		getCommand("setstat").setExecutor(new SetStat(this));
		getCommand("tame").setExecutor(new Tame(this));
	}

	/**
	 * Gets a ConfigurationSection object from the given path.
	 * @param sectionName - The path to get the ConfigurationSection for.
	 * @return A ConfigurationSection object matching the given path.
	 */
	public ConfigurationSection section(String sectionName) {
		ConfigurationSection section = this.getConfig().getConfigurationSection(sectionName);
		return section;
	}

	/**
	 * Returns the boolean value of the specified option node. This method is <b><i>exclusive</i></b>
	 * to the "options" section of the configuration.
	 * @param configBoolean - The boolean to be checked.
	 * @return The boolean value of the specified node.
	 */
	public boolean configBoolean(String configBoolean) {
		if (section("options").getBoolean(configBoolean) == true) {
			return true;
		}
		return false;
	}
}
