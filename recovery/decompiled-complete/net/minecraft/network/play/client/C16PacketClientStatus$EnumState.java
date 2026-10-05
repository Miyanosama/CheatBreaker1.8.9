package net.minecraft.network.play.client;

import org.apache.log4j.spi.NOPLogger;

public enum C16PacketClientStatus$EnumState {
   PERFORM_RESPAWN,
   REQUEST_STATS,
   OPEN_INVENTORY_ACHIEVEMENT;

   // $VF: synthetic field
   public static C16PacketClientStatus$EnumState[] $VALUES = new C16PacketClientStatus$EnumState[]{
      PERFORM_RESPAWN, C16PacketClientStatus$EnumState.REQUEST_STATS, C16PacketClientStatus$EnumState.OPEN_INVENTORY_ACHIEVEMENT
   };
   public NOPLogger field_0000;
}
