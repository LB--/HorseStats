package me.bdubz4552.horsestats;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.bdubz4552.horsestats.translate.Translate;

/**
 * Preset Message enums with type imbedded. Each type has a note to make recognition easier.
 */
public enum Message {
	//Base messages
	/** HorseStats tag and Green text */
	NORMAL(ChatColor.YELLOW + "[HorseStats] " + ChatColor.GREEN),
	/** HorseStats tag and red text */
	ERROR(ChatColor.YELLOW + "[HorseStats] " + ChatColor.RED),
	/** Green Text */
	STAT(ChatColor.GREEN + ""),

	//Delchest
	/** Chest Deleted. */
	CHEST_DELETED(NORMAL.toString() + Translate.message("chestDelete")),

	//Delname
	/** Custom name erased. */
	NAME_ERASED(NORMAL.toString() + Translate.message("nameErase")),

	//Hspawn
	/** Horse Spawned */
	HORSE(NORMAL.toString() + Translate.message("horseSpawn")),
	/** Donkey Spawned */
	DONKEY(NORMAL.toString() + Translate.message("donkeySpawn")),
	/** Mule Spawned */
	MULE(NORMAL.toString() + Translate.message("muleSpawn")),
	/** Cannot be riding a horse */
	NOT_RIDING(ERROR.toString() + Translate.message("cannotRide")),

	//htp
	/** No horse selected */
	NONE_SELECTED(ERROR.toString() + Translate.message("noneSelected")),
	/** Can't tp between worlds */
	INTER_WORLD(ERROR.toString() + Translate.message("worldTP")),
	/** Teleporting */
	TELEPORTING(NORMAL.toString() + Translate.message("teleporting")),
	/** Teleport failed */
	TELEPORT_FAIL(ERROR.toString() + Translate.message("teleportFail")),

	//Setowner
	/** Changed owner */
	OWNER_CHANGED(NORMAL.toString() + Translate.message("ownerChange")),

	//Setstat
	/** Horses jump no higher than 22 */
	JUMP_HEIGHT(ERROR.toString() + Translate.message("jumpLimit")),

	//Setstyle
	/** Only horses can be modified */
	ONLY_MODIFY_HORSE(ERROR.toString() + Translate.message("onlyHorse")),

	//Slayhorse
	/** He's a magical pony */
	LAUNCH(Translate.message("launch")),
	/** Slain */
	SLAIN(NORMAL.toString() + Translate.message("slain")),

	//Tame
	/** Now own this horse */
	NOW_OWN(NORMAL.toString() + Translate.message("nowOwn")),
	/** Already own this horse */
	ALREADY_OWN(NORMAL.toString() + Translate.message("alreadyOwn")),

	//Untame
	/** Untamed */
	UNTAME(NORMAL.toString() + Translate.message("untame")),

	//Admin listener
	/** Config outdated */
	CONFIG_WARN(NORMAL.toString() + Translate.message("configWarning")),
	/** No speed mode active */
	NO_SPEED_WARN(NORMAL.toString() + Translate.message("noSpeedWarning")),

	//Main listener
	/** Fixed owner-tamed continuity error */
	OWNER_FIX(NORMAL.toString() + Translate.message("ownerFix")),
	/** Horse selected for teleport */
	TELEPORT_SELECTED(NORMAL.toString() + Translate.message("teleportSelected")),
	/** Horse deselected for teleport */
	TELEPORT_DESELECTED(NORMAL.toString() + Translate.message("teleportDeselected")),
	/** Must be tamed */
	TELEPORT_TAME(ERROR.toString() + Translate.message("teleportUntame")),

	//Error Messages
	RIDING(ERROR.toString() + Translate.message("riding")),
	PERMS(ERROR.toString() + Translate.message("permissions")),
	ATTACK(ERROR.toString() + Translate.message("attack")),
	OWNER(ERROR.toString() + Translate.message("owner")),
	STYLE_PARAMS(ERROR.toString() + Translate.message("setstyleArgs")),
	PLAYER(ERROR.toString() + Translate.message("playerNotFound")),
	CONSOLE(ERROR.toString() + Translate.message("console"));

	public final String message;

	private Message(String str) {
		message = str;
	}

	@Override
	public String toString() {
		return message;
	}

	/**
	 * Send a message to a player, with a specific string.
	 * @param p - Player to receive the message.
	 * @param msg - The message to be received.
	 */
	public void send(Player p, String string) {
		p.sendMessage(this + string);
	}

	/**
	 * Sends a message to a player, using one of the predefined Message enums.
	 * @param p - Player to receive the message.
	 */
	public void send(Player p) {
		p.sendMessage(""+this);
	}
}
