package io.netty.channel.socket.nio;

import io.netty.channel.socket.InternetProtocolFamily;
import java.net.ProtocolFamily;
import java.net.StandardProtocolFamily;
import net.minecraft.item.ItemFireball;
import net.optifine.shaders.SMCLog;
import recovered.unidentified.UnidentifiedClass0943;

public class ProtocolFamilyConverter {
   public SMCLog __junk7257837161818927499;
   public ItemFireball __junk7715135521192889755;
   public UnidentifiedClass0943 __junk1162243100769837515;

   public static ProtocolFamily convert(InternetProtocolFamily var0) {
      switch (ProtocolFamilyConverter$1.$SwitchMap$io$netty$channel$socket$InternetProtocolFamily[var0.ordinal()]) {
         case 1:
            return StandardProtocolFamily.INET;
         case 2:
            return StandardProtocolFamily.INET6;
         default:
            throw new IllegalArgumentException();
      }
   }
}
