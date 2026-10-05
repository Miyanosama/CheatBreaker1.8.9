package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.client.gui.GuiFlatPresets;
import net.minecraft.client.model.ModelBlaze;
import net.optifine.reflect.FieldLocatorFixed;
import org.slf4j.helpers.Util;

public class SocksMessageEncoder extends MessageToByteEncoder<SocksMessage> {
   public static final String name = "SOCKS_MESSAGE_ENCODER";

   public static String getName() {
      return "SOCKS_MESSAGE_ENCODER";
   }

   public void encode(ChannelHandlerContext var1, SocksMessage var2, ByteBuf var3) throws java.lang.Exception {
      var2.encodeAsByteBuf(var3);
   }
}
