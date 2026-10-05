package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class TileEntityEnchantmentTableRenderer extends TileEntitySpecialRenderer<TileEntityEnchantmentTable> {
   public ModelBook field_147541_c = new ModelBook();
   public static ResourceLocation TEXTURE_BOOK = new ResourceLocation("textures/entity/enchanting_table_book.png");

   public void renderTileEntityAt(TileEntityEnchantmentTable var1, double var2, double var4, double var6, float var8, int var9) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var2 + 0.5F, (float)var4 + 0.75F, (float)var6 + 0.5F);
      float var10 = var1.tickCount + var8;
      GlStateManager.translate(0.0F, 0.1F + MathHelper.sin(var10 * 0.1F) * 0.01F, 0.0F);
      float var11 = var1.recoveredField1818 - var1.recoveredField1819;

      while (var11 >= (float) Math.PI) {
         var11 -= (float) (Math.PI * 2);
      }

      while (var11 < (float) -Math.PI) {
         var11 += (float) (Math.PI * 2);
      }

      float var12 = var1.recoveredField1819 + var11 * var8;
      GlStateManager.rotate(-var12 * 180.0F / (float) Math.PI, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(80.0F, 0.0F, 0.0F, 1.0F);
      this.bindTexture(TEXTURE_BOOK);
      float var13 = var1.recoveredField1822 + (var1.recoveredField1817 - var1.recoveredField1822) * var8 + 0.25F;
      float var14 = var1.recoveredField1822 + (var1.recoveredField1817 - var1.recoveredField1822) * var8 + 0.75F;
      var13 = (var13 - MathHelper.truncateDoubleToInt(var13)) * 1.6F - 0.3F;
      var14 = (var14 - MathHelper.truncateDoubleToInt(var14)) * 1.6F - 0.3F;
      if (var13 < 0.0F) {
         var13 = 0.0F;
      }

      if (var14 < 0.0F) {
         var14 = 0.0F;
      }

      if (var13 > 1.0F) {
         var13 = 1.0F;
      }

      if (var14 > 1.0F) {
         var14 = 1.0F;
      }

      float var15 = var1.recoveredField1825 + (var1.recoveredField1820 - var1.recoveredField1825) * var8;
      GlStateManager.enableCull();
      this.field_147541_c.render((Entity)null, var10, var13, var14, var15, 0.0F, 0.0625F);
      GlStateManager.popMatrix();
   }
}
