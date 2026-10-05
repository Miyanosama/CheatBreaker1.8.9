package net.minecraft.client.renderer.entity;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$KeyIterator;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.util.ResourceLocation;
import net.optifine.gui.GuiPerformanceSettingsOF;
import recovered.unidentified.UnidentifiedClass1443;
import recovered.unidentified.UnidentifiedClass3556;

public class RenderGiantZombie extends RenderLiving<EntityGiantZombie> {
   public GuiPerformanceSettingsOF field_0001;
   public ConcurrentHashMapV8$KeyIterator field_0004;
   public UnidentifiedClass3556 field_0005;
   public static ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
   public UnidentifiedClass1443 field_0002;
   public float scale;

   @Override
   public void y_() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   public RenderGiantZombie(RenderManager var1, ModelBase var2, float var3, float var4) {
      super(var1, var2, var3 * var4);
      this.scale = var4;
      this.a(new LayerHeldItem(this));
      this.a(new RenderGiantZombie$1(this, this));
   }

   public void preRenderCallback(EntityGiantZombie var1, float var2) {
      GlStateManager.scale(this.scale, this.scale, this.scale);
   }

   public ResourceLocation getEntityTexture(EntityGiantZombie var1) {
      return zombieTextures;
   }
}
