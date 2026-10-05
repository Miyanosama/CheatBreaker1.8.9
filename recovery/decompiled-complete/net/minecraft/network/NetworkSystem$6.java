package net.minecraft.network;

import java.util.concurrent.Callable;
import javax.vecmath.Point3i;
import net.minecraft.block.Block$3;

public class NetworkSystem$6 implements Callable<String> {
   public Block$3 field_0001;
   public Point3i field_0002;

   public NetworkSystem$6(NetworkSystem var1, NetworkManager var2) {
      this.field_0003 = var1;
      this.field_0000 = var2;
      super();
   }

   public String method_22410() {
      return this.field_0000.toString();
   }
}
