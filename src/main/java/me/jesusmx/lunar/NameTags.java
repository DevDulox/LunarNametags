package me.jesusmx.lunar;

import lombok.Getter;
import me.clip.placeholderapi.PlaceholderHook;
import me.jesusmx.lunar.papi.PlaceholderAPI;
import me.jesusmx.lunar.runnable.NameTagRunnable;
import me.jesusmx.lunar.utils.Color;
import me.jesusmx.lunar.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Getter
public final class NameTags extends JavaPlugin {

    private Config config;
    private NameTagRunnable nameTagRunnable;

    @Override
    public void onEnable() {
        this.config = new Config(this, "config");
        this.nameTagRunnable = new NameTagRunnable(this);
        this.nameTagRunnable.runTaskTimerAsynchronously(this, 5L, 10L);
        this.getServer().getConsoleSender().sendMessage(Color.translate("&b[LunarNametags] &ahas been registered"));
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PlaceholderAPI(this).register();
        }

    }
    Class<?> CPClass;
    String serverName  = Bukkit.getServer().getClass().getPackage().getName(),
            serverVersion = serverName.substring(serverName.lastIndexOf(".") + 1);
    public int getPing(Player p) {
        try {
            CPClass = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
            Object CraftPlayer = CPClass.cast(p);

            Method getHandle = CraftPlayer.getClass().getMethod("getHandle", new Class[0]);
            Object EntityPlayer = getHandle.invoke(CraftPlayer, new Object[0]);

            Field ping = EntityPlayer.getClass().getDeclaredField("ping");

            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
