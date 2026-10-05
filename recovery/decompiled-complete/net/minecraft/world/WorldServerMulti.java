package net.minecraft.world;

import com.cheatbreaker.client.module.type.PingModule;
import com.cheatbreaker.client.util.thread.AliasesThread;
import io.netty.handler.codec.spdy.SpdySession;
import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.ISaveHandler;

public class WorldServerMulti extends WorldServer {
   public SpdySession field_0002;
   public AliasesThread field_0000;
   public PingModule field_0001;
   public WorldServer delegate;

   @Override
   public World init() {
      this.z = this.delegate.T();
      this.C = this.delegate.Z();
      String var1 = VillageCollection.fileNameForProvider(this.t);
      VillageCollection var2 = (VillageCollection)this.z.loadData(VillageCollection.class, var1);
      if (var2 == null) {
         this.A = new VillageCollection(this);
         this.z.setData(var1, this.A);
      } else {
         this.A = var2;
         this.A.setWorldsForAll(this);
      }

      return this;
   }

   public WorldServerMulti(MinecraftServer var1, ISaveHandler var2, int var3, WorldServer var4, Profiler var5) {
      super(var1, var2, new DerivedWorldInfo(var4.P()), var3, var5);
      this.delegate = var4;
      var4.af().addListener(new WorldServerMulti$1(this));
   }

   @Override
   public void saveLevel() {
   }
}
