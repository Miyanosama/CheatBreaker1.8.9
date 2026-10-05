package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.awt.Color;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MainMenu extends MainMenuBase {
   public MinMaxFade recoveredField2506;
   public GradientTextButton multiplayerButton;
   public GradientTextButton singleplayerButton;
   public ResourceLocation innerLogo;
   public CosineFade recoveredField2507;
   public ResourceLocation outerLogo = new ResourceLocation("client/logo_255_outer.png");
   public MinMaxFade recoveredField2508;
   public static int recoveredField2509;

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (this.method_12917() && !this.recoveredField2506.method_21217()) {
         this.recoveredField2506.method_20200();
      }

      if ((!this.method_12917() || this.recoveredField2506.method_21210()) && !this.recoveredField2507.method_21217()) {
         this.recoveredField2508.method_20200();
         this.recoveredField2507.method_20200();
         this.recoveredField2507.method_21234();
      }
   }

   @Override
   public void initGui() {
      super.initGui();
      this.singleplayerButton.setElementSize(this.getScaledWidth() / 2.0F - 50.0F, this.getScaledHeight() / 2.0F + 5.0F, 100.0F, 12.0F);
      this.multiplayerButton.setElementSize(this.getScaledWidth() / 2.0F - 50.0F, this.getScaledHeight() / 2.0F + 24.0F, 100.0F, 12.0F);
      recoveredField2509++;
   }

   public MinMaxFade method_12919() {
      return this.recoveredField2508;
   }

   @Override
   public void drawMenu(float var1, float var2) {
      super.drawMenu(var1, var2);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.singleplayerButton.drawElement(var1, var2, true);
      this.multiplayerButton.drawElement(var1, var2, true);
      Gui.drawRect(
         this.singleplayerButton.getX() - 20.0F,
         this.getScaledHeight() / 2.0F - 80.0F,
         this.singleplayerButton.getX() + this.singleplayerButton.getWidth() + 20.0F,
         this.multiplayerButton.getY() + this.multiplayerButton.getHeight() + 14.0F,
         788529152
      );
      float var3 = this.method_12917() ? this.recoveredField2506.method_21227() : 1.0F;
      if (this.method_12917()) {
         Gui.drawRect(
            0.0F, 0.0F, this.getScaledWidth(), this.getScaledHeight(), new Color(1.0F, 1.0F, 1.0F, 1.0F - this.recoveredField2508.method_21227()).getRGB()
         );
      }

      this.method_12918(this.getScaledWidth(), this.getScaledHeight(), var3);
      float var4 = this.getScaledWidth() / 2.0F - 80.0F;
      float var5 = this.getScaledHeight() - 40.0F;
      CheatBreaker.getInstance()
         .recoveredField1564
         .drawCenteredString(
            "finished", this.getScaledWidth() / 2.0F, var5 - 11.0F, new Color(208, 208, 208, (int)(255.0F * (1.0F - Math.min(var3, 0.984F)))).getRGB()
         );
      RenderUtil.method_22054(var4, var5, var4 + 160.0F, var5 + 10.0F, 8.0, new Color(218, 66, 83, (int)(255.0F * (1.0F - var3))).getRGB());
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
   }

   public MainMenu() {
      this.innerLogo = new ResourceLocation("client/logo_108_inner.png");
      this.singleplayerButton = new GradientTextButton("SINGLEPLAYER");
      this.multiplayerButton = new GradientTextButton("MULTIPLAYER");
      this.recoveredField2506 = new MinMaxFade(750L);
      this.recoveredField2507 = new CosineFade(4000L);
      this.recoveredField2508 = new MinMaxFade(400L);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      super.onMouseClicked(var1, var2, var3);
      this.singleplayerButton.handleElementMouseClicked(var1, var2, var3, true);
      this.multiplayerButton.handleElementMouseClicked(var1, var2, var3, true);
      if (this.singleplayerButton.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new GuiSelectWorld(this));
      } else if (this.multiplayerButton.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new GuiMultiplayer(this));
      }
   }

   public boolean method_12917() {
      return recoveredField2509 == 1;
   }

   public void method_12918(double var1, double var3, float var5) {
      float var6 = 27.0F;
      double var7 = var1 / 2.0 - var6;
      double var9 = var3 / 2.0 - var6 - 35.0F * var5;
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef((float)var7, (float)var9, 1.0F);
      GL11.glTranslatef(var6, var6, var6);
      GL11.glRotatef(180.0F * this.recoveredField2507.method_21227(), 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-var6, -var6, -var6);
      RenderUtil.method_22063(this.outerLogo, var6, 0.0F, 0.0F);
      GL11.glPopMatrix();
      RenderUtil.method_22063(this.innerLogo, var6, (float)var7, (float)var9);
   }
}
