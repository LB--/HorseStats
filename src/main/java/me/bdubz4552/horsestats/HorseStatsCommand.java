package me.bdubz4552.horsestats;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

public abstract class HorseStatsCommand implements CommandExecutor {

	protected final HorseStatsMain main;
	private final String command;
	public HorseStatsCommand(HorseStatsMain hsm, String cmd) {
		main = hsm;
		command = cmd;
	}

	public boolean permCheck(Player player, String permission) {
		if (player.hasPermission("HorseStats." + permission)) {
			return true;
		} else {
			Message.PERMS.send(player);
			return false;
		}
	}

	@Override
	public final boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Horse h = null;
			if (p.isInsideVehicle()) {
				if (p.getVehicle() instanceof Horse) {
					h = (Horse) p.getVehicle();
				}
			}
			if (cmd.getName().equalsIgnoreCase(command)) {
				if (this.permCheck(p, /**/command/**/)) { ////////
					return this.run(p, h, args);
				}
			}
		} else {
			sender.sendMessage(""+Message.CONSOLE);
		}
		return false;
	}

	public abstract boolean run(Player p, Horse h, String[] args);
}
