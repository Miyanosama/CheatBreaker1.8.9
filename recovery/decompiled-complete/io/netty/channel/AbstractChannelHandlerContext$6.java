package io.netty.channel;

import io.netty.util.internal.OneTimeTask;
import net.minecraft.client.renderer.RenderGlobal$1;
import net.minecraft.item.ItemCoal;
import net.minecraft.world.biome.WorldChunkManagerHell;

public class AbstractChannelHandlerContext$6 extends OneTimeTask {
   public ItemCoal __junk2575563054762200845;
   public RenderGlobal$1 __junk5622140752033722384;
   public WorldChunkManagerHell __junk6668372253483307159;

   public AbstractChannelHandlerContext$6(AbstractChannelHandlerContext var1, AbstractChannelHandlerContext var2, Throwable var3) {
      this.this$0 = var1;
      this.val$next = var2;
      this.val$cause = var3;
      super();
   }

   @Override
   public void run() {
      AbstractChannelHandlerContext.access$500(this.val$next, this.val$cause);
   }
}
