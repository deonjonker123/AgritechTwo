package com.misterd.agritechtwo.command;

import com.misterd.agritechtwo.Config;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ATCommands {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                LiteralArgumentBuilder.<CommandSourceStack>literal("agritechtwo")
                        .then(Commands.literal("reload")
                                .executes(context -> reloadAll(context.getSource()))
        ));
    }

    private static int reloadAll(CommandSourceStack source) {
        try {
            Config.loadConfig();
            source.sendSuccess(() -> Component.literal("AgriTech config reloaded successfully!"), true);
            return 1;
        } catch (Exception e) {
            source.sendFailure(Component.literal("Failed to reload AgriTech config: " + e.getMessage()));
            return 0;
        }
    }
}
