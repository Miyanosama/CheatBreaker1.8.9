package net.minecraft.client.stream;

import net.minecraft.block.BlockBasePressurePlate;
import net.minecraft.entity.item.EntityEnderEye;
import net.optifine.Lang;

public enum BroadcastController$BroadcastState {
   FindingIngestServer,
   Authenticated,
   LoggedIn,
   Authenticating,
   Initialized,
   ReadyToBroadcast,
   Paused,
   IngestTesting,
   Starting,
   Broadcasting,
   Uninitialized,
   ReceivedIngestServers,
   LoggingIn,
   Stopping;
   public Lang field_0013;
   // $VF: synthetic field
   public static BroadcastController$BroadcastState[] $VALUES = new BroadcastController$BroadcastState[]{
      BroadcastController$BroadcastState.Uninitialized,
      BroadcastController$BroadcastState.Initialized,
      Authenticating,
      Authenticated,
      BroadcastController$BroadcastState.LoggingIn,
      LoggedIn,
      FindingIngestServer,
      BroadcastController$BroadcastState.ReceivedIngestServers,
      BroadcastController$BroadcastState.ReadyToBroadcast,
      BroadcastController$BroadcastState.Starting,
      BroadcastController$BroadcastState.Broadcasting,
      BroadcastController$BroadcastState.Stopping,
      BroadcastController$BroadcastState.Paused,
      BroadcastController$BroadcastState.IngestTesting
   };
   public BlockBasePressurePlate field_0011;
   public EntityEnderEye field_0009;
}
