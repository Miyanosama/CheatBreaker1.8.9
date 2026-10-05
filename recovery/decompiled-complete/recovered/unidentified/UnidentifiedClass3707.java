package recovered.unidentified;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker00;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemSign;
import net.minecraft.realms.RealmsSimpleScrolledSelectionList;
import net.minecraft.util.MathHelper;
import org.apache.log4j.lf5.PassingLogRecordFilter;

public class UnidentifiedClass3707 extends GuiSlot {
   public RealmsSimpleScrolledSelectionList field_0001;
   public ItemSign field_0003;
   public PassingLogRecordFilter field_0000;
   public WebSocketServerHandshaker00 field_0002;

   public int method_22572() {
      return super.b;
   }

   @Override
   public int getScrollBarX() {
      return this.field_0001.method_01315();
   }

   @Override
   public int getContentHeight() {
      return this.field_0001.method_01314();
   }

   @Override
   public void a(int var1, int var2, float var3) {
      if (this.field_178041_q) {
         this.mouseX = var1;
         this.mouseY = var2;
         this.drawBackground();
         int var4 = this.getScrollBarX();
         int var5 = var4 + 6;
         this.bindAmountScrolled();
         GlStateManager.disableLighting();
         GlStateManager.disableFog();
         Tessellator var6 = Tessellator.getInstance();
         WorldRenderer var7 = var6.getWorldRenderer();
         int var8 = this.left + this.b / 2 - this.v_() / 2 + 2;
         int var9 = this.d + 4 - (int)this.amountScrolled;
         if (this.hasListHeader) {
            this.drawListHeader(var8, var9, var6);
         }

         this.drawSelectionBox(var8, var9, var1, var2);
         GlStateManager.disableDepth();
         byte var10 = 4;
         this.overlayBackground(0, this.d, 255, 255);
         this.overlayBackground(this.bottom, this.height, 255, 255);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
         GlStateManager.disableAlpha();
         GlStateManager.shadeModel(7425);
         GlStateManager.disableTexture2D();
         int var11 = this.func_148135_f();
         if (var11 > 0) {
            int var12 = (this.bottom - this.d) * (this.bottom - this.d) / this.getContentHeight();
            var12 = MathHelper.clamp_int(var12, 32, this.bottom - this.d - 8);
            int var13 = (int)this.amountScrolled * (this.bottom - this.d - var12) / var11 + this.d;
            if (var13 < this.d) {
               var13 = this.d;
            }

            var7.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            var7.pos(var4, this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
            var7.pos(var5, this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
            var7.pos(var5, this.d, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
            var7.pos(var4, this.d, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
            var6.draw();
            var7.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            var7.pos(var4, var13 + var12, 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
            var7.pos(var5, var13 + var12, 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
            var7.pos(var5, var13, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
            var7.pos(var4, var13, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
            var6.draw();
            var7.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            var7.pos(var4, var13 + var12 - 1, 0.0).tex(0.0, 1.0).color(192, 192, 192, 255).endVertex();
            var7.pos(var5 - 1, var13 + var12 - 1, 0.0).tex(1.0, 1.0).color(192, 192, 192, 255).endVertex();
            var7.pos(var5 - 1, var13, 0.0).tex(1.0, 0.0).color(192, 192, 192, 255).endVertex();
            var7.pos(var4, var13, 0.0).tex(0.0, 0.0).color(192, 192, 192, 255).endVertex();
            var6.draw();
         }

         this.func_148142_b(var1, var2);
         GlStateManager.enableTexture2D();
         GlStateManager.shadeModel(7424);
         GlStateManager.enableAlpha();
         GlStateManager.disableBlend();
      }
   }

   public UnidentifiedClass3707(RealmsSimpleScrolledSelectionList var1, int var2, int var3, int var4, int var5, int var6) {
      super(Minecraft.getMinecraft(), var2, var3, var4, var5, var6);
      this.field_0001 = var1;
   }

   public int method_22576() {
      return super.mouseX;
   }

   public int method_22573() {
      return super.mouseY;
   }

   @Override
   public void drawBackground() {
      this.field_0001.renderBackground();
   }

   @Override
   public boolean isSelected(int var1) {
      return this.field_0001.isSelectedItem(var1);
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
   }

   @Override
   public int getSize() {
      return this.field_0001.method_01327();
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.field_0001.selectItem(var1, var2, var3, var4);
   }

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_0001.renderItem(var1, var2, var3, var4, var5, var6);
   }
}
