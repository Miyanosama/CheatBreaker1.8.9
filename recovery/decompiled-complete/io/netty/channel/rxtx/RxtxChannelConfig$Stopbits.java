package io.netty.channel.rxtx;

import net.minecraft.client.model.ModelMinecart;

public enum RxtxChannelConfig$Stopbits {
   STOPBITS_1(1),
   STOPBITS_1_5(3),
   STOPBITS_2(2);
   public int value;
   // $VF: synthetic field
   public static RxtxChannelConfig$Stopbits[] $VALUES = new RxtxChannelConfig$Stopbits[]{
      STOPBITS_1, RxtxChannelConfig$Stopbits.STOPBITS_2, RxtxChannelConfig$Stopbits.STOPBITS_1_5
   };
   public ModelMinecart __junk4998655761607047879;

   public RxtxChannelConfig$Stopbits(int var3) {
      this.value = var3;
   }

   public static RxtxChannelConfig$Stopbits valueOf(int var0) {
      for (RxtxChannelConfig$Stopbits var4 : values()) {
         if (var4.value == var0) {
            return var4;
         }
      }

      throw new IllegalArgumentException("unknown " + RxtxChannelConfig$Stopbits.class.getSimpleName() + " value: " + var0);
   }

   public int value() {
      return this.value;
   }
}
