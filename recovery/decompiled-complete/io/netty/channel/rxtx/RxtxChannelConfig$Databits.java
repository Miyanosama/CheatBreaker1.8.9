package io.netty.channel.rxtx;

import com.cheatbreaker.client.config.GlobalSettings;
import net.minecraft.network.login.client.C00PacketLoginStart;
import org.apache.log4j.chainsaw.ControlPanel$4;

public enum RxtxChannelConfig$Databits {
   DATABITS_7(7),
   DATABITS_6(6),
   DATABITS_5(5),
   DATABITS_8(8);
   public GlobalSettings __junk6715188379330871292;
   public C00PacketLoginStart __junk4677314296253916823;
   public int value;
   public ControlPanel$4 __junk4242386378512596468;
   // $VF: synthetic field
   public static RxtxChannelConfig$Databits[] $VALUES = new RxtxChannelConfig$Databits[]{
      DATABITS_5, DATABITS_6, DATABITS_7, RxtxChannelConfig$Databits.DATABITS_8
   };

   public RxtxChannelConfig$Databits(int var3) {
      this.value = var3;
   }

   public static RxtxChannelConfig$Databits valueOf(int var0) {
      for (RxtxChannelConfig$Databits var4 : values()) {
         if (var4.value == var0) {
            return var4;
         }
      }

      throw new IllegalArgumentException("unknown " + RxtxChannelConfig$Databits.class.getSimpleName() + " value: " + var0);
   }

   public int value() {
      return this.value;
   }
}
