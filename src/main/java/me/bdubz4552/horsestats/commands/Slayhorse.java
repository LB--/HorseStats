package me.bdubz4552.horsestats.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import me.bdubz4552.horsestats.HorseStatsCommand;
import me.bdubz4552.horsestats.HorseStatsMain;
import me.bdubz4552.horsestats.Message;

public class Slayhorse extends HorseStatsCommand {

	public Slayhorse(HorseStatsMain hsm) {
		super(hsm, "slayhorse");
	}

	@Override
	public boolean run(Player p, Horse h, String[] args) {
		if (h != null) {
			h.eject();
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("launch") && p.hasPermission("HorseStats.slayhorse.launch")) {
					Vector vec = new Vector(0, 6, 0);
					h.setVelocity(vec);
					p.chat(Message.LAUNCH.toString());
					Location loc = new Location(h.getWorld(), h.getLocation().getX(), 256, h.getLocation().getZ());
					h.getWorld().strikeLightning(loc);
				}
			}
			h.setHealth(0);
			Message.SLAIN.send(p);
			return true;
		} else {
			Message.RIDING.send(p);
		}
		return false;
	}
}
