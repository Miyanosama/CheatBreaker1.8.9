package io.netty.handler.ssl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.socks.SocksInitRequestDecoder$State;
import net.minecraft.client.gui.GuiTextField;
import net.optifine.gui.GuiPerformanceSettingsOF;

public class SslHandler$6 implements Runnable {
   public GuiPerformanceSettingsOF __junk2160789950631108304;
   public GuiTextField __junk2322409188598667569;
   public SocksInitRequestDecoder$State __junk5592616905479283323;

   public SslHandler$6(SslHandler var1, ChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$promise = var3;
      super();
   }

   @Override
   public void run() {
      SslHandler.access$200().warn(this.val$ctx.channel() + " last write attempt timed out." + " Force-closing the connection.");
      this.val$ctx.close(this.val$promise);
   }
}
