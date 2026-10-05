package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.nethandler.server.PacketNotification;
import net.minecraft.enchantment.EnchantmentProtection;

public class SpdyProtocolException extends Exception {
   public static final long serialVersionUID = 7870000537743847264L;

   public SpdyProtocolException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public SpdyProtocolException() {
   }

   public SpdyProtocolException(String var1) {
      super(var1);
   }

   public SpdyProtocolException(Throwable var1) {
      super(var1);
   }
}
