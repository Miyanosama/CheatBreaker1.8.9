package io.netty.channel;

import javazoom.jl.decoder.Header;

public class ChannelPromiseNotifier implements ChannelFutureListener {
   public ChannelPromise[] promises;

   public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
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
