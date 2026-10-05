package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.stream.IngestServerTester$3;
import net.optifine.shaders.Iterator3d;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditorRenderer;

public class LayerVillagerArmor extends LayerBipedArmor {
   public Iterator3d field_0001;
   public CategoryNodeEditorRenderer field_0002;
   public IngestServerTester$3 field_0000;

   public LayerVillagerArmor(RendererLivingEntity<?> var1) {
      super(var1);
   }

   @Override
   public void initArmor() {
      this.c = new ModelZombieVillager(0.5F, 0.0F, true);
      this.d = new ModelZombieVillager(1.0F, 0.0F, true);
   }
}
