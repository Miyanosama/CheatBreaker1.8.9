package net.minecraft.client.stream;

import net.minecraft.client.gui.GuiCustomizeWorldScreen;
import net.minecraft.client.multiplayer.ServerList;
import net.minecraft.server.management.BanEntry;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.ArchivingState;
import tv.twitch.broadcast.ChannelInfo;
import tv.twitch.broadcast.FrameBuffer;
import tv.twitch.broadcast.GameInfo;
import tv.twitch.broadcast.GameInfoList;
import tv.twitch.broadcast.IStreamCallbacks;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.UserInfo;

public class BroadcastController$1 implements IStreamCallbacks {
   public GuiCustomizeWorldScreen field_0001;
   public BanEntry field_0000;
   public ServerList field_0002;

   public void requestAuthTokenCallback(ErrorCode var1, AuthToken var2) {
      if (ErrorCode.succeeded(var1)) {
         this.field_177945_a.authenticationToken = var2;
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.Authenticated);
      } else {
         this.field_177945_a.authenticationToken.data = "";
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.Initialized);
         String var3 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("RequestAuthTokenDoneCallback got failure: %s", var3));
      }

      try {
         if (this.field_177945_a.broadcastListener != null) {
            this.field_177945_a.broadcastListener.func_152900_a(var1, var2);
         }
      } catch (Exception var4) {
         this.field_177945_a.logError(var4.toString());
      }
   }

   public void getGameNameListCallback(ErrorCode var1, GameInfoList var2) {
      if (ErrorCode.failed(var1)) {
         String var3 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("GameNameListCallback got failure: %s", var3));
      }

      try {
         if (this.field_177945_a.broadcastListener != null) {
            this.field_177945_a.broadcastListener.func_152898_a(var1, var2 == null ? new GameInfo[0] : var2.list);
         }
      } catch (Exception var4) {
         this.field_177945_a.logError(var4.toString());
      }
   }

   public void bufferUnlockCallback(long var1) {
      FrameBuffer var3 = FrameBuffer.lookupBuffer(var1);
      this.field_177945_a.field_152875_k.add(var3);
   }

   public void sendActionMetaDataCallback(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         String var2 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("sendActionMetaDataCallback got failure: %s", var2));
      }
   }

   public void setStreamInfoCallback(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         String var2 = ErrorCode.getString(var1);
         this.field_177945_a.logWarning(String.format("SetStreamInfoCallback got failure: %s", var2));
      }
   }

   public void getIngestServersCallback(ErrorCode var1, IngestList var2) {
      if (ErrorCode.succeeded(var1)) {
         this.field_177945_a.ingestList = var2;
         this.field_177945_a.ingestServ = this.field_177945_a.ingestList.getDefaultServer();
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.ReceivedIngestServers);

         try {
            if (this.field_177945_a.broadcastListener != null) {
               this.field_177945_a.broadcastListener.func_152896_a(var2);
            }
         } catch (Exception var4) {
            this.field_177945_a.logError(var4.toString());
         }
      } else {
         String var3 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("IngestListCallback got failure: %s", var3));
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.LoggingIn);
      }
   }

   public void getUserInfoCallback(ErrorCode var1, UserInfo var2) {
      this.field_177945_a.userInfo = var2;
      if (ErrorCode.failed(var1)) {
         String var3 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("UserInfoDoneCallback got failure: %s", var3));
      }
   }

   public void stopCallback(ErrorCode var1) {
      if (ErrorCode.succeeded(var1)) {
         this.field_177945_a.videoParamaters = null;
         this.field_177945_a.audioParamaters = null;
         this.field_177945_a.func_152831_M();

         try {
            if (this.field_177945_a.broadcastListener != null) {
               this.field_177945_a.broadcastListener.func_152901_c();
            }
         } catch (Exception var3) {
            this.field_177945_a.logError(var3.toString());
         }

         if (this.field_177945_a.field_152877_m) {
            this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.ReadyToBroadcast);
         } else {
            this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.Initialized);
         }
      } else {
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.ReadyToBroadcast);
         String var2 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("stopCallback got failure: %s", var2));
      }
   }

   public void loginCallback(ErrorCode var1, ChannelInfo var2) {
      if (ErrorCode.succeeded(var1)) {
         this.field_177945_a.channelInfo = var2;
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.LoggedIn);
         this.field_177945_a.field_152877_m = true;
      } else {
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.Initialized);
         this.field_177945_a.field_152877_m = false;
         String var3 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("LoginCallback got failure: %s", var3));
      }

      try {
         if (this.field_177945_a.broadcastListener != null) {
            this.field_177945_a.broadcastListener.func_152897_a(var1);
         }
      } catch (Exception var4) {
         this.field_177945_a.logError(var4.toString());
      }
   }

   public void runCommercialCallback(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         String var2 = ErrorCode.getString(var1);
         this.field_177945_a.logWarning(String.format("RunCommercialCallback got failure: %s", var2));
      }
   }

   public void sendStartSpanMetaDataCallback(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         String var2 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("sendStartSpanMetaDataCallback got failure: %s", var2));
      }
   }

   public void sendEndSpanMetaDataCallback(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         String var2 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("sendEndSpanMetaDataCallback got failure: %s", var2));
      }
   }

   public BroadcastController$1(BroadcastController var1) {
      this.field_177945_a = var1;
      super();
   }

   public void getStreamInfoCallback(ErrorCode var1, StreamInfo var2) {
      if (ErrorCode.succeeded(var1)) {
         this.field_177945_a.streamInfo = var2;

         try {
            if (this.field_177945_a.broadcastListener != null) {
               this.field_177945_a.broadcastListener.func_152894_a(var2);
            }
         } catch (Exception var4) {
            this.field_177945_a.logError(var4.toString());
         }
      } else {
         String var3 = ErrorCode.getString(var1);
         this.field_177945_a.logWarning(String.format("StreamInfoDoneCallback got failure: %s", var3));
      }
   }

   public void startCallback(ErrorCode var1) {
      if (ErrorCode.succeeded(var1)) {
         try {
            if (this.field_177945_a.broadcastListener != null) {
               this.field_177945_a.broadcastListener.func_152899_b();
            }
         } catch (Exception var4) {
            this.field_177945_a.logError(var4.toString());
         }

         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.Broadcasting);
      } else {
         this.field_177945_a.videoParamaters = null;
         this.field_177945_a.audioParamaters = null;
         this.field_177945_a.func_152827_a(BroadcastController$BroadcastState.ReadyToBroadcast);

         try {
            if (this.field_177945_a.broadcastListener != null) {
               this.field_177945_a.broadcastListener.func_152892_c(var1);
            }
         } catch (Exception var3) {
            this.field_177945_a.logError(var3.toString());
         }

         String var2 = ErrorCode.getString(var1);
         this.field_177945_a.logError(String.format("startCallback got failure: %s", var2));
      }
   }

   public void getArchivingStateCallback(ErrorCode var1, ArchivingState var2) {
      this.field_177945_a.archivingState = var2;
      if (ErrorCode.failed(var1)) {
      }
   }
}
