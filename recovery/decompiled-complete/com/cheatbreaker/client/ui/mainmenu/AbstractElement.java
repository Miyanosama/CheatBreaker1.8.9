package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;

public abstract class AbstractElement {
   public Minecraft mc = Minecraft.getMinecraft();
   public float y;
   public float x;
   public LayerArmorBase field_0000;
   public CheatBreaker client = CheatBreaker.getInstance();
   public float width;
   public float height;

   public float getHeight() {
      return this.height;
   }

   public float getX() {
      return this.x;
   }

   public boolean a_(float var1, float var2) {
      return var1 > this.x && var1 < this.x + this.width && var2 > this.y && var2 < this.y + this.height;
   }

   public void handleElementClose() {
   }

   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      return false;
   }

   public float getWidth() {
      return this.width;
   }

   public void setElementSize(float var1, float var2, float var3, float var4) {
      this.x = var1;
      this.y = var2;
      this.width = var3;
      this.height = var4;
   }

   public void handleElementMouse() {
   }

   public boolean handleMouseClickedInternal(float var1, float var2, int var3) {
      return false;
   }

   public void handleElementKeyTyped(char var1, int var2) {
   }

   public void handleElementUpdate() {
   }

   public float getY() {
      return this.y;
   }

   public abstract void handleElementDraw(float var1, float var2, boolean var3);

   public boolean drawElement(float var1, float var2, boolean var3) {
      this.handleElementDraw(var1, var2, var3);
      return this.a_(var1, var2);
   }

   public boolean handleElementMouseRelease(float var1, float var2, int var3, boolean var4) {
      return false;
   }
}
