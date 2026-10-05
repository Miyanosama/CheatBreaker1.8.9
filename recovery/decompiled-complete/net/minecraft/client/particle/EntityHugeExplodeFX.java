package net.minecraft.client.particle;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenStoneBeach;
import net.optifine.entity.model.ModelAdapterZombie;
import org.newsclub.net.unix.AFUNIXSocketImpl$Lenient;

public class EntityHugeExplodeFX extends EntityFX {
   public int maximumTime = 8;
   public BiomeGenStoneBeach field_0004;
   public ModelAdapterZombie field_0001;
   public AFUNIXSocketImpl$Lenient field_0003;
   public int timeSinceStart;

   @Override
   public int getFXLayer() {
      return 1;
   }

   public EntityHugeExplodeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, 0.0, 0.0, 0.0);
   }

   @Override
   public void onUpdate() {
      for (int var1 = 0; var1 < 6; var1++) {
         double var2 = this.s + (this.V.nextDouble() - this.V.nextDouble()) * 4.0;
         double var4 = this.t + (this.V.nextDouble() - this.V.nextDouble()) * 4.0;
         double var6 = this.u + (this.V.nextDouble() - this.V.nextDouble()) * 4.0;
         this.o.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, var2, var4, var6, (float)this.timeSinceStart / this.maximumTime, 0.0, 0.0);
      }

      this.timeSinceStart++;
      if (this.timeSinceStart == this.maximumTime) {
         this.setDead();
      }
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
   }
}
