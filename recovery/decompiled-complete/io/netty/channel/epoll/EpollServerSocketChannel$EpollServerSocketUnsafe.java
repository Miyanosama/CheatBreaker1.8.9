package io.netty.channel.epoll;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import java.net.SocketAddress;
import net.minecraft.entity.ai.EntityAIOcelotSit;
import net.optifine.shaders.uniform.UniformType$1;
import org.apache.log4j.config.PropertySetter;

public class EpollServerSocketChannel$EpollServerSocketUnsafe extends AbstractEpollChannel$AbstractEpollUnsafe {
   public EntityAIOcelotSit __junk1112904806939256062;
   public UniformType$1 __junk2024459217938455674;
   public PropertySetter __junk6466282703964127464;

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      var3.setFailure(new UnsupportedOperationException());
   }

   public EpollServerSocketChannel$EpollServerSocketUnsafe(EpollServerSocketChannel var1) {
      this.this$0 = var1;
      super(var1);
   }

   @Override
   public void epollInReady() {
      if (!$assertionsDisabled && !this.this$0.eventLoop().inEventLoop()) {
         throw new AssertionError();
      } else {
         ChannelPipeline var1 = this.this$0.pipeline();
         Throwable var2 = null;

         try {
            try {
               while (true) {
                  int var3 = Native.accept(this.this$0.fd);
                  if (var3 == -1) {
                     break;
                  }

                  try {
                     this.readPending = false;
                     var1.fireChannelRead(new EpollSocketChannel(this.this$0, var3));
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
            if (!EpollServerSocketChannel.access$000(this.this$0).isAutoRead() && !this.readPending) {
               this.clearEpollIn0();
            }
         }
      }
   }
}
