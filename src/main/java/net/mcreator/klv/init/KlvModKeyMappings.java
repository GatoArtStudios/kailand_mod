
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import net.mcreator.klv.network.VanishMessage;
import net.mcreator.klv.network.TeclaAdminMessage;
import net.mcreator.klv.network.DobleSaltoMessage;
import net.mcreator.klv.KlvMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class KlvModKeyMappings {
	public static final KeyMapping DOBLE_SALTO = new KeyMapping("key.klv.doble_salto", GLFW.GLFW_KEY_SPACE, "key.categories.movement") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				KlvMod.PACKET_HANDLER.sendToServer(new DobleSaltoMessage(0, 0));
				DobleSaltoMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping TECLA_ADMIN = new KeyMapping("key.klv.tecla_admin", GLFW.GLFW_KEY_F7, "key.categories.inventory") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				KlvMod.PACKET_HANDLER.sendToServer(new TeclaAdminMessage(0, 0));
				TeclaAdminMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};
	public static final KeyMapping VANISH = new KeyMapping("key.klv.vanish", GLFW.GLFW_KEY_F6, "key.categories.multiplayer") {
		private boolean isDownOld = false;

		@Override
		public void setDown(boolean isDown) {
			super.setDown(isDown);
			if (isDownOld != isDown && isDown) {
				KlvMod.PACKET_HANDLER.sendToServer(new VanishMessage(0, 0));
				VanishMessage.pressAction(Minecraft.getInstance().player, 0, 0);
			}
			isDownOld = isDown;
		}
	};

	@SubscribeEvent
	public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(DOBLE_SALTO);
		event.register(TECLA_ADMIN);
		event.register(VANISH);
	}

	@Mod.EventBusSubscriber({Dist.CLIENT})
	public static class KeyEventListener {
		@SubscribeEvent
		public static void onClientTick(TickEvent.ClientTickEvent event) {
			if (Minecraft.getInstance().screen == null) {
				DOBLE_SALTO.consumeClick();
				TECLA_ADMIN.consumeClick();
				VANISH.consumeClick();
			}
		}
	}
}
