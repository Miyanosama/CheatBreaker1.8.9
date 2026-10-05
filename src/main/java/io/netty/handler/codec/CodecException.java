package io.netty.handler.codec;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntityVillager;

public class CodecException extends RuntimeException {
   public static final long serialVersionUID = -1464830400709348473L;

   public CodecException() {
   }

   public CodecException(String var1) {
      super(var1);
   }

   public CodecException(Throwable var1) {
      super(var1);
   }

   public CodecException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
