package io.netty.handler.codec.spdy;

import com.cheatbreaker.client.nethandler.server.PacketNotification;
import net.minecraft.block.BlockRailBase$1;
import net.minecraft.enchantment.EnchantmentProtection;

public class SpdyProtocolException extends Exception {
   public static long serialVersionUID;
   public PacketNotification __junk5796968454311614339;
   public EnchantmentProtection __junk9067934150996030718;
   public BlockRailBase$1 __junk623515444895340563;

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
