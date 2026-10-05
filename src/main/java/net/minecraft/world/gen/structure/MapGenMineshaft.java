package net.minecraft.world.gen.structure;

import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.util.MathHelper;

public class MapGenMineshaft extends MapGenStructure {
   public double field_82673_e = 0.004;

   @Override
   public String getStructureName() {
      return "Mineshaft";
   }

   public MapGenMineshaft(Map<String, String> var1) {
      for (Entry var3 : var1.entrySet()) {
         if (((String)var3.getKey()).equals("chance")) {
            this.field_82673_e = MathHelper.parseDoubleWithDefault((String)var3.getValue(), this.field_82673_e);
         }
      }
   }

   public MapGenMineshaft() {
   }

   @Override
   public boolean canSpawnStructureAtCoords(int var1, int var2) {
      return this.b.nextDouble() < this.field_82673_e && this.b.nextInt(80) < Math.max(Math.abs(var1), Math.abs(var2));
   }

   @Override
   public StructureStart getStructureStart(int var1, int var2) {
      return new StructureMineshaftStart(this.c, this.b, var1, var2);
   }
}
