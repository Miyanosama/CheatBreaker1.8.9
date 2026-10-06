package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.mainmenu.AccountEntry;

public class AccountList extends AbstractElement {
   public MainMenuBase recoveredField346;
   public ScrollableElement recoveredField347;
   public ColorFade recoveredField348 = new ColorFade(1342177279, -1353670564);
   public float recoveredField349;
   public ResourceLocation recoveredField350;
   public String recoveredField351;
   public ColorFade recoveredField352 = new ColorFade(444958085, 1063565678);
   public float recoveredField353;
   public ColorFade recoveredField354 = new ColorFade(444958085, 1062577506);
   public boolean recoveredField355;
   public MinMaxFade recoveredField356 = new MinMaxFade(300L);

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      boolean var4 = false;
      RenderUtil.method_22057(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.recoveredField353,
         this.recoveredField348.method_25066(var4).getRGB(),
         this.recoveredField352.method_25066(var4).getRGB(),
         this.recoveredField354.method_25066(var4).getRGB()
      );
      float var5 = 6.0F;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.drawIcon(this.recoveredField350, var5, this.x + 4.0F, this.y + this.recoveredField353 / 2.0F - var5);
      float var10002 = this.x + 22.0F;
      float var10003 = this.y + 4.5F;
      CheatBreaker.getInstance().robotoRegular13px.drawString(this.recoveredField351, var10002, var10003, 0xE6FFFFFF);
      float var6 = this.recoveredField356.method_21232(this.a_(var1, var2) && var3);
      if (this.recoveredField356.method_21233()) {
         this.setElementSize(this.x, this.y, this.width, this.recoveredField353 + this.recoveredField349 * var6);
         this.recoveredField355 = false;
      } else if (!this.recoveredField356.method_21233() && !this.a_(var1, var2)) {
         this.recoveredField355 = false;
      }

      if (this.recoveredField355) {
         float var7 = 0.5F;
         float var8 = this.y + this.height + var7;
         float var9 = this.y + 5.0F + this.recoveredField353;
         if (var8 > var9) {
            Gui.drawBoxWithOutLine(this.x + 1.0F, var9, this.x + this.width - 1.0F, var8, var7, 1342177279, 444958085);
         }

         GL11.glPushMatrix();
         GL11.glEnable(3089);
         RenderUtil.method_22061(
            (int)this.x,
            (int)(this.y + this.recoveredField353),
            (int)(this.x + this.width),
            (int)(this.y + this.recoveredField353 + 7.0F + (this.height - this.recoveredField353 - 6.0F) * var6),
            (int)(this.recoveredField346.getResolution().getScaleFactor() * this.recoveredField346.getScaleFactor()),
            (int)this.recoveredField346.getScaledHeight()
         );
         this.recoveredField347.drawScrollable(var1, var2, var3);
         int var10 = 1;

         for (AccountEntry var12 : this.recoveredField346.getAccounts()) {
            float var13 = this.x;
            float var14 = this.x + this.width;
            float var15 = this.y + this.recoveredField353 + var10 * 16 - 8.0F;
            float var16 = var15 + 16.0F;
            boolean var17 = var1 > var13
               && var1 < var14
               && var2 - this.recoveredField347.method_12074() > var15
               && var2 - this.recoveredField347.method_12074() < var16
               && var3
               && !this.recoveredField347.a_(var1, var2)
               && !this.recoveredField347.isDragClick();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var17 ? 1.0F : 0.7F);
            RenderUtil.drawIcon(var12.method_09052(), var5, this.x + 4.0F, var15 + 8.0F - var5);
            CheatBreaker.getInstance().robotoRegular13px.drawString(var12.method_09047(), this.x + 22.0F, var15 + 4.0F, var17 ? -1 : 0xE6FFFFFF);
            var10++;
         }

         this.recoveredField347.handleElementDraw(var1, var2, var3);
         GL11.glDisable(3089);
         GL11.glPopMatrix();
      }
   }

   public void method_28068(String var1) {
      this.recoveredField351 = var1;
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      if (this.recoveredField353 == 0.0F) {
         this.recoveredField353 = var4;
      }

      this.recoveredField349 = Math.min(this.recoveredField346.getAccounts().size() * 16 + 12, 120);
      this.recoveredField347.setElementSize(var1 + var3 - 5.0F, var2 + this.recoveredField353 + 6.0F, 4.0F, this.recoveredField349 - 7.0F);
      this.recoveredField347.setScrollAmount(this.recoveredField346.getAccounts().size() * 16 + 4);
   }

   public void method_28069(ResourceLocation var1) {
      this.recoveredField350 = var1;
   }

   @Override
   public void handleElementMouse() {
      this.recoveredField347.handleElementMouse();
   }

   public float method_28067(float var1) {
      return 22.0F + var1 + 10.0F;
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public AccountList(MainMenuBase var1, String var2, ResourceLocation var3) {
      this.recoveredField347 = new ScrollableElement(this);
      this.recoveredField346 = var1;
      this.recoveredField350 = var3;
      this.recoveredField351 = var2;
   }
}
