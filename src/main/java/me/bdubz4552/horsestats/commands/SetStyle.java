package me.bdubz4552.horsestats.commands;

import static org.bukkit.ChatColor.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;

import me.bdubz4552.horsestats.HorseStatsCommand;
import me.bdubz4552.horsestats.HorseStatsMain;
import me.bdubz4552.horsestats.Message;

public class SetStyle extends HorseStatsCommand {

	public SetStyle(HorseStatsMain hsm) {
		super(hsm, "setstyle");
	}

	@Override
	public boolean run(Player p, Horse h, String[] args) {
		if (h != null) {
			if (h.getOwner() == p || main.hasGlobalOverride(p)) {
				if (h.getVariant() == Variant.HORSE) {
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("color")) {
							if (args[1].equalsIgnoreCase("black")) {
								h.setColor(Color.BLACK);
							}
							else if (args[1].equalsIgnoreCase("brown")) {
								h.setColor(Color.BROWN);
							}
							else if (args[1].equalsIgnoreCase("chestnut")) {
								h.setColor(Color.CHESTNUT);
							}
							else if (args[1].equalsIgnoreCase("creamy")) {
								h.setColor(Color.CREAMY);
							}

							else if (args[1].equalsIgnoreCase("darkbrown")) {
								h.setColor(Color.DARK_BROWN);
							}
							else if (args[1].equalsIgnoreCase("gray")) {
								h.setColor(Color.GRAY);
							}
							else if (args[1].equalsIgnoreCase("black")) {
								h.setColor(Color.WHITE);
							} else {
								Message.STYLE_PARAMS.send(p);
							}
						}
						if (args[0].equalsIgnoreCase("style")) {
							if (args[1].equalsIgnoreCase("blackdots")) {
								h.setStyle(Style.BLACK_DOTS);
							}
							else if (args[1].equalsIgnoreCase("none")) {
								h.setStyle(Style.NONE);
							}
							else if (args[1].equalsIgnoreCase("white")) {
								h.setStyle(Style.WHITE);
							}
							else if (args[1].equalsIgnoreCase("whitedots")) {
								h.setStyle(Style.WHITE_DOTS);
							}
							else if (args[1].equalsIgnoreCase("whitefield")) {
								h.setStyle(Style.WHITEFIELD);
							} else {
								Message.STYLE_PARAMS.send(p);
							}
						}
					} else if (args.length == 1){
						if (args[0].equals("?")) {
							setstatHelp(p);
						} else {
							Message.STYLE_PARAMS.send(p);
						}
					} else {
						Message.STYLE_PARAMS.send(p);
					}
				} else {
					Message.ONLY_MODIFY_HORSE.send(p);
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
	public void setstatHelp(Player p) {
		String[] styleHelp =
		{ GREEN  + "========================"
		, YELLOW + "Help for /setstyle"
		, GREEN  + "========================"
		, YELLOW + "Usage: /setstyle <color|style> <value>"
		, YELLOW + "Values for styles:"
		, GREEN  + "-none -blackdots -whitedots -white -whitefield"
		, YELLOW + "Values for color:"
		, GREEN  + "-black -brown -chestnut -creamy -darkbrown -gray -black"
		};
		p.sendMessage(styleHelp);
	}
}
