package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.world.gen.feature.WorldGenTrees;
import org.java_websocket.framing.DataFrame;
import org.slf4j.helpers.SubstituteLogger;
import recovered.unidentified.UnidentifiedClass1745;

public class ModelAdapterSheep extends ModelAdapterQuadruped {
   public SubstituteLogger field_0001;
   public WorldGenTrees field_0003;
   public DataFrame field_0000;
   public UnidentifiedClass1745 field_0002;

   public ModelAdapterSheep() {
      super(EntitySheep.class, "sheep", 0.7F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSheep2();
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderSheep(var3, var1, var2);
   }
}
