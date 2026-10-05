package io.netty.channel;

import java.net.SocketAddress;
import net.minecraft.client.resources.SimpleReloadableResourceManager$1;
import net.minecraft.init.Bootstrap$7;
import net.minecraft.world.gen.structure.StructureVillagePieces$Start;
import net.optifine.entity.model.ModelAdapterEndermite;
import net.optifine.render.CloudRenderer;

public class DefaultChannelPipeline$HeadContext extends AbstractChannelHandlerContext implements ChannelOutboundHandler {
   public CloudRenderer __junk8727301327447185380;
   public Channel$Unsafe unsafe;
   public SimpleReloadableResourceManager$1 __junk8609416621749730036;
   public Bootstrap$7 __junk1714526422982075746;
   public static String HEAD_NAME = DefaultChannelPipeline.access$300(DefaultChannelPipeline$HeadContext.class);
   public ModelAdapterEndermite __junk734983813839597075;
   public StructureVillagePieces$Start __junk5682973406737475620;

   @Override
   public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) {
      this.unsafe.bind(var2, var3);
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) {
      this.unsafe.close(var2);
   }

   @Override
   public void read(ChannelHandlerContext var1) {
      this.unsafe.beginRead();
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      this.unsafe.write(var2, var3);
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      var1.fireExceptionCaught(var2);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
   }

   @Override
   public void flush(ChannelHandlerContext var1) {
      this.unsafe.flush();
   }

   @Override
   public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) {
      this.unsafe.connect(var2, var3, var4);
   }

   @Override
   public ChannelHandler handler() {
      return this;
   }

   @Override
   public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) {
      this.unsafe.disconnect(var2);
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
   }

   public DefaultChannelPipeline$HeadContext(DefaultChannelPipeline var1) {
      super(var1, null, HEAD_NAME, false, true);
      this.unsafe = var1.channel().unsafe();
   }

   @Override
   public void deregister(ChannelHandlerContext var1, ChannelPromise var2) {
      this.unsafe.deregister(var2);
   }
}
