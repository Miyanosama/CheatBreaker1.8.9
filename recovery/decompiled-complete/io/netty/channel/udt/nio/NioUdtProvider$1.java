package io.netty.channel.udt.nio;

import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.KindUDT;
import net.minecraft.client.resources.data.LanguageMetadataSectionSerializer;

// $VF: synthetic class
public class NioUdtProvider$1 {
   public LanguageMetadataSectionSerializer __junk7210336630923069815;

   static {
      try {
         $SwitchMap$com$barchart$udt$nio$KindUDT[KindUDT.ACCEPTOR.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$com$barchart$udt$nio$KindUDT[KindUDT.CONNECTOR.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$com$barchart$udt$nio$KindUDT[KindUDT.RENDEZVOUS.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      $SwitchMap$com$barchart$udt$TypeUDT = new int[TypeUDT.values().length];

      try {
         $SwitchMap$com$barchart$udt$TypeUDT[TypeUDT.DATAGRAM.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$com$barchart$udt$TypeUDT[TypeUDT.STREAM.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
