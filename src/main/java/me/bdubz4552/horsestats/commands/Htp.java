package me.bdubz4552.horsestats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import me.bdubz4552.horsestats.*;
import me.bdubz4552.horsestats.event.HorseStatsListenerBase;

public class Htp extends HorseStatsCommand {

	private HorseStatsListenerBase base;

	public Htp(HorseStatsMain hsm, HorseStatsListenerBase hsl) {
		super(hsm, "htp");
		this.base = hsl;
	}

	/**
	 * Does not work if chunks are not loaded. Attempts at loading chunks were made,
	 * but no success.
	 * @param p - The player who initiated the teleport.
	 */
	@Override
	public boolean run(Player p, Horse _, String[] args) {
		if (base.teleportQueue.get(p.getName()) == null) {
			Message.NONE_SELECTED.send(p);
		} else {
			Horse h = base.teleportQueue.get(p.getName());
			if (main.configBoolean("interWorldTeleport") == false) {
				if (p.getWorld() != h.getWorld()) {
					Message.INTER_WORLD.send(p);
					return false;
				}
			}

			if (h.teleport(p) == true) {
				Message.TELEPORTING.send(p);
			} else {
				Message.TELEPORT_FAIL.send(p);
			}

			base.teleportQueue.remove(p.getName());
			return true;
		}
		return false;
	}
}
