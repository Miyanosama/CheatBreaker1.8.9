package net.minecraft.client.gui;

import io.netty.util.internal.logging.Log4JLoggerFactory;
import net.minecraft.block.BlockRedstoneOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.command.server.CommandSaveAll;
import net.minecraft.server.integrated.IntegratedServer$2;
import net.minecraft.util.MathHelper;
import net.optifine.CustomItems$1;
import org.apache.log4j.SortedKeyEnumeration;
import org.lwjgl.input.Mouse;
import recovered.unidentified.UnidentifiedClass3389;

public abstract class GuiSlot {
   public int scrollDownButtonID;
   public int left;
   public CustomItems$1 field_0027;
   public Log4JLoggerFactory field_0004;
   public SortedKeyEnumeration field_0011;
   public boolean showSelectionBox;
   public boolean k = true;
   public boolean field_178041_q;
   public int headerPadding;
   public int bottom;
   public int d;
   public int f;
   public int scrollUpButtonID;
   public int b;
   public BlockRedstoneOre field_0015;
   public Minecraft a;
   public CommandSaveAll field_0021;
   public UnidentifiedClass3389 field_0010;
   public int mouseY;
   public int initialClickY = -2;
   public boolean enabled;
   public float amountScrolled;
   public boolean hasListHeader;
   public int selectedElement = -1;
   public long p;
   public int mouseX;
   public IntegratedServer$2 field_0022;
   public int slotHeight;
   public float scrollMultiplier;
   public int height;

   public void func_148132_a(int var1, int var2) {
   }

   public int func_148135_f() {
      return Math.max(0, this.getContentHeight() - (this.bottom - this.d - 4));
   }

   public abstract boolean isSelected(int var1);

   public int getContentHeight() {
      return this.getSize() * this.slotHeight + this.headerPadding;
   }

   public void registerScrollButtons(int var1, int var2) {
      this.scrollUpButtonID = var1;
      this.scrollDownButtonID = var2;
   }

   public void drawContainerBackground(Tessellator var1) {
      WorldRenderer var2 = var1.getWorldRenderer();
      this.a.getTextureManager().bindTexture(Gui.b);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float var3 = 32.0F;
      var2.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var2.pos(this.left, this.bottom, 0.0).tex(this.left / var3, (this.bottom + (int)this.amountScrolled) / var3).color(32, 32, 32, 255).endVertex();
      var2.pos(this.f, this.bottom, 0.0).tex(this.f / var3, (this.bottom + (int)this.amountScrolled) / var3).color(32, 32, 32, 255).endVertex();
      var2.pos(this.f, this.d, 0.0).tex(this.f / var3, (this.d + (int)this.amountScrolled) / var3).color(32, 32, 32, 255).endVertex();
      var2.pos(this.left, this.d, 0.0).tex(this.left / var3, (this.d + (int)this.amountScrolled) / var3).color(32, 32, 32, 255).endVertex();
      var1.draw();
   }

   public void func_178040_a(int var1, int var2, int var3) {
   }

   public int c(int var1, int var2) {
      int var3 = this.left + this.b / 2 - this.v_() / 2;
      int var4 = this.left + this.b / 2 + this.v_() / 2;
      int var5 = var2 - this.d - this.headerPadding + (int)this.amountScrolled - 4;
      int var6 = var5 / this.slotHeight;
      return var1 < this.getScrollBarX() && var1 >= var3 && var1 <= var4 && var6 >= 0 && var5 >= 0 && var6 < this.getSize() ? var6 : -1;
   }

   public void handleMouseInput() {
      if (this.isMouseYWithinSlotBounds(this.mouseY)) {
         if (Mouse.getEventButton() == 0 && Mouse.getEventButtonState() && this.mouseY >= this.d && this.mouseY <= this.bottom) {
            int var1 = (this.b - this.v_()) / 2;
            int var2 = (this.b + this.v_()) / 2;
            int var3 = this.mouseY - this.d - this.headerPadding + (int)this.amountScrolled - 4;
            int var4 = var3 / this.slotHeight;
            if (var4 < this.getSize() && this.mouseX >= var1 && this.mouseX <= var2 && var4 >= 0 && var3 >= 0) {
               this.elementClicked(var4, false, this.mouseX, this.mouseY);
               this.selectedElement = var4;
            } else if (this.mouseX >= var1 && this.mouseX <= var2 && var3 < 0) {
               this.func_148132_a(this.mouseX - var1, this.mouseY - this.d + (int)this.amountScrolled - 4);
            }
         }

         if (!Mouse.isButtonDown(0) || !this.getEnabled()) {
            this.initialClickY = -1;
         } else if (this.initialClickY == -1) {
            boolean var10 = true;
            if (this.mouseY >= this.d && this.mouseY <= this.bottom) {
               int var12 = (this.b - this.v_()) / 2;
               int var13 = (this.b + this.v_()) / 2;
               int var14 = this.mouseY - this.d - this.headerPadding + (int)this.amountScrolled - 4;
               int var5 = var14 / this.slotHeight;
               if (var5 < this.getSize() && this.mouseX >= var12 && this.mouseX <= var13 && var5 >= 0 && var14 >= 0) {
                  boolean var6 = var5 == this.selectedElement && Minecraft.getSystemTime() - this.p < (2209714357437202682L & -2209714358726481670L);
                  this.elementClicked(var5, var6, this.mouseX, this.mouseY);
                  this.selectedElement = var5;
                  this.p = Minecraft.getSystemTime();
               } else if (this.mouseX >= var12 && this.mouseX <= var13 && var14 < 0) {
                  this.func_148132_a(this.mouseX - var12, this.mouseY - this.d + (int)this.amountScrolled - 4);
                  var10 = false;
               }

               int var15 = this.getScrollBarX();
               int var7 = var15 + 6;
               if (this.mouseX >= var15 && this.mouseX <= var7) {
                  this.scrollMultiplier = -1.0F;
                  int var8 = this.func_148135_f();
                  if (var8 < 1) {
                     var8 = 1;
                  }

                  int var9 = (int)((float)((this.bottom - this.d) * (this.bottom - this.d)) / this.getContentHeight());
                  var9 = MathHelper.clamp_int(var9, 32, this.bottom - this.d - 8);
                  this.scrollMultiplier = this.scrollMultiplier / ((float)(this.bottom - this.d - var9) / var8);
               } else {
                  this.scrollMultiplier = 1.0F;
               }

               if (var10) {
                  this.initialClickY = this.mouseY;
               } else {
                  this.initialClickY = -2;
               }
            } else {
               this.initialClickY = -2;
            }
         } else if (this.initialClickY >= 0) {
            this.amountScrolled = this.amountScrolled - (this.mouseY - this.initialClickY) * this.scrollMultiplier;
            this.initialClickY = this.mouseY;
         }

         int var11 = Mouse.getEventDWheel();
         if (var11 != 0) {
            if (var11 > 0) {
               var11 = -1;
            } else if (var11 < 0) {
               var11 = 1;
            }

            this.amountScrolled = this.amountScrolled + var11 * this.slotHeight / 2;
         }
      }
   }

   public void a(int var1, int var2, int var3, int var4) {
      this.b = var1;
      this.height = var2;
      this.d = var3;
      this.bottom = var4;
      this.left = 0;
      this.f = var1;
   }

   public void setShowSelectionBox(boolean var1) {
      this.showSelectionBox = var1;
   }

   public void drawListHeader(int var1, int var2, Tessellator var3) {
   }

   public void setHasListHeader(boolean var1, int var2) {
      this.hasListHeader = var1;
      this.headerPadding = var2;
      if (!var1) {
         this.headerPadding = 0;
      }
   }

   public void scrollBy(int var1) {
      this.amountScrolled += var1;
      this.bindAmountScrolled();
      this.initialClickY = -2;
   }

   public void bindAmountScrolled() {
      this.amountScrolled = MathHelper.clamp_float(this.amountScrolled, 0.0F, this.func_148135_f());
   }

   public int getAmountScrolled() {
      return (int)this.amountScrolled;
   }

   public int getSlotHeight() {
      return this.slotHeight;
   }

   public abstract void elementClicked(int var1, boolean var2, int var3, int var4);

   public int getScrollBarX() {
      return this.b / 2 + 124;
   }

   public abstract void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6);

   public void setEnabled(boolean var1) {
      this.enabled = var1;
   }

   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == this.scrollUpButtonID) {
            this.amountScrolled = this.amountScrolled - this.slotHeight * 2 / 3;
            this.initialClickY = -2;
            this.bindAmountScrolled();
         } else if (var1.k == this.scrollDownButtonID) {
            this.amountScrolled = this.amountScrolled + this.slotHeight * 2 / 3;
            this.initialClickY = -2;
            this.bindAmountScrolled();
         }
      }
   }

   public void i(int var1) {
      this.left = var1;
      this.f = var1 + this.b;
   }

   public abstract int getSize();

   public boolean isMouseYWithinSlotBounds(int var1) {
      return var1 >= this.d && var1 <= this.bottom && this.mouseX >= this.left && this.mouseX <= this.f;
   }

   public boolean getEnabled() {
      return this.enabled;
   }

   public void func_148142_b(int var1, int var2) {
   }

   public int v_() {
      return 220;
   }

   public void drawSelectionBox(int var1, int var2, int var3, int var4) {
      int var5 = this.getSize();
      Tessellator var6 = Tessellator.getInstance();
      WorldRenderer var7 = var6.getWorldRenderer();

      for (int var8 = 0; var8 < var5; var8++) {
         int var9 = var2 + var8 * this.slotHeight + this.headerPadding;
         int var10 = this.slotHeight - 4;
         if (var9 > this.bottom || var9 + var10 < this.d) {
            this.func_178040_a(var8, var1, var9);
         }

         if (this.showSelectionBox && this.isSelected(var8)) {
            int var11 = this.left + (this.b / 2 - this.v_() / 2);
            int var12 = this.left + this.b / 2 + this.v_() / 2;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableTexture2D();
            var7.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            var7.pos(var11, var9 + var10 + 2, 0.0).tex(0.0, 1.0).color(128, 128, 128, 255).endVertex();
            var7.pos(var12, var9 + var10 + 2, 0.0).tex(1.0, 1.0).color(128, 128, 128, 255).endVertex();
            var7.pos(var12, var9 - 2, 0.0).tex(1.0, 0.0).color(128, 128, 128, 255).endVertex();
            var7.pos(var11, var9 - 2, 0.0).tex(0.0, 0.0).color(128, 128, 128, 255).endVertex();
            var7.pos(var11 + 1, var9 + var10 + 1, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
            var7.pos(var12 - 1, var9 + var10 + 1, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
            var7.pos(var12 - 1, var9 - 1, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
            var7.pos(var11 + 1, var9 - 1, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
            var6.draw();
            GlStateManager.enableTexture2D();
         }

         if (!(this instanceof GuiResourcePackList) || var9 >= this.d - this.slotHeight && var9 <= this.bottom) {
            this.drawSlot(var8, var1, var9, var10, var3, var4);
         }
      }
   }

   public void overlayBackground(int var1, int var2, int var3, int var4) {
      Tessellator var5 = Tessellator.getInstance();
      WorldRenderer var6 = var5.getWorldRenderer();
      this.a.getTextureManager().bindTexture(Gui.b);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float var7 = 32.0F;
      var6.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var6.pos(this.left, var2, 0.0).tex(0.0, var2 / 32.0F).color(64, 64, 64, var4).endVertex();
      var6.pos(this.left + this.b, var2, 0.0).tex(this.b / 32.0F, var2 / 32.0F).color(64, 64, 64, var4).endVertex();
      var6.pos(this.left + this.b, var1, 0.0).tex(this.b / 32.0F, var1 / 32.0F).color(64, 64, 64, var3).endVertex();
      var6.pos(this.left, var1, 0.0).tex(0.0, var1 / 32.0F).color(64, 64, 64, var3).endVertex();
      var5.draw();
   }

   public abstract void drawBackground();

   public GuiSlot(Minecraft var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_178041_q = true;
      this.showSelectionBox = true;
      this.enabled = true;
      this.a = var1;
      this.b = var2;
      this.height = var3;
      this.d = var4;
      this.bottom = var5;
      this.slotHeight = var6;
      this.left = 0;
      this.f = var2;
   }

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
         this.drawContainerBackground(var6);
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
         var7.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         var7.pos(this.left, this.d + var10, 0.0).tex(0.0, 1.0).color(0, 0, 0, 0).endVertex();
         var7.pos(this.f, this.d + var10, 0.0).tex(1.0, 1.0).color(0, 0, 0, 0).endVertex();
         var7.pos(this.f, this.d, 0.0).tex(1.0, 0.0).color(0, 0, 0, 255).endVertex();
         var7.pos(this.left, this.d, 0.0).tex(0.0, 0.0).color(0, 0, 0, 255).endVertex();
         var6.draw();
         var7.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         var7.pos(this.left, this.bottom, 0.0).tex(0.0, 1.0).color(0, 0, 0, 255).endVertex();
         var7.pos(this.f, this.bottom, 0.0).tex(1.0, 1.0).color(0, 0, 0, 255).endVertex();
         var7.pos(this.f, this.bottom - var10, 0.0).tex(1.0, 0.0).color(0, 0, 0, 0).endVertex();
         var7.pos(this.left, this.bottom - var10, 0.0).tex(0.0, 0.0).color(0, 0, 0, 0).endVertex();
         var6.draw();
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
}
