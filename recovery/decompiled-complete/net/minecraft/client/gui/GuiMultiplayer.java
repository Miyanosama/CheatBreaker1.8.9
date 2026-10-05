package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.ServerRiskWarningGui;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.cheatbreaker.client.ui.serverlist.PinnedServerEntry;
import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.client.network.LanServerDetector$LanServer;
import net.minecraft.client.network.LanServerDetector$LanServerList;
import net.minecraft.client.network.LanServerDetector$ThreadLanServerFind;
import net.minecraft.client.network.OldServerPinger;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.ContainerEnchantment$1;
import net.minecraft.item.Item$17;
import org.apache.log4j.lf5.viewer.configure.MRUFileManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiMultiplayer extends GuiScreen implements GuiYesNoCallback {
   public LanServerDetector$LanServerList lanServerList;
   public LanServerDetector$ThreadLanServerFind lanServerDetector;
   public ServerList savedServerList;
   public GuiButton btnEditServer;
   public ServerSelectionList serverListSelector;
   public boolean field_0003;
   public static Logger logger = LogManager.getLogger();
   public GuiButton btnSelectServer;
   public boolean field_0004;
   public MRUFileManager field_0019;
   public OldServerPinger oldServerPinger = new OldServerPinger();
   public Item$17 field_0009;
   public String hoveringText;
   public boolean initialized;
   public boolean field_0013;
   public boolean field_0016;
   public ServerData selectedServer;
   public GuiScreen parentScreen;
   public ContainerEnchantment$1 field_0010;
   public GuiButton btnDeleteServer;

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.serverListSelector.handleMouseInput();
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
      if (this.lanServerDetector != null) {
         this.lanServerDetector.interrupt();
         this.lanServerDetector = null;
      }

      this.oldServerPinger.clearPendingNetworks();
   }

   public void refreshServerList() {
      this.j.displayGuiScreen(new GuiMultiplayer(this.parentScreen));
   }

   public void func_175391_a(ServerListEntryNormal var1, int var2, boolean var3) {
      int var4 = var3 ? 0 : var2 - 1;
      this.savedServerList.swapServers(var2, var4);
      if (this.serverListSelector.func_148193_k() == var2) {
         this.selectServer(var4);
      }

      this.serverListSelector.func_148195_a(this.savedServerList);
   }

   public GuiMultiplayer(GuiScreen var1) {
      this.parentScreen = var1;
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      if (this.lanServerList.getWasUpdated()) {
         List var1 = this.lanServerList.getLanServers();
         this.lanServerList.setWasNotUpdated();
         this.serverListSelector.func_148194_a(var1);
      }

      this.oldServerPinger.pingPendingNetworks();
   }

   public void func_175393_b(ServerListEntryNormal var1, int var2, boolean var3) {
      int var4 = var3 ? this.savedServerList.countServers() - 1 : var2 + 1;
      this.savedServerList.swapServers(var2, var4);
      if (this.serverListSelector.func_148193_k() == var2) {
         this.selectServer(var4);
      }

      this.serverListSelector.func_148195_a(this.savedServerList);
   }

   public boolean func_175394_b(ServerListEntryNormal var1, int var2) {
      return var2 < this.savedServerList.countServers() - 1;
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         GuiListExtended$IGuiListEntry var2 = this.serverListSelector.func_148193_k() < 0
            ? null
            : this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k());
         if (var1.k == 2 && var2 instanceof ServerListEntryNormal) {
            String var9 = ((ServerListEntryNormal)var2).getServerData().serverName;
            if (var9 != null) {
               this.field_0016 = true;
               String var4 = I18n.format("selectServer.deleteQuestion");
               String var5 = "'" + var9 + "' " + I18n.format("selectServer.deleteWarning");
               String var6 = I18n.format("selectServer.deleteButton");
               String var7 = I18n.format("gui.cancel");
               GuiYesNo var8 = new GuiYesNo(this, var4, var5, var6, var7, this.serverListSelector.func_148193_k());
               this.j.displayGuiScreen(var8);
            }
         } else if (var1.k == 1) {
            this.connectToSelected();
         } else if (var1.k == 4) {
            this.field_0013 = true;
            this.j.displayGuiScreen(new GuiScreenServerList(this, this.selectedServer = new ServerData(I18n.format("selectServer.defaultName"), "", false)));
         } else if (var1.k == 3) {
            this.field_0003 = true;
            this.j.displayGuiScreen(new GuiScreenAddServer(this, this.selectedServer = new ServerData(I18n.format("selectServer.defaultName"), "", false)));
         } else if (var1.k == 7 && var2 instanceof ServerListEntryNormal) {
            this.field_0004 = true;
            ServerData var3 = ((ServerListEntryNormal)var2).getServerData();
            this.selectedServer = new ServerData(var3.serverName, var3.serverIP, false);
            this.selectedServer.copyFrom(var3);
            this.j.displayGuiScreen(new GuiScreenAddServer(this, this.selectedServer));
         } else if (var1.k == 0) {
            if (!this.j.isSingleplayer() && (this.j.isIntegratedServerRunning() || this.j.theWorld == null)) {
               this.j.displayGuiScreen(new MainMenu());
            } else {
               this.j.displayGuiScreen(this.parentScreen);
            }
         } else if (var1.k == 8) {
            this.refreshServerList();
         }
      }
   }

   public void setHoveringText(String var1) {
      this.hoveringText = var1;
   }

   public void connectToSelected() {
      GuiListExtended$IGuiListEntry var1 = this.serverListSelector.func_148193_k() < 0
         ? null
         : this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k());
      if (var1 instanceof ServerListEntryNormal) {
         this.connectToServer(((ServerListEntryNormal)var1).getServerData());
      } else if (var1 instanceof PinnedServerEntry) {
         this.connectToServer(((PinnedServerEntry)var1).getServer());
      } else if (var1 instanceof ServerListEntryLanDetected) {
         LanServerDetector$LanServer var2 = ((ServerListEntryLanDetected)var1).getLanServer();
         this.connectToServer(new ServerData(var2.getServerMotd(), var2.getServerIpPort(), true));
      }
   }

   @Override
   public void keyTyped(char var1, int var2) {
      int var3 = this.serverListSelector.func_148193_k();
      GuiListExtended$IGuiListEntry var4 = var3 < 0 ? null : this.serverListSelector.getListEntry(var3);
      if (var2 == 63) {
         this.refreshServerList();
      } else if (var3 >= 0) {
         if (var2 == 200) {
            if (isShiftKeyDown()) {
               if (var3 > 0 && var4 instanceof ServerListEntryNormal) {
                  this.savedServerList.swapServers(var3, var3 - 1);
                  this.selectServer(this.serverListSelector.func_148193_k() - 1);
                  this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
                  this.serverListSelector.func_148195_a(this.savedServerList);
               }
            } else if (var3 > 0) {
               this.selectServer(this.serverListSelector.func_148193_k() - 1);
               this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
               if (this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k()) instanceof ServerListEntryLanScan) {
                  if (this.serverListSelector.func_148193_k() > 0) {
                     this.selectServer(this.serverListSelector.getSize() - 1);
                     this.serverListSelector.scrollBy(-this.serverListSelector.getSlotHeight());
                  } else {
                     this.selectServer(-1);
                  }
               }
            } else {
               this.selectServer(-1);
            }
         } else if (var2 == 208) {
            if (isShiftKeyDown()) {
               if (var3 < this.savedServerList.countServers() - 1) {
                  this.savedServerList.swapServers(var3, var3 + 1);
                  this.selectServer(var3 + 1);
                  this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
                  this.serverListSelector.func_148195_a(this.savedServerList);
               }
            } else if (var3 < this.serverListSelector.getSize()) {
               this.selectServer(this.serverListSelector.func_148193_k() + 1);
               this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
               if (this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k()) instanceof ServerListEntryLanScan) {
                  if (this.serverListSelector.func_148193_k() < this.serverListSelector.getSize() - 1) {
                     this.selectServer(this.serverListSelector.getSize() + 1);
                     this.serverListSelector.scrollBy(this.serverListSelector.getSlotHeight());
                  } else {
                     this.selectServer(-1);
                  }
               }
            } else {
               this.selectServer(-1);
            }
         } else if (var2 != 28 && var2 != 156) {
            super.keyTyped(var1, var2);
         } else {
            this.actionPerformed(this.n.get(2));
         }
      } else {
         super.keyTyped(var1, var2);
      }
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.n.clear();
      if (!this.initialized) {
         this.initialized = true;
         this.savedServerList = new ServerList(this.j);
         this.savedServerList.loadServerList();
         this.lanServerList = new LanServerDetector$LanServerList();

         try {
            this.lanServerDetector = new LanServerDetector$ThreadLanServerFind(this.lanServerList);
            this.lanServerDetector.start();
         } catch (Exception var2) {
            logger.warn("Unable to start LAN server detection: " + var2.getMessage());
         }

         this.serverListSelector = new ServerSelectionList(this, this.j, this.l, this.m, 32, this.m - 64, 36);
         this.serverListSelector.func_148195_a(this.savedServerList);
      } else {
         this.serverListSelector.a(this.l, this.m, 32, this.m - 64);
      }

      this.createButtons();
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.hoveringText = null;
      this.drawDefaultBackground();
      this.serverListSelector.a(var1, var2, var3);
      this.drawCenteredString(this.q, I18n.format("multiplayer.title"), this.l / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
      if (this.hoveringText != null) {
         this.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), var1, var2);
      }
   }

   public void connectToServer(ServerData var1) {
      if (this.j.currentServerData != null && this.j.theWorld != null) {
         Minecraft.getMinecraft().ingameGUI.method_28526("");
         Minecraft.getMinecraft().ingameGUI.method_28518("");
         this.j.theWorld.method_05035();
         this.j.loadWorld(null);
      }

      for (Entry var3 : CheatBreaker.getInstance().getGlobalSettings().method_02677().entrySet()) {
         for (String var7 : (String[])var3.getKey()) {
            if (var1.serverIP.toLowerCase().startsWith(var7.toLowerCase())
               || var1.serverIP.toLowerCase().matches("([a-zA-Z0-9]+)" + var7.toLowerCase() + "([a-zA-Z0-9]+)")) {
               this.j
                  .displayGuiScreen(
                     new ServerRiskWarningGui(
                        this, var1, ((String[])var3.getValue())[0], ((String[])var3.getValue())[1].equals(String.valueOf(ServerRestrictionAction.field_0005))
                     )
                  );
               return;
            }
         }
      }

      this.j.displayGuiScreen(new GuiConnecting(this, this.j, var1));
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.serverListSelector.b(var1, var2, var3);
   }

   public void createButtons() {
      this.n.add(this.btnEditServer = new GuiButton(7, this.l / 2 - 154, this.m - 28, 70, 20, I18n.format("selectServer.edit")));
      this.n.add(this.btnDeleteServer = new GuiButton(2, this.l / 2 - 74, this.m - 28, 70, 20, I18n.format("selectServer.delete")));
      this.n.add(this.btnSelectServer = new GuiButton(1, this.l / 2 - 154, this.m - 52, 100, 20, I18n.format("selectServer.select")));
      this.n.add(new GuiButton(4, this.l / 2 - 50, this.m - 52, 100, 20, I18n.format("selectServer.direct")));
      this.n.add(new GuiButton(3, this.l / 2 + 4 + 50, this.m - 52, 100, 20, I18n.format("selectServer.add")));
      this.n.add(new GuiButton(8, this.l / 2 + 4, this.m - 28, 70, 20, I18n.format("selectServer.refresh")));
      this.n.add(new GuiButton(0, this.l / 2 + 4 + 76, this.m - 28, 75, 20, I18n.format("gui.cancel")));
      this.selectServer(this.serverListSelector.func_148193_k());
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      super.mouseReleased(var1, var2, var3);
      this.serverListSelector.c(var1, var2, var3);
   }

   public void selectServer(int var1) {
      this.serverListSelector.setSelectedSlotIndex(var1);
      GuiListExtended$IGuiListEntry var2 = var1 < 0 ? null : this.serverListSelector.getListEntry(var1);
      this.btnSelectServer.l = false;
      this.btnEditServer.l = false;
      this.btnDeleteServer.l = false;
      if (var2 != null && !(var2 instanceof ServerListEntryLanScan)) {
         this.btnSelectServer.l = true;
         if (var2 instanceof ServerListEntryNormal) {
            this.btnEditServer.l = true;
            this.btnDeleteServer.l = true;
         }
      }
   }

   @Override
   public void confirmClicked(boolean var1, int var2) {
      GuiListExtended$IGuiListEntry var3 = this.serverListSelector.func_148193_k() < 0
         ? null
         : this.serverListSelector.getListEntry(this.serverListSelector.func_148193_k());
      if (this.field_0016) {
         this.field_0016 = false;
         if (var1 && var3 instanceof ServerListEntryNormal) {
            this.savedServerList.removeServerData(this.serverListSelector.func_148193_k());
            this.savedServerList.saveServerList();
            this.serverListSelector.setSelectedSlotIndex(-1);
            this.serverListSelector.func_148195_a(this.savedServerList);
         }

         this.j.displayGuiScreen(this);
      } else if (this.field_0013) {
         this.field_0013 = false;
         if (var1) {
            this.connectToServer(this.selectedServer);
         } else {
            this.j.displayGuiScreen(this);
         }
      } else if (this.field_0003) {
         this.field_0003 = false;
         if (var1) {
            this.savedServerList.addServerData(this.selectedServer);
            this.savedServerList.saveServerList();
            this.serverListSelector.setSelectedSlotIndex(-1);
            this.serverListSelector.func_148195_a(this.savedServerList);
         }

         this.j.displayGuiScreen(this);
      } else if (this.field_0004) {
         this.field_0004 = false;
         if (var1 && (var3 instanceof ServerListEntryNormal || var3 instanceof PinnedServerEntry)) {
            ServerData var4 = ((ServerListEntryNormal)var3).getServerData();
            var4.serverName = this.selectedServer.serverName;
            var4.serverIP = this.selectedServer.serverIP;
            var4.copyFrom(this.selectedServer);
            this.savedServerList.saveServerList();
            this.serverListSelector.func_148195_a(this.savedServerList);
         }

         this.j.displayGuiScreen(this);
      }
   }

   public ServerList getServerList() {
      return this.savedServerList;
   }

   public OldServerPinger getOldServerPinger() {
      return this.oldServerPinger;
   }

   public boolean func_175392_a(ServerListEntryNormal var1, int var2) {
      return var2 > 0;
   }
}
