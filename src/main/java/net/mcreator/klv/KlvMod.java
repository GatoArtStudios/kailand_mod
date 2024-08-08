package net.mcreator.klv;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.mcreator.klv.init.KlvModBlocks;
import net.mcreator.klv.init.KlvModEnchantments;
import net.mcreator.klv.init.KlvModEntities;
import net.mcreator.klv.init.KlvModFeatures;
import net.mcreator.klv.init.KlvModItems;
import net.mcreator.klv.init.KlvModMenus;
import net.mcreator.klv.init.KlvModMobEffects;
import net.mcreator.klv.init.KlvModPotions;
import net.mcreator.klv.init.KlvModSounds;
import net.mcreator.klv.init.KlvModTabs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("klv")
public class KlvMod {
    public static final Logger LOGGER = LogManager.getLogger(KlvMod.class);
    public static final String MODID = "klv";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("klv", "klv"), () -> {
        return "1";
    }, "1"::equals, "1"::equals);
    private static int messageID = 0;
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue();

    public KlvMod() {
        MinecraftForge.EVENT_BUS.register(this);
        KlvModTabs.load();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        KlvModSounds.REGISTRY.register(bus);
        KlvModBlocks.REGISTRY.register(bus);
        KlvModItems.REGISTRY.register(bus);
        KlvModEntities.REGISTRY.register(bus);
        KlvModFeatures.REGISTRY.register(bus);
        KlvModMobEffects.REGISTRY.register(bus);
        KlvModPotions.REGISTRY.register(bus);
        KlvModEnchantments.REGISTRY.register(bus);
        KlvModMenus.REGISTRY.register(bus);
    }

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        ++messageID;
    }

    public static void queueServerWork(int tick, Runnable action) {
        workQueue.add(new AbstractMap.SimpleEntry(action, tick));
    }

    @SubscribeEvent
    public void tick(TickEvent.ServerTickEvent event) {
        if (event.phase == Phase.END) {
            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList();
            workQueue.forEach((work) -> {
                work.setValue((Integer)work.getValue() - 1);
                if ((Integer)work.getValue() == 0) {
                    actions.add(work);
                }

            });
            actions.forEach((e) -> {
                ((Runnable)e.getKey()).run();
            });
            workQueue.removeAll(actions);
        }

    }
}