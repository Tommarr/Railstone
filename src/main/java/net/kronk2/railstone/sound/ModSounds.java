package net.kronk2.railstone.sound;

import net.kronk2.railstone.Railstone;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModSounds {

    public static SoundEvent STATION_BLOCK_PLACE = registerSoundEvent("station_block_place");
    public static SoundEvent STATION_BLOCK_BREAK = registerSoundEvent("station_block_break");
    public static SoundEvent STATION_BLOCK_STEP = registerSoundEvent("station_block_step");
    public static SoundEvent STATION_BLOCK_HIT = registerSoundEvent("station_block_hit");
    public static SoundEvent STATION_BLOCK_FALL = registerSoundEvent("station_block_fall");

    public static final BlockSoundGroup STATION_SOUNDS = new BlockSoundGroup(1f, 1f,
            ModSounds.STATION_BLOCK_BREAK, ModSounds.STATION_BLOCK_STEP,
            ModSounds.STATION_BLOCK_PLACE, ModSounds.STATION_BLOCK_HIT, ModSounds.STATION_BLOCK_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Railstone.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
