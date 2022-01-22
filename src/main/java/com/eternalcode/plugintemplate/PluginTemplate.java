package com.eternalcode.plugintemplate;

import com.eternalcode.plugintemplate.commands.binds.CommandInfoBind;
import com.eternalcode.plugintemplate.listeners.ExampleListener;
import com.eternalcode.plugintemplate.utils.ChatUtils;
import com.google.common.base.Stopwatch;
import lombok.Getter;
import net.dzikoysk.funnycommands.FunnyCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.LogPrefix;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;
import panda.std.stream.PandaStream;

import java.util.concurrent.TimeUnit;

@Plugin(name = "PluginTemplate", version = "1.0.1-SNAPSHOT")
@Author("EternalCodeTeam")
@ApiVersion(ApiVersion.Target.v1_17)
@Description("PluginTemplate for creating own projects!")
@LogPrefix("PluginTemplate")

public final class PluginTemplate extends JavaPlugin {

    private static final String version = Bukkit.getServer().getClass().getName().split("\\.")[3];
    @Getter private static PluginTemplate instance;
    @Getter private FunnyCommands funnyCommands;
    private boolean isPaper = false;

    @Override
    public void onEnable() {
        Stopwatch started = Stopwatch.createStarted();

        this.softwareCheck();

        instance = this;

        this.funnyCommands = FunnyCommands.configuration(() -> this)
                .bind(resources -> resources.on(PluginTemplate.class).assignInstance(this))
                .bind(new CommandInfoBind())
                .registerDefaultComponents()
                .commands().install();


        PandaStream.of(
                new ExampleListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        long millis = started.elapsed(TimeUnit.MILLISECONDS);
        this.getLogger().info(ChatUtils.color("&7Successfully loaded EternalCore in " + millis + "ms"));
    }

    public void onDisable() {
        funnyCommands.dispose();
    }

    private void softwareCheck() {
        try {
            Class.forName("com.destroystokyo.paper.VersionHistoryManager$VersionData");
            isPaper = true;
        } catch (ClassNotFoundException classNotFoundException) {
            getLogger().warning(ChatUtils.color("&c&lYour server running on unsupported software, use paper minecraft software and other paper 1.17x forks"));
            getLogger().warning(ChatUtils.color("&c&lDownload paper from https://papermc.io/downloads"));
            getLogger().warning(ChatUtils.color("&6&lWARRING&r &6Supported minecraft version is 1.17-1.18x"));
        }

        if (isPaper) {
            getLogger().info(ChatUtils.color("&a&lYour server running on supported software, congratulations!"));
            getLogger().info(ChatUtils.color("&a&lServer version: &7" + Bukkit.getServer().getVersion()));
        }

        switch (version) {
            case "v1_8_R3", "v1_9_R1", "v1_9_R2", "v1_10_R1", "v1_11_R1", "v1_12_R1", "v1_13_R1", "v1_14_R1", "v1_15_R1", "v1_16_R1" -> Bukkit.getLogger().info("PluginTemplate no longer supports your version, be aware that there may be bugs!");
        }
    }
}