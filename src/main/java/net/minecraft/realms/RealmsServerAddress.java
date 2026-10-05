package net.minecraft.realms;

import net.minecraft.client.multiplayer.ServerAddress;

public class RealmsServerAddress {
   public int recoveredField906;
   public String recoveredField907;

   public String method_07947() {
      return this.recoveredField907;
   }

   public int method_07949() {
      return this.recoveredField906;
   }

   public static RealmsServerAddress parseString(String var0) {
      ServerAddress var1 = ServerAddress.fromString(var0);
      return new RealmsServerAddress(var1.getIP(), var1.getPort());
   }

   public RealmsServerAddress(String var1, int var2) {
      this.recoveredField907 = var1;
      this.recoveredField906 = var2;
   }
}
