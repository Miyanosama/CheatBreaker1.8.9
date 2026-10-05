package io.netty.channel.oio;

import com.cheatbreaker.client.module.type.DamageTintModule;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.EventLoop;
import io.netty.channel.ThreadPerChannelEventLoop;
import io.netty.handler.timeout.IdleStateHandler;
import java.net.ConnectException;
import java.net.SocketAddress;
import javax.vecmath.Tuple3b;
import net.minecraft.command.server.CommandMessage;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import org.newsclub.net.unix.AFUNIXServerSocket;

public abstract class AbstractOioChannel extends AbstractChannel {
   public Runnable readTask = new Runnable() {

      @Override
      public void run() {
         if (AbstractOioChannel.this.isReadPending() || AbstractOioChannel.this.config().isAutoRead()) {
            AbstractOioChannel.this.setReadPending(false);
            AbstractOioChannel.this.doRead();
         }
      }
   };
   public static final int SO_TIMEOUT = 1000;
   public volatile boolean readPending;

   public abstract void doConnect(SocketAddress var1, SocketAddress var2) throws java.lang.Exception ;

   public AbstractOioChannel(Channel var1) {
      super(var1);
   }

   public void setReadPending(boolean var1) {
      this.readPending = var1;
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof ThreadPerChannelEventLoop;
   }

   @Override
   public void doBeginRead() throws java.lang.Exception {
      if (!this.isReadPending()) {
         this.setReadPending(true);
         this.eventLoop().execute(this.readTask);
      }
   }

   public boolean isReadPending() {
      return this.readPending;
   }

   public abstract void doRead();

   @Override
   public AbstractChannel.AbstractUnsafe newUnsafe() {
      return new AbstractOioChannel.DefaultOioUnsafe();
   }

   public final class DefaultOioUnsafe extends AbstractChannel.AbstractUnsafe {

      public DefaultOioUnsafe() {
      }

      @Override
      public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
         if (var3.setUncancellable() && this.ensureOpen(var3)) {
            try {
               boolean var7 = AbstractOioChannel.this.isActive();
               AbstractOioChannel.this.doConnect(var1, var2);
               this.safeSetSuccess(var3);
               if (!var7 && AbstractOioChannel.this.isActive()) {
                  AbstractOioChannel.this.pipeline().fireChannelActive();
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
}
