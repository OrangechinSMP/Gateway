package hell0hd.gateway.sounds;

import hell0hd.gateway.Gateway;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public interface ModJukeboxSongs {
    RegistryKey<JukeboxSong> DROOPY = of("droopy");
    RegistryKey<JukeboxSong> ELEVEN = of("eleven");
    RegistryKey<JukeboxSong> TEARS = of("tears");


    private static RegistryKey<JukeboxSong> of(String id) {
        return RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of(Gateway.MOD_ID, id));
    }
}
