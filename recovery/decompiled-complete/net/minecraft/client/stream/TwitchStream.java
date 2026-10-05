package net.minecraft.client.stream;

import com.cheatbreaker.client.module.AbstractModule;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.mojang.authlib.properties.Property;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.event.HoverEvent;
import net.minecraft.event.HoverEvent$Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Util;
import net.minecraft.util.Util$EnumOS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0753;
import recovered.unidentified.UnidentifiedInterface4921;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.VideoParams;
import tv.twitch.chat.ChatRawMessage;
import tv.twitch.chat.ChatTokenizedMessage;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class TwitchStream implements BroadcastController$BroadcastListener, IStream, IngestServerTester$IngestTestListener, UnidentifiedInterface4921 {
   public int targetFPS;
   public IChatComponent field_0017 = new ChatComponentText("Twitch");
   public boolean field_152960_l;
   public IStream$AuthFailureReason authFailureReason;
   public boolean field_152963_o;
   public boolean field_152962_n;
   public AbstractModule field_0018;
   public static boolean field_152965_q;
   public Minecraft mc;
   public static Marker STREAM_MARKER = MarkerManager.getMarker("STREAM");
   public boolean loggedIn;
   public UnidentifiedClass0753 field_0009;
   public boolean field_152957_i;
   public static Logger LOGGER = LogManager.getLogger();
   public long field_152959_k;
   public String field_176029_e;
   public ChatController chatController;
   public BroadcastController broadcastController;
   public Map<String, ChatUserInfo> field_152955_g = Maps.newHashMap();
   public Framebuffer framebuffer;

   @Override
   public void func_152896_a(IngestList var1) {
   }

   @Override
   public void func_152901_c() {
      LOGGER.info(STREAM_MARKER, "Broadcast to Twitch has stopped");
   }

   @Override
   public boolean func_152929_G() {
      boolean var1 = this.mc.gameSettings.streamMicToggleBehavior == 1;
      return this.field_152962_n || this.mc.gameSettings.streamMicVolume <= 0.0F || var1 != this.field_152963_o;
   }

   @Override
   public boolean func_152908_z() {
      return this.broadcastController.isIngestTesting();
   }

   @Override
   public void func_180607_b(String var1) {
      LOGGER.debug(STREAM_MARKER, "Chat disconnected");
      this.field_152955_g.clear();
   }

   @Override
   public String func_152921_C() {
      return this.field_176029_e;
   }

   @Override
   public void func_176019_a(String var1, String var2) {
   }

   @Override
   public boolean func_152913_F() {
      return this.loggedIn;
   }

   @Override
   public void func_152899_b() {
      this.updateStreamVolume();
      LOGGER.info(STREAM_MARKER, "Broadcast to Twitch has started");
   }

   @Override
   public boolean method_02394() {
      return this.broadcastController.isReadyToBroadcast();
   }

   @Override
   public void method_07678() {
   }

   @Override
   public void func_176022_e(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         LOGGER.error(STREAM_MARKER, "Chat failed to shutdown");
      }
   }

   @Override
   public void func_176023_d(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         LOGGER.error(STREAM_MARKER, "Chat failed to initialize");
      }
   }

   @Override
   public ErrorCode func_152912_E() {
      return !field_152965_q ? ErrorCode.TTV_EC_OS_TOO_OLD : this.broadcastController.getErrorCode();
   }

   @Override
   public IStream$AuthFailureReason func_152918_H() {
      return this.authFailureReason;
   }

   public static int formatStreamFps(float var0) {
      return MathHelper.floor_float(10.0F + var0 * 50.0F);
   }

   @Override
   public void func_180606_a(String var1) {
      LOGGER.debug(STREAM_MARKER, "Chat connected");
   }

   @Override
   public void func_152897_a(ErrorCode var1) {
      if (ErrorCode.succeeded(var1)) {
         LOGGER.debug(STREAM_MARKER, "Login attempt successful");
         this.loggedIn = true;
      } else {
         LOGGER.warn(STREAM_MARKER, "Login attempt unsuccessful: {} (error code {})", new Object[]{ErrorCode.getString(var1), var1.getValue()});
         this.loggedIn = false;
      }
   }

   @Override
   public void method_02381() {
      if (this.broadcastController.isBroadcasting() && !this.broadcastController.isBroadcastPaused()) {
         long var1 = System.nanoTime();
         long var3 = 1000000000 / this.targetFPS;
         long var5 = var1 - this.field_152959_k;
         boolean var7 = var5 >= var3;
         if (var7) {
            FrameBuffer var8 = this.broadcastController.func_152822_N();
            Framebuffer var9 = this.mc.getFramebuffer();
            this.framebuffer.bindFramebuffer(true);
            GlStateManager.matrixMode(5889);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.ortho(0.0, this.framebuffer.framebufferWidth, this.framebuffer.framebufferHeight, 0.0, 1000.0, 3000.0);
            GlStateManager.matrixMode(5888);
            GlStateManager.pushMatrix();
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, 0.0F, -2000.0F);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.viewport(0, 0, this.framebuffer.framebufferWidth, this.framebuffer.framebufferHeight);
            GlStateManager.enableTexture2D();
            GlStateManager.disableAlpha();
            GlStateManager.disableBlend();
            float var10 = this.framebuffer.framebufferWidth;
            float var11 = this.framebuffer.framebufferHeight;
            float var12 = (float)var9.framebufferWidth / var9.framebufferTextureWidth;
            float var13 = (float)var9.framebufferHeight / var9.framebufferTextureHeight;
            var9.bindFramebufferTexture();
            GL11.glTexParameterf(3553, 10241, 9729.0F);
            GL11.glTexParameterf(3553, 10240, 9729.0F);
            Tessellator var14 = Tessellator.getInstance();
            WorldRenderer var15 = var14.getWorldRenderer();
            var15.begin(7, DefaultVertexFormats.POSITION_TEX);
            var15.pos(0.0, var11, 0.0).tex(0.0, var13).endVertex();
            var15.pos(var10, var11, 0.0).tex(var12, var13).endVertex();
            var15.pos(var10, 0.0, 0.0).tex(var12, 0.0).endVertex();
            var15.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).endVertex();
            var14.draw();
            var9.unbindFramebufferTexture();
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5889);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
            this.broadcastController.captureFramebuffer(var8);
            this.framebuffer.unbindFramebuffer();
            this.broadcastController.submitStreamFrame(var8);
            this.field_152959_k = var1;
         }
      }
   }

   @Override
   public boolean isBroadcasting() {
      return this.broadcastController.isBroadcasting();
   }

   @Override
   public void func_152909_x() {
      IngestServerTester var1 = this.broadcastController.func_152838_J();
      if (var1 != null) {
         var1.func_153042_a(this);
      }
   }

   @Override
   public void func_152917_b(String var1) {
      this.chatController.func_175986_a(this.field_176029_e, var1);
   }

   @Override
   public void requestCommercial() {
      if (this.broadcastController.requestCommercial()) {
         LOGGER.debug(STREAM_MARKER, "Requested commercial from Twitch");
      } else {
         LOGGER.warn(STREAM_MARKER, "Could not request commercial from Twitch");
      }
   }

   @Override
   public void method_07705(String var1) {
   }

   @Override
   public void func_152895_a() {
      LOGGER.info(STREAM_MARKER, "Logged out of twitch");
   }

   @Override
   public void func_176018_a(String var1, ChatUserInfo[] var2, ChatUserInfo[] var3, ChatUserInfo[] var4) {
      for (ChatUserInfo var8 : var3) {
         this.field_152955_g.remove(var8.displayName);
      }

      for (ChatUserInfo var15 : var4) {
         this.field_152955_g.put(var15.displayName, var15);
      }

      for (ChatUserInfo var16 : var2) {
         this.field_152955_g.put(var16.displayName, var16);
      }
   }

   @Override
   public void method_02395() {
      GameSettings var1 = this.mc.gameSettings;
      VideoParams var2 = this.broadcastController
         .func_152834_a(
            formatStreamKbps(var1.streamKbps),
            formatStreamFps(var1.streamFps),
            formatStreamBps(var1.streamBytesPerPixel),
            (float)this.mc.displayWidth / this.mc.displayHeight
         );
      switch (var1.field_0164) {
         case 0:
            var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_LOW;
            break;
         case 1:
            var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_MEDIUM;
            break;
         case 2:
            var2.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
      }

      if (this.framebuffer == null) {
         this.framebuffer = new Framebuffer(var2.outputWidth, var2.outputHeight, false);
      } else {
         this.framebuffer.createBindFramebuffer(var2.outputWidth, var2.outputHeight);
      }

      if (var1.streamPreferredServer != null && var1.streamPreferredServer.length() > 0) {
         for (IngestServer var6 : this.func_152925_v()) {
            if (var6.serverUrl.equals(var1.streamPreferredServer)) {
               this.broadcastController.setIngestServer(var6);
               break;
            }
         }
      }

      this.targetFPS = var2.targetFps;
      this.field_152957_i = var1.streamSendMetadata;
      this.broadcastController.func_152836_a(var2);
      LOGGER.info(
         STREAM_MARKER,
         "Streaming at {}/{} at {} kbps to {}",
         new Object[]{var2.outputWidth, var2.outputHeight, var2.maxKbps, this.broadcastController.getIngestServer().serverUrl}
      );
      this.broadcastController.func_152828_a((String)null, "Minecraft", (String)null);
   }

   @Override
   public int func_152920_A() {
      return this.isBroadcasting() ? this.broadcastController.getStreamInfo().viewers : 0;
   }

   @Override
   public void updateStreamVolume() {
      if (this.isBroadcasting()) {
         float var1 = this.mc.gameSettings.streamGameVolume;
         boolean var2 = this.field_152962_n || var1 <= 0.0F;
         this.broadcastController.setPlaybackDeviceVolume(var2 ? 0.0F : var1);
         this.broadcastController.setRecordingDeviceVolume(this.func_152929_G() ? 0.0F : this.mc.gameSettings.streamMicVolume);
      }
   }

   public void func_152942_I() {
      ChatController$ChatState var1 = this.chatController.func_153000_j();
      String var2 = this.broadcastController.getChannelInfo().name;
      this.field_176029_e = var2;
      if (var1 != ChatController$ChatState.Initialized) {
         LOGGER.warn("Invalid twitch chat state {}", new Object[]{var1});
      } else if (this.chatController.func_175989_e(this.field_176029_e) == ChatController$EnumChannelState.Disconnected) {
         this.chatController.func_152986_d(var2);
      } else {
         LOGGER.warn("Invalid twitch chat state {}", new Object[]{var1});
      }
   }

   @Override
   public boolean func_152928_D() {
      return field_152965_q && this.broadcastController.func_152858_b();
   }

   @Override
   public void unpause() {
      this.broadcastController.func_152854_G();
      this.field_152962_n = false;
      this.updateStreamVolume();
   }

   @Override
   public void func_176017_a(ChatController$ChatState var1) {
   }

   @Override
   public void func_152935_j() {
      int var1 = this.mc.gameSettings.streamChatEnabled;
      boolean var2 = this.field_176029_e != null && this.chatController.func_175990_d(this.field_176029_e);
      boolean var3 = this.chatController.func_153000_j() == ChatController$ChatState.Initialized
         && (this.field_176029_e == null || this.chatController.func_175989_e(this.field_176029_e) == ChatController$EnumChannelState.Disconnected);
      if (var1 == 2) {
         if (var2) {
            LOGGER.debug(STREAM_MARKER, "Disconnecting from twitch chat per user options");
            this.chatController.func_175991_l(this.field_176029_e);
         }
      } else if (var1 == 1) {
         if (var3 && this.broadcastController.func_152849_q()) {
            LOGGER.debug(STREAM_MARKER, "Connecting to twitch chat per user options");
            this.func_152942_I();
         }
      } else if (var1 == 0) {
         if (var2 && !this.isBroadcasting()) {
            LOGGER.debug(STREAM_MARKER, "Disconnecting from twitch chat as user is no longer streaming");
            this.chatController.func_175991_l(this.field_176029_e);
         } else if (var3 && this.isBroadcasting()) {
            LOGGER.debug(STREAM_MARKER, "Connecting to twitch chat as user is streaming");
            this.func_152942_I();
         }
      }

      this.broadcastController.func_152821_H();
      this.chatController.func_152997_n();
   }

   static {
      try {
         if (Util.getOSType() == Util$EnumOS.WINDOWS) {
            System.loadLibrary("avutil-ttv-51");
            System.loadLibrary("swresample-ttv-0");
            System.loadLibrary("libmp3lame-ttv");
            if (System.getProperty("os.arch").contains("64")) {
               System.loadLibrary("libmfxsw64");
            } else {
               System.loadLibrary("libmfxsw32");
            }
         }

         field_152965_q = true;
      } catch (Throwable var1) {
         field_152965_q = false;
      }
   }

   public static int formatStreamKbps(float var0) {
      return MathHelper.floor_float(230.0F + var0 * 3270.0F);
   }

   @Override
   public boolean isPaused() {
      return this.broadcastController.isBroadcastPaused();
   }

   @Override
   public ChatUserInfo func_152926_a(String var1) {
      return this.field_152955_g.get(var1);
   }

   public boolean func_176028_a(Set<ChatUserMode> var1, Set<ChatUserSubscription> var2, int var3) {
      return var1.contains(ChatUserMode.TTV_CHAT_USERMODE_BANNED)
         ? false
         : (
            var1.contains(ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR)
               ? true
               : (
                  var1.contains(ChatUserMode.TTV_CHAT_USERMODE_MODERATOR)
                     ? true
                     : (
                        var1.contains(ChatUserMode.TTV_CHAT_USERMODE_STAFF)
                           ? true
                           : (var3 == 0 ? true : (var3 == 1 ? var2.contains(ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER) : false))
                     )
               )
         );
   }

   @Override
   public void muteMicrophone(boolean var1) {
      this.field_152963_o = var1;
      this.updateStreamVolume();
   }

   public TwitchStream(Minecraft var1, Property var2) {
      this.targetFPS = 30;
      this.field_152959_k = 679772224L & 91889701L;
      this.field_152960_l = false;
      this.authFailureReason = IStream$AuthFailureReason.ERROR;
      this.mc = var1;
      this.broadcastController = new BroadcastController();
      this.chatController = new ChatController();
      this.broadcastController.setBroadcastListener(this);
      this.chatController.func_152990_a(this);
      this.broadcastController.func_152842_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.chatController.func_152984_a("nmt37qblda36pvonovdkbopzfzw3wlq");
      this.field_0017.getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE);
      if (var2 != null && !Strings.isNullOrEmpty(var2.getValue()) && OpenGlHelper.framebufferSupported) {
         TwitchStream$1 var3 = new TwitchStream$1(this, "Twitch authenticator", var2);
         var3.setDaemon(true);
         var3.start();
      }
   }

   @Override
   public void func_152893_b(ErrorCode var1) {
      LOGGER.warn(STREAM_MARKER, "Issue submitting frame: {} (Error code {})", new Object[]{ErrorCode.getString(var1), var1.getValue()});
      this.mc
         .ingameGUI
         .getChatGUI()
         .printChatMessageWithOptionalDeletion(new ChatComponentText("Issue streaming frame: " + var1 + " (" + ErrorCode.getString(var1) + ")"), 2);
   }

   @Override
   public IngestServerTester func_152932_y() {
      return this.broadcastController.isReady();
   }

   @Override
   public void func_152892_c(ErrorCode var1) {
      if (var1 == ErrorCode.TTV_EC_SOUNDFLOWER_NOT_INSTALLED) {
         ChatComponentTranslation var2 = new ChatComponentTranslation("stream.unavailable.soundflower.chat.link");
         var2.getChatStyle()
            .setChatClickEvent(
               new ClickEvent(
                  ClickEvent$Action.OPEN_URL,
                  "https://help.mojang.com/customer/portal/articles/1374877-configuring-soundflower-for-streaming-on-apple-computers"
               )
            );
         var2.getChatStyle().setUnderlined(true);
         ChatComponentTranslation var3 = new ChatComponentTranslation("stream.unavailable.soundflower.chat", var2);
         var3.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
         this.mc.ingameGUI.getChatGUI().printChatMessage(var3);
      } else {
         ChatComponentTranslation var4 = new ChatComponentTranslation("stream.unavailable.unknown.chat", ErrorCode.getString(var1));
         var4.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
         this.mc.ingameGUI.getChatGUI().printChatMessage(var4);
      }
   }

   @Override
   public IngestServer[] func_152925_v() {
      return this.broadcastController.getIngestList().getServers();
   }

   @Override
   public void func_152894_a(StreamInfo var1) {
      LOGGER.debug(STREAM_MARKER, "Stream info updated; {} viewers on stream ID {}", new Object[]{var1.viewers, var1.streamId});
   }

   @Override
   public void func_152911_a(Metadata var1, long var2) {
      if (this.isBroadcasting() && this.field_152957_i) {
         long var4 = this.broadcastController.getStreamTime();
         if (!this.broadcastController.func_152840_a(var1.func_152810_c(), var4 + var2, var1.func_152809_a(), var1.func_152806_b())) {
            LOGGER.warn(STREAM_MARKER, "Couldn't send stream metadata action at {}: {}", new Object[]{var4 + var2, var1});
         } else {
            LOGGER.debug(STREAM_MARKER, "Sent stream metadata action at {}: {}", new Object[]{var4 + var2, var1});
         }
      }
   }

   @Override
   public void func_152898_a(ErrorCode var1, GameInfo[] var2) {
   }

   public static float formatStreamBps(float var0) {
      return 0.1F + var0 * 0.1F;
   }

   @Override
   public void func_176025_a(String var1, ChatTokenizedMessage[] var2) {
   }

   @Override
   public boolean method_02383() {
      return this.broadcastController.func_152849_q();
   }

   @Override
   public void func_152907_a(IngestServerTester var1, IngestServerTester$IngestTestState var2) {
      LOGGER.debug(STREAM_MARKER, "Ingest test state changed to {}", new Object[]{var2});
      if (var2 == IngestServerTester$IngestTestState.Finished) {
         this.field_152960_l = true;
      }
   }

   @Override
   public void func_152900_a(ErrorCode var1, AuthToken var2) {
   }

   @Override
   public boolean func_152927_B() {
      return this.field_176029_e != null && this.field_176029_e.equals(this.broadcastController.getChannelInfo().name);
   }

   @Override
   public void pause() {
      this.broadcastController.func_152847_F();
      this.field_152962_n = true;
      this.updateStreamVolume();
   }

   @Override
   public void func_152891_a(BroadcastController$BroadcastState var1) {
      LOGGER.debug(STREAM_MARKER, "Broadcast state changed to {}", new Object[]{var1});
      if (var1 == BroadcastController$BroadcastState.Initialized) {
         this.broadcastController.func_152827_a(BroadcastController$BroadcastState.Authenticated);
      }
   }

   @Override
   public void method_07704() {
   }

   @Override
   public void func_176026_a(Metadata var1, long var2, long var4) {
      if (this.isBroadcasting() && this.field_152957_i) {
         long var6 = this.broadcastController.getStreamTime();
         String var8 = var1.func_152809_a();
         String var9 = var1.func_152806_b();
         long var10 = this.broadcastController.func_177946_b(var1.func_152810_c(), var6 + var2, var8, var9);
         if (var10 < (1744847298L & 1532136197716445745L)) {
            LOGGER.warn(STREAM_MARKER, "Could not send stream metadata sequence from {} to {}: {}", new Object[]{var6 + var2, var6 + var4, var1});
         } else if (this.broadcastController.func_177947_a(var1.func_152810_c(), var6 + var4, var10, var8, var9)) {
            LOGGER.debug(STREAM_MARKER, "Sent stream metadata sequence from {} to {}: {}", new Object[]{var6 + var2, var6 + var4, var1});
         } else {
            LOGGER.warn(STREAM_MARKER, "Half-sent stream metadata sequence from {} to {}: {}", new Object[]{var6 + var2, var6 + var4, var1});
         }
      }
   }

   @Override
   public void stopBroadcasting() {
      if (this.broadcastController.stopBroadcasting()) {
         LOGGER.info(STREAM_MARKER, "Stopped streaming to Twitch");
      } else {
         LOGGER.warn(STREAM_MARKER, "Could not stop streaming to Twitch");
      }
   }

   @Override
   public void func_180605_a(String var1, ChatRawMessage[] var2) {
      for (ChatRawMessage var6 : var2) {
         this.func_176027_a(var6.userName, var6);
         if (this.func_176028_a(var6.modes, var6.subscriptions, this.mc.gameSettings.field_0144)) {
            ChatComponentText var7 = new ChatComponentText(var6.userName);
            ChatComponentTranslation var8 = new ChatComponentTranslation(
               "chat.stream." + (var6.action ? "emote" : "text"), this.field_0017, var7, EnumChatFormatting.getTextWithoutFormattingCodes(var6.message)
            );
            if (var6.action) {
               var8.getChatStyle().setItalic(true);
            }

            ChatComponentText var9 = new ChatComponentText("");
            var9.appendSibling(new ChatComponentTranslation("stream.userinfo.chatTooltip"));

            for (IChatComponent var11 : GuiTwitchUserMode.func_152328_a(var6.modes, var6.subscriptions, (IStream)null)) {
               var9.appendText("\n");
               var9.appendSibling(var11);
            }

            var7.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent$Action.SHOW_TEXT, var9));
            var7.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent$Action.field_0012, var6.userName));
            this.mc.ingameGUI.getChatGUI().printChatMessage(var8);
         }
      }
   }

   @Override
   public void method_07710(String var1) {
   }

   @Override
   public void shutdownStream() {
      LOGGER.debug(STREAM_MARKER, "Shutdown streaming");
      this.broadcastController.statCallback();
      this.chatController.func_175988_p();
   }

   public void func_176027_a(String var1, ChatRawMessage var2) {
      ChatUserInfo var3 = this.field_152955_g.get(var1);
      if (var3 == null) {
         var3 = new ChatUserInfo();
         var3.displayName = var1;
         this.field_152955_g.put(var1, var3);
      }

      var3.subscriptions = var2.subscriptions;
      var3.modes = var2.modes;
      var3.nameColorARGB = var2.nameColorARGB;
   }
}
