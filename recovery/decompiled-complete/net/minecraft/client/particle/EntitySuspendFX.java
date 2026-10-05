package net.minecraft.client.particle;

import net.minecraft.block.material.Material;
import net.minecraft.command.CommandGameMode;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityPigZombie$AIHurtByAggressor;
import net.minecraft.init.Bootstrap$6;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntitySuspendFX extends EntityFX {
   public EntityLightningBolt field_0001;
   public Bootstrap$6 field_0003;
   public CommandGameMode field_0000;
   public EntityPigZombie$AIHurtByAggressor field_0002;

   public EntitySuspendFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4 - 0.125, var6, var8, var10, var12);
      this.ar = 0.4F;
      this.as = 0.4F;
      this.at = 0.7F;
      this.k(0);
      this.setSize(0.01F, 0.01F);
      this.h = this.h * (this.V.nextFloat() * 0.6F + 0.2F);
      this.v = var8 * 0.0;
      this.w = var10 * 0.0;
      this.x = var12 * 0.0;
      this.g = (int)(16.0 / (Math.random() * 0.8 + 0.2));
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      this.d(this.v, this.w, this.x);
      if (this.o.getBlockState(new BlockPos(this)).getBlock().getMaterial() != Material.water) {
         this.setDead();
      }

      if (this.g-- <= 0) {
         this.setDead();
      }
   }
}
