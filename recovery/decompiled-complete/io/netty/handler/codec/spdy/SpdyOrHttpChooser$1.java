package io.netty.handler.codec.spdy;

import net.minecraft.command.server.CommandEmote;

// $VF: synthetic class
public class SpdyOrHttpChooser$1 {
   public CommandEmote __junk6140742757046078521;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyOrHttpChooser$SelectedProtocol[SpdyOrHttpChooser$SelectedProtocol.UNKNOWN.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyOrHttpChooser$SelectedProtocol[SpdyOrHttpChooser$SelectedProtocol.SPDY_3_1.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyOrHttpChooser$SelectedProtocol[SpdyOrHttpChooser$SelectedProtocol.HTTP_1_0.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$spdy$SpdyOrHttpChooser$SelectedProtocol[SpdyOrHttpChooser$SelectedProtocol.HTTP_1_1.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
