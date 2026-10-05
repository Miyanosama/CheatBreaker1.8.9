package junit.runner;

import com.cheatbreaker.client.module.type.BlockOverlayModule;
import net.minecraft.entity.passive.EntityHorse$1;
import net.optifine.EmissiveTextures;

public class SimpleTestCollector extends ClassPathTestCollector {
   public EntityHorse$1 field_0000;
   public EmissiveTextures field_0002;
   public BlockOverlayModule field_0001;

   public boolean isTestClass(String var1) {
      return var1.endsWith(".class") && var1.indexOf(36) < 0 && var1.indexOf("Test") > 0;
   }
}
