package io.netty.handler.codec.socks;

import net.minecraft.world.gen.structure.StructureVillagePieces$1;
import net.optifine.util.CompactArrayList;

public enum SocksAuthScheme {
   UNKNOWN((byte)-1),
   AUTH_GSSAPI((byte)1),
   NO_AUTH((byte)0),
   AUTH_PASSWORD((byte)2);
   public byte b;
   public CompactArrayList __junk675981774592255976;
   public StructureVillagePieces$1 __junk2887083174552895711;

   public byte byteValue() {
      return this.b;
   }

   public SocksAuthScheme(byte var3) {
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
