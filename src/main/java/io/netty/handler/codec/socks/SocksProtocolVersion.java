package io.netty.handler.codec.socks;

import io.netty.util.HashedWheelTimer;
import net.minecraft.entity.monster.EntitySilverfish;

public enum SocksProtocolVersion {
      SOCKS4a((byte)4),
      SOCKS5((byte)5),
      UNKNOWN((byte)-1);

   public byte b;
   public static SocksProtocolVersion[] $VALUES = new SocksProtocolVersion[]{SocksProtocolVersion.SOCKS4a, SOCKS5, SocksProtocolVersion.UNKNOWN};

   public byte byteValue() {
      return this.b;
   }

   public static SocksProtocolVersion fromByte(byte var0) {
      return valueOf(var0);
   }

   public static SocksProtocolVersion valueOf(byte var0) {
      for (SocksProtocolVersion var4 : values()) {
         if (var4.b == var0) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   SocksProtocolVersion(byte var3) {
      this.b = var3;
   }
}
