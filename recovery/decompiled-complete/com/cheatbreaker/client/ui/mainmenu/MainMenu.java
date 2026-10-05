package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.handler.codec.http.HttpObjectEncoder;
import io.netty.util.internal.MpscLinkedQueue;
import java.awt.Color;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class MainMenu extends MainMenuBase {
   public MinMaxFade field_0005;
   public HttpObjectEncoder field_0003;
   public GradientTextButton multiplayerButton;
   public GradientTextButton singleplayerButton;
   public ResourceLocation innerLogo;
   public EntityAnimal field_0010;
   public CosineFade field_0008;
   public ResourceLocation outerLogo = new ResourceLocation("client/logo_255_outer.png");
   public MinMaxFade field_0007;
   public MpscLinkedQueue field_0004;
   public static int field_0006;

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (this.method_12917() && !this.field_0005.method_21217()) {
         this.field_0005.method_20200();
      }

      if ((!this.method_12917() || this.field_0005.method_21210()) && !this.field_0008.method_21217()) {
         this.field_0007.method_20200();
         this.field_0008.method_20200();
         this.field_0008.method_21234();
      }
   }

   @Override
   public void initGui() {
      super.initGui();
      this.singleplayerButton.setElementSize(this.getScaledWidth() / 2.0F - 50.0F, this.getScaledHeight() / 2.0F + 5.0F, 100.0F, 12.0F);
      this.multiplayerButton.setElementSize(this.getScaledWidth() / 2.0F - 50.0F, this.getScaledHeight() / 2.0F + 24.0F, 100.0F, 12.0F);
      field_0006++;
   }

   public MinMaxFade method_12919() {
      return this.field_0007;
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
      float var3 = this.method_12917() ? this.field_0005.method_21227() : 1.0F;
      if (this.method_12917()) {
         Gui.drawRect(0.0F, 0.0F, this.getScaledWidth(), this.getScaledHeight(), new Color(1.0F, 1.0F, 1.0F, 1.0F - this.field_0007.method_21227()).getRGB());
      }

      this.method_12918(this.getScaledWidth(), this.getScaledHeight(), var3);
      float var4 = this.getScaledWidth() / 2.0F - 80.0F;
      float var5 = this.getScaledHeight() - 40.0F;
      CheatBreaker.getInstance()
         .field_0040
         .drawCenteredString(
            "finished", this.getScaledWidth() / 2.0F, var5 - 11.0F, new Color(208, 208, 208, (int)(255.0F * (1.0F - Math.min(var3, 0.984F)))).getRGB()
         );
      RenderUtil.method_22054(var4, var5, var4 + 160.0F, var5 + 10.0F, 8.0, new Color(218, 66, 83, (int)(255.0F * (1.0F - var3))).getRGB());
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
   }

   public MainMenu() {
      this.innerLogo = new ResourceLocation("client/logo_108_inner.png");
      this.singleplayerButton = new GradientTextButton("SINGLEPLAYER");
      this.multiplayerButton = new GradientTextButton("MULTIPLAYER");
      this.field_0005 = new MinMaxFade(8413809124379384814L & -8413809126211058962L);
      this.field_0008 = new CosineFade(537567154L & 1224740836L);
      this.field_0007 = new MinMaxFade(1111521688L & -5330609091509157488L);
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
      return field_0006 == 1;
   }

   public void method_12918(double var1, double var3, float var5) {
      float var6 = 27.0F;
      double var7 = var1 / 2.0 - var6;
      double var9 = var3 / 2.0 - var6 - 35.0F * var5;
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glTranslatef((float)var7, (float)var9, 1.0F);
      GL11.glTranslatef(var6, var6, var6);
      GL11.glRotatef(180.0F * this.field_0008.method_21227(), 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-var6, -var6, -var6);
      RenderUtil.method_22063(this.outerLogo, var6, 0.0F, 0.0F);
      GL11.glPopMatrix();
      RenderUtil.method_22063(this.innerLogo, var6, (float)var7, (float)var9);
   }
}
