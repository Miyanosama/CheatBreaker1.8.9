package net.minecraft.world.demo;

import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Team$EnumVisible;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.LowerStringMap;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import recovered.unidentified.UnidentifiedClass5082;

public class DemoWorldServer extends WorldServer {
   public Team$EnumVisible field_0002;
   public static WorldSettings demoWorldSettings = new WorldSettings(
         DemoWorldServer.demoWorldSeed, WorldSettings$GameType.SURVIVAL, true, false, WorldType.DEFAULT
      )
      .enableBonusChest();
   public LowerStringMap field_0001;
   public static long demoWorldSeed = "North Carolina".hashCode();
   public UnidentifiedClass5082 field_0003;

   public DemoWorldServer(MinecraftServer var1, ISaveHandler var2, WorldInfo var3, int var4, Profiler var5) {
      super(var1, var2, var3, var4, var5);
      this.x.populateFromWorldSettings(demoWorldSettings);
   }
}
