package io.netty.channel;

import javazoom.jl.decoder.Header;
import net.minecraft.entity.passive.EntityWolf$1;

public class ChannelPromiseNotifier implements ChannelFutureListener {
   public Header __junk4275448197000008712;
   public EntityWolf$1 __junk8352871761636423315;
   public ChannelPromise[] promises;

   public void operationComplete(ChannelFuture var1) {
      if (var1.isSuccess()) {
         for (ChannelPromise var10 : this.promises) {
            var10.setSuccess();
         }
      } else {
         Throwable var2 = var1.cause();

         for (ChannelPromise var6 : this.promises) {
            var6.setFailure(var2);
         }
      }
   }

   public ChannelPromiseNotifier(ChannelPromise... var1) {
      if (var1 == null) {
         throw new NullPointerException("promises");
      } else {
         for (ChannelPromise var5 : var1) {
            if (var5 == null) {
               throw new IllegalArgumentException("promises contains null ChannelPromise");
            }
         }

         this.promises = (ChannelPromise[])var1.clone();
      }
   }
}
