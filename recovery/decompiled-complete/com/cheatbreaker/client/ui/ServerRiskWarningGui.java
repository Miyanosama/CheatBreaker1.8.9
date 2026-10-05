package com.cheatbreaker.client.ui;

import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.websocket.shared.WSPacketFriendRequest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$LeftTurn;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0882;

public class ServerRiskWarningGui extends GuiScreen {
   public StructureStrongholdPieces$LeftTurn field_0003;
   public String field_0005;
   public GuiScreen field_0002;
   public WSPacketFriendRequest field_0004;
   public boolean field_0000;
   public long field_0001;
   public ServerData field_0006;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      this.drawDefaultBackground();
      float var4 = 0.52F;
      int var5 = this.field_0001 == (1855094616278320130L & -1855094617896840252L)
         ? 10
         : (int)(((1509964535L & 9198049036924694527L) - (System.currentTimeMillis() - this.field_0001)) / (44438510L & 138414072L));
      String var6 = this.field_0000 ? "Leave" : "Get me out of here!";
      String var7 = "I understand the risk, continue anyways " + (var5 > 0 ? "(" + var5 + ")" : "");
      String var8 = this.field_0000 ? "THIS SERVER IS " + EnumChatFormatting.RED + "BLOCKED" : "WARNING!";
      float var9 = this.j.fontRendererObj.getStringWidth(var6);
      boolean var10 = var1 > this.l / 2 + 20 && var1 < this.l / 2 + 140 && var2 > this.m / 2 + 40 && var2 < this.m / 2 + 50;
      boolean var11 = (this.field_0000 ? var1 > this.l / 2 - 20 : var1 > this.l / 2 - 130)
         && (this.field_0000 ? var1 < this.l / 2 - 20 + var9 / var4 : var1 < this.l / 2 - 130 + var9 / var4)
         && var2 > this.m / 2 + 30
         && var2 < this.m / 2 + 42;
      RenderUtil.method_22054(this.l / 2.0F - 140.0F, this.m / 2.0F - 100.0F, this.l / 2.0F + 140.0F, this.m / 2.0F + 50.0F, 8.0, 1342111744);
      GL11.glPushMatrix();
      GL11.glScalef(var4, var4, var4);
      if (!this.field_0000) {
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
      String var12 = UnidentifiedClass0882.method_05899(this.field_0005).method_29697(50).method_29703(false).method_29693();
      String[] var13 = var12.split("\n");
      int var14 = 0;

      for (String var18 : var13) {
         this.j.fontRendererObj.method_08776(var18, (int)(this.l / 2 / var4), (int)((this.m / 2 - 70 + var14 * 20) / var4), -1);
         var14++;
      }

      if (!this.field_0000) {
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
      if (this.field_0000) {
         this.j.fontRendererObj.method_08776(var6, this.l / 2, this.m / 2 + 30, var11 ? -1 : -1907998);
      } else {
         this.j.fontRendererObj.drawStringWithShadow(var6, this.l / 2 - 130, this.m / 2 + 30, var11 ? -1 : -1907998);
      }
   }

   public ServerRiskWarningGui(GuiScreen var1, ServerData var2, String var3, boolean var4) {
      this.field_0002 = var1;
      this.field_0006 = var2;
      this.field_0001 = System.currentTimeMillis();
      this.field_0005 = var3;
      this.field_0000 = var4;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      float var4 = 0.5F;
      String var5 = this.field_0000 ? "Leave" : "Get me out of here!";
      float var6 = this.j.fontRendererObj.getStringWidth(var5);
      boolean var7 = var1 > this.l / 2 + 20 && var1 < this.l / 2 + 140 && var2 > this.m / 2 + 40 && var2 < this.m / 2 + 50;
      boolean var8 = (this.field_0000 ? var1 > this.l / 2 - 20 : var1 > this.l / 2 - 130)
         && (this.field_0000 ? var1 < this.l / 2 - 20 + var6 / var4 : var1 < this.l / 2 - 130 + var6 / var4)
         && var2 > this.m / 2 + 30
         && var2 < this.m / 2 + 42;
      if (var8) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(this.field_0002);
      }

      if (var7 && !this.field_0000) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         int var9 = this.field_0001 == (3007599599385874432L & 566584320L)
            ? 10
            : (int)(((1166393335L & 1583871L) - (System.currentTimeMillis() - this.field_0001)) / (1647412200L & 541676L));
         if (var9 <= 0) {
            this.j.displayGuiScreen(new GuiConnecting(this.field_0002, this.j, this.field_0006));
         }
      }
   }
}
