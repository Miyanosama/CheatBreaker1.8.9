package io.netty.channel.local;

import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ReplayingDecoderBuffer;
import java.net.SocketAddress;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ConnectionPendingException;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import recovered.unidentified.UnidentifiedClass5091;

public class LocalChannel$LocalUnsafe extends AbstractChannel$AbstractUnsafe {
   public ReplayingDecoderBuffer __junk7161517310126752714;
   public UnidentifiedClass5091 __junk8739548375937746318;
   public S42PacketCombatEvent __junk6383663628249893801;

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      if (var3.setUncancellable() && this.ensureOpen(var3)) {
         if (LocalChannel.access$500(this.this$0) == 2) {
            AlreadyConnectedException var7 = new AlreadyConnectedException();
            this.safeSetFailure(var3, var7);
            this.this$0.pipeline().fireExceptionCaught(var7);
         } else if (LocalChannel.access$300(this.this$0) != null) {
            throw new ConnectionPendingException();
         } else {
            LocalChannel.access$302(this.this$0, var3);
            if (LocalChannel.access$500(this.this$0) != 1 && var2 == null) {
               var2 = new LocalAddress(this.this$0);
            }

            if (var2 != null) {
               try {
                  this.this$0.doBind((SocketAddress)var2);
               } catch (Throwable var6) {
                  this.safeSetFailure(var3, var6);
                  this.close(this.voidPromise());
                  return;
               }
            }

            Channel var4 = LocalChannelRegistry.get(var1);
            if (!(var4 instanceof LocalServerChannel)) {
               ChannelException var8 = new ChannelException("connection refused");
               this.safeSetFailure(var3, var8);
               this.close(this.voidPromise());
            } else {
               LocalServerChannel var5 = (LocalServerChannel)var4;
               LocalChannel.access$602(this.this$0, var5.serve(this.this$0));
            }
         }
      }
   }

   public LocalChannel$LocalUnsafe(LocalChannel var1) {
      this.this$0 = var1;
      super(var1);
   }
}
