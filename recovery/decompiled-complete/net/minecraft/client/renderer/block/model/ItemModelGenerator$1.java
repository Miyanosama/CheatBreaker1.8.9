package net.minecraft.client.renderer.block.model;

import net.minecraft.util.BlockPos$1;
import net.optifine.expr.ExpressionFloatCached;
import org.apache.log4j.PatternLayout;

// $VF: synthetic class
public class ItemModelGenerator$1 {
   public PatternLayout field_0001;
   public BlockPos$1 field_0000;
   public ExpressionFloatCached field_0002;

   static {
      try {
         field_0003[ItemModelGenerator$SpanFacing.UP.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0003[ItemModelGenerator$SpanFacing.DOWN.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0003[ItemModelGenerator$SpanFacing.LEFT.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0003[ItemModelGenerator$SpanFacing.RIGHT.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
