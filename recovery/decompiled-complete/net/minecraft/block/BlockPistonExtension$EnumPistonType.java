package net.minecraft.block;

import net.minecraft.util.IStringSerializable;
import net.optifine.expr.FunctionType$1;

public enum BlockPistonExtension$EnumPistonType implements IStringSerializable {
   STICKY("sticky"),
   DEFAULT("normal");

   public FunctionType$1 field_0002;
   public String VARIANT;

   @Override
   public String getName() {
      return this.VARIANT;
   }

   public BlockPistonExtension$EnumPistonType(String var3) {
      this.VARIANT = var3;
   }

   @Override
   public String toString() {
      return this.VARIANT;
   }
}
