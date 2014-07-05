package me.bdubz4552.horsestats.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import me.bdubz4552.horsestats.HorseStatsCommand;
import me.bdubz4552.horsestats.HorseStatsMain;
import me.bdubz4552.horsestats.Message;

public class Delname extends HorseStatsCommand {

	public Delname(HorseStatsMain hsm) {
		super(hsm, "delname");
	}

	@Override
	public boolean run(Player p, Horse h, String[] args) {
		if (h != null) {
			if (h.getOwner() == p || main.hasGlobalOverride(p)) {
				h.setCustomName(null);
				Message.NAME_ERASED.send(p);
			} else {
				Message.OWNER.send(p);
			}
			return true;
		} else {
			Message.RIDING.send(p);
		}
		return false;
	}
}
