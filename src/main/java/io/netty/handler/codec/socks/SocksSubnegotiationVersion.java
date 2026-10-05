package io.netty.handler.codec.socks;

import io.netty.util.internal.UnsafeAtomicLongFieldUpdater;
import org.apache.log4j.chainsaw.Main;
import com.cheatbreaker.client.ui.module.ModuleGuiNavigator;

public enum SocksSubnegotiationVersion {
      AUTH_PASSWORD((byte)1),
      UNKNOWN((byte)-1);
   public byte b;
   public static SocksSubnegotiationVersion[] $VALUES = new SocksSubnegotiationVersion[]{SocksSubnegotiationVersion.AUTH_PASSWORD, UNKNOWN};

   SocksSubnegotiationVersion(byte var3) {
      this.b = var3;
   }

   public byte byteValue() {
      return this.b;
   }

   public static SocksSubnegotiationVersion valueOf(byte var0) {
      for (SocksSubnegotiationVersion var4 : values()) {
         if (var4.b == var0) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   public static SocksSubnegotiationVersion fromByte(byte var0) {
      return valueOf(var0);
   }
}
