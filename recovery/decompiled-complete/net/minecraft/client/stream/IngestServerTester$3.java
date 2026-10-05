package net.minecraft.client.stream;

import com.cheatbreaker.client.config.Profile;
import io.netty.handler.timeout.IdleStateHandler$ReaderIdleTimeoutTask;
import net.minecraft.util.Vector3d;
import recovered.unidentified.UnidentifiedClass3688;
import tv.twitch.broadcast.StatType;

// $VF: synthetic class
public class IngestServerTester$3 {
   public IdleStateHandler$ReaderIdleTimeoutTask field_0005;
   public Profile field_0002;
   public UnidentifiedClass3688 field_0004;
   public Vector3d field_0000;

   static {
      try {
         field_176002_b[IngestServerTester$IngestTestState.Starting.ordinal()] = 1;
      } catch (NoSuchFieldError var11) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.DoneTestingServer.ordinal()] = 2;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.ConnectingToServer.ordinal()] = 3;
      } catch (NoSuchFieldError var9) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.TestingServer.ordinal()] = 4;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.Cancelling.ordinal()] = 5;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.Uninitalized.ordinal()] = 6;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.Finished.ordinal()] = 7;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.Cancelled.ordinal()] = 8;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_176002_b[IngestServerTester$IngestTestState.Failed.ordinal()] = 9;
      } catch (NoSuchFieldError var3) {
      }

      field_176003_a = new int[StatType.values().length];

      try {
         field_176003_a[StatType.TTV_ST_RTMPSTATE.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_176003_a[StatType.TTV_ST_RTMPDATASENT.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
