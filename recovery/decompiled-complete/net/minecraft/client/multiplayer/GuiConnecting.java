package net.minecraft.client.multiplayer;

import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuiConnecting extends GuiScreen {
   public static AtomicInteger CONNECTION_ID = new AtomicInteger(0);
   public GuiScreen previousGuiScreen;
   public NetworkManager networkManager;
   public static Logger logger = LogManager.getLogger();
   public boolean cancel;

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.k == 0) {
         this.cancel = true;
         if (this.networkManager != null) {
            this.networkManager.closeChannel(new ChatComponentText("Aborted"));
         }

         this.j.displayGuiScreen(this.previousGuiScreen);
      }
   }

   @Override
   public void keyTyped(char var1, int var2) {
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      if (this.networkManager == null) {
         this.drawCenteredString(this.q, I18n.format("connect.connecting"), this.l / 2, this.m / 2 - 50, 16777215);
      } else {
         this.drawCenteredString(this.q, I18n.format("connect.authorizing"), this.l / 2, this.m / 2 - 50, 16777215);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void connect(String var1, int var2) {
      logger.info("Connecting to " + var1 + ", " + var2);
      new GuiConnecting$1(this, "Server Connector #" + CONNECTION_ID.incrementAndGet(), var1, var2).start();
   }

   @Override
   public void initGui() {
      this.n.clear();
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 120 + 12, I18n.format("gui.cancel")));
   }

   public GuiConnecting(GuiScreen var1, Minecraft var2, ServerData var3) {
      this.j = var2;
      this.previousGuiScreen = var1;
      ServerAddress var4 = ServerAddress.fromString(var3.serverIP);
      var2.loadWorld((WorldClient)null);
      var2.setServerData(var3);
      this.connect(var4.getIP(), var4.getPort());
   }

   public GuiConnecting(GuiScreen var1, Minecraft var2, String var3, int var4) {
      this.j = var2;
      this.previousGuiScreen = var1;
      var2.loadWorld((WorldClient)null);
      this.connect(var3, var4);
   }

   @Override
   public void updateScreen() {
      if (this.networkManager != null) {
         if (this.networkManager.isChannelOpen()) {
            this.networkManager.processReceivedPackets();
         } else {
            this.networkManager.checkDisconnected();
         }
      }
   }
}
