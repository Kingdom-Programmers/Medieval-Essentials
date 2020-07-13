// Permissions:
// 'me.fly'
// 'me.fly.others'

package essentialsystem.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class FlyCommand {

    public void toggleFlight(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // if its the fly speed (number) command
            if (args.length > 0 && args.length < 3) {

                if (player.hasPermission("medievalessentials.fly.speed") || player.hasPermission("medievalessentials.default")) {
                    // if not enough arguments
                    if (args.length == 1 && args[0].equalsIgnoreCase("speed")) {
                        player.sendMessage(ChatColor.RED + "Usage: /fly speed (number)");
                        return;
                    }

                    if (args[0].equalsIgnoreCase("speed")) {

                        if (args.length > 1) {

                            int speed = Integer.parseInt(args[1]);

                            player.setFlySpeed(speed);

                        }

                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'medievalessentials.fly.speed");
                    return;
                }

            }

            if (args.length == 0) {
                if (player.hasPermission("me.fly") || player.hasPermission("me.admin")) {
                    // toggle player's flight
                    player.setAllowFlight(!player.getAllowFlight());
                    if (player.getAllowFlight()) {
                        player.sendMessage(ChatColor.GREEN + "Flight enabled!");
                        return;
                    }
                    else {
                        player.sendMessage(ChatColor.GREEN + "Flight disabled!");
                        return;
                    }
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.fly' permission to use this command.");
                }

            }
            else {
                if (player.hasPermission("me.fly.others") || player.hasPermission("me.admin")) {
                    try {
                        Player target = getServer().getPlayer(args[0]);
                        target.setAllowFlight(!target.getAllowFlight());
                        if (target.getAllowFlight()) {
                            if (!player.getName().equalsIgnoreCase(target.getName())) {

                            }
                            target.sendMessage(ChatColor.GREEN + "Flight enabled!");
                            if (!player.getName().equalsIgnoreCase(target.getName())) {
                                player.sendMessage("Flight enabled for " + target.getName());
                            }

                            return;
                        }
                        else {
                            target.sendMessage(ChatColor.GREEN + "Flight disabled!");
                            if (!player.getName().equalsIgnoreCase(target.getName())) {
                                player.sendMessage("Flight disabled for " + target.getName());
                            }
                            return;
                        }
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "That player isn't online.");
                        return;
                    }
                }
                else {
                    sender.sendMessage("Sorry! You need the 'me.fly.others' permission to use this command.");
                }
            }
        }

    }

}
