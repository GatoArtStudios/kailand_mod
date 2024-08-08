package net.mcreator.klv.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.klv.world.inventory.GUIDeseosMenu;
import net.mcreator.klv.network.GUIDeseosButtonMessage;
import net.mcreator.klv.KlvMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class GUIDeseosScreen extends AbstractContainerScreen<GUIDeseosMenu> {
	private final static HashMap<String, Object> guistate = GUIDeseosMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_inmortalidad_por_5_minutos;
	Button button_noche;
	Button button_diaaaa;

	public GUIDeseosScreen(GUIDeseosMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 170;
		this.imageHeight = 178;
	}

	private static final ResourceLocation texture = new ResourceLocation("klv:textures/screens/gui_deseos.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		button_inmortalidad_por_5_minutos = new Button(this.leftPos + 3, this.topPos + 5, 160, 20, Component.translatable("gui.klv.gui_deseos.button_inmortalidad_por_5_minutos"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new GUIDeseosButtonMessage(0, x, y, z));
				GUIDeseosButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_inmortalidad_por_5_minutos", button_inmortalidad_por_5_minutos);
		this.addRenderableWidget(button_inmortalidad_por_5_minutos);
		button_noche = new Button(this.leftPos + 83, this.topPos + 29, 51, 20, Component.translatable("gui.klv.gui_deseos.button_noche"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new GUIDeseosButtonMessage(1, x, y, z));
				GUIDeseosButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_noche", button_noche);
		this.addRenderableWidget(button_noche);
		button_diaaaa = new Button(this.leftPos + 25, this.topPos + 29, 56, 20, Component.translatable("gui.klv.gui_deseos.button_diaaaa"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new GUIDeseosButtonMessage(2, x, y, z));
				GUIDeseosButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:button_diaaaa", button_diaaaa);
		this.addRenderableWidget(button_diaaaa);
	}
}
