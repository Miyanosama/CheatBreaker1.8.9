package net.optifine.entity.model.anim;

import net.minecraft.block.BlockDropper;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.Config;

public enum ModelVariableType {
   SCALE_Z("sz"),
   POS_Z("tz"),
   OFFSET_Y("oy"),
   POS_X("tx"),
   OFFSET_Z("oz"),
   ANGLE_X("rx"),
   ANGLE_Z("rz"),
   OFFSET_X("ox"),
   SCALE_Y("sy"),
   ANGLE_Y("ry"),
   POS_Y("ty"),
   SCALE_X("sx");
   public static ModelVariableType[] VALUES = values();
   public BlockDropper field_0005;
   public String name;
   // $VF: synthetic field
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

   public static ModelVariableType parse(String var0) {
      for (int var1 = 0; var1 < VALUES.length; var1++) {
         ModelVariableType var2 = VALUES[var1];
         if (var2.getName().equals(var0)) {
            return var2;
         }
      }

      return null;
   }

   public ModelVariableType(String var3) {
      this.name = var3;
   }

   public float getFloat(ModelRenderer var1) {
      switch (ModelVariableType$1.$SwitchMap$net$optifine$entity$model$anim$ModelVariableType[this.ordinal()]) {
         case 1:
            return var1.rotationPointX;
         case 2:
            return var1.rotationPointY;
         case 3:
            return var1.rotationPointZ;
         case 4:
            return var1.rotateAngleX;
         case 5:
            return var1.rotateAngleY;
         case 6:
            return var1.rotateAngleZ;
         case 7:
            return var1.offsetX;
         case 8:
            return var1.offsetY;
         case 9:
            return var1.offsetZ;
         case 10:
            return var1.scaleX;
         case 11:
            return var1.scaleY;
         case 12:
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
      switch (ModelVariableType$1.$SwitchMap$net$optifine$entity$model$anim$ModelVariableType[this.ordinal()]) {
         case 1:
            var1.rotationPointX = var2;
            return;
         case 2:
            var1.rotationPointY = var2;
            return;
         case 3:
            var1.rotationPointZ = var2;
            return;
         case 4:
            var1.rotateAngleX = var2;
            return;
         case 5:
            var1.rotateAngleY = var2;
            return;
         case 6:
            var1.rotateAngleZ = var2;
            return;
         case 7:
            var1.offsetX = var2;
            return;
         case 8:
            var1.offsetY = var2;
            return;
         case 9:
            var1.offsetZ = var2;
            return;
         case 10:
            var1.scaleX = var2;
            return;
         case 11:
            var1.scaleY = var2;
            return;
         case 12:
            var1.scaleZ = var2;
            return;
         default:
            Config.warn("SetFloat not supported for: " + this);
      }
   }
}
