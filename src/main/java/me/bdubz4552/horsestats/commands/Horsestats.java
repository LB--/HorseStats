package me.bdubz4552.horsestats.commands;

import static org.bukkit.ChatColor.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bdubz4552.horsestats.HorseStatsCommand;
import me.bdubz4552.horsestats.HorseStatsMain;
import me.bdubz4552.horsestats.Message;

public class Horsestats extends HorseStatsCommand implements CommandExecutor {
	static String[] help =
	{ GREEN  + "========================"
	, YELLOW + "HorseStats by 'bdubz4552'"
	, GREEN  + "========================"
	, YELLOW + "Stat Display"
	, GREEN  + "Grab a lead and punch a horse to return a list of statistics:"
	, GREEN  + "-MaxHealth -Health -Jump Height -Speed (Blocks per Second)"
	, GREEN  + "-Can Breed -Is Adult -Owner"
	, YELLOW + "Noteworthy Things:"
	, GREEN  + "1) Horses will NOT take damage from the punch"
	, GREEN  + "2) Speed and jump values are not infinitely precise."
	, GREEN  + "Horse Teleporting"
	, GREEN  + "Grab an ender pearl and punch a horse to select it. The damage will be canceled, and the horse will be selected for teleporting. To teleport the horse, use '/htp' at the desired destination and the horse will teleport to you."
	, YELLOW + "To see HorseStats commands, use '/help horsestats'. If this does not work, contact an administrator."
	};

	public Horsestats(HorseStatsMain horseStatsMain) {
		this.main = horseStatsMain;
	}

	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (command.getName().equalsIgnoreCase("horsestats")) {
				p.sendMessage(help);
			}
		} else {
			sender.sendMessage(""+Message.CONSOLE);
		}
		return true;
	}
}
