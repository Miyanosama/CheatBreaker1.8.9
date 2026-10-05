package io.netty.handler.codec.compression;

import com.cheatbreaker.client.config.Setting;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.block.state.pattern.BlockStateHelper;
import net.minecraft.client.particle.EntityBreakingFX$SlimeFactory;
import net.minecraft.client.renderer.tileentity.TileEntityEnderChestRenderer;

public class JZlibEncoder$3 implements Runnable {
   public BlockStateHelper __junk1824426546649469441;
   public EntityBreakingFX$SlimeFactory __junk3808255454569676603;
   public Setting __junk1933499999967546888;
   public TileEntityEnderChestRenderer __junk7642499209125910369;

   public JZlibEncoder$3(JZlibEncoder var1, ChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$promise = var3;
      super();
   }

   @Override
   public void run() {
      this.val$ctx.close(this.val$promise);
   }
}
