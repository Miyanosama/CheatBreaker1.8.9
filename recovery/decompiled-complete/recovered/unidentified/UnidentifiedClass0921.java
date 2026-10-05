package recovered.unidentified;

import java.util.concurrent.Callable;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.util.NativeMemory$1;

public class UnidentifiedClass0921 implements Callable<String> {
   public ServerAddress field_0001;
   public NativeMemory$1 field_0000;

   public String method_06194() {
      return String.valueOf(this.field_0002);
   }

   public UnidentifiedClass0921(BiomeGenBase var1) {
      this.field_0002 = var1;
      super();
   }
}
