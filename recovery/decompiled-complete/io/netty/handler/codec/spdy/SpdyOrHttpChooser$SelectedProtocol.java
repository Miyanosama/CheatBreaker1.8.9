package io.netty.handler.codec.spdy;

import javazoom.jl.player.advanced.AdvancedPlayer;
import net.minecraft.entity.EntityMinecartCommandBlock$1;
import net.optifine.util.MathUtilsTest$1;
import org.apache.log4j.pattern.FormattingInfo;

public enum SpdyOrHttpChooser$SelectedProtocol {
   HTTP_1_0("http/1.0"),
   HTTP_1_1("http/1.1"),
   UNKNOWN("Unknown"),
   SPDY_3_1("spdy/3.1");

   public EntityMinecartCommandBlock$1 __junk4825371623561934735;
   public AdvancedPlayer __junk6056813038135770012;
   // $VF: synthetic field
   public static SpdyOrHttpChooser$SelectedProtocol[] $VALUES = new SpdyOrHttpChooser$SelectedProtocol[]{
      SpdyOrHttpChooser$SelectedProtocol.SPDY_3_1, HTTP_1_1, HTTP_1_0, SpdyOrHttpChooser$SelectedProtocol.UNKNOWN
   };
   public MathUtilsTest$1 __junk1403852100992097338;
   public FormattingInfo __junk7204439762767264927;
   public String name;

   public String protocolName() {
      return this.name;
   }

   public static SpdyOrHttpChooser$SelectedProtocol protocol(String var0) {
      for (SpdyOrHttpChooser$SelectedProtocol var4 : values()) {
         if (var4.protocolName().equals(var0)) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   public SpdyOrHttpChooser$SelectedProtocol(String var3) {
      this.name = var3;
   }
}
