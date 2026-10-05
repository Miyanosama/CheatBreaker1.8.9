package net.minecraft.client.gui;

import net.minecraft.block.material.MapColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec4b;
import net.minecraft.world.storage.MapData;

public class MapItemRenderer$Instance {
   public ResourceLocation location;
   public int[] mapTextureData;
   public MapData mapData;
   public DynamicTexture mapTexture;

   public void updateMapTexture() {
      for (int var1 = 0; var1 < 16384; var1++) {
         int var2 = this.mapData.colors[var1] & 255;
         if (var2 / 4 == 0) {
            this.mapTextureData[var1] = (var1 + var1 / 128 & 1) * 8 + 16 << 24;
         } else {
            this.mapTextureData[var1] = MapColor.mapColorArray[var2 / 4].getMapColor(var2 & 3);
         }
      }

      this.mapTexture.updateDynamicTexture();
   }

   public void render(boolean var1) {
      byte var2 = 0;
      byte var3 = 0;
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      float var6 = 0.0F;
      MapItemRenderer.access$400(this.field_148244_a).bindTexture(this.location);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(1, 771, 0, 1);
      GlStateManager.disableAlpha();
      var5.begin(7, DefaultVertexFormats.POSITION_TEX);
      var5.pos(var2 + 0 + var6, var3 + 128 - var6, -0.01F).tex(0.0, 1.0).endVertex();
      var5.pos(var2 + 128 - var6, var3 + 128 - var6, -0.01F).tex(1.0, 1.0).endVertex();
      var5.pos(var2 + 128 - var6, var3 + 0 + var6, -0.01F).tex(1.0, 0.0).endVertex();
      var5.pos(var2 + 0 + var6, var3 + 0 + var6, -0.01F).tex(0.0, 0.0).endVertex();
      var4.draw();
      GlStateManager.enableAlpha();
      GlStateManager.disableBlend();
      MapItemRenderer.access$400(this.field_148244_a).bindTexture(MapItemRenderer.access$500());
      int var7 = 0;

      for (Vec4b var9 : this.mapData.mapDecorations.values()) {
         if (!var1 || var9.func_176110_a() == 1) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(var2 + var9.func_176112_b() / 2.0F + 64.0F, var3 + var9.func_176113_c() / 2.0F + 64.0F, -0.02F);
            GlStateManager.rotate(var9.func_176111_d() * 360 / 16.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.scale(4.0F, 4.0F, 3.0F);
            GlStateManager.translate(-0.125F, 0.125F, 0.0F);
            byte var10 = var9.func_176110_a();
            float var11 = (var10 % 4 + 0) / 4.0F;
            float var12 = (var10 / 4 + 0) / 4.0F;
            float var13 = (var10 % 4 + 1) / 4.0F;
            float var14 = (var10 / 4 + 1) / 4.0F;
            var5.begin(7, DefaultVertexFormats.POSITION_TEX);
            float var15 = -0.001F;
            var5.pos(-1.0, 1.0, var7 * -0.001F).tex(var11, var12).endVertex();
            var5.pos(1.0, 1.0, var7 * -0.001F).tex(var13, var12).endVertex();
            var5.pos(1.0, -1.0, var7 * -0.001F).tex(var13, var14).endVertex();
            var5.pos(-1.0, -1.0, var7 * -0.001F).tex(var11, var14).endVertex();
            var4.draw();
            GlStateManager.popMatrix();
            var7++;
         }
      }

      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, 0.0F, -0.04F);
      GlStateManager.scale(1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public MapItemRenderer$Instance(MapItemRenderer var1, MapData var2) {
      this.field_148244_a = var1;
      super();
      this.mapData = var2;
      this.mapTexture = new DynamicTexture(128, 128);
      this.mapTextureData = this.mapTexture.getTextureData();
      this.location = MapItemRenderer.access$400(var1).getDynamicTextureLocation("map/" + var2.a, this.mapTexture);

      for (int var3 = 0; var3 < this.mapTextureData.length; var3++) {
         this.mapTextureData[var3] = 0;
      }
   }
}
