package net.minecraft.network.play.server;

import io.netty.channel.sctp.nio.NioSctpChannel$2;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker13;
import io.netty.handler.ssl.JettyNpnSslEngine$2;
import junit.swingui.TestSelector$TestCellRenderer;
import net.minecraft.command.CommandCompare;

// $VF: synthetic class
public class S44PacketWorldBorder$1 {
   public TestSelector$TestCellRenderer field_0003;
   public JettyNpnSslEngine$2 field_0005;
   public WebSocketClientHandshaker13 field_0002;
   public CommandCompare field_0004;
   public NioSctpChannel$2 field_0000;

   static {
      try {
         field_179947_a[S44PacketWorldBorder$Action.SET_SIZE.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_179947_a[S44PacketWorldBorder$Action.LERP_SIZE.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_179947_a[S44PacketWorldBorder$Action.SET_CENTER.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_179947_a[S44PacketWorldBorder$Action.SET_WARNING_BLOCKS.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_179947_a[S44PacketWorldBorder$Action.SET_WARNING_TIME.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_179947_a[S44PacketWorldBorder$Action.INITIALIZE.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
