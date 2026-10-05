package io.netty.handler.codec.socks;

import io.netty.util.HashedWheelTimer$HashedWheelBucket;
import net.minecraft.entity.monster.EntitySilverfish$AIHideInStone;

public enum SocksProtocolVersion {
   SOCKS5((byte)5),
   UNKNOWN((byte)-1),
   SOCKS4a((byte)4);

   public byte b;
   // $VF: synthetic field
   public static SocksProtocolVersion[] $VALUES = new SocksProtocolVersion[]{SocksProtocolVersion.SOCKS4a, SOCKS5, SocksProtocolVersion.UNKNOWN};
   public HashedWheelTimer$HashedWheelBucket __junk3514997451313431001;
   public EntitySilverfish$AIHideInStone __junk8151115004264678504;

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

   public SocksProtocolVersion(byte var3) {
      this.b = var3;
   }
}
