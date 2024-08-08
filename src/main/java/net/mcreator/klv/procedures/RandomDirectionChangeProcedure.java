package net.mcreator.klv.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.client.player.LocalPlayer;

import java.util.Random;

public class RandomDirectionChangeProcedure {
    public static void execute(LivingEntity entity) {
        if (entity.level.isClientSide()) {
            Random random = new Random();

            // Generar una rotación aleatoria
            float yaw = entity.getYRot() + (random.nextFloat() * 360 - 180); // Rotar entre -180 y 180 grados
            entity.setYRot(yaw);

            // Si la entidad es un jugador y está en el lado del cliente, asegúrate de cambiar también la rotación del cliente local
            if (entity instanceof LocalPlayer) {
                LocalPlayer localPlayer = (LocalPlayer) entity;
                localPlayer.setYRot(yaw);
            }
        }
    }
}
