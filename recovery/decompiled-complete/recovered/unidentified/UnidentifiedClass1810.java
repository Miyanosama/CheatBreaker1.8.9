package recovered.unidentified;

import java.util.concurrent.Callable;
import net.minecraft.entity.passive.EntityRabbit$EnumMoveType;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.log4j.PropertyConfigurator;

public class UnidentifiedClass1810 implements Callable<String> {
   public EntityRabbit$EnumMoveType field_0001;
   public PropertyConfigurator field_0002;

   public UnidentifiedClass1810(BiomeGenBase var1) {
      this.field_0000 = var1;
      super();
   }

   public String method_12479() {
      return String.valueOf(this.field_0000);
   }
}
