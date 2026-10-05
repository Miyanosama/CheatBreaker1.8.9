package io.netty.channel;

import java.net.SocketAddress;
import recovered.unidentified.UnidentifiedClass1605;

public class ChannelDuplexHandler extends ChannelInboundHandlerAdapter implements ChannelOutboundHandler {
   public UnidentifiedClass1605 __junk3794377568869839116;

   @Override
   public void read(ChannelHandlerContext var1) {
      var1.read();
   }

   @Override
   public void flush(ChannelHandlerContext var1) {
      var1.flush();
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      var1.write(var2, var3);
   }

   @Override
   public void deregister(ChannelHandlerContext var1, ChannelPromise var2) {
      var1.deregister(var2);
   }

   @Override
   public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) {
      var1.connect(var2, var3, var4);
   }

   @Override
   public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) {
      var1.bind(var2, var3);
   }

   @Override
   public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) {
      var1.disconnect(var2);
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) {
      var1.close(var2);
   }
}
