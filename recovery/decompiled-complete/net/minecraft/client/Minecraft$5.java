package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.world.gen.structure.StructureVillagePieces$Hall;
import net.optifine.reflect.ReflectorRaw;

public class Minecraft$5 implements Callable<String> {
   public StructureVillagePieces$Hall field_0001;
   public ReflectorRaw field_0000;

   public String call() {
      return Minecraft.access$100(this.field_142056_a).getCurrentLanguage().toString();
   }

   public Minecraft$5(Minecraft var1) {
      this.field_142056_a = var1;
      super();
   }
}
