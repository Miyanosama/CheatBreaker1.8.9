package net.minecraft.entity.passive;

import net.minecraft.client.renderer.block.model.ModelBlockDefinition$Variants;
import net.minecraft.crash.CrashReportCategory$1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;

public class EntityRabbit$AIAvoidEntity<T extends Entity> extends EntityAIAvoidEntity<T> {
   public EntityRabbit entityInstance;
   public GenLayerFuzzyZoom field_0004;
   public EnumWorldBlockLayer field_0000;
   public CrashReportCategory$1 field_0001;
   public ModelBlockDefinition$Variants field_0002;

   public EntityRabbit$AIAvoidEntity(EntityRabbit var1, Class<T> var2, float var3, double var4, double var6) {
      super(var1, var2, var3, var4, var6);
      this.entityInstance = var1;
   }

   @Override
   public void updateTask() {
      super.updateTask();
   }
}
