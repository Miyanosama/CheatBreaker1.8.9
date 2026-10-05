package io.netty.channel;

import net.minecraft.entity.monster.EntityEnderman$AIPlaceBlock;
import net.optifine.util.MathUtilsTest;

public class DefaultChannelPipeline$4 implements Runnable {
   public EntityEnderman$AIPlaceBlock __junk9211418893846677047;
   public MathUtilsTest __junk2170827517096633790;

   public DefaultChannelPipeline$4(DefaultChannelPipeline var1, AbstractChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }

   @Override
   public void run() {
      DefaultChannelPipeline.access$200(this.this$0, this.val$ctx);
   }
}
