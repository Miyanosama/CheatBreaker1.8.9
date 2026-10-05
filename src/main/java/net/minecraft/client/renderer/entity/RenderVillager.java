package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;

public class RenderVillager extends RenderLiving<EntityVillager> {
   public static ResourceLocation villagerTextures = new ResourceLocation("textures/entity/villager/villager.png");
   public static ResourceLocation farmerVillagerTextures = new ResourceLocation("textures/entity/villager/farmer.png");
   public static ResourceLocation librarianVillagerTextures = new ResourceLocation("textures/entity/villager/librarian.png");
   public static ResourceLocation priestVillagerTextures = new ResourceLocation("textures/entity/villager/priest.png");
   public static ResourceLocation smithVillagerTextures = new ResourceLocation("textures/entity/villager/smith.png");
   public static ResourceLocation butcherVillagerTextures = new ResourceLocation("textures/entity/villager/butcher.png");

   public ModelVillager getMainModel() {
      return (ModelVillager)super.getMainModel();
   }

   public RenderVillager(RenderManager var1) {
      super(var1, new ModelVillager(0.0F), 0.5F);
      this.a(new LayerCustomHead(this.getMainModel().villagerHead));
   }

   public void preRenderCallback(EntityVillager var1, float var2) {
      float var3 = 0.9375F;
      if (var1.l() < 0) {
         var3 = (float)(var3 * 0.5);
         this.c = 0.25F;
      } else {
         this.c = 0.5F;
      }

      GlStateManager.scale(var3, var3, var3);
   }

   public ResourceLocation getEntityTexture(EntityVillager var1) {
      switch (var1.getProfession()) {
         case 0:
            return farmerVillagerTextures;
         case 1:
            return librarianVillagerTextures;
         case 2:
            return priestVillagerTextures;
         case 3:
            return smithVillagerTextures;
         case 4:
            return butcherVillagerTextures;
         default:
            return villagerTextures;
      }
   }
}
