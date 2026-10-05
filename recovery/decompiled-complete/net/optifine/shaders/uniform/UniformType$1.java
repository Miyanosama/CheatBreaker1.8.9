package net.optifine.shaders.uniform;

import javazoom.jl.decoder.LayerIIIDecoder;
import net.minecraft.world.gen.structure.StructureComponent$BlockSelector;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$LeftTurn;

// $VF: synthetic class
public class UniformType$1 {
   public StructureStrongholdPieces$LeftTurn field_0001;
   public LayerIIIDecoder field_0003;
   public StructureComponent$BlockSelector field_0000;

   static {
      try {
         $SwitchMap$net$optifine$shaders$uniform$UniformType[UniformType.BOOL.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$net$optifine$shaders$uniform$UniformType[UniformType.INT.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$net$optifine$shaders$uniform$UniformType[UniformType.FLOAT.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$net$optifine$shaders$uniform$UniformType[UniformType.VEC2.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$optifine$shaders$uniform$UniformType[UniformType.VEC3.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$optifine$shaders$uniform$UniformType[UniformType.VEC4.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
