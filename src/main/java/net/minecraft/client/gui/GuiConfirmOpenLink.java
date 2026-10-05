package net.minecraft.client.gui;

import java.net.URI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GuiConfirmOpenLink extends GuiYesNo {
   public String openLinkWarning;
   public String copyLinkButtonText;
   public String linkText;
   public boolean showSecurityWarning = true;

   public void copyLinkToClipboard() {
      setClipboardString(this.linkText);
      Minecraft.getMinecraft().displayGuiScreen(null);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      if (this.showSecurityWarning) {
         this.drawCenteredString(this.q, this.openLinkWarning, this.l / 2, 110, 16764108);
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      try {
         if (var1.k == 2) {
            this.copyLinkToClipboard();
         }

         if (var1.k == 0) {
            this.openWebLink(new URI(this.linkText));
            Minecraft.getMinecraft().displayGuiScreen(null);
         }

         if (var1.k == 1) {
            Minecraft.getMinecraft().displayGuiScreen(null);
         }
      } catch (Throwable var3) {
         com.cheatbreaker.recovery.ExceptionRethrow.<RuntimeException>rethrow(var3);
      }
   }

   public void disableSecurityWarning() {
      this.showSecurityWarning = false;
   }

   public GuiConfirmOpenLink(GuiYesNoCallback var1, String var2, int var3, boolean var4) {
      super(var1, I18n.format(var4 ? "chat.link.confirmTrusted" : "chat.link.confirm"), var2, var3);
      this.confirmButtonText = I18n.format(var4 ? "chat.link.open" : "gui.yes");
      this.cancelButtonText = I18n.format(var4 ? "gui.cancel" : "gui.no");
      this.copyLinkButtonText = I18n.format("chat.copy");
      this.openLinkWarning = I18n.format("chat.link.warning");
      this.linkText = var2;
   }

   @Override
   public void initGui() {
      super.initGui();
      this.n.clear();
      this.n.add(new GuiButton(0, this.l / 2 - 50 - 105, this.m / 6 + 96, 100, 20, this.confirmButtonText));
      this.n.add(new GuiButton(2, this.l / 2 - 50, this.m / 6 + 96, 100, 20, this.copyLinkButtonText));
      this.n.add(new GuiButton(1, this.l / 2 - 50 + 105, this.m / 6 + 96, 100, 20, this.cancelButtonText));
   }
}
