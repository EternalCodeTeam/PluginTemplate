package com.eternalcode.plugintemplate;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Description;
import org.bukkit.plugin.java.annotation.plugin.LogPrefix;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name = "PluginTemplate", version = "1.0.1-SNAPSHOT")
@Author("EternalCodeTeam")
@ApiVersion(ApiVersion.Target.v1_17)
@Description("PluginTemplate for creating own projects!")
@LogPrefix("PluginTemplate")

public class PluginTemplate extends JavaPlugin {

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

}
