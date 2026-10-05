package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.block.BlockPressurePlateWeighted;
import net.minecraft.client.renderer.entity.RenderSnowMan;

public class UnknownSocksResponse extends SocksResponse {

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
   }

   public UnknownSocksResponse() {
      super(SocksResponseType.UNKNOWN);
   }
}
