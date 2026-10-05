package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.resources.SkinManager$1;
import net.minecraft.crash.CrashReport$6;
import net.optifine.Lang;

public class Delimiters {
   public CrashReport$6 __junk1423355578096967207;
   public ModelPig __junk8244298959458076751;
   public Lang __junk7451978182685508892;
   public SkinManager$1 __junk4586938270541080848;

   public static ByteBuf[] lineDelimiter() {
      return new ByteBuf[]{Unpooled.wrappedBuffer(new byte[]{13, 10}), Unpooled.wrappedBuffer(new byte[]{10})};
   }

   public static ByteBuf[] nulDelimiter() {
      return new ByteBuf[]{Unpooled.wrappedBuffer(new byte[]{0})};
   }
}
