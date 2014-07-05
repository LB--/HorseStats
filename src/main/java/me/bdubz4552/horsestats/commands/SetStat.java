package me.bdubz4552.horsestats.commands;

import me.bdubz4552.horsestats.*;
import me.bdubz4552.horsestats.translate.Translate;
import org.bukkit.command.*;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public class SetStat extends HorseStatsCommand {

	public SetStat(HorseStatsMain hsm) {
		super(hsm, "setstat");
	}

	@Override
	public boolean run(Player p, Horse h, String args[]) {
		if (h != null) {
			if (h.getOwner() == p || main.hasGlobalOverride(p)) {
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("health")) {
						double health = Double.parseDouble(args[1]);
						h.setMaxHealth(2 * health);
						Message.NORMAL.send(p, Translate.setstat("healthSetTo") + " " + health + " " + Translate.setstat("hearts"));
					} else if (args[0].equalsIgnoreCase("jump")) {
						double jump = Double.parseDouble(args[1]);
						if (jump > 22) {
							jump = 22;
							Message.JUMP_HEIGHT.send(p);
						}
						h.setJumpStrength(Math.sqrt(jump / 5.5));
						Message.NORMAL.send(p, Translate.setstat("jumpSetTo") + " " + jump + " " + Translate.setstat("blocks"));
					} else {
						Message.ERROR.send(p, Translate.setstat("usage"));
					}
				} else {
					Message.ERROR.send(p, Translate.setstat("usage"));
				}
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
