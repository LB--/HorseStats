package me.bdubz4552.horsestats.commands;

import java.util.Random;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;

import me.bdubz4552.horsestats.HorseStatsCommand;
import me.bdubz4552.horsestats.HorseStatsMain;
import me.bdubz4552.horsestats.Message;
import me.bdubz4552.horsestats.translate.Translate;

public class Hspawn extends HorseStatsCommand {

	public Hspawn(HorseStatsMain hsm) {
		super(hsm, "hspawn");
	}

	@Override
	public boolean run(Player p, Horse h, String[] args) {
		if (h == null) {
			Variant v = null;
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("donkey")) {
					v = Variant.DONKEY;
					Message.DONKEY.send(p);
				} else if (args[0].equalsIgnoreCase("mule")) {
					v = Variant.MULE;
					Message.MULE.send(p);
				} else {
					Message.NORMAL.send(p, Translate.hspawn("usage"));
				}
			} else {
				v = Variant.HORSE;
			}
			h = (Horse) p.getWorld().spawnEntity(p.getLocation(), EntityType.HORSE);
			h.setAdult();
			h.setVariant(v);
			if (v == Variant.HORSE) {
				Random rand = new Random();
				Color[] c = {Color.BLACK, Color.BROWN, Color.CHESTNUT, Color.CREAMY, Color.DARK_BROWN, Color.GRAY, Color.WHITE};
				Style[] s = {Style.BLACK_DOTS, Style.NONE, Style.WHITE, Style.WHITE_DOTS, Style.WHITEFIELD};
				int x = rand.nextInt(7);
				int y = rand.nextInt(5);
				h.setColor(c[x]);
				h.setStyle(s[y]);
				Message.HORSE.send(p);
			}
			return true;
		} else {
			Message.NOT_RIDING.send(p);
		}
		return false;
	}
}
