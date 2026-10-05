package io.netty.handler.codec.socks;

import net.minecraft.item.ItemTool;

public enum SocksAuthStatus {
   SUCCESS((byte)0),
   FAILURE((byte)-1);

   public byte b;
   public ItemTool __junk7682031798591811336;

   public static SocksAuthStatus fromByte(byte var0) {
      return valueOf(var0);
   }

   public byte byteValue() {
      return this.b;
   }

   public SocksAuthStatus(byte var3) {
      this.b = var3;
   }

   public static SocksAuthStatus valueOf(byte var0) {
      for (SocksAuthStatus var4 : values()) {
         if (var4.b == var0) {
            return var4;
         }
      }

      return FAILURE;
   }
}
