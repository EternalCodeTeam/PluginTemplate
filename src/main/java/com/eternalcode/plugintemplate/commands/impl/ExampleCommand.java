package com.eternalcode.plugintemplate.commands.impl;


import com.eternalcode.plugintemplate.commands.binds.CommandInfoBind;
import net.dzikoysk.funnycommands.commands.CommandInfo;
import net.dzikoysk.funnycommands.stereotypes.FunnyCommand;
import net.dzikoysk.funnycommands.stereotypes.FunnyComponent;

import java.util.Locale;

import static com.eternalcode.plugintemplate.commands.Valid.when;

@FunnyComponent
public final class ExampleCommand {

    @FunnyCommand(
            name = "examplecommand",
            aliases = {"ec"},
            permission = "plugintemplate.command.examplecommand",
            usage = "Use /examplecommand",
            acceptsExceeded = true
    )

    public void execute(String[] args, CommandInfo commandInfo) {
        when(args.length < 1, commandInfo.getUsageMessage());
    }
}
