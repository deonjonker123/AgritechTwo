package com.misterd.agritechtwo.command;

import com.misterd.agritechtwo.Config;
import com.misterd.agritechtwo.config.PlantablesConfig;
import com.misterd.agritechtwo.config.PlantablesOverrideConfig;
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
                                .then(Commands.literal("plantables")
                                        .executes(context -> {
                                            try {
                                                PlantablesOverrideConfig.resetErrorFlag();
                                                PlantablesConfig.loadConfig();
                                                context.getSource().sendSuccess(() -> Component.literal("AgriTech: Two plantables config reloaded successfully!"), true);
                                                return 1;
                                            } catch (Exception e) {
                                                context.getSource().sendFailure(Component.literal("Failed to reload AgriTech: Two plantables config: " + e.getMessage()));
                                                return 0;
                                            }
                                        }))
                                .then(Commands.literal("config")
                                        .executes(context -> {
                                            try {
                                                Config.loadConfig();
                                                context.getSource().sendSuccess(() -> Component.literal("AgriTech: Two main config reloaded successfully!"), true);
                                                return 1;
                                            } catch (Exception e) {
                                                context.getSource().sendFailure(Component.literal("Failed to reload AgriTech: Two main config: " + e.getMessage()));
                                                return 0;
                                            }
                                        })))
        );
    }

    private static int reloadAll(CommandSourceStack source) {
        try {
            PlantablesOverrideConfig.resetErrorFlag();
            Config.loadConfig();
            PlantablesConfig.loadConfig();
            source.sendSuccess(() -> Component.literal("All AgriTech: Two configs reloaded successfully!"), true);
            return 1;
        } catch (Exception e) {
            source.sendFailure(Component.literal("Failed to reload AgriTech: Two configs: " + e.getMessage()));
            return 0;
        }
    }
}
