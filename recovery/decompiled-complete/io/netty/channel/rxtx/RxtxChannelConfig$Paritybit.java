package io.netty.channel.rxtx;

import com.cheatbreaker.client.config.SettingsDetailLevel;
import io.netty.buffer.ByteBufProcessor$5;
import io.netty.channel.oio.AbstractOioByteChannel;
import io.netty.handler.codec.compression.SnappyFramedDecoder$1;
import recovered.unidentified.UnidentifiedClass3255;

public enum RxtxChannelConfig$Paritybit {
   ODD(1),
   SPACE(4),
   MARK(3),
   NONE(0),
   EVEN(2);
   public SettingsDetailLevel __junk6965539696745786607;
   public int value;
   public ByteBufProcessor$5 __junk3258520036242380493;
   public UnidentifiedClass3255 __junk2176057080193855060;
   // $VF: synthetic field
   public static RxtxChannelConfig$Paritybit[] $VALUES = new RxtxChannelConfig$Paritybit[]{NONE, ODD, RxtxChannelConfig$Paritybit.EVEN, MARK, SPACE};
   public SnappyFramedDecoder$1 __junk2205177120374005079;
   public AbstractOioByteChannel __junk3761769623385375805;

   public int value() {
      return this.value;
   }

   public static RxtxChannelConfig$Paritybit valueOf(int var0) {
      for (RxtxChannelConfig$Paritybit var4 : values()) {
         if (var4.value == var0) {
            return var4;
         }
      }

      throw new IllegalArgumentException("unknown " + RxtxChannelConfig$Paritybit.class.getSimpleName() + " value: " + var0);
   }

   public RxtxChannelConfig$Paritybit(int var3) {
      this.value = var3;
   }
}
