package net.minecraft.client.stream;

import com.google.common.collect.Lists;
import io.netty.channel.FixedRecvByteBufAllocator;
import java.util.Arrays;
import java.util.List;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ThreadSafeBoundList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedInterface4921;
import tv.twitch.AuthToken;
import tv.twitch.Core;
import tv.twitch.ErrorCode;
import tv.twitch.MessageLevel;
import tv.twitch.StandardCoreAPI;
import tv.twitch.broadcast.ArchivingState;
import tv.twitch.broadcast.AudioDeviceType;
import tv.twitch.broadcast.AudioParams;
import tv.twitch.broadcast.ChannelInfo;
import tv.twitch.broadcast.DesktopStreamAPI;
import tv.twitch.broadcast.EncodingCpuUsage;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.IStatCallbacks;
import tv.twitch.broadcast.IStreamCallbacks;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.broadcast.PixelFormat;
import tv.twitch.broadcast.StartFlags;
import tv.twitch.broadcast.Stream;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.StreamInfoForSetting;
import tv.twitch.broadcast.UserInfo;
import tv.twitch.broadcast.VideoParams;

public class BroadcastController {
   public UserInfo userInfo;
   public static ThreadSafeBoundList<String> field_152862_C = new ThreadSafeBoundList<>(String.class, 50);
   public ArchivingState archivingState;
   public IngestServerTester ingestServTester;
   public AudioParams audioParamaters;
   public AuthToken authenticationToken;
   public boolean field_0031;
   public List<FrameBuffer> field_152874_j;
   public String lastError;
   public UnidentifiedInterface4921 broadcastListener;
   public String field_0005;
   public String field_0018;
   public long field_152890_z;
   public IStreamCallbacks streamCallback;
   public ChannelInfo channelInfo;
   public static Logger logger = LogManager.getLogger();
   public IngestServer ingestServ;
   public Core streamCore;
   public String field_152880_p;
   public Stream theStream;
   public StreamInfo streamInfo;
   public boolean field_0001;
   public String field_152868_d;
   public BroadcastController$BroadcastState broadcastState;
   public int field_0026;
   public List<FrameBuffer> field_152875_k;
   public int field_0024 = 30;
   public FixedRecvByteBufAllocator field_0015;
   public ErrorCode errorCode;
   public IStatCallbacks field_177949_C;
   public boolean field_152877_m;
   public VideoParams videoParamaters;
   public boolean field_152878_n;
   public IngestList ingestList;

   public boolean func_152836_a(VideoParams var1) {
      if (var1 != null && this.isReadyToBroadcast()) {
         this.videoParamaters = var1.clone();
         this.audioParamaters = new AudioParams();
         this.audioParamaters.audioEnabled = this.field_0001 && this.func_152848_y();
         this.audioParamaters.enableMicCapture = this.audioParamaters.audioEnabled;
         this.audioParamaters.enablePlaybackCapture = this.audioParamaters.audioEnabled;
         this.audioParamaters.enablePassthroughAudio = false;
         if (!this.func_152823_L()) {
            this.videoParamaters = null;
            this.audioParamaters = null;
            return false;
         } else {
            ErrorCode var2 = this.theStream.start(var1, this.audioParamaters, this.ingestServ, StartFlags.None, true);
            if (ErrorCode.failed(var2)) {
               this.func_152831_M();
               String var3 = ErrorCode.getString(var2);
               this.logError(String.format("Error while starting to broadcast: %s", var3));
               this.videoParamaters = null;
               this.audioParamaters = null;
               return false;
            } else {
               this.func_152827_a(BroadcastController$BroadcastState.Starting);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   public boolean func_152828_a(String var1, String var2, String var3) {
      if (!this.field_152877_m) {
         return false;
      } else {
         if (var1 == null || var1.equals("")) {
            var1 = this.field_152880_p;
         }

         if (var2 == null) {
            var2 = "";
         }

         if (var3 == null) {
            var3 = "";
         }

         StreamInfoForSetting var4 = new StreamInfoForSetting();
         var4.streamTitle = var3;
         var4.gameName = var2;
         ErrorCode var5 = this.theStream.setStreamInfo(this.authenticationToken, var1, var4);
         this.func_152853_a(var5);
         return ErrorCode.succeeded(var5);
      }
   }

   public boolean requestCommercial() {
      if (!this.isBroadcasting()) {
         return false;
      } else {
         ErrorCode var1 = this.theStream.runCommercial(this.authenticationToken);
         this.func_152853_a(var1);
         return ErrorCode.succeeded(var1);
      }
   }

   public boolean method_30192() {
      if (!this.field_0031) {
         return true;
      } else if (this.isIngestTesting()) {
         return false;
      } else {
         this.field_152878_n = true;
         this.func_152845_C();
         this.theStream.setStreamCallbacks((IStreamCallbacks)null);
         this.theStream.setStatCallbacks((IStatCallbacks)null);
         ErrorCode var1 = this.streamCore.shutdown();
         this.func_152853_a(var1);
         this.field_0031 = false;
         this.field_152878_n = false;
         this.func_152827_a(BroadcastController$BroadcastState.Uninitialized);
         return true;
      }
   }

   public boolean func_152847_F() {
      if (!this.isBroadcasting()) {
         return false;
      } else {
         ErrorCode var1 = this.theStream.pauseVideo();
         if (ErrorCode.failed(var1)) {
            this.stopBroadcasting();
            String var2 = ErrorCode.getString(var1);
            this.logError(String.format("Error pausing stream: %s\n", var2));
         } else {
            this.func_152827_a(BroadcastController$BroadcastState.Paused);
         }

         return ErrorCode.succeeded(var1);
      }
   }

   public void statCallback() {
      if (this.broadcastState != BroadcastController$BroadcastState.Uninitialized) {
         if (this.ingestServTester != null) {
            this.ingestServTester.func_153039_l();
         }

         for (; this.ingestServTester != null; this.func_152821_H()) {
            try {
               Thread.sleep(805369L & -888629134498791220L);
            } catch (Exception var2) {
               this.logError(var2.toString());
            }
         }

         this.method_30192();
      }
   }

   public PixelFormat getPixelFormat() {
      return PixelFormat.TTV_PF_RGBA;
   }

   public long func_177946_b(String var1, long var2, String var4, String var5) {
      long var6 = this.theStream.sendStartSpanMetaData(this.authenticationToken, var1, var2, var4, var5);
      if (var6 == (-1L & -1L)) {
         this.logError(String.format("Error in SendStartSpanMetaData\n"));
      }

      return var6;
   }

   public void logWarning(String var1) {
      field_152862_C.func_152757_a("<Warning> " + var1);
      logger.warn(TwitchStream.STREAM_MARKER, "[Broadcast controller] {}", new Object[]{var1});
   }

   public void setBroadcastListener(UnidentifiedInterface4921 var1) {
      this.broadcastListener = var1;
   }

   public VideoParams func_152834_a(int var1, int var2, float var3, float var4) {
      int[] var5 = this.theStream.getMaxResolution(var1, var2, var3, var4);
      VideoParams var6 = new VideoParams();
      var6.maxKbps = var1;
      var6.encodingCpuUsage = EncodingCpuUsage.TTV_ECU_HIGH;
      var6.pixelFormat = this.getPixelFormat();
      var6.targetFps = var2;
      var6.outputWidth = var5[0];
      var6.outputHeight = var5[1];
      var6.disableAdaptiveBitrate = false;
      var6.verticalFlip = false;
      return var6;
   }

   public boolean func_152845_C() {
      if (this.isIngestTesting()) {
         return false;
      } else {
         if (this.isBroadcasting()) {
            this.theStream.stop(false);
         }

         this.field_152880_p = "";
         this.authenticationToken = new AuthToken();
         if (!this.field_152877_m) {
            return false;
         } else {
            this.field_152877_m = false;
            if (!this.field_152878_n) {
               try {
                  if (this.broadcastListener != null) {
                     this.broadcastListener.func_152895_a();
                  }
               } catch (Exception var2) {
                  this.logError(var2.toString());
               }
            }

            this.func_152827_a(BroadcastController$BroadcastState.Initialized);
            return true;
         }
      }
   }

   public boolean func_152849_q() {
      return this.field_152877_m;
   }

   public ErrorCode getErrorCode() {
      return this.errorCode;
   }

   public void setIngestServer(IngestServer var1) {
      this.ingestServ = var1;
   }

   public boolean func_152818_a(String var1, AuthToken var2) {
      if (this.isIngestTesting()) {
         return false;
      } else {
         this.func_152845_C();
         if (var1 == null || var1.isEmpty()) {
            this.logError("Username must be valid");
            return false;
         } else if (var2 != null && var2.data != null && !var2.data.isEmpty()) {
            this.field_152880_p = var1;
            this.authenticationToken = var2;
            if (this.func_152858_b()) {
               this.func_152827_a(BroadcastController$BroadcastState.Authenticated);
            }

            return true;
         } else {
            this.logError("Auth token must be valid");
            return false;
         }
      }
   }

   public void func_152821_H() {
      if (this.theStream != null && this.field_0031) {
         ErrorCode var1 = this.theStream.pollTasks();
         this.func_152853_a(var1);
         if (this.isIngestTesting()) {
            this.ingestServTester.method_10735();
            if (this.ingestServTester.func_153032_e()) {
               this.ingestServTester = null;
               this.func_152827_a(BroadcastController$BroadcastState.ReadyToBroadcast);
            }
         }

         switch (BroadcastController$3.field_0001[this.broadcastState.ordinal()]) {
            case 1:
               this.func_152827_a(BroadcastController$BroadcastState.LoggingIn);
               var1 = this.theStream.login(this.authenticationToken);
               if (ErrorCode.failed(var1)) {
                  String var9 = ErrorCode.getString(var1);
                  this.logError(String.format("Error in TTV_Login: %s\n", var9));
               }
               break;
            case 2:
               this.func_152827_a(BroadcastController$BroadcastState.FindingIngestServer);
               var1 = this.theStream.getIngestServers(this.authenticationToken);
               if (ErrorCode.failed(var1)) {
                  this.func_152827_a(BroadcastController$BroadcastState.LoggedIn);
                  String var8 = ErrorCode.getString(var1);
                  this.logError(String.format("Error in TTV_GetIngestServers: %s\n", var8));
               }
               break;
            case 3:
               this.func_152827_a(BroadcastController$BroadcastState.ReadyToBroadcast);
               var1 = this.theStream.getUserInfo(this.authenticationToken);
               if (ErrorCode.failed(var1)) {
                  String var2 = ErrorCode.getString(var1);
                  this.logError(String.format("Error in TTV_GetUserInfo: %s\n", var2));
               }

               this.method_30187();
               var1 = this.theStream.getArchivingState(this.authenticationToken);
               if (ErrorCode.failed(var1)) {
                  String var7 = ErrorCode.getString(var1);
                  this.logError(String.format("Error in TTV_GetArchivingState: %s\n", var7));
               }
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            default:
               break;
            case 11:
            case 12:
               this.method_30187();
         }
      }
   }

   public boolean isIngestTesting() {
      return this.broadcastState == BroadcastController$BroadcastState.IngestTesting;
   }

   public BroadcastController() {
      this.field_0026 = 3;
      this.lastError = null;
      this.broadcastListener = null;
      this.field_152868_d = "";
      this.field_0005 = "";
      this.field_0018 = "";
      this.field_0001 = true;
      this.streamCore = null;
      this.theStream = null;
      this.field_152874_j = Lists.newArrayList();
      this.field_152875_k = Lists.newArrayList();
      this.field_0031 = false;
      this.field_152877_m = false;
      this.field_152878_n = false;
      this.broadcastState = BroadcastController$BroadcastState.Uninitialized;
      this.field_152880_p = null;
      this.videoParamaters = null;
      this.audioParamaters = null;
      this.ingestList = new IngestList(new IngestServer[0]);
      this.ingestServ = null;
      this.authenticationToken = new AuthToken();
      this.channelInfo = new ChannelInfo();
      this.userInfo = new UserInfo();
      this.streamInfo = new StreamInfo();
      this.archivingState = new ArchivingState();
      this.field_152890_z = -7310902239348816760L & 7310902239049423458L;
      this.ingestServTester = null;
      this.streamCallback = new BroadcastController$1(this);
      this.field_177949_C = new BroadcastController$2(this);
      this.streamCore = Core.getInstance();
      if (Core.getInstance() == null) {
         this.streamCore = new Core(new StandardCoreAPI());
      }

      this.theStream = new Stream(new DesktopStreamAPI());
   }

   public long getStreamTime() {
      return this.theStream.getStreamTime();
   }

   public void captureFramebuffer(FrameBuffer var1) {
      try {
         this.theStream.captureFrameBuffer_ReadPixels(var1);
      } catch (Throwable var5) {
         CrashReport var3 = CrashReport.makeCrashReport(var5, "Trying to submit a frame to Twitch");
         CrashReportCategory var4 = var3.makeCategory("Broadcast State");
         var4.addCrashSection("Last reported errors", Arrays.toString(field_152862_C.func_152756_c()));
         var4.addCrashSection("Buffer", var1);
         var4.addCrashSection("Free buffer count", this.field_152875_k.size());
         var4.addCrashSection("Capture buffer count", this.field_152874_j.size());
         throw new ReportedException(var3);
      }
   }

   public boolean stopBroadcasting() {
      if (!this.isBroadcasting()) {
         return false;
      } else {
         ErrorCode var1 = this.theStream.stop(true);
         if (ErrorCode.failed(var1)) {
            String var2 = ErrorCode.getString(var1);
            this.logError(String.format("Error while stopping the broadcast: %s", var2));
            return false;
         } else {
            this.func_152827_a(BroadcastController$BroadcastState.Stopping);
            return ErrorCode.succeeded(var1);
         }
      }
   }

   public ChannelInfo getChannelInfo() {
      return this.channelInfo;
   }

   public boolean func_152853_a(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         this.logError(ErrorCode.getString(var1));
         return false;
      } else {
         return true;
      }
   }

   public void setPlaybackDeviceVolume(float var1) {
      this.theStream.setVolume(AudioDeviceType.TTV_PLAYBACK_DEVICE, var1);
   }

   public boolean func_152854_G() {
      if (!this.isBroadcastPaused()) {
         return false;
      } else {
         this.func_152827_a(BroadcastController$BroadcastState.Broadcasting);
         return true;
      }
   }

   public void method_30187() {
      long var1 = System.nanoTime();
      long var3 = (var1 - this.field_152890_z) / (1000001186L & 2078001740L);
      if (var3 >= (-2522765284461246274L & 84411230L)) {
         this.field_152890_z = var1;
         ErrorCode var5 = this.theStream.getStreamInfo(this.authenticationToken, this.field_152880_p);
         if (ErrorCode.failed(var5)) {
            String var6 = ErrorCode.getString(var5);
            this.logError(String.format("Error in TTV_GetStreamInfo: %s", var6));
         }
      }
   }

   public void setRecordingDeviceVolume(float var1) {
      this.theStream.setVolume(AudioDeviceType.TTV_RECORDER_DEVICE, var1);
   }

   public boolean isReadyToBroadcast() {
      return this.broadcastState == BroadcastController$BroadcastState.ReadyToBroadcast;
   }

   public boolean func_152817_A() {
      if (this.field_0031) {
         return false;
      } else {
         this.theStream.setStreamCallbacks(this.streamCallback);
         ErrorCode var1 = this.streamCore.initialize(this.field_152868_d, System.getProperty("java.library.path"));
         if (!this.func_152853_a(var1)) {
            this.theStream.setStreamCallbacks((IStreamCallbacks)null);
            this.errorCode = var1;
            return false;
         } else {
            var1 = this.streamCore.setTraceLevel(MessageLevel.TTV_ML_ERROR);
            if (!this.func_152853_a(var1)) {
               this.theStream.setStreamCallbacks((IStreamCallbacks)null);
               this.streamCore.shutdown();
               this.errorCode = var1;
               return false;
            } else if (ErrorCode.succeeded(var1)) {
               this.field_0031 = true;
               this.func_152827_a(BroadcastController$BroadcastState.Initialized);
               return true;
            } else {
               this.errorCode = var1;
               this.streamCore.shutdown();
               return false;
            }
         }
      }
   }

   public void func_152831_M() {
      for (int var1 = 0; var1 < this.field_152874_j.size(); var1++) {
         FrameBuffer var2 = this.field_152874_j.get(var1);
         var2.free();
      }

      this.field_152875_k.clear();
      this.field_152874_j.clear();
   }

   public IngestServer getIngestServer() {
      return this.ingestServ;
   }

   public void logError(String var1) {
      this.lastError = var1;
      field_152862_C.func_152757_a("<Error> " + var1);
      logger.error(TwitchStream.STREAM_MARKER, "[Broadcast controller] {}", new Object[]{var1});
   }

   public IngestList getIngestList() {
      return this.ingestList;
   }

   public void func_152827_a(BroadcastController$BroadcastState var1) {
      if (var1 != this.broadcastState) {
         this.broadcastState = var1;

         try {
            if (this.broadcastListener != null) {
               this.broadcastListener.func_152891_a(var1);
            }
         } catch (Exception var3) {
            this.logError(var3.toString());
         }
      }
   }

   public FrameBuffer func_152822_N() {
      if (this.field_152875_k.size() == 0) {
         this.logError(String.format("Out of free buffers, this should never happen"));
         return null;
      } else {
         FrameBuffer var1 = this.field_152875_k.get(this.field_152875_k.size() - 1);
         this.field_152875_k.remove(this.field_152875_k.size() - 1);
         return var1;
      }
   }

   public void func_152842_a(String var1) {
      this.field_152868_d = var1;
   }

   public ErrorCode submitStreamFrame(FrameBuffer var1) {
      if (this.isBroadcastPaused()) {
         this.func_152854_G();
      } else if (!this.isBroadcasting()) {
         return ErrorCode.TTV_EC_STREAM_NOT_STARTED;
      }

      ErrorCode var2 = this.theStream.submitVideoFrame(var1);
      if (var2 != ErrorCode.TTV_EC_SUCCESS) {
         String var3 = ErrorCode.getString(var2);
         if (ErrorCode.succeeded(var2)) {
            this.logWarning(String.format("Warning in SubmitTexturePointer: %s\n", var3));
         } else {
            this.logError(String.format("Error in SubmitTexturePointer: %s\n", var3));
            this.stopBroadcasting();
         }

         if (this.broadcastListener != null) {
            this.broadcastListener.func_152893_b(var2);
         }
      }

      return var2;
   }

   public IngestServerTester isReady() {
      return this.ingestServTester;
   }

   public boolean func_177947_a(String var1, long var2, long var4, String var6, String var7) {
      if (var4 == (-1L & -1L)) {
         this.logError(String.format("Invalid sequence id: %d\n", var4));
         return false;
      } else {
         ErrorCode var8 = this.theStream.sendEndSpanMetaData(this.authenticationToken, var1, var2, var4, var6, var7);
         if (ErrorCode.failed(var8)) {
            String var9 = ErrorCode.getString(var8);
            this.logError(String.format("Error in SendStopSpanMetaData: %s\n", var9));
            return false;
         } else {
            return true;
         }
      }
   }

   public StreamInfo getStreamInfo() {
      return this.streamInfo;
   }

   public boolean func_152848_y() {
      return true;
   }

   public IngestServerTester func_152838_J() {
      if (!this.isReadyToBroadcast() || this.ingestList == null) {
         return null;
      } else if (this.isIngestTesting()) {
         return null;
      } else {
         this.ingestServTester = new IngestServerTester(this.theStream, this.ingestList);
         this.ingestServTester.method_10734();
         this.func_152827_a(BroadcastController$BroadcastState.IngestTesting);
         return this.ingestServTester;
      }
   }

   public boolean func_152858_b() {
      return this.field_0031;
   }

   public boolean isBroadcastPaused() {
      return this.broadcastState == BroadcastController$BroadcastState.Paused;
   }

   public boolean func_152840_a(String var1, long var2, String var4, String var5) {
      ErrorCode var6 = this.theStream.sendActionMetaData(this.authenticationToken, var1, var2, var4, var5);
      if (ErrorCode.failed(var6)) {
         String var7 = ErrorCode.getString(var6);
         this.logError(String.format("Error while sending meta data: %s\n", var7));
         return false;
      } else {
         return true;
      }
   }

   public boolean func_152823_L() {
      for (int var1 = 0; var1 < 3; var1++) {
         FrameBuffer var2 = this.theStream.allocateFrameBuffer(this.videoParamaters.outputWidth * this.videoParamaters.outputHeight * 4);
         if (!var2.getIsValid()) {
            this.logError(String.format("Error while allocating frame buffer"));
            return false;
         }

         this.field_152874_j.add(var2);
         this.field_152875_k.add(var2);
      }

      return true;
   }

   public boolean isBroadcasting() {
      return this.broadcastState == BroadcastController$BroadcastState.Broadcasting || this.broadcastState == BroadcastController$BroadcastState.Paused;
   }
}
