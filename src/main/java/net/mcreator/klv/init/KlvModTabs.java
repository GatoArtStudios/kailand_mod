package net.mcreator.klv.init;

import net.minecraft.world.item.CreativeModeTab;

public class KlvModTabs {
    public static CreativeModeTab TAB_KL;

    public KlvModTabs() {
    }

    public static void load() {
        TAB_KL = new 1("tabkl");
    }
}
