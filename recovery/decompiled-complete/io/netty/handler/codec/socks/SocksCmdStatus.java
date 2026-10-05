package io.netty.handler.codec.socks;

import org.apache.log4j.xml.DOMConfigurator$2;

public enum SocksCmdStatus {
   NETWORK_UNREACHABLE((byte)3),
   HOST_UNREACHABLE((byte)4),
   TTL_EXPIRED((byte)6),
   REFUSED((byte)5),
   COMMAND_NOT_SUPPORTED((byte)7),
   ADDRESS_NOT_SUPPORTED((byte)8),
   FAILURE((byte)1),
   UNASSIGNED((byte)-1),
   FORBIDDEN((byte)2),
   SUCCESS((byte)0);
   // $VF: synthetic field
   public static SocksCmdStatus[] $VALUES = new SocksCmdStatus[]{
      SocksCmdStatus.SUCCESS,
      SocksCmdStatus.FAILURE,
      SocksCmdStatus.FORBIDDEN,
      NETWORK_UNREACHABLE,
      SocksCmdStatus.HOST_UNREACHABLE,
      SocksCmdStatus.REFUSED,
      SocksCmdStatus.TTL_EXPIRED,
      SocksCmdStatus.COMMAND_NOT_SUPPORTED,
      SocksCmdStatus.ADDRESS_NOT_SUPPORTED,
      SocksCmdStatus.UNASSIGNED
   };
   public byte b;
   public DOMConfigurator$2 __junk5467188988684163313;

   public static SocksCmdStatus fromByte(byte var0) {
      return valueOf(var0);
   }

   public SocksCmdStatus(byte var3) {
      this.b = var3;
   }

   public static SocksCmdStatus valueOf(byte var0) {
      for (SocksCmdStatus var4 : values()) {
         if (var4.b == var0) {
            return var4;
         }
      }

      return UNASSIGNED;
   }

   public byte byteValue() {
      return this.b;
   }
}
