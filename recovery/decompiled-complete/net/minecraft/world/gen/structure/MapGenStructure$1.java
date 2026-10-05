package net.minecraft.world.gen.structure;

import java.util.concurrent.Callable;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.optifine.entity.model.ModelAdapterSnowman;
import recovered.unidentified.UnidentifiedClass3838;

public class MapGenStructure$1 implements Callable<String> {
   public ModelAdapterSnowman field_0006;
   public UnidentifiedClass3838 field_0002;
   public SlotFurnaceOutput field_0005;
   public FontMetadataSectionSerializer field_0001;
   public BaseAttributeMap field_0004;

   public String call() {
      return this.field_85168_c.canSpawnStructureAtCoords(this.field_85169_a, this.field_85167_b) ? "True" : "False";
   }

   public MapGenStructure$1(MapGenStructure var1, int var2, int var3) {
      this.field_85168_c = var1;
      this.field_85169_a = var2;
      this.field_85167_b = var3;
      super();
   }
}
