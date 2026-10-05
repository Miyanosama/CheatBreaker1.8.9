package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBAnchorHelper;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.json.CDL;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass0105 extends GuiScreen {
   public CBModulesGui field_0001;
   public CDL field_0002;
   public AbstractModule field_0000;

   @Override
   public void initGui() {
   }

   @Override
   public void keyTyped(char var1, int var2) {
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      if (var3 == 0) {
         ScaledResolution var4 = new ScaledResolution(this.j);
         CBGuiAnchor var5 = CBAnchorHelper.getAnchor(var1, var2, var4);
         this.field_0000.method_28819(var5);
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.field_0000.setState(true);
         CBModulesGui var6 = new CBModulesGui();
         this.j.displayGuiScreen(var6);
         var6.currentScrollableElement = var6.field_0017;
         var6.currentScrollableElement.field_0000 = false;
         var6.currentScrollableElement.field_0013 = this.field_0001.field_0017.field_0013;
         var6.currentScrollableElement.yOffset = 0;
      }
   }

   public float method_00848(AbstractModule var1, float var2, float[] var3, float var4, boolean var5) {
      float var7 = var2;
      int var6 = var5 ? 0 : 3;
      if (var2 + var3[0] < var6) {
         var7 = -var3[0] + var6;
      } else if (var2 + var3[0] * var1.method_28770() + var4 > this.l - var6) {
         var7 = (int)(this.l - var3[0] * var1.method_28770() - var4 - var6);
      }

      return var7;
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.method_11292();
      RenderUtil.method_22054(0.0, this.m / 3, this.l, this.m / 3 + 0.5F, 0.0, 1862270976);
      RenderUtil.method_22054(0.0, this.m / 3 * 2, this.l, this.m / 3 * 2 + 0.5F, 0.0, 1862270976);
      RenderUtil.method_22054(this.l / 3, 0.0, this.l / 3 + 0.5F, this.m, 0.0, 1862270976);
      RenderUtil.method_22054(this.l / 3 * 2, 0.0, this.l / 3 * 2 + 0.5F, this.m, 0.0, 1862270976);
      RenderUtil.method_22054(this.l / 3 + this.l / 6, this.m / 3 * 2, this.l / 3 + this.l / 6 + 0.5F, this.m, 0.0, 1862270976);
      float var4 = 1.0F / CheatBreaker.method_19763() / this.field_0000.method_28770();
      ScaledResolution var5 = new ScaledResolution(this.j);
      float[] var6 = CBAnchorHelper.getPositions(var1, var2, var5);
      CBGuiAnchor var7 = CBAnchorHelper.getAnchor(var1, var2, var5);
      if (var7 != CBGuiAnchor.MIDDLE_MIDDLE) {
         if (var7 != CBGuiAnchor.MIDDLE_BOTTOM_LEFT && var7 != CBGuiAnchor.MIDDLE_BOTTOM_RIGHT) {
            Gui.drawRect(var6[0], var6[1], var6[0] + var5.getScaledWidth() / 3, var6[1] + var5.getScaledHeight() / 3, 788529152);
         } else {
            Gui.drawRect(var6[0], var6[1], var6[0] + var5.getScaledWidth() / 6, var6[1] + var5.getScaledHeight() / 3, 788529152);
         }
      }

      int var8 = var5.getScaledWidth();
      int var9 = var5.getScaledHeight();
      float[] var10 = CBAnchorHelper.getPositions(this.field_0000, var1, var2, var5);
      if (var7 != this.field_0000.getGuiAnchor()) {
         this.field_0000.method_28819(var7);
         this.field_0000.setTranslations(0.0F, 0.0F);
      }

      if (!Mouse.isButtonDown(1)) {
         RenderUtil.method_22054(2.0, 0.0, 2.5, var9, 0.0, -15599126);
         RenderUtil.method_22054(var8 - 2.5F, 0.0, var8 - 2, var9, 0.0, -15599126);
         RenderUtil.method_22054(0.0, 2.0, var8, 2.5, 0.0, -15599126);
         RenderUtil.method_22054(0.0, var9 - 3.5F, var8, var9 - 3, 0.0, -15599126);
      }

      float var11 = var1 - var6[0] - var10[0];
      float var12 = var2 - var6[1] - var10[1];
      if (!Mouse.isButtonDown(1)) {
         float[] var13 = this.field_0000.getScaledPoints(var5, false);
         var11 = this.method_00848(this.field_0000, var11, var13, (int)(this.field_0000.field_0041 * this.field_0000.method_28770()), false);
         var12 = this.method_00850(this.field_0000, var12, var13, (int)(this.field_0000.field_0012 * this.field_0000.method_28770()), false);
      }

      this.field_0000.setTranslations(var11, var12);
      GL11.glPushMatrix();
      this.field_0000.scaleAndTranslate(var5);
      RenderUtil.method_22054(-2.0, -2.0, this.field_0000.field_0041 + 2.0F, this.field_0000.field_0012 + 2.0F, 4.0, 551805923);
      GL11.glPushMatrix();
      GL11.glScalef(var4, var4, var4);
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0024.getValue()) {
         float var17 = var2 < 9 ? this.field_0000.field_0012 / var4 : -CheatBreaker.getInstance().field_0068.getHeight() - 4;
         switch (UnidentifiedClass4502.field_0001[this.field_0000.getPosition().ordinal()]) {
            case 1:
               float var14 = 0.0F;
               CheatBreaker.getInstance().field_0068.drawStringWithShadow(this.field_0000.getName(), var14, var17, -1);
               break;
            case 2:
               float var15 = this.field_0000.field_0041 / var4 / 2.0F;
               CheatBreaker.getInstance().field_0068.method_03191(this.field_0000.getName(), var15, var17, -1);
               break;
            case 3:
               float var16 = this.field_0000.field_0041 / var4 - CheatBreaker.getInstance().field_0068.getStringWidth(this.field_0000.getName());
               CheatBreaker.getInstance().field_0068.drawStringWithShadow(this.field_0000.getName(), var16, var17, -1);
         }
      }

      GL11.glPopMatrix();
      GL11.glPopMatrix();
   }

   @Override
   public void updateScreen() {
   }

   public UnidentifiedClass0105(CBModulesGui var1, AbstractModule var2) {
      var2.setState(true);
      this.field_0000 = var2;
      this.field_0001 = var1;
   }

   public float method_00850(AbstractModule var1, float var2, float[] var3, float var4, boolean var5) {
      float var7 = var2;
      int var6 = var5 ? 0 : 2;
      if (var2 + var3[1] < var6) {
         var7 = -var3[1] + var6;
      } else if (var2 + var3[1] * var1.method_28770() + var4 > this.m - var6) {
         var7 = (int)(this.m - var3[1] * var1.method_28770() - var4 - var6);
      }

      return var7;
   }
}
