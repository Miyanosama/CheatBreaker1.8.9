package io.netty.handler.timeout;

import io.netty.channel.AbstractChannel$AbstractUnsafe$7;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.item.ItemArmorStand;

public class IdleStateHandler$WriterIdleTimeoutTask implements Runnable {
   public AbstractChannel$AbstractUnsafe$7 __junk1830954608929047161;
   public ItemArmorStand __junk1498104923248590590;
   public JdkZlibDecoder __junk4500828675116166393;
   public ShaderManager __junk6884778761104821753;
   public ChannelHandlerContext ctx;

   @Override
   public void run() {
      if (this.ctx.channel().isOpen()) {
         long var1 = System.nanoTime();
         long var3 = this.this$0.lastWriteTime;
         long var5 = IdleStateHandler.access$400(this.this$0) - (var1 - var3);
         if (var5 <= (-1140389693691084720L & 9441920L)) {
            this.this$0.writerIdleTimeout = this.ctx.executor().schedule(this, IdleStateHandler.access$400(this.this$0), TimeUnit.NANOSECONDS);

            try {
               IdleStateEvent var7;
               if (IdleStateHandler.access$000(this.this$0)) {
                  IdleStateHandler.access$002(this.this$0, false);
                  var7 = IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT;
               } else {
                  var7 = IdleStateEvent.WRITER_IDLE_STATE_EVENT;
               }

               this.this$0.channelIdle(this.ctx, var7);
            } catch (Throwable var8) {
               this.ctx.fireExceptionCaught(var8);
            }
         } else {
            this.this$0.writerIdleTimeout = this.ctx.executor().schedule(this, var5, TimeUnit.NANOSECONDS);
         }
      }
   }

   public IdleStateHandler$WriterIdleTimeoutTask(IdleStateHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      super();
      this.ctx = var2;
   }
}
