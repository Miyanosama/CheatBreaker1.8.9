package net.minecraft.realms;

public class RealmsServerPing {
   public volatile String playerList;
   public volatile String nrOfPlayers = "0";
   public volatile long lastPingSnapshot = 2634737513356894804L & -2634737513935141888L;

   public RealmsServerPing() {
      this.playerList = "";
   }
}
