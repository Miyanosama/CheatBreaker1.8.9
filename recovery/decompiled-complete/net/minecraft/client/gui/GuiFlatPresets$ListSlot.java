package net.minecraft.client.gui;

import io.netty.handler.codec.socks.SocksCmdResponseDecoder$1;
import net.minecraft.client.renderer.BlockModelRenderer$VertexTranslations;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.command.server.CommandAchievement$1;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerLogRecordFilter;

public class GuiFlatPresets$ListSlot extends GuiSlot {
   public int field_148175_k;
   public CommandAchievement$1 field_0005;
   public SocksCmdResponseDecoder$1 field_0002;
   public CrashReport field_0004;
   public BlockModelRenderer$VertexTranslations field_0000;
   public CategoryExplorerLogRecordFilter field_0001;

   public void func_148173_e(int var1, int var2) {
      this.func_148171_c(var1, var2, 0, 0);
   }

   @Override
   public int getSize() {
      return GuiFlatPresets.access$400().size();
   }

   public GuiFlatPresets$ListSlot(GuiFlatPresets var1) {
      this.field_148174_l = var1;
      super(var1.j, var1.l, var1.m, 80, var1.m - 37, 24);
      this.field_148175_k = -1;
   }

   public void func_178054_a(int var1, int var2, Item var3, int var4) {
      this.func_148173_e(var1 + 1, var2 + 1);
      GlStateManager.enableRescaleNormal();
      RenderHelper.enableGUIStandardItemLighting();
      this.field_148174_l.k.renderItemIntoGUI(new ItemStack(var3, 1, var4), var1 + 2, var2 + 2);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_148175_k = var1;
      this.field_148174_l.func_146426_g();
      GuiFlatPresets.access$600(this.field_148174_l)
         .setText(((GuiFlatPresets$LayerItem)GuiFlatPresets.access$400().get(GuiFlatPresets.access$500(this.field_148174_l).field_148175_k)).field_148233_c);
   }

   public void func_148171_c(int var1, int var2, int var3, int var4) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.a.getTextureManager().bindTexture(Gui.statIcons);
      float var5 = 0.0078125F;
      float var6 = 0.0078125F;
      byte var7 = 18;
      byte var8 = 18;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX);
      var10.pos(var1 + 0, var2 + 18, GuiFlatPresets.field_0003).tex((var3 + 0) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 18, GuiFlatPresets.field_0003).tex((var3 + 18) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 0, GuiFlatPresets.field_0003).tex((var3 + 18) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var10.pos(var1 + 0, var2 + 0, GuiFlatPresets.field_0003).tex((var3 + 0) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var9.draw();
   }

   @Override
   public void drawBackground() {
   }

   @Override
   public boolean isSelected(int var1) {
      return var1 == this.field_148175_k;
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      GuiFlatPresets$LayerItem var7 = (GuiFlatPresets$LayerItem)GuiFlatPresets.access$400().get(var1);
      this.func_178054_a(var2, var3, var7.field_148234_a, var7.field_179037_b);
      this.field_148174_l.q.drawString(var7.field_148232_b, var2 + 18 + 5, var3 + 6, 16777215);
   }
}
