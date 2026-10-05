package net.minecraft.server;

import io.netty.util.DefaultAttributeMap;
import java.util.concurrent.Callable;

public class MinecraftServer$4 implements Callable<String> {
   public DefaultAttributeMap field_0001;

   public MinecraftServer$4(MinecraftServer var1) {
      this.field_74274_a = var1;
      super();
   }

   public String call() {
      return MinecraftServer.access$100(this.field_74274_a).getCurrentPlayerCount()
         + " / "
         + MinecraftServer.access$100(this.field_74274_a).getMaxPlayers()
         + "; "
         + MinecraftServer.access$100(this.field_74274_a).getPlayerList();
   }
}
