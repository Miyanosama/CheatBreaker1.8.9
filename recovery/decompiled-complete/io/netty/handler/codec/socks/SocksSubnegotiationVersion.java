package io.netty.handler.codec.socks;

import io.netty.util.internal.UnsafeAtomicLongFieldUpdater;
import net.minecraft.client.renderer.entity.ArmorStandRenderer$1;
import org.apache.log4j.chainsaw.Main;
import recovered.unidentified.UnidentifiedClass0611;

public enum SocksSubnegotiationVersion {
   UNKNOWN((byte)-1),
   AUTH_PASSWORD((byte)1);
   public ArmorStandRenderer$1 __junk3850220126115839184;
   public Main __junk4213878638183801161;
   public UnsafeAtomicLongFieldUpdater __junk7393994417270637328;
   public byte b;
   public UnidentifiedClass0611 __junk8630973022803176879;
   // $VF: synthetic field
   public static SocksSubnegotiationVersion[] $VALUES = new SocksSubnegotiationVersion[]{SocksSubnegotiationVersion.AUTH_PASSWORD, UNKNOWN};

   public SocksSubnegotiationVersion(byte var3) {
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
