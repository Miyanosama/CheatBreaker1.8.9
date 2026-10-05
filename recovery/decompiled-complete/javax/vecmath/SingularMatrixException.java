package javax.vecmath;

import net.minecraft.world.gen.NoiseGeneratorSimplex;

public class SingularMatrixException extends RuntimeException {
   public NoiseGeneratorSimplex field_0000;

   public SingularMatrixException(String var1) {
      super(var1);
   }

   public SingularMatrixException() {
   }
}
