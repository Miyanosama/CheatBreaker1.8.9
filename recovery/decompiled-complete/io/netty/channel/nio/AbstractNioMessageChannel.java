package io.netty.channel.nio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelOutboundBuffer;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.List;
import net.optifine.http.HttpPipeline$1;

public abstract class AbstractNioMessageChannel extends AbstractNioChannel {
   public HttpPipeline$1 __junk7628189243911936466;

   public AbstractNioMessageChannel(Channel var1, SelectableChannel var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean continueOnWriteError() {
      return false;
   }

   public AbstractNioChannel$AbstractNioUnsafe newUnsafe() {
      return new AbstractNioMessageChannel$NioMessageUnsafe(this, null);
   }

   public abstract int doReadMessages(List<Object> var1);

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      SelectionKey var2 = this.selectionKey();
      int var3 = var2.interestOps();

      while (true) {
         Object var4 = var1.current();
         if (var4 == null) {
            if ((var3 & 4) != 0) {
               var2.interestOps(var3 & -5);
            }
            break;
         }

         try {
            boolean var5 = false;

            for (int var6 = this.config().getWriteSpinCount() - 1; var6 >= 0; var6--) {
               if (this.doWriteMessage(var4, var1)) {
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               if ((var3 & 4) == 0) {
                  var2.interestOps(var3 | 4);
               }
               break;
            }

            var1.remove();
         } catch (IOException var7) {
            if (!this.continueOnWriteError()) {
               throw var7;
            }

            var1.remove(var7);
         }
      }
   }

   public abstract boolean doWriteMessage(Object var1, ChannelOutboundBuffer var2);
}
