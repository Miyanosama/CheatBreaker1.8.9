package net.minecraft.entity.passive;

import io.netty.buffer.ByteBufUtil$ThreadLocalDirectByteBuf$1;
import net.minecraft.block.BlockAnvil$Anvil;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.realms.RealmsConnect$1;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.optifine.render.GlBlendState;
import org.apache.log4j.lf5.util.LogFileParser$1;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$4;

public abstract class EntityWaterMob extends EntityLiving implements IAnimals {
   public AttributeModifier field_0000;
   public GlBlendState field_0002;
   public RealmsConnect$1 field_0006;
   public ByteBufUtil$ThreadLocalDirectByteBuf$1 field_0004;
   public LogBrokerMonitor$4 field_0003;
   public BlockAnvil$Anvil field_0001;
   public LogFileParser$1 field_0005;

   @Override
   public void onEntityUpdate() {
      int var1 = this.getAir();
      super.onEntityUpdate();
      if (this.isEntityAlive() && !this.V()) {
         this.setAir(--var1);
         if (this.getAir() == -20) {
            this.setAir(0);
            this.attackEntityFrom(DamageSource.drown, 2.0F);
         }
      } else {
         this.setAir(300);
      }
   }

   @Override
   public int getExperiencePoints(EntityPlayer var1) {
      return 1 + this.o.s.nextInt(3);
   }

   @Override
   public boolean method_06476() {
      return true;
   }

   public EntityWaterMob(World var1) {
      super(var1);
   }

   @Override
   public int getTalkInterval() {
      return 120;
   }

   @Override
   public boolean isPushedByWater() {
      return false;
   }

   @Override
   public boolean getCanSpawnHere() {
      return true;
   }

   @Override
   public boolean canDespawn() {
      return true;
   }

   @Override
   public boolean isNotColliding() {
      return this.o.checkNoEntityCollision(this.getEntityBoundingBox(), this);
   }
}
