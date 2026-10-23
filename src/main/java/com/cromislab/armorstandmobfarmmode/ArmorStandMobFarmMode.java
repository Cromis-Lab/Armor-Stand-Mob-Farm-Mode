java
package com.cromislab.armorstandmobfarmmode;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.RayTraceResult;

import java.util.Locale;

public class ArmorStandMobFarmMode extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("ArmorStandFarm").setExecutor((sender, command, label, args) -> {

            if (!(sender instanceof Player player)) {
                sender.sendMessage("This command can only be used by a player.");
                return true;
            }

            if (args.length != 1 || !args[0].equalsIgnoreCase("Toggle")) {
                player.sendMessage("Usage: /ArmorStandFarm Toggle");
                return true;
            }

            RayTraceResult result = player.getWorld().rayTraceEntities(
                player.getEyeLocation(),
                player.getEyeLocation().getDirection(),
                5.0,
                0.2,
                entity -> entity instanceof ArmorStand
            );

            Entity target = result == null ? null : result.getHitEntity();

            if (!(target instanceof ArmorStand stand)) {
                player.sendMessage("Look at an armor stand within 5 blocks.");
                return true;
            }

            boolean newState = !stand.isMarkedInvulnerable();
            stand.setInvulnerable(newState);

            player.sendMessage(
                "Armor stand invulnerability: "
                + (newState ? "ON" : "OFF")
            );

            return true;
        });
    }
}
