package net.minecraft.realms;

import io.netty.buffer.PoolSubpage;
import io.netty.util.ThreadDeathWatcher;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.network.play.server.S03PacketTimeUpdate;

public class RealmsServerAddress {
   public int field_0002;
   public ThreadDeathWatcher field_0004;
   public PoolSubpage field_0001;
   public S03PacketTimeUpdate field_0003;
   public String field_0000;

   public String method_07947() {
      return this.field_0000;
   }

   public int method_07949() {
      return this.field_0002;
   }

   public static RealmsServerAddress parseString(String var0) {
      ServerAddress var1 = ServerAddress.fromString(var0);
      return new RealmsServerAddress(var1.getIP(), var1.getPort());
   }

   public RealmsServerAddress(String var1, int var2) {
      this.field_0000 = var1;
      this.field_0002 = var2;
   }
}
