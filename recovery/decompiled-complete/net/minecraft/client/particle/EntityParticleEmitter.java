package net.minecraft.client.particle;

import io.netty.handler.stream.ChunkedNioFile;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityParticleEmitter extends EntityFX {
   public Entity attachedEntity;
   public float field_0005;
   public int field_0002;
   public ChunkedNioFile field_0004;
   public int field_0000;
   public EnumParticleTypes particleTypes;

   public EntityParticleEmitter(World var1, Entity var2, EnumParticleTypes var3) {
      super(var1, var2.s, var2.getEntityBoundingBox().b + var2.K / 2.0F, var2.u, var2.v, var2.w, var2.x);
      this.attachedEntity = var2;
      this.field_0002 = 3;
      this.particleTypes = var3;
      this.field_0005 = 1.0F;
      this.onUpdate();
   }

   @Override
   public int getFXLayer() {
      return 3;
   }

   @Override
   public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6, float var7, float var8) {
   }

   public EntityParticleEmitter(World var1, Entity var2, EnumParticleTypes var3, float var4) {
      super(var1, var2.s, var2.getEntityBoundingBox().b + var2.K / 2.0F, var2.u, var2.v, var2.w, var2.x);
      this.attachedEntity = var2;
      this.field_0002 = 3;
      this.particleTypes = var3;
      this.field_0005 = var4;
      this.onUpdate();
   }

   @Override
   public void onUpdate() {
      for (int var1 = 0; var1 < 16.0F * this.field_0005; var1++) {
         double var2 = this.V.nextFloat() * 2.0F - 1.0F;
         double var4 = this.V.nextFloat() * 2.0F - 1.0F;
         double var6 = this.V.nextFloat() * 2.0F - 1.0F;
         if (var2 * var2 + var4 * var4 + var6 * var6 <= 1.0) {
            double var8 = this.attachedEntity.s + var2 * this.attachedEntity.J / 4.0;
            double var10 = this.attachedEntity.getEntityBoundingBox().b + this.attachedEntity.K / 2.0F + var4 * this.attachedEntity.K / 4.0;
            double var12 = this.attachedEntity.u + var6 * this.attachedEntity.J / 4.0;
            this.o.spawnParticle(this.particleTypes, false, var8, var10, var12, var2, var4 + 0.2, var6);
         }
      }

      this.field_0000++;
      if (this.field_0000 >= this.field_0002) {
         this.setDead();
      }
   }
}
