package io.netty.channel.rxtx;

import io.netty.channel.ChannelOption;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$KeySetView;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

public class RxtxChannelOption<T> extends ChannelOption<T> {
   public static RxtxChannelOption<Integer> WAIT_TIME = new RxtxChannelOption<>("WAIT_TIME");
   public static RxtxChannelOption<Integer> BAUD_RATE = new RxtxChannelOption<>("BAUD_RATE");
   public StateMapperBase __junk1931694047562586401;
   public static RxtxChannelOption<Boolean> RTS = new RxtxChannelOption<>("RTS");
   public static RxtxChannelOption<Integer> READ_TIMEOUT = new RxtxChannelOption<>("READ_TIMEOUT");
   public static RxtxChannelOption<RxtxChannelConfig$Paritybit> PARITY_BIT = new RxtxChannelOption<>("PARITY_BIT");
   public static RxtxChannelOption<RxtxChannelConfig$Databits> DATA_BITS = new RxtxChannelOption<>("DATA_BITS");
   public static RxtxChannelOption<RxtxChannelConfig$Stopbits> STOP_BITS = new RxtxChannelOption<>("STOP_BITS");
   public ConcurrentHashMapV8$KeySetView __junk2935505759825391690;
   public static RxtxChannelOption<Boolean> DTR = new RxtxChannelOption<>("DTR");

   public RxtxChannelOption(String var1) {
      super(var1);
   }
}
