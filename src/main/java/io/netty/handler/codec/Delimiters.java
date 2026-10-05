package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.model.ModelPig;
import net.optifine.Lang;

public class Delimiters {

   public static ByteBuf[] lineDelimiter() {
      return new ByteBuf[]{Unpooled.wrappedBuffer(new byte[]{13, 10}), Unpooled.wrappedBuffer(new byte[]{10})};
   }

   public static ByteBuf[] nulDelimiter() {
      return new ByteBuf[]{Unpooled.wrappedBuffer(new byte[]{0})};
   }
}
