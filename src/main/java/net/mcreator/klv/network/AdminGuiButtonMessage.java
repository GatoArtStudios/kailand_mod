
package net.mcreator.klv.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.klv.world.inventory.AdminGuiMenu;
import net.mcreator.klv.procedures.VanishProcedureProcedure;
import net.mcreator.klv.procedures.DarVisionNocturnaProcedure;
import net.mcreator.klv.procedures.AdminSaturacionProcedure;
import net.mcreator.klv.procedures.AdminQuitarEfectoProcedure;
import net.mcreator.klv.procedures.AdminInvisibleProcedure;
import net.mcreator.klv.procedures.AdminInmortalProcedure;
import net.mcreator.klv.KlvMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AdminGuiButtonMessage {
	private final int buttonID, x, y, z;

	public AdminGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public AdminGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(AdminGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(AdminGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = AdminGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			AdminInmortalProcedure.execute(entity);
		}
		if (buttonID == 1) {

			AdminSaturacionProcedure.execute(entity);
		}
		if (buttonID == 2) {

			AdminInvisibleProcedure.execute(entity);
		}
		if (buttonID == 3) {

			AdminQuitarEfectoProcedure.execute(entity);
		}
		if (buttonID == 4) {

			DarVisionNocturnaProcedure.execute(entity);
		}
		if (buttonID == 5) {

			VanishProcedureProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		KlvMod.addNetworkMessage(AdminGuiButtonMessage.class, AdminGuiButtonMessage::buffer, AdminGuiButtonMessage::new, AdminGuiButtonMessage::handler);
	}
}
