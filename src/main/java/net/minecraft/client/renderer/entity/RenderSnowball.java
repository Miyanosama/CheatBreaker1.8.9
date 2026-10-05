package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderSnowball<T extends Entity> extends Render<T> {
   public Item recoveredField1608;
   public RenderItem recoveredField1609;

   public RenderSnowball(RenderManager var1, Item var2, RenderItem var3) {
      super(var1);
      this.recoveredField1608 = var2;
      this.recoveredField1609 = var3;
   }

   public ItemStack func_177082_d(T var1) {
      return new ItemStack(this.recoveredField1608, 1, 0);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity var1) {
      return TextureMap.locationBlocksTexture;
   }

   @Override
   public void doRender(T var1, double var2, double var4, double var6, float var8, float var9) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var2, (float)var4, (float)var6);
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(0.5F, 0.5F, 0.5F);
      GlStateManager.rotate(-this.b.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(this.b.playerViewX, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      this.a(TextureMap.locationBlocksTexture);
      this.recoveredField1609.renderItem(this.func_177082_d((T)var1), ItemCameraTransforms.TransformType.GROUND);
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender((T)var1, var2, var4, var6, var8, var9);
   }
}
