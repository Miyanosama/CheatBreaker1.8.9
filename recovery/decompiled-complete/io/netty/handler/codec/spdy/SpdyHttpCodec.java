package io.netty.handler.codec.spdy;

import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.compression.Snappy$1;
import net.minecraft.block.BlockVine$1;
import net.optifine.shaders.IteratorAxis;

public class SpdyHttpCodec extends CombinedChannelDuplexHandler<SpdyHttpDecoder, SpdyHttpEncoder> {
   public IteratorAxis __junk8516885174579562516;
   public Snappy$1 __junk2462199613044944880;
   public BlockVine$1 __junk3099965384276264162;

   public SpdyHttpCodec(SpdyVersion var1, int var2, boolean var3) {
      super(new SpdyHttpDecoder(var1, var2, var3), new SpdyHttpEncoder(var1));
   }

   public SpdyHttpCodec(SpdyVersion var1, int var2) {
      super(new SpdyHttpDecoder(var1, var2), new SpdyHttpEncoder(var1));
   }
}
