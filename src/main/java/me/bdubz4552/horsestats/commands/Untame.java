package me.bdubz4552.horsestats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.bdubz4552.horsestats.HorseStatsCommand;
import me.bdubz4552.horsestats.HorseStatsMain;
import me.bdubz4552.horsestats.Message;

public class Untame extends HorseStatsCommand {

	public Untame(HorseStatsMain hsm) {
		super(hsm, "untame");
	}

	@Override
	public boolean run(Player p, Horse h, String[] args) {
		if (h != null) {
			if (h.getOwner() == p || main.hasGlobalOverride(p)) {
				h.eject();
				h.setOwner(null);
				h.setTamed(false);
				if (h.getInventory().getSaddle() != null) {
					ItemStack stack = h.getInventory().getSaddle();
					h.getInventory().setSaddle(null);
					h.getWorld().dropItemNaturally(h.getLocation(), stack);
				}
				Message.NORMAL.send(p, "This horse is no longer tamed.");
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
