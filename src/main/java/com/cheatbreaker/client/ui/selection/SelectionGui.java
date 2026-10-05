package com.cheatbreaker.client.ui.selection;

import com.cheatbreaker.client.ui.selection.SelectionOption;

import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class SelectionGui extends GuiScreen {
   public Consumer<SelectionOption> recoveredField1386;
   public int recoveredField1387;
   public int recoveredField1388;
   public SelectionOption[] recoveredField1389 = new SelectionOption[8];
   public static final int recoveredField1390 = 8;
   public SelectionOptionElement[] recoveredField1391 = new SelectionOptionElement[8];

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.recoveredField1388++;
      if (!Keyboard.isKeyDown(this.recoveredField1387)) {
         if (this.recoveredField1386 != null) {
            for (int var1 = 0; var1 < this.recoveredField1391.length; var1++) {
               SelectionOptionElement var2 = this.recoveredField1391[var1];
               SelectionOption var3 = this.recoveredField1389[var1];
               ScaledResolution var4 = new ScaledResolution(this.j);
               int var5 = var4.getScaledWidth();
               int var6 = var4.getScaledHeight();
               if (var3 != null
                  && var3.method_00994().toString().contains("minecraft:")
                  && var2.method_28761((float)Mouse.getX() * var5 / this.j.displayHeight, (float)Mouse.getY() * var6 / this.j.displayWidth - 1.0F)) {
                  this.recoveredField1386.accept(var3);
                  break;
               }
            }
         }

         this.j.displayGuiScreen(null);
      }
   }

   public SelectionGui(int var1, List<SelectionOption> var2) {
      this.recoveredField1388 = 0;
      Preconditions.checkNotNull(var2, "options");
      Preconditions.checkArgument(var2.size() <= 8, "cannot have more than 8 options");

      for (int var3 = 0; var3 < var2.size(); var3++) {
         this.recoveredField1389[var3] = (SelectionOption)var2.get(var3);
      }

      for (int var5 = 0; var5 < this.recoveredField1391.length; var5++) {
         SelectionOption var4 = null;
         if (var5 < var2.size()) {
            var4 = (SelectionOption)var2.get(var5);
         }

         this.recoveredField1391[var5] = new SelectionOptionElement(this, var5, var4);
      }

      this.recoveredField1387 = var1;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      ScaledResolution var4 = new ScaledResolution(this.j);
      int var5 = var4.getScaledWidth();
      int var6 = var4.getScaledHeight();

      for (SelectionOptionElement var10 : this.recoveredField1391) {
         if (var10 != null) {
            var10.handleElementDraw(var1, var2, true);
         }
      }

      float var11 = 10.0F;
      float var12 = this.recoveredField1388 >= var11 ? 1.0F : this.recoveredField1388 / var11;
      GL11.glPushMatrix();
      GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.5F * var12);
      RenderUtil.method_22055(var5 / 2.0F, var6 / 2.0F, 90.0, 88.0, 100.0, 100, 100.0);
      RenderUtil.method_22055(var5 / 2.0F, var6 / 2.0F, 20.0, 18.0, 100.0, 100, 100.0);
      GL11.glPopMatrix();
   }
}
