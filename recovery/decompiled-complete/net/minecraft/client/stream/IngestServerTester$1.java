package net.minecraft.client.stream;

import net.optifine.entity.model.ModelAdapterSlime;
import tv.twitch.AuthToken;
import tv.twitch.ErrorCode;
import tv.twitch.broadcast.ArchivingState;
import tv.twitch.broadcast.ChannelInfo;
import tv.twitch.broadcast.GameInfoList;
import tv.twitch.broadcast.IStreamCallbacks;
import tv.twitch.broadcast.IngestList;
import tv.twitch.broadcast.StreamInfo;
import tv.twitch.broadcast.UserInfo;

public class IngestServerTester$1 implements IStreamCallbacks {
   public ModelAdapterSlime field_0000;

   public void getUserInfoCallback(ErrorCode var1, UserInfo var2) {
   }

   public void setStreamInfoCallback(ErrorCode var1) {
   }

   public void getGameNameListCallback(ErrorCode var1, GameInfoList var2) {
   }

   public void getStreamInfoCallback(ErrorCode var1, StreamInfo var2) {
   }

   public void getIngestServersCallback(ErrorCode var1, IngestList var2) {
   }

   public void startCallback(ErrorCode var1) {
      this.field_176010_a.field_176008_y = false;
      if (ErrorCode.succeeded(var1)) {
         this.field_176010_a.field_176009_x = true;
         this.field_176010_a.field_153054_l = System.currentTimeMillis();
         this.field_176010_a.func_153034_a(IngestServerTester$IngestTestState.ConnectingToServer);
      } else {
         this.field_176010_a.field_153056_n = false;
         this.field_176010_a.func_153034_a(IngestServerTester$IngestTestState.DoneTestingServer);
      }
   }

   public void bufferUnlockCallback(long var1) {
   }

   public void sendEndSpanMetaDataCallback(ErrorCode var1) {
   }

   public void getArchivingStateCallback(ErrorCode var1, ArchivingState var2) {
   }

   public void stopCallback(ErrorCode var1) {
      if (ErrorCode.failed(var1)) {
         System.out.println("IngestTester.stopCallback failed to stop - " + this.field_176010_a.field_153059_q.serverName + ": " + var1.toString());
      }

      this.field_176010_a.field_176007_z = false;
      this.field_176010_a.field_176009_x = false;
      this.field_176010_a.func_153034_a(IngestServerTester$IngestTestState.DoneTestingServer);
      this.field_176010_a.field_153059_q = null;
      if (this.field_176010_a.field_153060_r) {
         this.field_176010_a.func_153034_a(IngestServerTester$IngestTestState.Cancelling);
      }
   }

   public void sendStartSpanMetaDataCallback(ErrorCode var1) {
   }

   public IngestServerTester$1(IngestServerTester var1) {
      this.field_176010_a = var1;
      super();
   }

   public void loginCallback(ErrorCode var1, ChannelInfo var2) {
   }

   public void runCommercialCallback(ErrorCode var1) {
   }

   public void sendActionMetaDataCallback(ErrorCode var1) {
   }

   public void requestAuthTokenCallback(ErrorCode var1, AuthToken var2) {
   }
}
