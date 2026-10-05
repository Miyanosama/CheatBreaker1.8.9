package net.minecraft.client.gui;

import io.netty.util.internal.ReadOnlyIterator;
import net.minecraft.client.resources.I18n;
import net.minecraft.world.gen.FlatGeneratorInfo;
import net.optifine.shaders.BlockAlias;

public class GuiCreateFlatWorld extends GuiScreen {
   public FlatGeneratorInfo theFlatGeneratorInfo = FlatGeneratorInfo.getDefaultFlatGenerator();
   public GuiCreateFlatWorld$Details createFlatWorldListSlotGui;
   public String field_146391_r;
   public BlockAlias field_0007;
   public GuiButton field_146389_t;
   public String flatWorldTitle;
   public String field_146394_i;
   public GuiCreateWorld createWorldGui;
   public ReadOnlyIterator field_0003;
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
   public void actionPerformed(GuiButton var1) {
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
   public void handleMouseInput() {
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
      this.createFlatWorldListSlotGui = new GuiCreateFlatWorld$Details(this);
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
}
