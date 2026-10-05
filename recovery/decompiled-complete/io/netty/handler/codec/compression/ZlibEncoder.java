package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.world.biome.BiomeColorHelper;
import recovered.unidentified.UnidentifiedClass4985;

public abstract class ZlibEncoder extends MessageToByteEncoder<ByteBuf> {
   public UnidentifiedClass4985 __junk3757746972709000930;
   public BiomeColorHelper __junk4263871426077557200;
   public ChannelOption __junk4424480632700475973;

   public abstract boolean isClosed();

   public ZlibEncoder() {
      super(false);
   }

   public abstract ChannelFuture close(ChannelPromise var1);

   public abstract ChannelFuture close();
}
