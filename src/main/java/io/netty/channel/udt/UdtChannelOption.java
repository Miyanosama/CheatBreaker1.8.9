package io.netty.channel.udt;

import io.netty.channel.ChannelOption;
import net.minecraft.util.EnumWorldBlockLayer;
import com.cheatbreaker.client.util.render.LegacyItemAnimations;

public class UdtChannelOption<T> extends ChannelOption<T> {
   public static UdtChannelOption<Integer> PROTOCOL_RECEIVE_BUFFER_SIZE = new UdtChannelOption<>("PROTOCOL_RECEIVE_BUFFER_SIZE");
   public static UdtChannelOption<Integer> PROTOCOL_SEND_BUFFER_SIZE = new UdtChannelOption<>("PROTOCOL_SEND_BUFFER_SIZE");
   public static UdtChannelOption<Integer> SYSTEM_RECEIVE_BUFFER_SIZE = new UdtChannelOption<>("SYSTEM_RECEIVE_BUFFER_SIZE");
   public static UdtChannelOption<Integer> SYSTEM_SEND_BUFFER_SIZE = new UdtChannelOption<>("SYSTEM_SEND_BUFFER_SIZE");

   public UdtChannelOption(String var1) {
      super(var1);
   }
}
