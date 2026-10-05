package io.netty.channel;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.PlatformDependent;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.inventory.InventoryHelper;
import net.optifine.TextureAnimations;

public class FailedChannelFuture extends CompleteChannelFuture {
   public Throwable cause;

   public FailedChannelFuture(Channel var1, EventExecutor var2, Throwable var3) {
      super(var1, var2);
      if (var3 == null) {
         throw new NullPointerException("cause");
      } else {
         this.cause = var3;
      }
   }

   @Override
   public ChannelFuture syncUninterruptibly() {
      PlatformDependent.throwException(this.cause);
      return this;
   }

   @Override
   public Throwable cause() {
      return this.cause;
   }

   @Override
   public ChannelFuture sync() {
      PlatformDependent.throwException(this.cause);
      return this;
   }

   @Override
   public boolean isSuccess() {
      return false;
   }
}
