package io.netty.handler.codec.socks;

import net.optifine.util.CompactArrayList;

public enum SocksAuthScheme {
      NO_AUTH((byte)0),
      AUTH_GSSAPI((byte)1),
      AUTH_PASSWORD((byte)2),
      UNKNOWN((byte)-1);

   public byte b;
   public static SocksAuthScheme[] $VALUES = new SocksAuthScheme[]{NO_AUTH, AUTH_GSSAPI, AUTH_PASSWORD, UNKNOWN};

   public byte byteValue() {
      return this.b;
   }

   SocksAuthScheme(byte var3) {
      this.b = var3;
   }

   public static SocksAuthScheme valueOf(byte var0) {
      for (SocksAuthScheme var4 : values()) {
         if (var4.b == var0) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   public static SocksAuthScheme fromByte(byte var0) {
      return valueOf(var0);
   }
}
