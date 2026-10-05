package javax.vecmath;

import java.io.Serializable;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$1;

public class TexCoord4f extends Tuple4f implements Serializable {
   public static long field_0000;
   public StructureOceanMonumentPieces$1 field_0001;

   public TexCoord4f(TexCoord4f var1) {
      super(var1);
   }

   public TexCoord4f(Tuple4d var1) {
      super(var1);
   }

   public TexCoord4f() {
   }

   public TexCoord4f(float var1, float var2, float var3, float var4) {
      super(var1, var2, var3, var4);
   }

   public TexCoord4f(float[] var1) {
      super(var1);
   }

   public TexCoord4f(Tuple4f var1) {
      super(var1);
   }
}
