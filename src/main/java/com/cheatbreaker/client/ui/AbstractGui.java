package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public abstract class AbstractGui extends GuiScreen {
   public ScaledResolution resolution;
   public int elementListSize = 0;
   public float recoveredField13;
   public List<AbstractElement> recoveredField14;
   public List<AbstractElement> elements;
   public float recoveredField15;

   public void setElementsAndUpdateSize(AbstractElement... var1) {
      this.recoveredField14 = new ArrayList<>();
      this.recoveredField14.addAll(Arrays.asList(var1));
      this.elementListSize = this.recoveredField14.size();
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      float var4 = this.getScaleFactor();
      GL11.glPushMatrix();
      GL11.glScalef(var4, var4, var4);
      this.drawMenu(var1 / var4, var2 / var4);
      GL11.glPopMatrix();
   }

   public float getScaledHeight() {
      return this.recoveredField15;
   }

   public List<AbstractElement> getElements() {
      return this.recoveredField14;
   }

   public void drawElements(float var1, float var2, AbstractElement... var3) {
      List var4 = Arrays.asList(var3);

      for (AbstractElement var6 : this.recoveredField14) {
         if (!var4.contains(var6)) {
            var6.drawElement(var1, var2, this.isMouseHovered(var6, var1, var2));
         }
      }
   }

   public float getScaleFactor() {
      return ClientUiScale.getRenderScale(this.j, this.resolution);
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      float var4 = this.getScaleFactor();
      this.onMouseReleased(var1 / var4, var2 / var4, var3);
   }

   public ScaledResolution getResolution() {
      return this.resolution;
   }

   public abstract void onMouseReleased(float var1, float var2, int var3);

   public boolean isMouseHovered(AbstractElement var1, float var2, float var3, AbstractElement... var4) {
      List var6 = Arrays.asList(var4);
      boolean var7 = true;

      AbstractElement var5;
      for (int var8 = this.recoveredField14.size() - 1; var8 >= 0 && (var5 = this.recoveredField14.get(var8)) != var1; var8--) {
         if (!var6.contains(var5) && var5.a_(var2, var3)) {
            var7 = false;
            break;
         }
      }

      return var7;
   }

   public void addElements(AbstractElement... var1) {
      this.recoveredField14.addAll(Arrays.asList(var1));
      this.initGui();
   }

   public void method_02935() {
      this.recoveredField14.forEach(AbstractElement::handleElementUpdate);
   }

   public void removeElements(AbstractElement... var1) {
      this.recoveredField14.removeAll(Arrays.asList(var1));
      this.initGui();
   }

   public void method_02931(ScaledResolution var1) {
      this.resolution = var1;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      float var4 = this.getScaleFactor();
      this.onMouseClicked(var1 / var4, var2 / var4, var3);
   }

   public void method_02922() {
      this.recoveredField14.forEach(AbstractElement::handleElementClose);
   }

   public void onMouseClicked(float var1, float var2, int var3, AbstractElement... var4) {
      List var5 = Arrays.asList(var4);
      AbstractElement var6 = null;
      boolean var7 = false;

      for (AbstractElement var9 : this.recoveredField14) {
         if (!var5.contains(var9) && var9.a_(var1, var2)) {
            if (!this.elements.contains(var9)) {
               var6 = var9;
            }

            if (var9.handleElementMouseClicked(var1, var2, var3, this.isMouseHovered(var9, var1, var2, var4))) {
               var7 = true;
               break;
            }
         }
      }

      if (!var7) {
         if (var6 != null) {
            this.recoveredField14.add(this.recoveredField14.remove(this.recoveredField14.indexOf(var6)));
         }

         for (AbstractElement var11 : this.recoveredField14) {
            if (var11.handleMouseClickedInternal(var1, var2, var3)) {
               break;
            }
         }
      }
   }

   @Override
   public void setWorldAndResolution(Minecraft var1, int var2, int var3) {
      net.minecraft.client.WindowsImeSupport.beginScreen(this);
      this.j = var1;
      this.q = var1.fontRendererObj;
      this.l = var2;
      this.m = var3;
      this.n.clear();
      this.resolution = new ScaledResolution(this.j);
      float var4 = this.getScaleFactor();
      this.recoveredField13 = var2 / var4;
      this.recoveredField15 = var3 / var4;
      this.initGui();
      net.minecraft.client.WindowsImeSupport.updateGameInput(this);
   }

   public float getScaledWidth() {
      return this.recoveredField13;
   }

   public void handleKeyTyped(char var1, int var2) {
      for (AbstractElement var4 : this.recoveredField14) {
         var4.handleElementKeyTyped(var1, var2);
      }
   }

   public void handleMouse() {
      this.recoveredField14.forEach(AbstractElement::handleElementMouse);
   }

   public abstract void onMouseClicked(float var1, float var2, int var3);

   public abstract void drawMenu(float var1, float var2);

   public void setElements(AbstractElement... var1) {
      this.elements = new ArrayList<>();
      this.elements.addAll(Arrays.asList(var1));
   }

   public void handleMouseReleased(float var1, float var2, int var3) {
      for (AbstractElement var5 : this.recoveredField14) {
         if (var5.a_(var1, var2)) {
            var5.handleElementMouseRelease(var1, var2, var3, this.isMouseHovered(var5, var1, var2));
         }
      }
   }
}
