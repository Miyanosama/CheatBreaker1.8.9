package net.minecraft.item;

import com.google.common.base.Function;
import io.netty.buffer.ByteBufInputStream;
import io.netty.handler.codec.socks.SocksInitRequestDecoder$State;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.optifine.player.CapeImageBuffer;

public class ItemMultiTexture$1 implements Function<ItemStack, String> {
   public ByteBufInputStream field_0002;
   public SocksInitRequestDecoder$State field_0004;
   public EntityJumpHelper field_0003;
   public CapeImageBuffer field_0000;

   public String apply(ItemStack var1) {
      int var2 = var1.getMetadata();
      if (var2 < 0 || var2 >= this.field_179542_a.length) {
         var2 = 0;
      }

      return this.field_179542_a[var2];
   }

   public ItemMultiTexture$1(String[] var1) {
      this.field_179542_a = var1;
      super();
   }
}
