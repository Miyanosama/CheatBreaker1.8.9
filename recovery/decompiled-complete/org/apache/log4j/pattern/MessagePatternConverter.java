package org.apache.log4j.pattern;

import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.client.particle.EntityExplodeFX;
import net.minecraft.client.renderer.block.model.ModelBlock$Deserializer;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import org.apache.log4j.spi.LoggingEvent;

public class MessagePatternConverter extends LoggingEventPatternConverter {
   public MessageToMessageDecoder field_0002;
   public C0DPacketCloseWindow field_0004;
   public EntityExplodeFX field_0001;
   public static MessagePatternConverter field_0003 = new MessagePatternConverter();
   public ModelBlock$Deserializer field_0000;

   public void format(LoggingEvent var1, StringBuffer var2) {
      var2.append(var1.getRenderedMessage());
   }

   public MessagePatternConverter() {
      super("Message", "message");
   }

   public static MessagePatternConverter method_29443(String[] var0) {
      return field_0003;
   }
}
