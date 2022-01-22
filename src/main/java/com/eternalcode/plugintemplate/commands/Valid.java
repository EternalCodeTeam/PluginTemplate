package com.eternalcode.plugintemplate.commands;

import net.dzikoysk.funnycommands.resources.ValidationException;

public class Valid {

    public static void whenNull(Object object, String message) {
        when(object == null, message);
    }

    public static void when(boolean when, String message) {
        if (when) {
            throw new ValidationException(message);
        }
    }

    public static void returnAndSend(String message) {
        throw new ValidationException(message);
    }
}