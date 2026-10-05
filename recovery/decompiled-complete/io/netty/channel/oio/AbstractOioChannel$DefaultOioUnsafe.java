package io.netty.channel.oio;

import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ConnectTimeoutException;
import io.netty.handler.timeout.IdleStateHandler$ReaderIdleTimeoutTask;
import java.net.ConnectException;
import java.net.SocketAddress;
import net.minecraft.network.login.server.S03PacketEnableCompression;

public class AbstractOioChannel$DefaultOioUnsafe extends AbstractChannel$AbstractUnsafe {
   public S03PacketEnableCompression __junk8259990187359369588;
   public ConnectTimeoutException __junk950801254158720690;
   public IdleStateHandler$ReaderIdleTimeoutTask __junk2759893766296127730;

   public AbstractOioChannel$DefaultOioUnsafe(AbstractOioChannel var1) {
      this.this$0 = var1;
      super(var1);
   }

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      if (var3.setUncancellable() && this.ensureOpen(var3)) {
         try {
            boolean var7 = this.this$0.isActive();
            this.this$0.doConnect(var1, var2);
            this.safeSetSuccess(var3);
            if (!var7 && this.this$0.isActive()) {
               this.this$0.pipeline().fireChannelActive();
            }
         } catch (Throwable var6) {
            Object var4 = var6;
            if (var6 instanceof ConnectException) {
               ConnectException var5 = new ConnectException(var6.getMessage() + ": " + var1);
               var5.setStackTrace(var6.getStackTrace());
               var4 = var5;
            }

            this.safeSetFailure(var3, (Throwable)var4);
            this.closeIfClosed();
         }
      }
   }
}
