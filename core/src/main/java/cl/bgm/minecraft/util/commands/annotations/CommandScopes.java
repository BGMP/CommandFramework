package cl.bgm.minecraft.util.commands.annotations;

import cl.bgm.minecraft.util.commands.CommandScope;

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
    CommandScope[] value() default { CommandScope.ANY };
}
