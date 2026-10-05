package net.minecraft.block;

import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Variant;
import net.minecraft.util.IStringSerializable;
import recovered.unidentified.UnidentifiedClass0517;

public enum BlockStairs$EnumShape implements IStringSerializable {
   INNER_LEFT("inner_left"),
   OUTER_LEFT("outer_left"),
   INNER_RIGHT("inner_right"),
   OUTER_RIGHT("outer_right"),
   STRAIGHT("straight");
   public ModelBlockDefinition$Variant field_0007;
   public String name;
   public UnidentifiedClass0517 field_0001;
   // $VF: synthetic field
   public static BlockStairs$EnumShape[] $VALUES = new BlockStairs$EnumShape[]{
      BlockStairs$EnumShape.STRAIGHT, INNER_LEFT, INNER_RIGHT, OUTER_LEFT, OUTER_RIGHT
   };

   @Override
   public String toString() {
      return this.name;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public BlockStairs$EnumShape(String var3) {
      this.name = var3;
   }
}
