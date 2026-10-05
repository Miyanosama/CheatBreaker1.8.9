package net.minecraft.world;

import net.minecraft.profiler.Profiler;
import net.minecraft.server.MinecraftServer;
import net.minecraft.village.VillageCollection;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.ISaveHandler;

public class WorldServerMulti extends WorldServer {
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
      var4.af().addListener(new IBorderListener() {
         @Override
         public void onSizeChanged(WorldBorder var1, double var2x) {
            WorldServerMulti.this.af().setTransition(var2x);
         }

         @Override
         public void onTransitionStarted(WorldBorder var1, double var2x, double var4x, long var6) {
            WorldServerMulti.this.af().setTransition(var2x, var4x, var6);
         }

         @Override
         public void onWarningDistanceChanged(WorldBorder var1, int var2x) {
            WorldServerMulti.this.af().setWarningDistance(var2x);
         }

         @Override
         public void onCenterChanged(WorldBorder var1, double var2x, double var4x) {
            WorldServerMulti.this.af().setCenter(var2x, var4x);
         }

         @Override
         public void method_02434(WorldBorder var1, double var2x) {
            WorldServerMulti.this.af().setDamageAmount(var2x);
         }

         @Override
         public void onWarningTimeChanged(WorldBorder var1, int var2x) {
            WorldServerMulti.this.af().setWarningTime(var2x);
         }

         @Override
         public void method_02429(WorldBorder var1, double var2x) {
            WorldServerMulti.this.af().setDamageBuffer(var2x);
         }
      });
   }

   @Override
   public void saveLevel() {
   }
}
