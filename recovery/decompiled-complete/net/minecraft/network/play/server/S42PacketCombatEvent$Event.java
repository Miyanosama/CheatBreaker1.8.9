package net.minecraft.network.play.server;

import io.netty.util.internal.chmv8.ForkJoinPool$2;
import javazoom.jl.decoder.LayerIDecoder$SubbandLayer1IntensityStereo;
import net.minecraft.client.audio.SoundListSerializer;
import recovered.unidentified.UnidentifiedClass3223;

public enum S42PacketCombatEvent$Event {
   ENTER_COMBAT,
   END_COMBAT,
   ENTITY_DIED;
   public UnidentifiedClass3223 field_0006;
   public ForkJoinPool$2 field_0002;
   public SoundListSerializer field_0000;
   public LayerIDecoder$SubbandLayer1IntensityStereo field_0001;
}
