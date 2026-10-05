package net.minecraft.init;

import io.netty.channel.oio.AbstractOioByteChannel;
import io.netty.handler.ssl.util.ThreadLocalInsecureRandom;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.world.World;
import net.optifine.shaders.config.ShaderPackParser;

public class Bootstrap$11 extends BehaviorProjectileDispense {
   public ShaderPackParser field_0002;
   public ThreadLocalInsecureRandom field_0001;
   public AbstractOioByteChannel field_0000;

   @Override
   public IProjectile getProjectileEntity(World var1, IPosition var2) {
      return new EntityExpBottle(var1, var2.getX(), var2.getY(), var2.getZ());
   }

   @Override
   public float func_82500_b() {
      return super.func_82500_b() * 1.25F;
   }

   @Override
   public float func_82498_a() {
      return super.func_82498_a() * 0.5F;
   }
}
