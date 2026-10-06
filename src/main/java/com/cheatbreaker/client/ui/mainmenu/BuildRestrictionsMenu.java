package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.davidmoten.text.utils.WordWrap;

public class BuildRestrictionsMenu extends MainMenuBase {
   public ScrollableElement recoveredField2878;
   public GradientTextButton recoveredField2879;
   public boolean recoveredField2880 = false;

   @Override
   public void drawMenu(float var1, float var2) {
      super.drawMenu(var1, var2);
      float var3 = Math.min(240.0F, this.getScaledWidth() - 10.0F);
      float var4 = this.getScaledWidth() / 2.0F - var3 / 2.0F;
      float var5 = this.getScaledWidth() / 2.0F + var3 / 2.0F;
      float var6 = 40.0F;
      float var7 = this.getScaledHeight() - 40.0F;
      Gui.drawRect(var4, var6, var5, var7, 788529152);
      Gui.drawRect(var4 + 8.0F, var6 + 16.0F, var5 - 8.0F, var6 + 16.5F, 452984831);
      String var8 = "CheatBreaker 1.8.9";
      CheatBreaker.getInstance().recoveredField1548.drawString(var8.toUpperCase(), var4 + 8.0F, var6 + 5.0F, -1);
      String var9 = "CheatBreaker 1.8.9";
      String var10 = WordWrap.method_05899(var9).method_29697(60).method_29703(false).method_29693();
      String[] var11 = var10.split("\n");
      int var12 = 0;
      boolean var13 = false;
      this.recoveredField2879.setElementSize(this.getScaledWidth() / 2.0F - 30.0F, this.getScaledHeight() - 35.0F, 60.0F, 12.0F);
      this.recoveredField2879.drawElement(var1, var2, true);
      this.recoveredField2878.drawScrollable(var1, var2, true);
      GL11.glPushMatrix();
      GL11.glEnable(3089);
      RenderUtil.method_22061(
         (int)var4, (int)var6 + 18, (int)var5, (int)var7 - 8, (int)(this.getResolution().getScaleFactor() * this.getScaleFactor()), (int)this.getScaledHeight()
      );

      for (String var17 : var11) {
         this.getClass();
         CheatBreaker.getInstance()
            .playRegular14px
            .drawString(var17, var4 + 8.0F, var6 + 18.0F + var12 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F), -1);
         var12++;
      }

      this.recoveredField2878.setScrollAmount(var12 * (CheatBreaker.getInstance().playRegular14px.getFont().getSize() / 2.0F) + 2.0F);
      GL11.glDisable(3089);
      GL11.glPopMatrix();
      this.recoveredField2878.drawElement(var1, var2, true);
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.recoveredField2878.handleElementMouse();
   }

   public BuildRestrictionsMenu() {
      this.recoveredField2879 = new GradientTextButton("BACK");
      this.recoveredField2878 = new ScrollableElement(null);
   }

   @Override
   public void initGui() {
      super.initGui();
      float var1 = Math.min(240.0F, this.getScaledWidth() - 10.0F);
      float var2 = this.getScaledWidth() / 2.0F + var1 / 2.0F;
      float var3 = 40.0F;
      float var4 = this.getScaledHeight() - 40.0F;
      this.recoveredField2878.setElementSize(var2 - 8.0F, var3 + 18.0F, 4.0F, var4 - var3 - 28.0F);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      if (this.recoveredField2879.a_(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new MainMenu());
      } else if (this.recoveredField2878.a_(var1, var2)) {
         this.recoveredField2878.handleElementMouseClicked(var1, var2, var3, true);
      }
   }
}
