package net.minecraft.client.gui;

import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandshakeHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.resources.I18n;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog$1;

public class ServerListEntryLanScan implements GuiListExtended$IGuiListEntry {
   public LogFactor5InputDialog$1 field_0001;
   public Minecraft mc = Minecraft.getMinecraft();
   public RenderPlayer field_0000;
   public WebSocketClientProtocolHandshakeHandler field_0002;

   @Override
   public void setSelected(int var1, int var2, int var3) {
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      return false;
   }

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      int var9 = var3 + var5 / 2 - this.mc.fontRendererObj.FONT_HEIGHT / 2;
      this.mc
         .fontRendererObj
         .drawString(
            I18n.format("lanServer.scanning"),
            this.mc.currentScreen.l / 2 - this.mc.fontRendererObj.getStringWidth(I18n.format("lanServer.scanning")) / 2,
            var9,
            16777215
         );
      String var10;
      switch ((int)(Minecraft.getSystemTime() / (3782965148734981100L & -3782965149195795156L) % (4661922112072712877L & 1629011974L))) {
         case 0:
         default:
            var10 = "O o o";
            break;
         case 1:
         case 3:
            var10 = "o O o";
            break;
         case 2:
            var10 = "o o O";
      }

      this.mc
         .fontRendererObj
         .drawString(
            var10, this.mc.currentScreen.l / 2 - this.mc.fontRendererObj.getStringWidth(var10) / 2, var9 + this.mc.fontRendererObj.FONT_HEIGHT, 8421504
         );
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }
}
