package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.ui.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.davidmoten.text.utils.WordWrap;

public class ServerRiskWarningGui extends GuiScreen {
   public String recoveredField218;
   public GuiScreen recoveredField219;
   public boolean recoveredField220;
   public long recoveredField221;
   public ServerData recoveredField222;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      this.drawDefaultBackground();
      float var4 = 0.52F;
      int var5 = this.recoveredField221 == 0L ? 10 : (int)((10999L - (System.currentTimeMillis() - this.recoveredField221)) / 1000L);
      String var6 = this.recoveredField220 ? "Leave" : "Get me out of here!";
      String var7 = "I understand the risk, continue anyways " + (var5 > 0 ? "(" + var5 + ")" : "");
      String var8 = this.recoveredField220 ? "THIS SERVER IS " + EnumChatFormatting.RED + "BLOCKED" : "WARNING!";
      float var9 = this.j.fontRendererObj.getStringWidth(var6);
      boolean var10 = var1 > this.l / 2 + 20 && var1 < this.l / 2 + 140 && var2 > this.m / 2 + 40 && var2 < this.m / 2 + 50;
      boolean var11 = (this.recoveredField220 ? var1 > this.l / 2 - 20 : var1 > this.l / 2 - 130)
         && (this.recoveredField220 ? var1 < this.l / 2 - 20 + var9 / var4 : var1 < this.l / 2 - 130 + var9 / var4)
         && var2 > this.m / 2 + 30
         && var2 < this.m / 2 + 42;
      RenderUtil.method_22054(this.l / 2.0F - 140.0F, this.m / 2.0F - 100.0F, this.l / 2.0F + 140.0F, this.m / 2.0F + 50.0F, 8.0, 1342111744);
      GL11.glPushMatrix();
      GL11.glScalef(var4, var4, var4);
      if (!this.recoveredField220) {
         this.j
            .fontRendererObj
            .drawStringWithShadow(
               var7, (int)((this.l / 2 + (var5 < 0 ? 29 : 30)) / var4), (int)((this.m / 2 + 40) / var4), var10 ? -1 : (var5 <= 0 ? -1907998 : -5066062)
            );
      }

      GL11.glPopMatrix();
      GL11.glPushMatrix();
      var4 = 1.0F;
      GL11.glScalef(var4, var4, var4);
      String var12 = WordWrap.method_05899(this.recoveredField218).method_29697(50).method_29703(false).method_29693();
      String[] var13 = var12.split("\n");
      int var14 = 0;

      for (String var18 : var13) {
         this.j.fontRendererObj.method_08776(var18, (int)(this.l / 2 / var4), (int)((this.m / 2 - 70 + var14 * 20) / var4), -1);
         var14++;
      }

      if (!this.recoveredField220) {
         this.j
            .fontRendererObj
            .method_08776(
               "Join at " + EnumChatFormatting.BOLD + "your own" + EnumChatFormatting.RESET + " risk!",
               (int)(this.l / 2 / var4),
               (int)((this.m / 2 - 70 + var14 * 20) / var4),
               -1
            );
      }

      GL11.glPopMatrix();
      this.j.fontRendererObj.method_08776(var8, this.l / 2, this.m / 2 - 90, -1);
      if (this.recoveredField220) {
         this.j.fontRendererObj.method_08776(var6, this.l / 2, this.m / 2 + 30, var11 ? -1 : -1907998);
      } else {
         this.j.fontRendererObj.drawStringWithShadow(var6, this.l / 2 - 130, this.m / 2 + 30, var11 ? -1 : -1907998);
      }
   }

   public ServerRiskWarningGui(GuiScreen var1, ServerData var2, String var3, boolean var4) {
      this.recoveredField219 = var1;
      this.recoveredField222 = var2;
      this.recoveredField221 = System.currentTimeMillis();
      this.recoveredField218 = var3;
      this.recoveredField220 = var4;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      super.mouseClicked(var1, var2, var3);
      float var4 = 0.5F;
      String var5 = this.recoveredField220 ? "Leave" : "Get me out of here!";
      float var6 = this.j.fontRendererObj.getStringWidth(var5);
      boolean var7 = var1 > this.l / 2 + 20 && var1 < this.l / 2 + 140 && var2 > this.m / 2 + 40 && var2 < this.m / 2 + 50;
      boolean var8 = (this.recoveredField220 ? var1 > this.l / 2 - 20 : var1 > this.l / 2 - 130)
         && (this.recoveredField220 ? var1 < this.l / 2 - 20 + var6 / var4 : var1 < this.l / 2 - 130 + var6 / var4)
         && var2 > this.m / 2 + 30
         && var2 < this.m / 2 + 42;
      if (var8) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(this.recoveredField219);
      }

      if (var7 && !this.recoveredField220) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         int var9 = this.recoveredField221 == 0L ? 10 : (int)((10999L - (System.currentTimeMillis() - this.recoveredField221)) / 1000L);
         if (var9 <= 0) {
            this.j.displayGuiScreen(new GuiConnecting(this.recoveredField219, this.j, this.recoveredField222));
         }
      }
   }
}
