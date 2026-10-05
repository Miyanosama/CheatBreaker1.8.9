package net.optifine.gui;

import com.mojang.authlib.exceptions.InvalidCredentialsException;
import java.math.BigInteger;
import java.net.URI;
import java.util.Random;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.src.Config;
import net.optifine.Lang;

public class GuiScreenCapeOF extends GuiScreenOF {
   public GuiScreen parentScreen;
   public long messageHideTimeMs;
   public FontRenderer fontRenderer = Config.getMinecraft().fontRendererObj;
   public String recoveredField3773;
   public String recoveredField3774;
   public String linkUrl;
   public GuiButtonOF buttonCopyLink;

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k == 200) {
            this.j.displayGuiScreen(this.parentScreen);
         }

         if (var1.k == 210) {
            try {
               String var2 = this.j.getSession().getProfile().getName();
               String var3 = this.j.getSession().getProfile().getId().toString().replace("-", "");
               String var4 = this.j.getSession().getToken();
               Random var5 = new Random();
               Random var6 = new Random(System.identityHashCode(new Object()));
               BigInteger var7 = new BigInteger(128, var5);
               BigInteger var8 = new BigInteger(128, var6);
               BigInteger var9 = var7.xor(var8);
               String var10 = var9.toString(16);
               this.j.getSessionService().joinServer(this.j.getSession().getProfile(), var4, var10);
               String var11 = "https://optifine.net/capeChange?u=" + var3 + "&n=" + var2 + "&s=" + var10;
               boolean var12 = Config.openWebLink(new URI(var11));
               if (var12) {
                  this.showMessage(Lang.get("of.message.capeOF.openEditor"), 10000L);
               } else {
                  this.showMessage(Lang.get("of.message.capeOF.openEditorError"), 10000L);
                  this.setLinkUrl(var11);
               }
            } catch (InvalidCredentialsException var13) {
               Config.showGuiMessage(I18n.format("of.message.capeOF.error1"), I18n.format("of.message.capeOF.error2", var13.getMessage()));
               Config.warn("Mojang authentication failed");
               Config.warn(var13.getClass().getName() + ": " + var13.getMessage());
            } catch (Exception var14) {
               Config.warn("Error opening OptiFine cape link");
               Config.warn(var14.getClass().getName() + ": " + var14.getMessage());
            }
         }

         if (var1.k == 220) {
            this.showMessage(Lang.get("of.message.capeOF.reloadCape"), 15000L);
            if (this.j.thePlayer != null) {
               long var15 = 15000L;
               long var16 = System.currentTimeMillis() + var15;
               this.j.thePlayer.setReloadCapeTimeMs(var16);
            }
         }

         if (var1.k == 230 && this.linkUrl != null) {
            setClipboardString(this.linkUrl);
         }
      }
   }

   public GuiScreenCapeOF(GuiScreen var1) {
      this.parentScreen = var1;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.recoveredField3774, this.l / 2, 20, 16777215);
      if (this.recoveredField3773 != null) {
         this.drawCenteredString(this.fontRenderer, this.recoveredField3773, this.l / 2, this.m / 6 + 60, 16777215);
         if (System.currentTimeMillis() > this.messageHideTimeMs) {
            this.recoveredField3773 = null;
            this.setLinkUrl((String)null);
         }
      }

      super.drawScreen(var1, var2, var3);
   }

   public void setLinkUrl(String var1) {
      this.linkUrl = var1;
      this.buttonCopyLink.m = var1 != null;
   }

   @Override
   public void initGui() {
      int var1 = 0;
      this.recoveredField3774 = I18n.format("of.options.capeOF.title");
      var1 += 2;
      this.n.add(new GuiButtonOF(210, this.l / 2 - 155, this.m / 6 + 24 * (var1 >> 1), 150, 20, I18n.format("of.options.capeOF.openEditor")));
      this.n.add(new GuiButtonOF(220, this.l / 2 - 155 + 160, this.m / 6 + 24 * (var1 >> 1), 150, 20, I18n.format("of.options.capeOF.reloadCape")));
      var1 += 6;
      this.buttonCopyLink = new GuiButtonOF(230, this.l / 2 - 100, this.m / 6 + 24 * (var1 >> 1), 200, 20, I18n.format("of.options.capeOF.copyEditorLink"));
      this.buttonCopyLink.m = this.linkUrl != null;
      this.n.add(this.buttonCopyLink);
      var1 += 4;
      this.n.add(new GuiButtonOF(200, this.l / 2 - 100, this.m / 6 + 24 * (var1 >> 1), I18n.format("gui.done")));
   }

   public void showMessage(String var1, long var2) {
      this.recoveredField3773 = var1;
      this.messageHideTimeMs = System.currentTimeMillis() + var2;
      this.setLinkUrl((String)null);
   }
}
