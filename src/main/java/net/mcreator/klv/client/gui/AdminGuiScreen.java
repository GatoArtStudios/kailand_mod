package net.mcreator.klv.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.klv.world.inventory.AdminGuiMenu;
import net.mcreator.klv.network.AdminGuiButtonMessage;
import net.mcreator.klv.KlvMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class AdminGuiScreen extends AbstractContainerScreen<AdminGuiMenu> {
	private final static HashMap<String, Object> guistate = AdminGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	Button button_inmortalidad;
	Button button_saturacion;
	Button button_invisible;
	Button button_quitarefectos;
	Button button_vision_nocturna;
	Button button_vanish;

	public AdminGuiScreen(AdminGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 236;
		this.imageHeight = 133;
	}

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
		button_inmortalidad = new Button(this.leftPos + 4, this.topPos + 34, 88, 20, Component.translatable("gui.klv.admin_gui.button_inmortalidad"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new AdminGuiButtonMessage(0, x, y, z));
				AdminGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		});
		guistate.put("button:button_inmortalidad", button_inmortalidad);
		this.addRenderableWidget(button_inmortalidad);
		button_saturacion = new Button(this.leftPos + 4, this.topPos + 84, 77, 20, Component.translatable("gui.klv.admin_gui.button_saturacion"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new AdminGuiButtonMessage(1, x, y, z));
				AdminGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		});
		guistate.put("button:button_saturacion", button_saturacion);
		this.addRenderableWidget(button_saturacion);
		button_invisible = new Button(this.leftPos + 4, this.topPos + 59, 72, 20, Component.translatable("gui.klv.admin_gui.button_invisible"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new AdminGuiButtonMessage(2, x, y, z));
				AdminGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		});
		guistate.put("button:button_invisible", button_invisible);
		this.addRenderableWidget(button_invisible);
		button_quitarefectos = new Button(this.leftPos + 4, this.topPos + 9, 93, 20, Component.translatable("gui.klv.admin_gui.button_quitarefectos"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new AdminGuiButtonMessage(3, x, y, z));
				AdminGuiButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:button_quitarefectos", button_quitarefectos);
		this.addRenderableWidget(button_quitarefectos);
		button_vision_nocturna = new Button(this.leftPos + 4, this.topPos + 109, 103, 20, Component.translatable("gui.klv.admin_gui.button_vision_nocturna"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new AdminGuiButtonMessage(4, x, y, z));
				AdminGuiButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		guistate.put("button:button_vision_nocturna", button_vision_nocturna);
		this.addRenderableWidget(button_vision_nocturna);
		button_vanish = new Button(this.leftPos + 103, this.topPos + 9, 56, 20, Component.translatable("gui.klv.admin_gui.button_vanish"), e -> {
			if (true) {
				KlvMod.PACKET_HANDLER.sendToServer(new AdminGuiButtonMessage(5, x, y, z));
				AdminGuiButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		guistate.put("button:button_vanish", button_vanish);
		this.addRenderableWidget(button_vanish);
	}
}
