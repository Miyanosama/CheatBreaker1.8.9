package net.minecraft.entity.ai;

import io.netty.handler.codec.http.HttpContentCompressor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.world.biome.BiomeGenSavanna;

public class EntityAIWatchClosest2 extends EntityAIWatchClosest {
   public HttpContentCompressor field_0000;
   public EntitySnowball field_0001;
   public BiomeGenSavanna field_0002;

   public EntityAIWatchClosest2(EntityLiving var1, Class<? extends Entity> var2, float var3, float var4) {
      super(var1, var2, var3, var4);
      this.setMutexBits(3);
   }
}
