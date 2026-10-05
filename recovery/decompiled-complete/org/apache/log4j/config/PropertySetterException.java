package org.apache.log4j.config;

import javax.vecmath.Quat4f;
import net.minecraft.block.BlockFlowerPot$EnumFlowerType;
import org.apache.log4j.pattern.RelativeTimePatternConverter;

public class PropertySetterException extends Exception {
   public RelativeTimePatternConverter field_0002;
   public static long field_0004;
   public Throwable rootCause;
   public BlockFlowerPot$EnumFlowerType field_0003;
   public Quat4f field_0000;

   public PropertySetterException(String var1) {
      super(var1);
   }

   public String getMessage() {
      String var1 = super.getMessage();
      if (var1 == null && this.rootCause != null) {
         var1 = this.rootCause.getMessage();
      }

      return var1;
   }

   public PropertySetterException(Throwable var1) {
      this.rootCause = var1;
   }
}
