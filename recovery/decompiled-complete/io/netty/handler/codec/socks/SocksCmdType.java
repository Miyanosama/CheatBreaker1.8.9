package io.netty.handler.codec.socks;

import net.minecraft.enchantment.EnchantmentLootBonus;
import net.optifine.shaders.config.RenderScale;

public enum SocksCmdType {
   UNKNOWN((byte)-1),
   CONNECT((byte)1),
   BIND((byte)2),
   UDP((byte)3);

   public byte b;
   // $VF: synthetic field
   public static SocksCmdType[] $VALUES = new SocksCmdType[]{CONNECT, BIND, SocksCmdType.UDP, UNKNOWN};
   public RenderScale __junk4180900555383359561;
   public EnchantmentLootBonus __junk7903479911927013131;

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

   public SocksCmdType(byte var3) {
      this.b = var3;
   }

   public static SocksCmdType fromByte(byte var0) {
      return valueOf(var0);
   }
}
