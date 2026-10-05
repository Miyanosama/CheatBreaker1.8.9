package com.cheatbreaker.client;

import com.cheatbreaker.client.config.ConfigManager;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.event.EventBus;
import com.cheatbreaker.client.event.type.DisconnectEvent;
import com.cheatbreaker.client.event.type.PluginMessageEvent;
import com.cheatbreaker.client.module.ModuleManager;
import com.cheatbreaker.client.nethandler.NetHandler;
import com.cheatbreaker.client.ui.mainmenu.ChangelogMenu;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import com.cheatbreaker.client.util.DiscordPresenceManager;
import com.cheatbreaker.client.util.HardwareId;
import com.cheatbreaker.client.util.SessionServer;
import com.cheatbreaker.client.util.UuidParser;
import com.cheatbreaker.client.util.dash.CBDashManager;
import com.cheatbreaker.client.util.friend.FriendsManager;
import com.cheatbreaker.client.util.friend.Status;
import com.cheatbreaker.client.util.server.ServerRestrictionManager;
import com.cheatbreaker.client.util.voicechat.CaptureDeviceManager;
import com.cheatbreaker.client.util.worldborder.WorldBorderManager;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.google.gson.JsonParser;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.entities.RichPresence$Builder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.sound.sampled.AudioFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.cheatbreaker.client.emote.EmoteManager;
import com.cheatbreaker.client.ui.module.ModuleGuiNavigator;
import com.cheatbreaker.client.util.title.TitleManager;
import com.cheatbreaker.client.util.branch.BranchManager;
import com.cheatbreaker.client.util.HttpJsonReader;
import com.cheatbreaker.client.websocket.client.WSPacketClientKeySync;
import com.cheatbreaker.client.event.type.ClientInitializedEvent;
import com.cheatbreaker.client.command.ModuleCommandManager;
import com.cheatbreaker.client.event.type.WorldChangeEvent;
import com.cheatbreaker.client.util.cosmetic.CosmeticsManager;
import com.cheatbreaker.client.util.discord.DiscordReadyListener;

public class CheatBreaker {
   public CBFontRenderer recoveredField1548;
   public CBFontRenderer recoveredField1549;
   public ResourceLocation recoveredField1550;
   public static ResourceLocation recoveredField1551;
   public ModuleGuiNavigator recoveredField1552;
   public String recoveredField1553;
   public CBFontRenderer recoveredField1554;
   public CBFontRenderer robotoRegular13px;
   public static CheatBreaker instance;
   public String recoveredField1555;
   public IPCClient recoveredField1556;
   public CBFontRenderer recoveredField1557;
   public CBFontRenderer recoveredField1558;
   public ResourceLocation recoveredField1559;
   public String recoveredField1560;
   public static byte[] recoveredField1561 = new byte[]{86, 79, 84, 69, 32, 84, 82, 85, 77, 80, 32, 50, 48, 50, 48, 33};
   public ServerRestrictionManager recoveredField1562;
   public EmoteManager recoveredField1563;
   public CBFontRenderer recoveredField1564;
   public UuidParser recoveredField1565;
   public Map<String, ResourceLocation> playerSkins;
   public String recoveredField1566;
   public ResourceLocation recoveredField1567;
   public EventBus recoveredField1568;
   public String recoveredField1569;
   public String recoveredField1570;
   public CBDashManager radioManager;
   public boolean recoveredField1571;
   public String recoveredField1572;
   public String recoveredField1573;
   public CBFontRenderer playRegular14px;
   public String recoveredField1574;
   public CBFontRenderer recoveredField1575;
   public List<String> recoveredField1576;
   public GuiScreen recoveredField1577;
   public NetHandler netHandler;
   public List<SessionServer> recoveredField1578;
   public ConfigManager configManager;
   public Logger recoveredField1579;
   public static byte[] recoveredField1580 = new byte[]{
      104, 116, 116, 112, 115, 58, 47, 47, 99, 104, 97, 110, 103, 101, 108, 111, 103, 46, 110, 111, 120, 105, 117, 97, 109, 46, 103, 113
   };
   public CaptureDeviceManager recoveredField1581;
   public BranchManager recoveredField1582;
   public CBFontRenderer recoveredField1583;
   public ResourceLocation recoveredField1584;
   public GlobalSettings globalSettings;
   public static AudioFormat recoveredField1585 = new AudioFormat(8000.0F, 16, 1, true, true);
   public ModuleCommandManager recoveredField1586;
   public DiscordPresenceManager recoveredField1587;
   public String recoveredField1588;
   public CBFontRenderer recoveredField1589;
   public boolean recoveredField1590;
   public List<Session> recoveredField1591;
   public CBFontRenderer robotoBold14px;
   public boolean recoveredField1592;
   public boolean recoveredField1593;
   public ResourceLocation recoveredField1594;
   public CBFontRenderer recoveredField1595;
   public Minecraft recoveredField1596 = Minecraft.getMinecraft();
   public ModuleManager moduleManager;
   public AssetsWebSocket websocket;
   public FriendsManager friendsManager;
   public WorldBorderManager borderManager;
   public long startTime;
   public static boolean recoveredField1597 = false;
   public CosmeticsManager recoveredField1598;
   public TitleManager recoveredField1599;
   public Status statusEnum = Status.ONLINE;

   public void setConsoleAllowed(boolean var1) {
      this.recoveredField1593 = var1;
   }

   public ResourceLocation method_19775() {
      return this.recoveredField1567;
   }

   public void method_19809() {
      this.recoveredField1549 = new CBFontRenderer(this.recoveredField1594, 22.0F);
      this.recoveredField1583 = new CBFontRenderer(this.recoveredField1550, 22.0F);
      this.recoveredField1557 = new CBFontRenderer(this.recoveredField1550, 18.0F);
      this.playRegular14px = new CBFontRenderer(this.recoveredField1550, 14.0F);
      this.recoveredField1554 = new CBFontRenderer(this.recoveredField1550, 12.0F);
      this.recoveredField1548 = new CBFontRenderer(this.recoveredField1550, 16.0F);
      this.recoveredField1595 = new CBFontRenderer(this.recoveredField1594, 18.0F);
      this.recoveredField1575 = new CBFontRenderer(this.recoveredField1594, 16.0F);
      this.recoveredField1589 = new CBFontRenderer(this.recoveredField1584, 16.0F);
      this.recoveredField1564 = new CBFontRenderer(this.recoveredField1584, 14.0F);
      this.robotoRegular13px = new CBFontRenderer(this.recoveredField1567, 13.0F);
      this.robotoBold14px = new CBFontRenderer(this.recoveredField1559, 14.0F);
      this.recoveredField1558 = new CBFontRenderer(this.recoveredField1567, 24.0F);
      this.recoveredField1579.info(this.recoveredField1553 + "Loaded all fonts");
   }

   public UuidParser method_19771() {
      return this.recoveredField1565;
   }

   public AssetsWebSocket getAssetsWebSocket() {
      return this.websocket;
   }

   public boolean isAcceptingFriendRequests() {
      return this.recoveredField1592;
   }

   public CBFontRenderer method_19755() {
      return this.recoveredField1549;
   }

   public GlobalSettings getGlobalSettings() {
      return this.globalSettings;
   }

   public NetHandler getNetHandler() {
      return this.netHandler;
   }

   public ModuleCommandManager method_19756() {
      return this.recoveredField1586;
   }

   public long getStartTime() {
      return this.startTime;
   }

   // $VF: synthetic method
   public static DiscordPresenceManager method_19781(CheatBreaker var0) {
      return var0.recoveredField1587;
   }

   public String method_19749() {
      return this.recoveredField1573;
   }

   public EmoteManager method_19783() {
      return this.recoveredField1563;
   }

   public CBFontRenderer method_19790() {
      return this.recoveredField1583;
   }

   public CBFontRenderer method_19764() {
      return this.recoveredField1595;
   }

   public List<Session> method_19800() {
      return this.recoveredField1591;
   }

   public CBFontRenderer method_19747() {
      return this.recoveredField1558;
   }

   public static float method_19763() {
      ScaledResolution var0 = new ScaledResolution(Minecraft.getMinecraft());
      if (getInstance().getGlobalSettings().recoveredField595.method_08908()) {
         switch (Minecraft.getMinecraft().gameSettings.guiScale) {
            case 0:
               return 2.0F;
            case 1:
               return 0.5F;
            case 2:
               return 1.0F;
            case 3:
               return 1.5F;
            default:
               return 1.0F;
         }
      } else {
         return var0.getScaleFactor() / 2.0F;
      }
   }

   public ServerRestrictionManager method_19786() {
      return this.recoveredField1562;
   }

   public Status getStatus() {
      return this.statusEnum;
   }

   public String method_19746() {
      return "CB-Client";
   }

   public boolean method_19745() {
      return this.recoveredField1571;
   }

   public String method_19748() {
      return this.recoveredField1553;
   }

   public CBFontRenderer method_19743() {
      return this.recoveredField1554;
   }

   public String method_19803() {
      return "CB-Binary";
   }

   public CBFontRenderer getRobotoBold14px() {
      return this.robotoBold14px;
   }

   public WorldBorderManager getBorderManager() {
      return this.borderManager;
   }

   public ConfigManager getConfigManager() {
      return this.configManager;
   }

   public FriendsManager getFriendsManager() {
      return this.friendsManager;
   }

   public CosmeticsManager method_19791() {
      return this.recoveredField1598;
   }

   public ModuleManager getModuleManager() {
      return this.moduleManager;
   }

   public CBFontRenderer method_19751() {
      return this.recoveredField1548;
   }

   public ResourceLocation method_19767() {
      return this.recoveredField1559;
   }

   public void method_19761() throws java.net.URISyntaxException {
      this.recoveredField1579.info(this.recoveredField1553 + "Attempting to connect to player assets server");
      HashMap var1 = new HashMap();
      var1.put("username", this.recoveredField1596.getSession().getUsername());
      var1.put("playerId", getInstance().method_19771().method_21033(this.recoveredField1596.getSession().getPlayerID()));
      var1.put("HWID", HardwareId.method_10533());
      var1.put("version", "1.8.9");
      var1.put("gitCommit", this.method_19749());
      var1.put("branch", this.method_19798());
      var1.put("buildType", this.method_19784());
      if (this.recoveredField1574 != null) {
         var1.put("server", this.recoveredField1574);
      }

      this.websocket = new AssetsWebSocket(new URI("ws://dev.moose1301.cf/connect"), var1);
      this.websocket.connect();
   }

   public TitleManager method_19760() {
      return this.recoveredField1599;
   }

   public void method_19759() {
      this.recoveredField1578.add(new SessionServer("Session", "session.minecraft.net"));
      this.recoveredField1578.add(new SessionServer("Login", "authserver.mojang.com"));
      this.recoveredField1578.add(new SessionServer("Account", "account.mojang.com"));
      this.recoveredField1578.add(new SessionServer("API", "api.mojang.com"));
      this.recoveredField1579.info(this.recoveredField1553 + "Loaded mojang session status entries");
   }

   public void method_19777(AssetsWebSocket var1) {
      this.websocket = var1;
   }

   public DiscordPresenceManager method_19742() {
      return this.recoveredField1587;
   }

   public void setStatus(Status var1) {
      this.statusEnum = var1;
   }

   public ModuleGuiNavigator method_19818() {
      return this.recoveredField1552;
   }

   public String method_19784() {
      return this.recoveredField1572;
   }

   public List<String> method_19794() {
      return this.recoveredField1576;
   }

   public String method_19773() {
      return this.recoveredField1574;
   }

   public ResourceLocation method_19765() {
      return this.recoveredField1550;
   }

   public GuiScreen method_19772() {
      return this.recoveredField1577;
   }

   public CaptureDeviceManager method_19741() {
      return this.recoveredField1581;
   }

   public String method_19754() {
      return this.recoveredField1560;
   }

   public void method_19757() {
      try {
         ResourceLocation var1 = new ResourceLocation("client/properties/app.properties");
         Properties var2 = new Properties();
         InputStream var3 = this.recoveredField1596.getResourceManager().getResource(var1).getInputStream();
         if (var3 == null) {
            return;
         }

         var2.load(var3);
         var3.close();
         this.recoveredField1560 = var2.getProperty("git.commit.id.abbrev");
         this.recoveredField1573 = var2.getProperty("git.commit.id");
         this.recoveredField1570 = var2.getProperty("git.branch");
         this.recoveredField1572 = var2.getProperty("git.build.version");
         this.recoveredField1582.method_10218(BuildBranch.method_06001(this.recoveredField1570));
         this.recoveredField1579.info(this.recoveredField1553 + "Loaded client properties");
      } catch (IOException var4) {
         var4.printStackTrace();
         this.recoveredField1582.method_10218(BuildBranch.DEVELOPMENT);
         this.recoveredField1579.error(this.recoveredField1553 + "An error occurred when loading client properties");
      }
   }

   public CheatBreaker() {
      this.recoveredField1577 = null;
      this.playerSkins = new HashMap<>();
      this.recoveredField1591 = new ArrayList<>();
      this.recoveredField1578 = new ArrayList<>();
      this.recoveredField1576 = new ArrayList<>();
      this.recoveredField1560 = "?";
      this.recoveredField1570 = "?";
      this.recoveredField1573 = "?";
      this.recoveredField1572 = "?";
      this.recoveredField1569 = "CB-Client";
      this.recoveredField1566 = "Lunar-Client";
      this.recoveredField1555 = "lunarclient:pm";
      this.recoveredField1588 = "CB-Binary";
      this.recoveredField1553 = "[CB] ";
      this.recoveredField1579 = LogManager.getLogger("CheatBreaker");
      this.recoveredField1592 = true;
      this.recoveredField1590 = false;
      this.recoveredField1571 = false;
      this.recoveredField1550 = new ResourceLocation("client/font/Play-Regular.ttf");
      this.recoveredField1594 = new ResourceLocation("client/font/Play-Bold.ttf");
      this.recoveredField1567 = new ResourceLocation("client/font/Roboto-Regular.ttf");
      this.recoveredField1559 = new ResourceLocation("client/font/Roboto-Bold.ttf");
      this.recoveredField1584 = new ResourceLocation("client/font/Ubuntu-M.ttf");
      this.startTime = System.currentTimeMillis();
      instance = this;
      this.recoveredField1579.info(this.recoveredField1553 + "Starting CheatBreaker setup");
      this.recoveredField1596.recoveredField3820.method_25824("CheatBreaker Setup");
      this.configManager = new ConfigManager();
      this.configManager.method_25098();
      this.recoveredField1596.recoveredField3820.method_25824("Settings");
      this.globalSettings = new GlobalSettings();
      this.recoveredField1582 = new BranchManager();
      this.recoveredField1596.recoveredField3820.method_25824("EventBus");
      this.recoveredField1568 = new EventBus();
      this.recoveredField1596.recoveredField3820.method_25824("Mods");
      this.moduleManager = new ModuleManager(this.recoveredField1568);
      this.recoveredField1586 = new ModuleCommandManager();
      this.recoveredField1562 = new ServerRestrictionManager();
      this.recoveredField1596.recoveredField3820.method_25824("Network Manager");
      this.netHandler = new NetHandler();
      this.recoveredField1587 = new DiscordPresenceManager();
      this.recoveredField1596.recoveredField3820.method_25824("Emotes");
      this.recoveredField1598 = new CosmeticsManager();
      this.recoveredField1563 = new EmoteManager();
      this.recoveredField1596.recoveredField3820.method_25824("Radio");
      this.recoveredField1581 = new CaptureDeviceManager();
      this.radioManager = new CBDashManager();
      this.recoveredField1596.recoveredField3820.method_25824("Friends");
      this.friendsManager = new FriendsManager();
      this.recoveredField1596.recoveredField3820.method_25824("Titles");
      this.recoveredField1599 = new TitleManager();

      try {
         ChangelogMenu.recoveredField2865 = new JsonParser()
            .parse(
               new BufferedReader(
                  new InputStreamReader(HttpJsonReader.method_10985(new URL("https://cheatbreaker2.com/changelog"), false), StandardCharsets.UTF_8)
               )
            )
            .getAsJsonObject();
      } catch (Exception var2) {
         var2.printStackTrace();
         getInstance().method_19789().error(getInstance().method_19748() + "Failed to load changelog.");
      }

      this.recoveredField1596.recoveredField3820.method_25824("World Border");
      this.borderManager = new WorldBorderManager();
      this.recoveredField1565 = new UuidParser();
      this.recoveredField1552 = new ModuleGuiNavigator();
      this.recoveredField1596.recoveredField3820.method_25824("Network Events");
      this.recoveredField1568.method_21938(DisconnectEvent.class, this.netHandler::method_11459);
      this.recoveredField1568.method_21938(WorldChangeEvent.class, this.netHandler::method_11465);
      this.recoveredField1568.method_21938(PluginMessageEvent.class, this.netHandler::method_11464);
      this.recoveredField1579.info(this.recoveredField1553 + "Registered network events");
      SimpleReloadableResourceManager.method_01482();
   }

   public void method_19780(String var1, String[] var2) {
      try {
         if (this.recoveredField1556 != null) {
            RichPresence$Builder var3 = new RichPresence$Builder();
            var3.method_13369(var1).method_13379(var2[2]).method_13376(OffsetDateTime.now()).method_13380(var2[1], var2[0]);
            this.recoveredField1556.method_13320(var3.method_13372());
         }
      } catch (Exception var4) {
      }
   }

   public void method_19788() {
      this.recoveredField1596.recoveredField3820.method_25824("Fonts");
      this.method_19809();
      this.recoveredField1596.recoveredField3820.method_25824("Configurations");
      this.configManager.method_25106();
      this.configManager.method_25101();
      this.recoveredField1596.recoveredField3820.method_25824("Properties");
      this.method_19757();
      this.recoveredField1596.recoveredField3820.method_25824("Session");
      this.method_19759();
      this.recoveredField1596.recoveredField3820.method_25824("Overlay");
      OverlayGui.setInstance(new OverlayGui());

      try {
         this.recoveredField1596.recoveredField3820.method_25824("Player Assets");
         this.method_19761();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.recoveredField1596.recoveredField3820.method_25824("Finishing");
      this.getModuleManager().keyStrokes.initialize();
      this.recoveredField1596.recoveredField3820.method_25825();
      this.recoveredField1579.info(this.recoveredField1553 + "Finished startup in " + (System.currentTimeMillis() - this.startTime) + "ms!");
      this.recoveredField1593 = true;
      new Thread(() -> {
         try {
            while (true) {
               try {
                  if (this.websocket != null) {
                     this.websocket.sentToServer(new WSPacketClientKeySync());
                  }
               } catch (Exception var2x) {
                  var2x.printStackTrace();
               }

               Thread.sleep(30000L);
            }
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      }).start();
      if (System.getProperty("os.name").toLowerCase().contains("win") && this.getGlobalSettings().recoveredField599.method_08908()) {
         this.recoveredField1596.recoveredField3820.method_25825();
         this.method_19739();
      }

      this.recoveredField1596.recoveredField3820.method_25825();
      this.recoveredField1568.method_21935(new ClientInitializedEvent());
   }

   public CBFontRenderer method_19792() {
      return this.recoveredField1564;
   }

   public static CheatBreaker getInstance() {
      return instance;
   }

   public String method_19758() {
      switch (this.statusEnum) {
         case AWAY:
            return "Away";
         case BUSY:
            return "Busy";
         case OFFLINE:
            return "Offline";
         default:
            return "Online";
      }
   }

   public String method_19804() {
      return "lunarclient:pm";
   }

   public void method_19778(String var1) {
      if (this.getGlobalSettings().recoveredField522.method_08908()) {
         this.method_19780(
            this.recoveredField1587.method_10921(this.recoveredField1574)[3] != null ? this.recoveredField1587.method_10921(this.recoveredField1574)[3] : var1,
            this.recoveredField1587.method_10921(this.recoveredField1574)
         );
      } else {
         this.method_19780(var1, new String[]{"Minecraft 1.8.9", "cb", "Minecraft 1.8.9"});
      }
   }

   public CBFontRenderer method_19795() {
      return this.recoveredField1557;
   }

   public ResourceLocation method_19810(String var1) {
      ResourceLocation var2 = this.playerSkins.getOrDefault(var1, new ResourceLocation("client/heads/" + var1 + ".png"));
      if (!this.playerSkins.containsKey(var1)) {
         ThreadDownloadImageData var3 = new ThreadDownloadImageData(
            null, "https://minotar.net/helm/" + var1 + "/32.png", new ResourceLocation("client/defaults/steve.png"), null
         );
         this.recoveredField1596.renderEngine.loadTexture(var2, var3);
         this.playerSkins.put(var1, var2);
      }

      return var2;
   }

   public EventBus method_19817() {
      return this.recoveredField1568;
   }

   public CBFontRenderer getRobotoRegular13px() {
      return this.robotoRegular13px;
   }

   public CBFontRenderer method_19740() {
      return this.recoveredField1589;
   }

   public void method_19779(String var1, String var2, int var3) {
      if (this.websocket != null) {
         try {
            this.recoveredField1574 = var1;
            if (this.getGlobalSettings().recoveredField561.method_08908()) {
               this.method_19778(this.recoveredField1596.getSession().getUsername());
            } else {
               this.method_19778(null);
            }
         } catch (UnsatisfiedLinkError | Exception var5) {
         }

         boolean var4 = var1.equals(var2 + ":" + var3);
         if (!var4) {
            this.websocket.sendUpdateServer(var1);
            this.recoveredField1574 = var1.isEmpty() ? "In-Menus" : var1;
         } else {
            this.websocket.sendUpdateServer("server");
            this.recoveredField1574 = "server";
         }
      }
   }

   public List<SessionServer> method_19770() {
      return this.recoveredField1578;
   }

   public Logger method_19789() {
      return this.recoveredField1579;
   }

   public BranchManager method_19797() {
      return this.recoveredField1582;
   }

   public CBFontRenderer getPlayRegular14px() {
      return this.playRegular14px;
   }

   public ResourceLocation method_19785() {
      return this.recoveredField1584;
   }

   public void setAcceptingFriendRequests(boolean var1) {
      this.recoveredField1592 = var1;
   }

   public IPCClient method_19768() {
      return this.recoveredField1556;
   }

   public ResourceLocation method_19814() {
      return this.recoveredField1594;
   }

   public String method_19766() {
      return "Lunar-Client";
   }

   public boolean method_19793() {
      return this.recoveredField1593;
   }

   public void method_19739() {
      try {
         (this.recoveredField1556 = new IPCClient(925912458353340447L)).method_13319(new DiscordReadyListener(this));
         this.recoveredField1556.method_13323();
         this.recoveredField1579.info(this.recoveredField1553 + "Connected to Discord IPC");
      } catch (Exception var2) {
         this.recoveredField1579.error(this.recoveredField1553 + "Failed to connect to Discord IPC");
      }
   }

   public Minecraft method_19774() {
      return this.recoveredField1596;
   }

   public boolean method_19806() {
      return false;
   }

   public String method_19798() {
      return this.recoveredField1570;
   }

   public Map<String, ResourceLocation> getPlayerSkins() {
      return this.playerSkins;
   }

   public CBFontRenderer method_19753() {
      return this.recoveredField1575;
   }

   public CBDashManager getRadioManager() {
      return this.radioManager;
   }
}
