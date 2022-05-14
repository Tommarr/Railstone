package net.kronk2.railstone.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.kronk2.railstone.Railstone;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModParticles {
    public static final DefaultParticleType STATION_PARTICLE = FabricParticleTypes.simple();

    public static void registerParticles() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Railstone.MOD_ID, "station_particle"),
                STATION_PARTICLE);
    }
}
