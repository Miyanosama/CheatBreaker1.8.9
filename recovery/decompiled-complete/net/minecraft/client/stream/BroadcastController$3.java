package net.minecraft.client.stream;

import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import net.minecraft.client.network.OldServerPinger$2;

// $VF: synthetic class
public class BroadcastController$3 {
   public OldServerPinger$2 field_0002;
   public DefaultUnmarshallerProvider field_0000;

   static {
      try {
         field_0001[BroadcastController$BroadcastState.Authenticated.ordinal()] = 1;
      } catch (NoSuchFieldError var12) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.LoggedIn.ordinal()] = 2;
      } catch (NoSuchFieldError var11) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.ReceivedIngestServers.ordinal()] = 3;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.Starting.ordinal()] = 4;
      } catch (NoSuchFieldError var9) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.Stopping.ordinal()] = 5;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.FindingIngestServer.ordinal()] = 6;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.Authenticating.ordinal()] = 7;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.Initialized.ordinal()] = 8;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.Uninitialized.ordinal()] = 9;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.IngestTesting.ordinal()] = 10;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.Paused.ordinal()] = 11;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0001[BroadcastController$BroadcastState.Broadcasting.ordinal()] = 12;
      } catch (NoSuchFieldError var1) {
      }
   }
}
