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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.minecraft.world.gen.FlatLayerInfo;

public class GuiCreateFlatWorld extends GuiScreen {
   public FlatGeneratorInfo theFlatGeneratorInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
   public GuiCreateFlatWorld.Details createFlatWorldListSlotGui;
   public String field_146391_r;
   public GuiButton field_146389_t;
   public String flatWorldTitle;
   public String field_146394_i;
   public GuiCreateWorld createWorldGui;
   public GuiButton field_146386_v;
   public GuiButton field_146388_u;

   public GuiCreateFlatWorld(GuiCreateWorld var1, String var2) {
      this.createWorldGui = var1;
      this.func_146383_a(var2);
   }

   public boolean func_146382_i() {
      return this.createFlatWorldListSlotGui.field_148228_k > -1
         && this.createFlatWorldListSlotGui.field_148228_k < this.theFlatGeneratorInfo.getFlatLayers().size();
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.createFlatWorldListSlotGui.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.flatWorldTitle, this.l / 2, 8, 16777215);
      int var4 = this.l / 2 - 92 - 16;
      this.drawString(this.q, this.field_146394_i, var4, 32, 16777215);
      this.drawString(this.q, this.field_146391_r, var4 + 2 + 213 - this.q.getStringWidth(this.field_146391_r), 32, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      int var2 = this.theFlatGeneratorInfo.getFlatLayers().size() - this.createFlatWorldListSlotGui.field_148228_k - 1;
      if (var1.k == 1) {
         this.j.displayGuiScreen(this.createWorldGui);
      } else if (var1.k == 0) {
         this.createWorldGui.chunkProviderSettingsJson = this.func_146384_e();
         this.j.displayGuiScreen(this.createWorldGui);
      } else if (var1.k == 5) {
         this.j.displayGuiScreen(new GuiFlatPresets(this));
      } else if (var1.k == 4 && this.func_146382_i()) {
         this.theFlatGeneratorInfo.getFlatLayers().remove(var2);
         this.createFlatWorldListSlotGui.field_148228_k = Math.min(
            this.createFlatWorldListSlotGui.field_148228_k, this.theFlatGeneratorInfo.getFlatLayers().size() - 1
         );
      }

      this.theFlatGeneratorInfo.func_82645_d();
      this.func_146375_g();
   }

   public String func_146384_e() {
      return this.theFlatGeneratorInfo.toString();
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.createFlatWorldListSlotGui.handleMouseInput();
   }

   public void func_146383_a(String var1) {
      this.theFlatGeneratorInfo = FlatGeneratorInfo.createFlatGeneratorFromString(var1);
   }

   @Override
   public void initGui() {
      this.n.clear();
      this.flatWorldTitle = I18n.format("createWorld.customize.flat.title");
      this.field_146394_i = I18n.format("createWorld.customize.flat.tile");
      this.field_146391_r = I18n.format("createWorld.customize.flat.height");
      this.createFlatWorldListSlotGui = new GuiCreateFlatWorld.Details();
      this.n.add(this.field_146389_t = new GuiButton(2, this.l / 2 - 154, this.m - 52, 100, 20, I18n.format("createWorld.customize.flat.addLayer") + " (NYI)"));
      this.n.add(this.field_146388_u = new GuiButton(3, this.l / 2 - 50, this.m - 52, 100, 20, I18n.format("createWorld.customize.flat.editLayer") + " (NYI)"));
      this.n.add(this.field_146386_v = new GuiButton(4, this.l / 2 - 155, this.m - 52, 150, 20, I18n.format("createWorld.customize.flat.removeLayer")));
      this.n.add(new GuiButton(0, this.l / 2 - 155, this.m - 28, 150, 20, I18n.format("gui.done")));
      this.n.add(new GuiButton(5, this.l / 2 + 5, this.m - 52, 150, 20, I18n.format("createWorld.customize.presets")));
      this.n.add(new GuiButton(1, this.l / 2 + 5, this.m - 28, 150, 20, I18n.format("gui.cancel")));
      this.field_146389_t.m = this.field_146388_u.m = false;
      this.theFlatGeneratorInfo.func_82645_d();
      this.func_146375_g();
   }

   public void func_146375_g() {
      boolean var1 = this.func_146382_i();
      this.field_146386_v.l = var1;
      this.field_146388_u.l = var1;
      this.field_146388_u.l = false;
      this.field_146389_t.l = false;
   }

   public class Details extends GuiSlot {
      public int field_148228_k = -1;

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         FlatLayerInfo var7 = GuiCreateFlatWorld.this.theFlatGeneratorInfo
            .getFlatLayers()
            .get(GuiCreateFlatWorld.this.theFlatGeneratorInfo.getFlatLayers().size() - var1 - 1);
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
         GuiCreateFlatWorld.this.q.drawString(var12, var2 + 18 + 5, var3 + 3, 16777215);
         String var13;
         if (var1 == 0) {
            var13 = I18n.format("createWorld.customize.flat.layer.top", var7.getLayerCount());
         } else if (var1 == GuiCreateFlatWorld.this.theFlatGeneratorInfo.getFlatLayers().size() - 1) {
            var13 = I18n.format("createWorld.customize.flat.layer.bottom", var7.getLayerCount());
         } else {
            var13 = I18n.format("createWorld.customize.flat.layer", var7.getLayerCount());
         }

         GuiCreateFlatWorld.this.q.drawString(var13, var2 + 2 + 213 - GuiCreateFlatWorld.this.q.getStringWidth(var13), var3 + 3, 16777215);
      }

      @Override
      public int getScrollBarX() {
         return this.b - 70;
      }

      public Details() {
         super(GuiCreateFlatWorld.this.j, GuiCreateFlatWorld.this.l, GuiCreateFlatWorld.this.m, 43, GuiCreateFlatWorld.this.m - 60, 24);
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
         var10.pos(var1 + 0, var2 + 18, GuiCreateFlatWorld.recoveredField2942).tex((var3 + 0) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
         var10.pos(var1 + 18, var2 + 18, GuiCreateFlatWorld.recoveredField2942).tex((var3 + 18) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
         var10.pos(var1 + 18, var2 + 0, GuiCreateFlatWorld.recoveredField2942).tex((var3 + 18) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
         var10.pos(var1 + 0, var2 + 0, GuiCreateFlatWorld.recoveredField2942).tex((var3 + 0) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
         var9.draw();
      }

      public void func_148225_a(int var1, int var2, ItemStack var3) {
         this.func_148226_e(var1 + 1, var2 + 1);
         GlStateManager.enableRescaleNormal();
         if (var3 != null && var3.getItem() != null) {
            RenderHelper.enableGUIStandardItemLighting();
            GuiCreateFlatWorld.this.k.renderItemIntoGUI(var3, var1 + 2, var2 + 2);
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
         return GuiCreateFlatWorld.this.theFlatGeneratorInfo.getFlatLayers().size();
      }

      @Override
      public void elementClicked(int var1, boolean var2, int var3, int var4) {
         this.field_148228_k = var1;
         GuiCreateFlatWorld.this.func_146375_g();
      }
   }
}
