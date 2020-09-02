package com.sk89q.minecraft.util.commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Represents the target scope(s) of the command
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandScopes {

    /**
     * The command's target scopes, which by default are all possible senders
     * @return them
     */
    String[] value() default {"console", "player", "block"};
}
