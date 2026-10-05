package io.netty.channel;

import io.netty.buffer.UnpooledDirectByteBuf;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.optifine.CustomBlockLayers;

public class DefaultChannelPipeline$2 implements Runnable {
   public CustomBlockLayers __junk9167924592299058602;
   public GenLayerRiverInit __junk4563078474676891315;
   public UnpooledDirectByteBuf __junk7395926900288430192;

   public DefaultChannelPipeline$2(DefaultChannelPipeline var1, AbstractChannelHandlerContext var2, String var3, AbstractChannelHandlerContext var4) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$newName = var3;
      this.val$newCtx = var4;
      super();
   }

   @Override
   public void run() {
      synchronized (this.this$0) {
         DefaultChannelPipeline.access$000(this.this$0, this.val$ctx, this.val$newName, this.val$newCtx);
      }
   }
}
