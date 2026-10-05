package net.minecraft.client.multiplayer;

import com.cheatbreaker.client.CheatBreaker;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cheatbreaker.client.event.type.WorldChangeEvent;

public class GuiConnecting extends GuiScreen {
   public static AtomicInteger CONNECTION_ID = new AtomicInteger(0);
   public GuiScreen previousGuiScreen;
   public volatile NetworkManager networkManager;
   public static Logger logger = LogManager.getLogger();
   public volatile boolean cancel;

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == 0) {
         this.cancel = true;
         if (this.networkManager != null) {
            this.networkManager.closeChannel(new ChatComponentText("Aborted"));
         }

         this.j.displayGuiScreen(this.previousGuiScreen);
      }
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
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

   public void connect(final String var1, final int var2) {
      this.connect(var1, var2, false);
   }

   protected ServerAddress resolveAddress(String host, int port, boolean lookupSrv) {
      return lookupSrv ? ServerAddress.fromString(host) : new ServerAddress(host, port);
   }

   private void showConnectionError(final String reason) {
      this.j.addScheduledTask(() -> {
         if (!this.cancel && this.j.currentScreen == this) {
            this.j.displayGuiScreen(new GuiDisconnected(this.previousGuiScreen, "connect.failed",
               new ChatComponentTranslation("disconnect.genericReason", reason)));
         }
      });
   }

   private void connect(final String var1, final int var2, final boolean lookupSrv) {
      logger.info("Connecting to " + var1 + ", " + var2);
      (new Thread("Server Connector #" + CONNECTION_ID.incrementAndGet()) {
            @Override
            public void run() {
               InetAddress var1x = null;
               int resolvedPort = var2;

               try {
                  if (GuiConnecting.this.cancel) {
                     return;
                  }

                  ServerAddress address = GuiConnecting.this.resolveAddress(var1, var2, lookupSrv);
                  String host = address.getIP();
                  resolvedPort = address.getPort();
                  if (GuiConnecting.this.cancel) return;
                  var1x = InetAddress.getByName(host);
                  if (GuiConnecting.this.cancel) return;
                  CheatBreaker.getInstance().method_19817().method_21935(new WorldChangeEvent());
                  GuiConnecting.this.networkManager = NetworkManager.createNetworkManagerAndConnect(
                     var1x, resolvedPort, GuiConnecting.this.j.gameSettings.isUsingNativeTransport()
                  );
                  if (GuiConnecting.this.cancel) {
                     GuiConnecting.this.networkManager.closeChannel(new ChatComponentText("Aborted"));
                     return;
                  }
                  GuiConnecting.this.networkManager
                     .setNetHandler(new NetHandlerLoginClient(GuiConnecting.this.networkManager, GuiConnecting.this.j, GuiConnecting.this.previousGuiScreen));
                  GuiConnecting.this.networkManager.sendPacket(new C00Handshake(47, host, resolvedPort, EnumConnectionState.LOGIN));
                  GuiConnecting.this.networkManager.sendPacket(new C00PacketLoginStart(GuiConnecting.this.j.getSession().getProfile()));
               } catch (UnknownHostException var5) {
                  if (GuiConnecting.this.cancel) {
                     return;
                  }

                  GuiConnecting.logger.error("Couldn't connect to server", var5);
                  GuiConnecting.this.showConnectionError("Unknown host");
               } catch (Exception var6) {
                  if (GuiConnecting.this.cancel) {
                     return;
                  }

                  GuiConnecting.logger.error("Couldn't connect to server", var6);
                  String var3 = var6.toString();
                  if (var1x != null) {
                     String var4 = var1x.toString() + ":" + resolvedPort;
                     var3 = var3.replace(var4, "");
                  }

                  GuiConnecting.this.showConnectionError(var3);
               }
            }
         })
         .start();
   }

   @Override
   public void initGui() {
      this.n.clear();
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 120 + 12, I18n.format("gui.cancel")));
   }

   public GuiConnecting(GuiScreen var1, Minecraft var2, ServerData var3) {
      this.j = var2;
      this.previousGuiScreen = var1;
      if (var2.theWorld != null || var2.getIntegratedServer() != null) {
         var2.loadWorld((WorldClient)null);
      }
      var2.setServerData(var3);
      this.connect(var3.serverIP, 25565, true);
   }

   public GuiConnecting(GuiScreen var1, Minecraft var2, String var3, int var4) {
      this.j = var2;
      this.previousGuiScreen = var1;
      if (var2.theWorld != null || var2.getIntegratedServer() != null) {
         var2.loadWorld((WorldClient)null);
      }
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
