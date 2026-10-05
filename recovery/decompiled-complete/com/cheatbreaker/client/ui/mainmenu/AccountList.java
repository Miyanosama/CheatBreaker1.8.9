package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.codec.serialization.CompactObjectOutputStream;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.optifine.config.EntityClassLocator;
import net.optifine.gui.TooltipProviderEnumShaderOptions;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass1308;

public class AccountList extends AbstractElement {
   public MainMenuBase field_0006;
   public ScrollableElement field_0011;
   public ColorFade field_0005 = new ColorFade(1342177279, -1353670564);
   public EntityClassLocator field_0010;
   public float field_0001;
   public CompactObjectOutputStream field_0002;
   public ResourceLocation field_0012;
   public String field_0009;
   public ColorFade field_0003 = new ColorFade(444958085, 1063565678);
   public float field_0013;
   public ColorFade field_0000 = new ColorFade(444958085, 1062577506);
   public TooltipProviderEnumShaderOptions field_0007;
   public boolean field_0008;
   public MinMaxFade field_0004 = new MinMaxFade(268510124L & 8479108613860541756L);

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      boolean var4 = false;
      RenderUtil.method_22057(
         this.x,
         this.y,
         this.x + this.width,
         this.y + this.field_0013,
         this.field_0005.method_25066(var4).getRGB(),
         this.field_0003.method_25066(var4).getRGB(),
         this.field_0000.method_25066(var4).getRGB()
      );
      float var5 = 6.0F;
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.drawIcon(this.field_0012, var5, this.x + 4.0F, this.y + this.field_0013 / 2.0F - var5);
      float var10002 = this.x + 22.0F;
      float var10003 = this.y + 4.5F;
      CheatBreaker.getInstance().robotoRegular13px.drawString(this.field_0009, var10002, var10003, -1342177281);
      float var6 = this.field_0004.method_21232(this.a_(var1, var2) && var3);
      if (this.field_0004.method_21233()) {
         this.setElementSize(this.x, this.y, this.width, this.field_0013 + this.field_0001 * var6);
         this.field_0008 = false;
      } else if (!this.field_0004.method_21233() && !this.a_(var1, var2)) {
         this.field_0008 = false;
      }

      if (this.field_0008) {
         float var7 = 0.5F;
         float var8 = this.y + this.height + var7;
         float var9 = this.y + 5.0F + this.field_0013;
         if (var8 > var9) {
            Gui.drawBoxWithOutLine(this.x + 1.0F, var9, this.x + this.width - 1.0F, var8, var7, 1342177279, 444958085);
         }

         GL11.glPushMatrix();
         GL11.glEnable(3089);
         RenderUtil.method_22061(
            (int)this.x,
            (int)(this.y + this.field_0013),
            (int)(this.x + this.width),
            (int)(this.y + this.field_0013 + 7.0F + (this.height - this.field_0013 - 6.0F) * var6),
            (int)(this.field_0006.getResolution().getScaleFactor() * this.field_0006.getScaleFactor()),
            (int)this.field_0006.getScaledHeight()
         );
         this.field_0011.drawScrollable(var1, var2, var3);
         int var10 = 1;

         for (UnidentifiedClass1308 var12 : this.field_0006.getAccounts()) {
            float var13 = this.x;
            float var14 = this.x + this.width;
            float var15 = this.y + this.field_0013 + var10 * 16 - 8.0F;
            float var16 = var15 + 16.0F;
            boolean var17 = var1 > var13
               && var1 < var14
               && var2 - this.field_0011.method_12074() > var15
               && var2 - this.field_0011.method_12074() < var16
               && var3
               && !this.field_0011.a_(var1, var2)
               && !this.field_0011.isDragClick();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var17 ? 1.0F : 0.7F);
            RenderUtil.drawIcon(var12.method_09052(), var5, this.x + 4.0F, var15 + 8.0F - var5);
            CheatBreaker.getInstance().robotoRegular13px.drawString(var12.method_09047(), this.x + 22.0F, var15 + 4.0F, var17 ? -1 : -1342177281);
            var10++;
         }

         this.field_0011.handleElementDraw(var1, var2, var3);
         GL11.glDisable(3089);
         GL11.glPopMatrix();
      }
   }

   public void method_28068(String var1) {
      this.field_0009 = var1;
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      if (this.field_0013 == 0.0F) {
         this.field_0013 = var4;
      }

      this.field_0001 = Math.min(this.field_0006.getAccounts().size() * 16 + 12, 120);
      this.field_0011.setElementSize(var1 + var3 - 5.0F, var2 + this.field_0013 + 6.0F, 4.0F, this.field_0001 - 7.0F);
      this.field_0011.setScrollAmount(this.field_0006.getAccounts().size() * 16 + 4);
   }

   public void method_28069(ResourceLocation var1) {
      this.field_0012 = var1;
   }

   @Override
   public void handleElementMouse() {
      this.field_0011.handleElementMouse();
   }

   public float method_28067(float var1) {
      return 22.0F + var1 + 10.0F;
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return !var4 ? false : false;
   }

   public AccountList(MainMenuBase var1, String var2, ResourceLocation var3) {
      this.field_0011 = new ScrollableElement(this);
      this.field_0006 = var1;
      this.field_0012 = var3;
      this.field_0009 = var2;
   }
}
