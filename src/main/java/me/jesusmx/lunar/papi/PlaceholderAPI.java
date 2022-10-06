package me.jesusmx.lunar.papi;

import com.lunarclient.bukkitapi.LunarClientAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.jesusmx.lunar.NameTags;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class PlaceholderAPI extends PlaceholderExpansion {


    private final NameTags instance;

    public PlaceholderAPI(NameTags instance) {
        this.instance = instance;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "nametag";
    }

    @Override
    public @NotNull String getAuthor() {
        return null;
    }

    @Override
    public @NotNull String getVersion() {
        return null;
    }
    @Override
    public boolean canRegister() {
        return true;
    }
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("name")){
            return player.getName();
        }
        if(params.equalsIgnoreCase("ping")){
            Player p = player.getPlayer();
            return instance.getPing(p)+"ms";
        }
        return null;
    }

}
