package net.minecraft.item;

import io.netty.handler.codec.socks.SocksCmdResponseDecoder;
import net.optifine.util.RenderChunkUtils;

public class ItemSimpleFoiled extends Item {
   public SocksCmdResponseDecoder field_0000;
   public RenderChunkUtils field_0001;

   @Override
   public boolean hasEffect(ItemStack var1) {
      return true;
   }
}
