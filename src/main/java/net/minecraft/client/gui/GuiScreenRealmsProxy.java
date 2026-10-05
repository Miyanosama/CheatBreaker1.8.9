package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.realms.RealmsButton;
import net.minecraft.realms.RealmsScreen;

public class GuiScreenRealmsProxy extends GuiScreen {
   public RealmsScreen field_154330_a;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.field_154330_a.render(var1, var2, var3);
   }

   public void func_154324_i() {
      super.n.clear();
   }

   public List<RealmsButton> func_154320_j() {
      ArrayList var1 = Lists.newArrayListWithExpectedSize(super.n.size());

      for (GuiButton var3 : super.n) {
         var1.add(((GuiButtonRealmsProxy)var3).getRealmsButton());
      }

      return var1;
   }

   public void func_154319_c(String var1, int var2, int var3, int var4) {
      this.q.drawStringWithShadow(var1, var2, var3, var4);
   }

   @Override
   public void drawGradientRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      super.drawGradientRect(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void drawWorldBackground(int var1) {
      super.drawWorldBackground(var1);
   }

   @Override
   public void drawCreativeTabHoveringText(String var1, int var2, int var3) {
      super.drawCreativeTabHoveringText(var1, var2, var3);
   }

   public int func_154329_h() {
      return this.q.FONT_HEIGHT;
   }

   @Override
   public void updateScreen() {
      this.field_154330_a.method_01601();
      super.updateScreen();
   }

   public RealmsScreen func_154321_a() {
      return this.field_154330_a;
   }

   @Override
   public void confirmClicked(boolean var1, int var2) {
      this.field_154330_a.confirmResult(var1, var2);
   }

   public int func_154326_c(String var1) {
      return this.q.getStringWidth(var1);
   }

   public void func_154327_a(RealmsButton var1) {
      super.n.add(var1.getProxy());
   }

   public GuiScreenRealmsProxy(RealmsScreen var1) {
      this.field_154330_a = var1;
      super.n = Collections.synchronizedList(Lists.newArrayList());
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      this.field_154330_a.method_01562();
      super.handleMouseInput();
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      this.field_154330_a.buttonClicked(((GuiButtonRealmsProxy)var1).getRealmsButton());
   }

   @Override
   public void initGui() {
      this.field_154330_a.init();
      super.initGui();
   }

   public void func_154322_b(String var1, int var2, int var3, int var4, boolean var5) {
      if (var5) {
         super.drawString(this.q, var1, var2, var3, var4);
      } else {
         this.q.drawString(var1, var2, var3, var4);
      }
   }

   @Override
   public void drawTexturedModalRect(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_154330_a.blit(var1, var2, var3, var4, var5, var6);
      super.drawTexturedModalRect(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void a_() {
      this.field_154330_a.method_01561();
      super.a_();
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      this.field_154330_a.keyPressed(var1, var2);
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      this.field_154330_a.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }

   @Override
   public void renderToolTip(ItemStack var1, int var2, int var3) {
      super.renderToolTip(var1, var2, var3);
   }

   public void func_154328_b(RealmsButton var1) {
      super.n.remove(var1.getProxy());
   }

   @Override
   public void handleKeyboardInput() throws java.io.IOException {
      this.field_154330_a.method_01593();
      super.handleKeyboardInput();
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      this.field_154330_a.mouseReleased(var1, var2, var3);
   }

   public List<String> func_154323_a(String var1, int var2) {
      return this.q.listFormattedStringToWidth(var1, var2);
   }

   @Override
   public void drawHoveringText(List<String> var1, int var2, int var3) {
      super.drawHoveringText(var1, var2, var3);
   }

   @Override
   public void drawDefaultBackground() {
      super.drawDefaultBackground();
   }

   @Override
   public boolean b_() {
      return super.b_();
   }

   public void func_154325_a(String var1, int var2, int var3, int var4) {
      super.drawCenteredString(this.q, var1, var2, var3, var4);
   }

   @Override
   public void mouseClickMove(int var1, int var2, int var3, long var4) {
      this.field_154330_a.mouseDragged(var1, var2, var3, var4);
   }
}
