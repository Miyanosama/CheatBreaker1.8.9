package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.minecraft.client.gui.GuiFlatPresets$ListSlot;
import net.minecraft.client.model.ModelBlaze;
import net.optifine.reflect.FieldLocatorFixed;
import org.slf4j.helpers.Util;

public class SocksMessageEncoder extends MessageToByteEncoder<SocksMessage> {
   public static String name;
   public ModelBlaze __junk3864896372347307088;
   public Util __junk1950751525667052459;
   public GuiFlatPresets$ListSlot __junk8709099597449855099;
   public FieldLocatorFixed __junk1265340788034340359;

   public static String getName() {
      return "SOCKS_MESSAGE_ENCODER";
   }

   public void encode(ChannelHandlerContext var1, SocksMessage var2, ByteBuf var3) {
      var2.encodeAsByteBuf(var3);
   }
}
