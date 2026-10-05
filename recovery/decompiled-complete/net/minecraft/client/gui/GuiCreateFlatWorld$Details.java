package net.minecraft.client.gui;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.FlatLayerInfo;
import org.apache.log4j.chainsaw.LoggingReceiver;

public class GuiCreateFlatWorld$Details extends GuiSlot {
   public ContainerBrewingStand field_0001;
   public int field_148228_k;
   public LoggingReceiver field_0002;

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      FlatLayerInfo var7 = GuiCreateFlatWorld.access$400(this.field_148227_l)
         .getFlatLayers()
         .get(GuiCreateFlatWorld.access$400(this.field_148227_l).getFlatLayers().size() - var1 - 1);
      IBlockState var8 = var7.getLayerMaterial();
      Block var9 = var8.getBlock();
      Item var10 = Item.getItemFromBlock(var9);
      ItemStack var11 = var9 != Blocks.air && var10 != null ? new ItemStack(var10, 1, var9.getMetaFromState(var8)) : null;
      String var12 = var11 == null ? "Air" : var10.getItemStackDisplayName(var11);
      if (var10 == null) {
         if (var9 == Blocks.water || var9 == Blocks.flowing_water) {
            var10 = Items.water_bucket;
         } else if (var9 == Blocks.lava || var9 == Blocks.flowing_lava) {
            var10 = Items.lava_bucket;
         }

         if (var10 != null) {
            var11 = new ItemStack(var10, 1, var9.getMetaFromState(var8));
            var12 = var9.getLocalizedName();
         }
      }

      this.func_148225_a(var2, var3, var11);
      this.field_148227_l.q.drawString(var12, var2 + 18 + 5, var3 + 3, 16777215);
      String var13;
      if (var1 == 0) {
         var13 = I18n.format("createWorld.customize.flat.layer.top", var7.getLayerCount());
      } else if (var1 == GuiCreateFlatWorld.access$400(this.field_148227_l).getFlatLayers().size() - 1) {
         var13 = I18n.format("createWorld.customize.flat.layer.bottom", var7.getLayerCount());
      } else {
         var13 = I18n.format("createWorld.customize.flat.layer", var7.getLayerCount());
      }

      this.field_148227_l.q.drawString(var13, var2 + 2 + 213 - this.field_148227_l.q.getStringWidth(var13), var3 + 3, 16777215);
   }

   @Override
   public int getScrollBarX() {
      return this.b - 70;
   }

   public GuiCreateFlatWorld$Details(GuiCreateFlatWorld var1) {
      this.field_148227_l = var1;
      super(var1.j, var1.l, var1.m, 43, var1.m - 60, 24);
      this.field_148228_k = -1;
   }

   public void func_148224_c(int var1, int var2, int var3, int var4) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.a.getTextureManager().bindTexture(Gui.statIcons);
      float var5 = 0.0078125F;
      float var6 = 0.0078125F;
      byte var7 = 18;
      byte var8 = 18;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX);
      var10.pos(var1 + 0, var2 + 18, GuiCreateFlatWorld.field_0003).tex((var3 + 0) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 18, GuiCreateFlatWorld.field_0003).tex((var3 + 18) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 0, GuiCreateFlatWorld.field_0003).tex((var3 + 18) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var10.pos(var1 + 0, var2 + 0, GuiCreateFlatWorld.field_0003).tex((var3 + 0) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var9.draw();
   }

   public void func_148225_a(int var1, int var2, ItemStack var3) {
      this.func_148226_e(var1 + 1, var2 + 1);
      GlStateManager.enableRescaleNormal();
      if (var3 != null && var3.getItem() != null) {
         RenderHelper.enableGUIStandardItemLighting();
         this.field_148227_l.k.renderItemIntoGUI(var3, var1 + 2, var2 + 2);
         RenderHelper.disableStandardItemLighting();
      }

      GlStateManager.disableRescaleNormal();
   }

   @Override
   public void drawBackground() {
   }

   public void func_148226_e(int var1, int var2) {
      this.func_148224_c(var1, var2, 0, 0);
   }

   @Override
   public boolean isSelected(int var1) {
      return var1 == this.field_148228_k;
   }

   @Override
   public int getSize() {
      return GuiCreateFlatWorld.access$400(this.field_148227_l).getFlatLayers().size();
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_148228_k = var1;
      this.field_148227_l.func_146375_g();
   }
}
