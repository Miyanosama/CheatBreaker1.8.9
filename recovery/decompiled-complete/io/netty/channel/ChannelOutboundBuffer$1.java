package io.netty.channel;

import io.netty.util.concurrent.FastThreadLocal;
import java.nio.ByteBuffer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.world.gen.feature.WorldGenIceSpike;

public class ChannelOutboundBuffer$1 extends FastThreadLocal<ByteBuffer[]> {
   public WorldGenIceSpike __junk3247829404045231153;
   public ItemPickaxe __junk4574058818821950810;

   public ByteBuffer[] initialValue() {
      return new ByteBuffer[1024];
   }
}
