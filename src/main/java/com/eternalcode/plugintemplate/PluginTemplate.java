package com.eternalcode.plugintemplate;

import com.eternalcode.plugintemplate.listeners.PlayerJoinListener;
import com.eternalcode.plugintemplate.scheduler.BukkitSchedulerImpl;
import com.eternalcode.plugintemplate.scheduler.Scheduler;
import com.google.common.base.Stopwatch;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.LogPrefix;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import panda.std.stream.PandaStream;

import java.util.concurrent.TimeUnit;

@Plugin(name = "PluginTemplate", version = "1.0.0-SNAPSHOT")
@Author("EternalCodeTeam")
@ApiVersion(ApiVersion.Target.v1_17)
@Description("PluginTemplate for creating own projects!")
@LogPrefix("PluginTemplate")

public class PluginTemplate extends JavaPlugin {

    private static final String VERSION = Bukkit.getServer().getClass().getName().split("\\.")[3];

    @Getter private Scheduler scheduler;
    private boolean isPaper = false;

    // TODO: Dodać jakieś super przydatne Utile i wgl
    // TODO: Dodać impl litecommands i jakąś example komende

    @Override
    public void onEnable() {
        Stopwatch started = Stopwatch.createStarted();

        this.softwareCheck();
        this.scheduler = new BukkitSchedulerImpl(this);

        PandaStream.of(
            new PlayerJoinListener()
        ).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, this));

        long millis = started.elapsed(TimeUnit.MILLISECONDS);
        this.getLogger().info("Successfully loaded EternalCore in " + millis + "ms");
    }

    @Override
    public void onDisable() {

    }

    private void softwareCheck() {
        try {
            Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData");
            this.isPaper = true;
        } catch (ClassNotFoundException classNotFoundException) {
            this.getLogger().warning("Your server running on unsupported software, use paper minecraft software and other paper 1.17x forks");
            this.getLogger().warning("Download paper from https://papermc.io/downloads");
            this.getLogger().warning("WARRING: Supported minecraft version is 1.17-1.18x");
        }

        if (this.isPaper) {
            this.getLogger().info("Your server running on supported software, congratulations!");
            this.getLogger().info("Server version: " + this.getServer().getVersion());
        }

        switch (VERSION) {
            case "v1_8_R1", "v1_8_R2", "v1_8_R3", "v1_9_R1", "v1_9_R2", "v1_10_R1", "v1_11_R1", "v1_12_R1", "v1_13_R1", "v1_13_R2", "v1_14_R1", "v1_15_R1", "v1_16_R1" -> this.getLogger().info("EternalCore no longer supports your version, be aware that there may be bugs!");
        }
    }
}
