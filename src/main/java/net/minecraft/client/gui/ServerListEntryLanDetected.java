package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.resources.I18n;

public class ServerListEntryLanDetected implements GuiListExtended.IGuiListEntry {
   public long field_148290_d = 0L;
   public LanServerDetector.LanServer field_148291_b;
   public Minecraft mc;
   public GuiMultiplayer recoveredField1375;

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   public ServerListEntryLanDetected(GuiMultiplayer var1, LanServerDetector.LanServer var2) {
      this.recoveredField1375 = var1;
      this.field_148291_b = var2;
      this.mc = Minecraft.getMinecraft();
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.recoveredField1375.selectServer(var1);
      if (Minecraft.getSystemTime() - this.field_148290_d < 250L) {
         this.recoveredField1375.connectToSelected();
      }

      this.field_148290_d = Minecraft.getSystemTime();
      return false;
   }

   public LanServerDetector.LanServer getLanServer() {
      return this.field_148291_b;
   }

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      this.mc.fontRendererObj.drawString(I18n.format("lanServer.title"), var2 + 32 + 3, var3 + 1, 16777215);
      this.mc.fontRendererObj.drawString(this.field_148291_b.getServerMotd(), var2 + 32 + 3, var3 + 12, 8421504);
      if (this.mc.gameSettings.hideServerAddress) {
         this.mc.fontRendererObj.drawString(I18n.format("selectServer.hiddenAddress"), var2 + 32 + 3, var3 + 12 + 11, 3158064);
      } else {
         this.mc.fontRendererObj.drawString(this.field_148291_b.getServerIpPort(), var2 + 32 + 3, var3 + 12 + 11, 3158064);
      }
   }

   @Override
   public void setSelected(int var1, int var2, int var3) {
   }
}
