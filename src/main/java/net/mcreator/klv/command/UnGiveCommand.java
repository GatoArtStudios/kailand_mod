package net.mcreator.klv.command;

import org.checkerframework.checker.units.qual.s;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.mcreator.klv.procedures.QuitarItemDelInventarioProcedureProcedure;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.commands.CommandBuildContext;

@Mod.EventBusSubscriber
public class UnGiveCommand {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        CommandBuildContext context = event.getBuildContext();
        event.getDispatcher().register(Commands.literal("ungive")
            .requires(s -> s.hasPermission(3))
            .then(Commands.argument("targets", EntityArgument.players())
                .then(Commands.argument("item", ItemArgument.item(context))
                    .then(Commands.argument("quantity", IntegerArgumentType.integer(1))
                        .executes(arguments -> execute(arguments, EntityArgument.getPlayers(arguments, "targets"), ItemArgument.getItem(arguments, "item").getItem(), IntegerArgumentType.getInteger(arguments, "quantity")))))));
    }

    private static int execute(CommandContext<CommandSourceStack> arguments, Collection<ServerPlayer> targets, Item item, int quantity) {
        try {
            ServerLevel world = arguments.getSource().getLevel();

            ItemStack itemstack = new ItemStack(item, quantity);

            for (ServerPlayer player : targets) {
                boolean itemRemoved = false;

                // Intentar eliminar el item del inventario del jugador
                for (int i = 0; i < player.getInventory().items.size(); i++) {
                    ItemStack stackInSlot = player.getInventory().items.get(i);
                    if (!stackInSlot.isEmpty() && stackInSlot.sameItem(itemstack)) {
                        if (stackInSlot.getCount() >= quantity) {
                            stackInSlot.shrink(quantity);
                            itemRemoved = true;
                            break;
                        } else {
                            arguments.getSource().sendFailure(Component.literal("¡El jugador " + player.getName().getString() + " no tiene suficiente cantidad del ítem!"));
                            return 0;
                        }
                    }
                }

                if (!itemRemoved) {
                    arguments.getSource().sendFailure(Component.literal("¡El jugador " + player.getName().getString() + " no tiene el ítem!"));
                    return 0;
                }

                Map<String, Object> dependencies = new HashMap<>();
                dependencies.put("entity", player);
                dependencies.put("itemstack", itemstack);

                QuitarItemDelInventarioProcedureProcedure.executeProcedure(dependencies);
                arguments.getSource().sendSuccess(Component.literal("Se han eliminado " + quantity + " de " + item.getDescription().getString() + " del inventario de " + player.getName().getString()), false);
                player.sendSystemMessage(Component.literal("Has perdido " + quantity + " de " + item.getDescription().getString()));
            }

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
