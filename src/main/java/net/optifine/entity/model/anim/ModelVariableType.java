package net.optifine.entity.model.anim;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.Config;

public enum ModelVariableType {
      POS_X("tx"),
      POS_Y("ty"),
      POS_Z("tz"),
      ANGLE_X("rx"),
      ANGLE_Y("ry"),
      ANGLE_Z("rz"),
      OFFSET_X("ox"),
      OFFSET_Y("oy"),
      OFFSET_Z("oz"),
      SCALE_X("sx"),
      SCALE_Y("sy"),
      SCALE_Z("sz");
   public static ModelVariableType[] $VALUES = new ModelVariableType[]{
      POS_X,
      ModelVariableType.POS_Y,
      POS_Z,
      ANGLE_X,
      ModelVariableType.ANGLE_Y,
      ModelVariableType.ANGLE_Z,
      ModelVariableType.OFFSET_X,
      OFFSET_Y,
      OFFSET_Z,
      ModelVariableType.SCALE_X,
      ModelVariableType.SCALE_Y,
      SCALE_Z
   };
   public String name;
   public static ModelVariableType[] VALUES = values();

   public static ModelVariableType parse(String var0) {
      for (int var1 = 0; var1 < VALUES.length; var1++) {
         ModelVariableType var2 = VALUES[var1];
         if (var2.getName().equals(var0)) {
            return var2;
         }
      }

      return null;
   }

   ModelVariableType(String var3) {
      this.name = var3;
   }

   public float getFloat(ModelRenderer var1) {
      switch (this) {
         case POS_X:
            return var1.rotationPointX;
         case POS_Y:
            return var1.rotationPointY;
         case POS_Z:
            return var1.rotationPointZ;
         case ANGLE_X:
            return var1.rotateAngleX;
         case ANGLE_Y:
            return var1.rotateAngleY;
         case ANGLE_Z:
            return var1.rotateAngleZ;
         case OFFSET_X:
            return var1.offsetX;
         case OFFSET_Y:
            return var1.offsetY;
         case OFFSET_Z:
            return var1.offsetZ;
         case SCALE_X:
            return var1.scaleX;
         case SCALE_Y:
            return var1.scaleY;
         case SCALE_Z:
            return var1.scaleZ;
         default:
            Config.warn("GetFloat not supported for: " + this);
            return 0.0F;
      }
   }

   public String getName() {
      return this.name;
   }

   public void setFloat(ModelRenderer var1, float var2) {
      switch (this) {
         case POS_X:
            var1.rotationPointX = var2;
            return;
         case POS_Y:
            var1.rotationPointY = var2;
            return;
         case POS_Z:
            var1.rotationPointZ = var2;
            return;
         case ANGLE_X:
            var1.rotateAngleX = var2;
            return;
         case ANGLE_Y:
            var1.rotateAngleY = var2;
            return;
         case ANGLE_Z:
            var1.rotateAngleZ = var2;
            return;
         case OFFSET_X:
            var1.offsetX = var2;
            return;
         case OFFSET_Y:
            var1.offsetY = var2;
            return;
         case OFFSET_Z:
            var1.offsetZ = var2;
            return;
         case SCALE_X:
            var1.scaleX = var2;
            return;
         case SCALE_Y:
            var1.scaleY = var2;
            return;
         case SCALE_Z:
            var1.scaleZ = var2;
            return;
         default:
            Config.warn("SetFloat not supported for: " + this);
      }
   }
}
