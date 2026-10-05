package io.netty.channel;

import io.netty.util.ReferenceCountUtil;
import net.minecraft.block.BlockColored;

public class DefaultChannelPipeline$TailContext extends AbstractChannelHandlerContext implements ChannelInboundHandler {
   public BlockColored __junk4445508990310047104;
   public static String TAIL_NAME = DefaultChannelPipeline.access$300(DefaultChannelPipeline$TailContext.class);

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      try {
         DefaultChannelPipeline.logger
            .debug("Discarded inbound message {} that reached at the tail of the pipeline. Please check your pipeline configuration.", var2);
      } finally {
         ReferenceCountUtil.release(var2);
      }
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
   }

   @Override
   public void channelWritabilityChanged(ChannelHandlerContext var1) {
   }

   @Override
   public ChannelHandler handler() {
      return this;
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
   }

   @Override
   public void channelUnregistered(ChannelHandlerContext var1) {
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) {
   }

   @Override
   public void userEventTriggered(ChannelHandlerContext var1, Object var2) {
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) {
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      DefaultChannelPipeline.logger
         .warn(
            "An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.",
            var2
         );
   }

   public DefaultChannelPipeline$TailContext(DefaultChannelPipeline var1) {
      super(var1, null, TAIL_NAME, true, false);
   }

   @Override
   public void channelReadComplete(ChannelHandlerContext var1) {
   }
}
