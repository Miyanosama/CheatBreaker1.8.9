package net.minecraft.client.gui;

import java.net.URI;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiScreenDemo extends GuiScreen {
   public static Logger logger = LogManager.getLogger();
   public static ResourceLocation field_146348_f = new ResourceLocation("textures/gui/demo_background.png");

   @Override
   public void updateScreen() {
      super.updateScreen();
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      switch (var1.k) {
         case 1:
            var1.l = false;

            try {
               Class var2 = Class.forName("java.awt.Desktop");
               Object var3 = var2.getMethod("getDesktop").invoke(null);
               var2.getMethod("browse", URI.class).invoke(var3, new URI("http://www.minecraft.net/store?source=demo"));
            } catch (Throwable var4) {
               logger.error("Couldn't open link", var4);
            }
            break;
         case 2:
            this.j.displayGuiScreen((GuiScreen)null);
            this.j.method_20340();
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      int var4 = (this.l - 248) / 2 + 10;
      int var5 = (this.m - 166) / 2 + 8;
      this.q.drawString(I18n.format("demo.help.title"), var4, var5, 2039583);
      var5 += 12;
      GameSettings var6 = this.j.gameSettings;
      this.q
         .drawString(
            I18n.format(
               "demo.help.movementShort",
               GameSettings.getKeyDisplayString(var6.keyBindForward.getKeyCode()),
               GameSettings.getKeyDisplayString(var6.keyBindLeft.getKeyCode()),
               GameSettings.getKeyDisplayString(var6.keyBindBack.getKeyCode()),
               GameSettings.getKeyDisplayString(var6.keyBindRight.getKeyCode())
            ),
            var4,
            var5,
            5197647
         );
      this.q.drawString(I18n.format("demo.help.movementMouse"), var4, var5 + 12, 5197647);
      this.q.drawString(I18n.format("demo.help.jump", GameSettings.getKeyDisplayString(var6.keyBindJump.getKeyCode())), var4, var5 + 24, 5197647);
      this.q.drawString(I18n.format("demo.help.inventory", GameSettings.getKeyDisplayString(var6.keyBindInventory.getKeyCode())), var4, var5 + 36, 5197647);
      this.q.drawSplitString(I18n.format("demo.help.fullWrapped"), var4, var5 + 68, 218, 2039583);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void initGui() {
      this.n.clear();
      byte var1 = -16;
      this.n.add(new GuiButton(1, this.l / 2 - 116, this.m / 2 + 62 + var1, 114, 20, I18n.format("demo.help.buy")));
      this.n.add(new GuiButton(2, this.l / 2 + 2, this.m / 2 + 62 + var1, 114, 20, I18n.format("demo.help.later")));
   }

   @Override
   public void drawDefaultBackground() {
      super.drawDefaultBackground();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(field_146348_f);
      int var1 = (this.l - 248) / 2;
      int var2 = (this.m - 166) / 2;
      this.drawTexturedModalRect(var1, var2, 0, 0, 248, 166);
   }
}
