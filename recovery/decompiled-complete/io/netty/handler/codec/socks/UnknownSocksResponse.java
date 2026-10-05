package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesTask;
import net.minecraft.block.BlockPressurePlateWeighted;
import net.minecraft.client.renderer.entity.RenderSnowMan;

public class UnknownSocksResponse extends SocksResponse {
   public RenderSnowMan __junk7740449091969429302;
   public ConcurrentHashMapV8$MapReduceValuesTask __junk2324427467520950475;
   public BlockPressurePlateWeighted __junk313743628728161726;

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
   }

   public UnknownSocksResponse() {
      super(SocksResponseType.UNKNOWN);
   }
}
