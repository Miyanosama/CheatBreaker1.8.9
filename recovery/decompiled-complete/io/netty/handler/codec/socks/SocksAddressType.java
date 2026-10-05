package io.netty.handler.codec.socks;

import io.netty.util.internal.logging.Slf4JLoggerFactory$1;

public enum SocksAddressType {
   IPv6((byte)4),
   IPv4((byte)1),
   DOMAIN((byte)3),
   UNKNOWN((byte)-1);

   public Slf4JLoggerFactory$1 __junk2910151610832274265;
   public byte b;

   public static SocksAddressType fromByte(byte var0) {
      return valueOf(var0);
   }

   public byte byteValue() {
      return this.b;
   }

   public SocksAddressType(byte var3) {
      this.b = var3;
   }

   public static SocksAddressType valueOf(byte var0) {
      for (SocksAddressType var4 : values()) {
         if (var4.b == var0) {
            return var4;
         }
      }

      return UNKNOWN;
   }
}
