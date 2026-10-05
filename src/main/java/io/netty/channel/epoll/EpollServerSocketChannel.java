package io.netty.channel.epoll;

import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.ServerSocketChannel;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.minecraft.util.HttpUtil;
import org.apache.log4j.config.PropertySetter;
import net.minecraft.world.gen.structure.StructureBoundingBox$EnumSwitch;

public class EpollServerSocketChannel extends AbstractEpollChannel implements ServerSocketChannel {
   public volatile InetSocketAddress local;
   public EpollServerSocketChannelConfig config = new EpollServerSocketChannelConfig(this);

   public EpollServerSocketChannel() {
      super(Native.socketStreamFd(), 4);
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   @Override
   public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
      return new EpollServerSocketChannel.EpollServerSocketUnsafe();
   }

   @Override
   public Object filterOutboundMessage(Object var1) throws java.lang.Exception {
      throw new UnsupportedOperationException();
   }

   public InetSocketAddress remoteAddress0() {
      return null;
   }

   @Override
   public void doBind(SocketAddress var1) throws java.lang.Exception {
      InetSocketAddress var2 = (InetSocketAddress)var1;
      checkResolvable(var2);
      Native.bind(this.fd, var2.getAddress(), var2.getPort());
      this.local = Native.localAddress(this.fd);
      Native.listen(this.fd, this.config.getBacklog());
      this.active = true;
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof EpollEventLoop;
   }

   public InetSocketAddress localAddress0() {
      return this.local;
   }

   public EpollServerSocketChannelConfig config() {
      return this.config;
   }

   public final class EpollServerSocketUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
      // $VF: synthetic field
      public final boolean $assertionsDisabled = !EpollServerSocketChannel.class.desiredAssertionStatus();

      @Override
      public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
         var3.setFailure(new UnsupportedOperationException());
      }

      @Override
      public void epollInReady() {
         if (!$assertionsDisabled && !EpollServerSocketChannel.this.eventLoop().inEventLoop()) {
            throw new AssertionError();
         } else {
            ChannelPipeline var1 = EpollServerSocketChannel.this.pipeline();
            Throwable var2 = null;

            try {
               try {
                  while (true) {
                     int var3 = Native.accept(EpollServerSocketChannel.this.fd);
                     if (var3 == -1) {
                        break;
                     }

                     try {
                        this.readPending = false;
                        var1.fireChannelRead(new EpollSocketChannel(EpollServerSocketChannel.this, var3));
                     } catch (Throwable var9) {
                        var1.fireChannelReadComplete();
                        var1.fireExceptionCaught(var9);
                     }
                  }
               } catch (Throwable var10) {
                  var2 = var10;
               }

               var1.fireChannelReadComplete();
               if (var2 != null) {
                  var1.fireExceptionCaught(var2);
               }
            } finally {
               if (!EpollServerSocketChannel.this.config.isAutoRead() && !this.readPending) {
                  this.clearEpollIn0();
               }
            }
         }
      }
   }
}
