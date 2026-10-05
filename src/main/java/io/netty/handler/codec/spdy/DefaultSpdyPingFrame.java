package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;
import net.minecraft.block.BlockDropper;

public class DefaultSpdyPingFrame implements SpdyPingFrame {
   public int id;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append(StringUtil.NEWLINE);
      var1.append("--> ID = ");
      var1.append(this.id());
      return var1.toString();
   }

   public DefaultSpdyPingFrame(int var1) {
      this.setId(var1);
   }

   @Override
   public int id() {
      return this.id;
   }

   @Override
   public SpdyPingFrame setId(int var1) {
      this.id = var1;
      return this;
   }
}
