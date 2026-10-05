package net.minecraft.world.demo;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;

public class DemoWorldServer extends WorldServer {
   public static long demoWorldSeed = "North Carolina".hashCode();
   public static WorldSettings demoWorldSettings = new WorldSettings(
         DemoWorldServer.demoWorldSeed, WorldSettings.GameType.SURVIVAL, true, false, WorldType.DEFAULT
      )
      .enableBonusChest();

   public DemoWorldServer(MinecraftServer var1, ISaveHandler var2, WorldInfo var3, int var4, Profiler var5) {
      super(var1, var2, var3, var4, var5);
      this.x.populateFromWorldSettings(demoWorldSettings);
   }
}
