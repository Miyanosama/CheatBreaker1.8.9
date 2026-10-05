package io.netty.channel;

import io.netty.util.AbstractReferenceCounted;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ShaderOptionResolver;

public class ChannelFutureListener$2 implements ChannelFutureListener {
   public AbstractReferenceCounted __junk2484779139797901852;
   public TileEntitySkullRenderer __junk722689596471461298;
   public ShaderOptionResolver __junk8627457687948791711;
   public RenderScale __junk6213639356470981036;

   public void operationComplete(ChannelFuture var1) {
      if (!var1.isSuccess()) {
         var1.channel().close();
      }
   }
}
