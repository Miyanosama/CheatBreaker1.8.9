package javax.vecmath;

import java.io.Serializable;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;

public class TexCoord3f extends Tuple3f implements Serializable {
   public S32PacketConfirmTransaction field_0001;
   public static long field_0002;
   public S23PacketBlockChange field_0000;

   public TexCoord3f(Tuple3f var1) {
      super(var1);
   }

   public TexCoord3f(Tuple3d var1) {
      super(var1);
   }

   public TexCoord3f(float[] var1) {
      super(var1);
   }

   public TexCoord3f(TexCoord3f var1) {
      super(var1);
   }

   public TexCoord3f() {
   }

   public TexCoord3f(float var1, float var2, float var3) {
      super(var1, var2, var3);
   }
}
