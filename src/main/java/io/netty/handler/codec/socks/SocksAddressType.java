package io.netty.handler.codec.socks;

public enum SocksAddressType {
      IPv4((byte)1),
      DOMAIN((byte)3),
      IPv6((byte)4),
      UNKNOWN((byte)-1);

   public static SocksAddressType[] $VALUES = new SocksAddressType[]{IPv4, DOMAIN, IPv6, UNKNOWN};
   public byte b;

   public static SocksAddressType fromByte(byte var0) {
      return valueOf(var0);
   }

   public byte byteValue() {
      return this.b;
   }

   SocksAddressType(byte var3) {
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
