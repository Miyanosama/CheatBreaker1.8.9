package net.optifine.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.block.BlockDoubleStoneSlab;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.src.Config;
import net.optifine.BlockPosM$1$1;
import org.apache.log4j.ConsoleAppender$SystemOutStream;
import recovered.unidentified.UnidentifiedClass3499;

public class GuiMessage extends GuiScreen {
   public GuiScreen parentScreen;
   public String confirmButtonText;
   public ConsoleAppender$SystemOutStream field_0003;
   public String messageLine1;
   public String messageLine2;
   public BlockPosM$1$1 field_0001;
   public List listLines2 = Lists.newArrayList();
   public UnidentifiedClass3499 field_0005;
   public int ticksUntilEnable;
   public BlockDoubleStoneSlab field_0009;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.messageLine1, this.l / 2, 70, 16777215);
      int var4 = 90;

      for (Object var6 : this.listLines2) {
         String var7 = (String)var6;
         this.drawCenteredString(this.q, var7, this.l / 2, var4, 16777215);
         var4 += this.q.FONT_HEIGHT;
      }

      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void initGui() {
      this.n.add(new GuiOptionButton(0, this.l / 2 - 74, this.m / 6 + 96, this.confirmButtonText));
      this.listLines2.clear();
      this.listLines2.addAll(this.q.listFormattedStringToWidth(this.messageLine2, this.l - 50));
   }

   public GuiMessage(GuiScreen var1, String var2, String var3) {
      this.parentScreen = var1;
      this.messageLine1 = var2;
      this.messageLine2 = var3;
      this.confirmButtonText = I18n.format("gui.done");
   }

   public void setButtonDelay(int var1) {
      this.ticksUntilEnable = var1;

      for (GuiButton var3 : this.n) {
         var3.l = false;
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      Config.getMinecraft().displayGuiScreen(this.parentScreen);
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (--this.ticksUntilEnable == 0) {
         for (GuiButton var2 : this.n) {
            var2.l = true;
         }
      }
   }
}
