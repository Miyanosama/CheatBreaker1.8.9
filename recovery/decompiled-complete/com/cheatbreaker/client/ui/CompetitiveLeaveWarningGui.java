package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import io.netty.util.concurrent.AbstractFuture;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Bootstrap$15;
import net.minecraft.util.ResourceLocation;
import org.slf4j.event.SubstituteLoggingEvent;

public class CompetitiveLeaveWarningGui extends AbstractGui {
   public ColorFade field_0001;
   public boolean field_0007;
   public GradientTextButton field_0000;
   public GradientTextButton field_0003;
   public SubstituteLoggingEvent field_0004;
   public AbstractFuture field_0002;
   public Bootstrap$15 field_0005;
   public GuiScreen field_0006;

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
   }

   public CompetitiveLeaveWarningGui(GuiScreen var1) {
      this.field_0006 = var1;
      this.field_0001 = new ColorFade(-8749717822247383088L & 8749717820778223578L, -1, -52429);
      this.field_0003 = new GradientTextButton("Cancel");
      this.field_0000 = new GradientTextButton("I understand, continue");
   }

   @Override
   public void drawMenu(float var1, float var2) {
      if (this.field_0007 && this.field_0001.method_21210()) {
         this.field_0007 = false;
      } else if (!this.field_0007 && this.field_0001.method_21210()) {
         this.field_0007 = true;
      }

      this.method_11295(this.getScaledWidth(), this.getScaledHeight());
      float var3 = this.getScaledWidth() / 2.0F;
      float var4 = this.getScaledHeight() / 2.0F - 50.0F;
      CheatBreaker.getInstance().field_0039.drawCenteredString("WARNING!", var3, var4, this.field_0001.method_25066(this.field_0007).getRGB());
      CheatBreaker.getInstance().field_0036.drawCenteredString("Leaving competitive games may result in penalties.", var3, var4 + 15.0F, -1);
      CheatBreaker.getInstance()
         .field_0036
         .drawCenteredString("You may be suspended from competitive games if you continue leaving games!", var3, var4 + 25.0F, -1);
      this.field_0003.drawElement(var1, var2, true);
      this.field_0000.drawElement(var1, var2, true);
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      if (this.field_0003.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(this.field_0006);
      } else if (this.field_0000.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.theWorld.method_05035();
         this.j.loadWorld(null);
         this.j.displayGuiScreen(new MainMenu());
      }
   }

   @Override
   public void a_() {
      this.j.entityRenderer.stopUseShader();
   }

   @Override
   public void initGui() {
      this.method_11296();
      this.field_0007 = true;
      float var1 = this.getScaledWidth() / 2.0F;
      float var2 = this.getScaledHeight() / 2.0F - 50.0F;
      this.field_0000.setElementSize(var1 - 75.0F, var2 + 50.0F, 150.0F, 12.0F);
      this.field_0003.setElementSize(var1 - 75.0F, var2 + 64.0F, 150.0F, 12.0F);
      this.field_0003.method_25133();
   }
}
