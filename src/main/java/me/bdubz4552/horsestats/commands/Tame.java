package me.bdubz4552.horsestats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import me.bdubz4552.horsestats.HorseStatsCommand;
import me.bdubz4552.horsestats.HorseStatsMain;
import me.bdubz4552.horsestats.Message;

public class Tame extends HorseStatsCommand {

	public Tame(HorseStatsMain hsm) {
		super(hsm, "tame");
	}

	@Override
	public boolean run(Player p, Horse h, String[] args) {
		if (h != null) {
			if (h.getOwner() == null) {
				h.setOwner(p);
				Message.NOW_OWN.send(p);
			} else if (h.getOwner() == p) {
				Message.ALREADY_OWN.send(p);
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
