package io.netty.handler.codec.socks;

import net.minecraft.enchantment.EnchantmentLootBonus;
import net.optifine.shaders.config.RenderScale;

public enum SocksCmdType {
      CONNECT((byte)1),
      BIND((byte)2),
      UDP((byte)3),
      UNKNOWN((byte)-1);

   public byte b;
   public static SocksCmdType[] $VALUES = new SocksCmdType[]{CONNECT, BIND, SocksCmdType.UDP, UNKNOWN};

   public byte byteValue() {
      return this.b;
   }

   public static SocksCmdType valueOf(byte var0) {
      for (SocksCmdType var4 : values()) {
         if (var4.b == var0) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   SocksCmdType(byte var3) {
      this.b = var3;
   }

   public static SocksCmdType fromByte(byte var0) {
      return valueOf(var0);
   }
}
