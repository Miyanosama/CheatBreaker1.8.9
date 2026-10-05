package io.netty.channel;

import java.util.LinkedHashSet;
import java.util.Set;

public class ChannelPromiseAggregator implements ChannelFutureListener {
   public Set<ChannelPromise> pendingPromises;
   public ChannelPromise aggregatePromise;

   public synchronized void operationComplete(ChannelFuture var1) throws java.lang.Exception {
      if (this.pendingPromises == null) {
         this.aggregatePromise.setSuccess();
      } else {
         this.pendingPromises.remove(var1);
         if (!var1.isSuccess()) {
            this.aggregatePromise.setFailure(var1.cause());

            for (ChannelPromise var3 : this.pendingPromises) {
               var3.setFailure(var1.cause());
            }
         } else if (this.pendingPromises.isEmpty()) {
            this.aggregatePromise.setSuccess();
         }
      }
   }

   public ChannelPromiseAggregator add(ChannelPromise... var1) {
      if (var1 == null) {
         throw new NullPointerException("promises");
      } else if (var1.length == 0) {
         return this;
      } else {
         synchronized (this) {
            if (this.pendingPromises == null) {
               int var3;
               if (var1.length > 1) {
                  var3 = var1.length;
               } else {
                  var3 = 2;
               }

               this.pendingPromises = new LinkedHashSet<>(var3);
            }

            for (ChannelPromise var6 : var1) {
               if (var6 != null) {
                  this.pendingPromises.add(var6);
                  var6.addListener(this);
               }
            }

            return this;
         }
      }
   }

   public ChannelPromiseAggregator(ChannelPromise var1) {
      if (var1 == null) {
         throw new NullPointerException("aggregatePromise");
      } else {
         this.aggregatePromise = var1;
      }
   }
}
