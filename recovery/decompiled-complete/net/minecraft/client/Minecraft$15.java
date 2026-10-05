package net.minecraft.client;

import java.util.concurrent.Callable;
import net.optifine.reflect.ReflectorConstructor;
import org.lwjgl.Sys;

public class Minecraft$15 implements Callable<String> {
   public ReflectorConstructor field_0001;

   public Minecraft$15(Minecraft var1) {
      this.field_74503_a = var1;
      super();
   }

   public String call() {
      return Sys.getVersion();
   }
}
