package Primal.NoStrength.com;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


public class Primal extends JavaPlugin implements Listener{

	public static ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "=====================");
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "NoStrength has been Enabled!");
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "=====================");
	}
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "=====================");
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "NoStrength has been Disabled!");
		Bukkit.getConsoleSender().sendMessage(ChatColor.WHITE + "=====================");
	}



	@EventHandler
	public void onInventoryLook(InventoryInteractEvent e) {
		for(ItemStack item : items) {
			if(e.getInventory().contains(item)) {
				Player p = (Player) e.getView().getPlayer();
				p.getInventory().remove(item);
				p.sendMessage(ChatColor.RED + "You cannot pickup or use Strength Potions!");
			}
		}
	}


	@EventHandler
	public void onInventoryLook(PlayerPickupItemEvent e) {

		for(ItemStack item : items) {
			if(e.getPlayer().getInventory().contains(item)) {
				Player p = (Player) e.getPlayer().getInventory();
				e.setCancelled(true);
				p.sendMessage(ChatColor.RED + "You cannot pickup or use Strength Potions!");
			}
		}
	}
	@EventHandler
	public void onDispenseItem(BlockDispenseEvent e) {
		for(ItemStack item : items) {
			if(e.getItem().equals(item)) {
				e.setItem(new ItemStack(Material.AIR));
			}
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("nostrength")) {
			items.add(new ItemStack(Material.POTION, (short) 8201));
			items.add(new ItemStack(Material.POTION, (short) 8265));
			items.add(new ItemStack(Material.POTION, (short) 8233));
			items.add(new ItemStack(Material.POTION, (short) 8297));
			items.add(new ItemStack(Material.POTION, (short) 16393));
			items.add(new ItemStack(Material.POTION, (short) 16457));
			items.add(new ItemStack(Material.POTION, (short) 16425));
			items.add(new ItemStack(Material.POTION, (short) 16489));
			items.add(new ItemStack(Material.POTION, (short) 8201));
			p.sendMessage(ChatColor.YELLOW + "You have enabled NoStrength! Players can no longer pickup/use/ dispence, or drop strength potions!");
		}
		return false;
	}
}
