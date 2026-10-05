package com.cheatbreaker.client;

import com.cheatbreaker.client.config.ConfigManager;
import com.cheatbreaker.client.config.GlobalSettings;
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
import junit.runner.SimpleTestCollector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.network.play.client.C07PacketPlayerDigging$Action;
import net.minecraft.server.management.PlayerProfileCache$ProfileEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedClass0189;
import recovered.unidentified.UnidentifiedClass0611;
import recovered.unidentified.UnidentifiedClass1117;
import recovered.unidentified.UnidentifiedClass1468;
import recovered.unidentified.UnidentifiedClass1610;
import recovered.unidentified.UnidentifiedClass1812;
import recovered.unidentified.UnidentifiedClass1944;
import recovered.unidentified.UnidentifiedClass1951;
import recovered.unidentified.UnidentifiedClass3556;
import recovered.unidentified.UnidentifiedClass4396;
import recovered.unidentified.UnidentifiedClass4492;
import recovered.unidentified.UnidentifiedClass4579;

public class CheatBreaker {
   public CBFontRenderer field_0036;
   public CBFontRenderer field_0062;
   public ResourceLocation field_0032;
   public static ResourceLocation field_0058;
   public UnidentifiedClass0611 field_0011;
   public String field_0016;
   public CBFontRenderer field_0063;
   public CBFontRenderer robotoRegular13px;
   public static CheatBreaker instance;
   public String field_0066;
   public IPCClient field_0010;
   public CBFontRenderer field_0037;
   public CBFontRenderer field_0044;
   public ResourceLocation field_0025;
   public String field_0054;
   public static byte[] field_0061 = new byte[]{86, 79, 84, 69, 32, 84, 82, 85, 77, 80, 32, 50, 48, 50, 48, 33};
   public ServerRestrictionManager field_0008;
   public UnidentifiedClass0189 field_0024;
   public CBFontRenderer field_0040;
   public UuidParser field_0060;
   public Map<String, ResourceLocation> playerSkins;
   public String field_0006;
   public ResourceLocation field_0009;
   public UnidentifiedClass3556 field_0004;
   public String field_0056;
   public String field_0041;
   public CBDashManager radioManager;
   public boolean field_0030;
   public String field_0064;
   public String field_0045;
   public CBFontRenderer playRegular14px;
   public SimpleTestCollector field_0012;
   public String field_0028;
   public CBFontRenderer field_0022;
   public List<String> field_0021;
   public GuiScreen field_0020;
   public PlayerProfileCache$ProfileEntry field_0003;
   public NetHandler netHandler;
   public List<SessionServer> field_0038;
   public ConfigManager configManager;
   public Logger field_0034;
   public C07PacketPlayerDigging$Action field_0026;
   public static byte[] field_0033 = new byte[]{
      104, 116, 116, 112, 115, 58, 47, 47, 99, 104, 97, 110, 103, 101, 108, 111, 103, 46, 110, 111, 120, 105, 117, 97, 109, 46, 103, 113
   };
   public CaptureDeviceManager field_0002;
   public UnidentifiedClass1468 field_0015;
   public CBFontRenderer field_0018;
   public ResourceLocation field_0042;
   public GlobalSettings globalSettings;
   public static AudioFormat field_0013 = new AudioFormat(8000.0F, 16, 1, true, true);
   public UnidentifiedClass1951 field_0019;
   public DiscordPresenceManager field_0057;
   public String field_0049;
   public CBFontRenderer field_0068;
   public boolean field_0055;
   public List<Session> field_0001;
   public CBFontRenderer robotoBold14px;
   public boolean field_0043;
   public boolean field_0051;
   public ResourceLocation field_0005;
   public CBFontRenderer field_0039;
   public Minecraft field_0029 = Minecraft.getMinecraft();
   public ModuleManager moduleManager;
   public AssetsWebSocket websocket;
   public FriendsManager friendsManager;
   public WorldBorderManager borderManager;
   public long startTime;
   public static boolean field_0059 = false;
   public UnidentifiedClass4492 field_0052;
   public UnidentifiedClass1117 field_0067;
   public Status statusEnum = Status.ONLINE;

   public void setConsoleAllowed(boolean var1) {
      this.field_0051 = var1;
   }

   public ResourceLocation method_19775() {
      return this.field_0009;
   }

   public void method_19809() {
      this.field_0062 = new CBFontRenderer(this.field_0005, 22.0F);
      this.field_0018 = new CBFontRenderer(this.field_0032, 22.0F);
      this.field_0037 = new CBFontRenderer(this.field_0032, 18.0F);
      this.playRegular14px = new CBFontRenderer(this.field_0032, 14.0F);
      this.field_0063 = new CBFontRenderer(this.field_0032, 12.0F);
      this.field_0036 = new CBFontRenderer(this.field_0032, 16.0F);
      this.field_0039 = new CBFontRenderer(this.field_0005, 18.0F);
      this.field_0022 = new CBFontRenderer(this.field_0005, 16.0F);
      this.field_0068 = new CBFontRenderer(this.field_0042, 16.0F);
      this.field_0040 = new CBFontRenderer(this.field_0042, 14.0F);
      this.robotoRegular13px = new CBFontRenderer(this.field_0009, 13.0F);
      this.robotoBold14px = new CBFontRenderer(this.field_0025, 14.0F);
      this.field_0044 = new CBFontRenderer(this.field_0009, 24.0F);
      this.field_0034.info(this.field_0016 + "Loaded all fonts");
   }

   public UuidParser method_19771() {
      return this.field_0060;
   }

   public AssetsWebSocket getAssetsWebSocket() {
      return this.websocket;
   }

   public boolean isAcceptingFriendRequests() {
      return this.field_0043;
   }

   public CBFontRenderer method_19755() {
      return this.field_0062;
   }

   public GlobalSettings getGlobalSettings() {
      return this.globalSettings;
   }

   public NetHandler getNetHandler() {
      return this.netHandler;
   }

   public UnidentifiedClass1951 method_19756() {
      return this.field_0019;
   }

   public long getStartTime() {
      return this.startTime;
   }

   public String method_19749() {
      return this.field_0045;
   }

   public UnidentifiedClass0189 method_19783() {
      return this.field_0024;
   }

   public CBFontRenderer method_19790() {
      return this.field_0018;
   }

   public CBFontRenderer method_19764() {
      return this.field_0039;
   }

   public List<Session> method_19800() {
      return this.field_0001;
   }

   public CBFontRenderer method_19747() {
      return this.field_0044;
   }

   public static float method_19763() {
      ScaledResolution var0 = new ScaledResolution(Minecraft.getMinecraft());
      if (getInstance().getGlobalSettings().field_0080.method_08908()) {
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
      return this.field_0008;
   }

   public Status getStatus() {
      return this.statusEnum;
   }

   public String method_19746() {
      return "CB-Client";
   }

   public boolean method_19745() {
      return this.field_0030;
   }

   public String method_19748() {
      return this.field_0016;
   }

   public CBFontRenderer method_19743() {
      return this.field_0063;
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

   public UnidentifiedClass4492 method_19791() {
      return this.field_0052;
   }

   public ModuleManager getModuleManager() {
      return this.moduleManager;
   }

   public CBFontRenderer method_19751() {
      return this.field_0036;
   }

   public ResourceLocation method_19767() {
      return this.field_0025;
   }

   public void method_19761() {
      this.field_0034.info(this.field_0016 + "Attempting to connect to player assets server");
      HashMap var1 = new HashMap();
      var1.put("username", this.field_0029.getSession().getUsername());
      var1.put("playerId", getInstance().method_19771().method_21033(this.field_0029.getSession().getPlayerID()));
      var1.put("HWID", HardwareId.method_10533());
      var1.put("version", "1.8.9");
      var1.put("gitCommit", this.method_19749());
      var1.put("branch", this.method_19798());
      var1.put("buildType", this.method_19784());
      if (this.field_0028 != null) {
         var1.put("server", this.field_0028);
      }

      this.websocket = new AssetsWebSocket(new URI("ws://dev.moose1301.cf/connect"), var1);
      this.websocket.connect();
   }

   public UnidentifiedClass1117 method_19760() {
      return this.field_0067;
   }

   public void method_19759() {
      this.field_0038.add(new SessionServer("Session", "session.minecraft.net"));
      this.field_0038.add(new SessionServer("Login", "authserver.mojang.com"));
      this.field_0038.add(new SessionServer("Account", "account.mojang.com"));
      this.field_0038.add(new SessionServer("API", "api.mojang.com"));
      this.field_0034.info(this.field_0016 + "Loaded mojang session status entries");
   }

   public void method_19777(AssetsWebSocket var1) {
      this.websocket = var1;
   }

   public DiscordPresenceManager method_19742() {
      return this.field_0057;
   }

   public void setStatus(Status var1) {
      this.statusEnum = var1;
   }

   public UnidentifiedClass0611 method_19818() {
      return this.field_0011;
   }

   public String method_19784() {
      return this.field_0064;
   }

   public List<String> method_19794() {
      return this.field_0021;
   }

   public String method_19773() {
      return this.field_0028;
   }

   public ResourceLocation method_19765() {
      return this.field_0032;
   }

   public GuiScreen method_19772() {
      return this.field_0020;
   }

   public CaptureDeviceManager method_19741() {
      return this.field_0002;
   }

   public String method_19754() {
      return this.field_0054;
   }

   public void method_19757() {
      try {
         ResourceLocation var1 = new ResourceLocation("client/properties/app.properties");
         Properties var2 = new Properties();
         InputStream var3 = this.field_0029.getResourceManager().getResource(var1).getInputStream();
         if (var3 == null) {
            return;
         }

         var2.load(var3);
         var3.close();
         this.field_0054 = var2.getProperty("git.commit.id.abbrev");
         this.field_0045 = var2.getProperty("git.commit.id");
         this.field_0041 = var2.getProperty("git.branch");
         this.field_0064 = var2.getProperty("git.build.version");
         this.field_0015.method_10218(BuildBranch.method_06001(this.field_0041));
         this.field_0034.info(this.field_0016 + "Loaded client properties");
      } catch (IOException var4) {
         var4.printStackTrace();
         this.field_0015.method_10218(BuildBranch.field_0007);
         this.field_0034.error(this.field_0016 + "An error occurred when loading client properties");
      }
   }

   public CheatBreaker() {
      this.field_0020 = null;
      this.playerSkins = new HashMap<>();
      this.field_0001 = new ArrayList<>();
      this.field_0038 = new ArrayList<>();
      this.field_0021 = new ArrayList<>();
      this.field_0054 = "?";
      this.field_0041 = "?";
      this.field_0045 = "?";
      this.field_0064 = "?";
      this.field_0056 = "CB-Client";
      this.field_0006 = "Lunar-Client";
      this.field_0066 = "lunarclient:pm";
      this.field_0049 = "CB-Binary";
      this.field_0016 = "[CB] ";
      this.field_0034 = LogManager.getLogger("CheatBreaker");
      this.field_0043 = true;
      this.field_0055 = false;
      this.field_0030 = false;
      this.field_0032 = new ResourceLocation("client/font/Play-Regular.ttf");
      this.field_0005 = new ResourceLocation("client/font/Play-Bold.ttf");
      this.field_0009 = new ResourceLocation("client/font/Roboto-Regular.ttf");
      this.field_0025 = new ResourceLocation("client/font/Roboto-Bold.ttf");
      this.field_0042 = new ResourceLocation("client/font/Ubuntu-M.ttf");
      this.startTime = System.currentTimeMillis();
      instance = this;
      this.field_0034.info(this.field_0016 + "Starting CheatBreaker setup");
      this.field_0029.field_0002.method_25824("CheatBreaker Setup");
      this.configManager = new ConfigManager();
      this.configManager.method_25098();
      this.field_0029.field_0002.method_25824("Settings");
      this.globalSettings = new GlobalSettings();
      this.field_0015 = new UnidentifiedClass1468();
      this.field_0029.field_0002.method_25824("EventBus");
      this.field_0004 = new UnidentifiedClass3556();
      this.field_0029.field_0002.method_25824("Mods");
      this.moduleManager = new ModuleManager(this.field_0004);
      this.field_0019 = new UnidentifiedClass1951();
      this.field_0008 = new ServerRestrictionManager();
      this.field_0029.field_0002.method_25824("Network Manager");
      this.netHandler = new NetHandler();
      this.field_0057 = new DiscordPresenceManager();
      this.field_0029.field_0002.method_25824("Emotes");
      this.field_0052 = new UnidentifiedClass4492();
      this.field_0024 = new UnidentifiedClass0189();
      this.field_0029.field_0002.method_25824("Radio");
      this.field_0002 = new CaptureDeviceManager();
      this.radioManager = new CBDashManager();
      this.field_0029.field_0002.method_25824("Friends");
      this.friendsManager = new FriendsManager();
      this.field_0029.field_0002.method_25824("Titles");
      this.field_0067 = new UnidentifiedClass1117();

      try {
         ChangelogMenu.field_0000 = new JsonParser()
            .parse(
               new BufferedReader(
                  new InputStreamReader(UnidentifiedClass1610.method_10985(new URL("https://cheatbreaker2.com/changelog"), false), StandardCharsets.UTF_8)
               )
            )
            .getAsJsonObject();
      } catch (Exception var2) {
         var2.printStackTrace();
         getInstance().method_19789().error(getInstance().method_19748() + "Failed to load changelog.");
      }

      this.field_0029.field_0002.method_25824("World Border");
      this.borderManager = new WorldBorderManager();
      this.field_0060 = new UuidParser();
      this.field_0011 = new UnidentifiedClass0611();
      this.field_0029.field_0002.method_25824("Network Events");
      this.field_0004.method_21938(DisconnectEvent.class, this.netHandler::method_11459);
      this.field_0004.method_21938(UnidentifiedClass4396.class, this.netHandler::method_11465);
      this.field_0004.method_21938(PluginMessageEvent.class, this.netHandler::method_11464);
      this.field_0034.info(this.field_0016 + "Registered network events");
      SimpleReloadableResourceManager.method_01482();
   }

   public void method_19780(String var1, String[] var2) {
      try {
         if (this.field_0010 != null) {
            RichPresence$Builder var3 = new RichPresence$Builder();
            var3.method_13369(var1).method_13379(var2[2]).method_13376(OffsetDateTime.now()).method_13380(var2[1], var2[0]);
            this.field_0010.method_13320(var3.method_13372());
         }
      } catch (Exception var4) {
      }
   }

   public void method_19788() {
      this.field_0029.field_0002.method_25824("Fonts");
      this.method_19809();
      this.field_0029.field_0002.method_25824("Configurations");
      this.configManager.method_25106();
      this.configManager.method_25101();
      this.field_0029.field_0002.method_25824("Properties");
      this.method_19757();
      this.field_0029.field_0002.method_25824("Session");
      this.method_19759();
      this.field_0029.field_0002.method_25824("Overlay");
      OverlayGui.setInstance(new OverlayGui());

      try {
         this.field_0029.field_0002.method_25824("Player Assets");
         this.method_19761();
      } catch (Exception var2) {
         var2.printStackTrace();
      }

      this.field_0029.field_0002.method_25824("Finishing");
      this.getModuleManager().keyStrokes.initialize();
      this.field_0029.field_0002.method_25825();
      this.field_0034.info(this.field_0016 + "Finished startup in " + (System.currentTimeMillis() - this.startTime) + "ms!");
      this.field_0051 = true;
      new Thread(() -> {
         try {
            while (true) {
               try {
                  if (this.websocket != null) {
                     this.websocket.sentToServer(new UnidentifiedClass1812());
                  }
               } catch (Exception var2x) {
                  var2x.printStackTrace();
               }

               Thread.sleep(8426915599694853428L & 1663104377L);
            }
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      }).start();
      if (System.getProperty("os.name").toLowerCase().contains("win") && this.getGlobalSettings().field_0097.method_08908()) {
         this.field_0029.field_0002.method_25825();
         this.method_19739();
      }

      this.field_0029.field_0002.method_25825();
      this.field_0004.method_21935(new UnidentifiedClass1944());
   }

   public CBFontRenderer method_19792() {
      return this.field_0040;
   }

   public static CheatBreaker getInstance() {
      return instance;
   }

   public String method_19758() {
      switch (CheatBreaker$1.field_0003[this.statusEnum.ordinal()]) {
         case 1:
            return "Away";
         case 2:
            return "Busy";
         case 3:
            return "Offline";
         default:
            return "Online";
      }
   }

   public String method_19804() {
      return "lunarclient:pm";
   }

   public void method_19778(String var1) {
      if (this.getGlobalSettings().field_0071.method_08908()) {
         this.method_19780(
            this.field_0057.method_10921(this.field_0028)[3] != null ? this.field_0057.method_10921(this.field_0028)[3] : var1,
            this.field_0057.method_10921(this.field_0028)
         );
      } else {
         this.method_19780(var1, new String[]{"Minecraft 1.8.9", "cb", "Minecraft 1.8.9"});
      }
   }

   public CBFontRenderer method_19795() {
      return this.field_0037;
   }

   public ResourceLocation method_19810(String var1) {
      ResourceLocation var2 = this.playerSkins.getOrDefault(var1, new ResourceLocation("client/heads/" + var1 + ".png"));
      if (!this.playerSkins.containsKey(var1)) {
         ThreadDownloadImageData var3 = new ThreadDownloadImageData(
            null, "https://minotar.net/helm/" + var1 + "/32.png", new ResourceLocation("client/defaults/steve.png"), null
         );
         this.field_0029.renderEngine.loadTexture(var2, var3);
         this.playerSkins.put(var1, var2);
      }

      return var2;
   }

   public UnidentifiedClass3556 method_19817() {
      return this.field_0004;
   }

   public CBFontRenderer getRobotoRegular13px() {
      return this.robotoRegular13px;
   }

   public CBFontRenderer method_19740() {
      return this.field_0068;
   }

   public void method_19779(String var1, String var2, int var3) {
      if (this.websocket != null) {
         try {
            this.field_0028 = var1;
            if (this.getGlobalSettings().field_0065.method_08908()) {
               this.method_19778(this.field_0029.getSession().getUsername());
            } else {
               this.method_19778(null);
            }
         } catch (UnsatisfiedLinkError | Exception var5) {
         }

         boolean var4 = var1.equals(var2 + ":" + var3);
         if (!var4) {
            this.websocket.sendUpdateServer(var1);
            this.field_0028 = var1.isEmpty() ? "In-Menus" : var1;
         } else {
            this.websocket.sendUpdateServer("server");
            this.field_0028 = "server";
         }
      }
   }

   public List<SessionServer> method_19770() {
      return this.field_0038;
   }

   public Logger method_19789() {
      return this.field_0034;
   }

   public UnidentifiedClass1468 method_19797() {
      return this.field_0015;
   }

   public CBFontRenderer getPlayRegular14px() {
      return this.playRegular14px;
   }

   public ResourceLocation method_19785() {
      return this.field_0042;
   }

   public void setAcceptingFriendRequests(boolean var1) {
      this.field_0043 = var1;
   }

   public IPCClient method_19768() {
      return this.field_0010;
   }

   public ResourceLocation method_19814() {
      return this.field_0005;
   }

   public String method_19766() {
      return "Lunar-Client";
   }

   public boolean method_19793() {
      return this.field_0051;
   }

   public void method_19739() {
      try {
         (this.field_0010 = new IPCClient(925912458355440159L & 925912459436519455L)).method_13319(new UnidentifiedClass4579(this));
         this.field_0010.method_13323();
         this.field_0034.info(this.field_0016 + "Connected to Discord IPC");
      } catch (Exception var2) {
         this.field_0034.error(this.field_0016 + "Failed to connect to Discord IPC");
      }
   }

   public Minecraft method_19774() {
      return this.field_0029;
   }

   public boolean method_19806() {
      return false;
   }

   public String method_19798() {
      return this.field_0041;
   }

   public Map<String, ResourceLocation> getPlayerSkins() {
      return this.playerSkins;
   }

   public CBFontRenderer method_19753() {
      return this.field_0022;
   }

   public CBDashManager getRadioManager() {
      return this.radioManager;
   }
}
