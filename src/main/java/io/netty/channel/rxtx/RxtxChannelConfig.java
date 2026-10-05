package io.netty.channel.rxtx;

import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.oio.AbstractOioByteChannel;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.network.login.client.C00PacketLoginStart;
import org.apache.log4j.chainsaw.ControlPanel$4;
import com.cheatbreaker.client.ui.mainmenu.CreditsGui;

public interface RxtxChannelConfig extends ChannelConfig {
   RxtxChannelConfig setRts(boolean var1);

   RxtxChannelConfig setDtr(boolean var1);

   RxtxChannelConfig setWriteBufferLowWaterMark(int var1);

   int getBaudrate();

   RxtxChannelConfig setWriteSpinCount(int var1);

   RxtxChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1);

   RxtxChannelConfig setConnectTimeoutMillis(int var1);

   RxtxChannelConfig setMaxMessagesPerRead(int var1);

   RxtxChannelConfig setDatabits(RxtxChannelConfig.Databits var1);

   RxtxChannelConfig setParitybit(RxtxChannelConfig.Paritybit var1);

   RxtxChannelConfig.Databits getDatabits();

   RxtxChannelConfig setAllocator(ByteBufAllocator var1);

   boolean isDtr();

   RxtxChannelConfig setWriteBufferHighWaterMark(int var1);

   RxtxChannelConfig setBaudrate(int var1);

   RxtxChannelConfig.Paritybit getParitybit();

   int getWaitTimeMillis();

   RxtxChannelConfig setReadTimeout(int var1);

   RxtxChannelConfig setAutoRead(boolean var1);

   RxtxChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1);

   int getReadTimeout();

   RxtxChannelConfig setStopbits(RxtxChannelConfig.Stopbits var1);

   RxtxChannelConfig setWaitTimeMillis(int var1);

   boolean isRts();

   RxtxChannelConfig setAutoClose(boolean var1);

   RxtxChannelConfig.Stopbits getStopbits();

   public static enum Databits {
      DATABITS_5(5),
      DATABITS_6(6),
      DATABITS_7(7),
      DATABITS_8(8);
      public int value;
      // $VF: synthetic field
      public static RxtxChannelConfig.Databits[] $VALUES = new RxtxChannelConfig.Databits[]{
         DATABITS_5, DATABITS_6, DATABITS_7, RxtxChannelConfig.Databits.DATABITS_8
      };

      Databits(int var3) {
         this.value = var3;
      }

      public static RxtxChannelConfig.Databits valueOf(int var0) {
         for (RxtxChannelConfig.Databits var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         throw new IllegalArgumentException("unknown " + RxtxChannelConfig.Databits.class.getSimpleName() + " value: " + var0);
      }

      public int value() {
         return this.value;
      }
   }

   public static enum Paritybit {
      NONE(0),
      ODD(1),
      EVEN(2),
      MARK(3),
      SPACE(4);
      public int value;
      // $VF: synthetic field
      public static RxtxChannelConfig.Paritybit[] $VALUES = new RxtxChannelConfig.Paritybit[]{NONE, ODD, RxtxChannelConfig.Paritybit.EVEN, MARK, SPACE};

      public int value() {
         return this.value;
      }

      public static RxtxChannelConfig.Paritybit valueOf(int var0) {
         for (RxtxChannelConfig.Paritybit var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         throw new IllegalArgumentException("unknown " + RxtxChannelConfig.Paritybit.class.getSimpleName() + " value: " + var0);
      }

      Paritybit(int var3) {
         this.value = var3;
      }
   }

   public static enum Stopbits {
      STOPBITS_1(1),
      STOPBITS_2(2),
      STOPBITS_1_5(3);
      public int value;
      // $VF: synthetic field
      public static RxtxChannelConfig.Stopbits[] $VALUES = new RxtxChannelConfig.Stopbits[]{
         STOPBITS_1, RxtxChannelConfig.Stopbits.STOPBITS_2, RxtxChannelConfig.Stopbits.STOPBITS_1_5
      };

      Stopbits(int var3) {
         this.value = var3;
      }

      public static RxtxChannelConfig.Stopbits valueOf(int var0) {
         for (RxtxChannelConfig.Stopbits var4 : values()) {
            if (var4.value == var0) {
               return var4;
            }
         }

         throw new IllegalArgumentException("unknown " + RxtxChannelConfig.Stopbits.class.getSimpleName() + " value: " + var0);
      }

      public int value() {
         return this.value;
      }
   }
}
