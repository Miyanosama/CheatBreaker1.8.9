package io.netty.channel;

import io.netty.buffer.ReadOnlyUnsafeDirectByteBuf;
import io.netty.util.internal.logging.CommonsLoggerFactory;
import java.net.SocketAddress;
import net.minecraft.block.BlockBookshelf;

public class CombinedChannelDuplexHandler<I extends ChannelInboundHandler, O extends ChannelOutboundHandler> extends ChannelDuplexHandler {
   public I inboundHandler;
   public ReadOnlyUnsafeDirectByteBuf __junk1684720312302439822;
   public BlockBookshelf __junk2295884797372965655;
   public O outboundHandler;
   public CommonsLoggerFactory __junk411366976584856135;

   @Override
   public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) {
      this.outboundHandler.bind(var1, var2, var3);
   }

   @Override
   public void channelWritabilityChanged(ChannelHandlerContext var1) {
      this.inboundHandler.channelWritabilityChanged(var1);
   }

   @Override
   public void read(ChannelHandlerContext var1) {
      this.outboundHandler.read(var1);
   }

   public void init(I var1, O var2) {
      this.validate((I)var1, (O)var2);
      this.inboundHandler = (I)var1;
      this.outboundHandler = (O)var2;
   }

   @Override
   public void userEventTriggered(ChannelHandlerContext var1, Object var2) {
      this.inboundHandler.userEventTriggered(var1, var2);
   }

   @Override
   public void flush(ChannelHandlerContext var1) {
      this.outboundHandler.flush(var1);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      if (this.inboundHandler == null) {
         throw new IllegalStateException(
            "init() must be invoked before being added to a "
               + ChannelPipeline.class.getSimpleName()
               + " if "
               + CombinedChannelDuplexHandler.class.getSimpleName()
               + " was constructed with the default constructor."
         );
      } else {
         try {
            this.inboundHandler.handlerAdded(var1);
         } finally {
            this.outboundHandler.handlerAdded(var1);
         }
      }
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      this.inboundHandler.exceptionCaught(var1, var2);
   }

   public O outboundHandler() {
      return this.outboundHandler;
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) {
      this.inboundHandler.channelActive(var1);
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) {
      this.inboundHandler.channelRegistered(var1);
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      this.inboundHandler.channelInactive(var1);
   }

   public CombinedChannelDuplexHandler() {
   }

   public void validate(I var1, O var2) {
      if (this.inboundHandler != null) {
         throw new IllegalStateException(
            "init() can not be invoked if " + CombinedChannelDuplexHandler.class.getSimpleName() + " was constructed with non-default constructor."
         );
      } else if (var1 == null) {
         throw new NullPointerException("inboundHandler");
      } else if (var2 == null) {
         throw new NullPointerException("outboundHandler");
      } else if (var1 instanceof ChannelOutboundHandler) {
         throw new IllegalArgumentException("inboundHandler must not implement " + ChannelOutboundHandler.class.getSimpleName() + " to get combined.");
      } else if (var2 instanceof ChannelInboundHandler) {
         throw new IllegalArgumentException("outboundHandler must not implement " + ChannelInboundHandler.class.getSimpleName() + " to get combined.");
      }
   }

   @Override
   public void channelReadComplete(ChannelHandlerContext var1) {
      this.inboundHandler.channelReadComplete(var1);
   }

   public CombinedChannelDuplexHandler(I var1, O var2) {
      this.init((I)var1, (O)var2);
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) {
      this.outboundHandler.close(var1, var2);
   }

   public I inboundHandler() {
      return this.inboundHandler;
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      this.outboundHandler.write(var1, var2, var3);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      this.inboundHandler.channelRead(var1, var2);
   }

   @Override
   public void channelUnregistered(ChannelHandlerContext var1) {
      this.inboundHandler.channelUnregistered(var1);
   }

   @Override
   public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) {
      this.outboundHandler.connect(var1, var2, var3, var4);
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
      try {
         this.inboundHandler.handlerRemoved(var1);
      } finally {
         this.outboundHandler.handlerRemoved(var1);
      }
   }

   @Override
   public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) {
      this.outboundHandler.disconnect(var1, var2);
   }

   @Override
   public void deregister(ChannelHandlerContext var1, ChannelPromise var2) {
      this.outboundHandler.deregister(var1, var2);
   }
}
