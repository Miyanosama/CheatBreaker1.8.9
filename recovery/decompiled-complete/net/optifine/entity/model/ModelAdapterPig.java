package net.optifine.entity.model;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$EncoderMode;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.init.Bootstrap$6;
import net.minecraft.world.gen.ChunkProviderSettings$Serializer;

public class ModelAdapterPig extends ModelAdapterQuadruped {
   public ChunkProviderSettings$Serializer field_0001;
   public HttpPostRequestEncoder$EncoderMode field_0002;
   public Bootstrap$6 field_0000;

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderPig(var3, var1, var2);
   }

   public ModelAdapterPig() {
      super(EntityPig.class, "pig", 0.7F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelPig();
   }
}
