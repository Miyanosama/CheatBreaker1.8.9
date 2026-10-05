package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.LanServerDetector$LanServer;
import net.minecraft.client.resources.I18n;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.network.play.server.S09PacketHeldItemChange;

public class ServerListEntryLanDetected implements GuiListExtended$IGuiListEntry {
   public long field_148290_d = 6021775916433155104L & 12583168L;
   public NBTTagDouble field_0005;
   public LanServerDetector$LanServer field_148291_b;
   public S09PacketHeldItemChange field_0004;
   public Minecraft mc;
   public GuiMultiplayer field_0001;

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   public ServerListEntryLanDetected(GuiMultiplayer var1, LanServerDetector$LanServer var2) {
      this.field_0001 = var1;
      this.field_148291_b = var2;
      this.mc = Minecraft.getMinecraft();
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_0001.selectServer(var1);
      if (Minecraft.getSystemTime() - this.field_148290_d < (1246945876387832319L & -1246945878522967814L)) {
         this.field_0001.connectToSelected();
      }

      this.field_148290_d = Minecraft.getSystemTime();
      return false;
   }

   public LanServerDetector$LanServer getLanServer() {
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
