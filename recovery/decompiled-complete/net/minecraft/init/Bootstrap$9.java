package net.minecraft.init;

import io.netty.handler.codec.socks.UnknownSocksResponse;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass3599;

public class Bootstrap$9 extends BehaviorProjectileDispense {
   public UnknownSocksResponse field_0001;
   public UnidentifiedClass3599 field_0000;

   @Override
   public IProjectile getProjectileEntity(World var1, IPosition var2) {
      return new EntityEgg(var1, var2.getX(), var2.getY(), var2.getZ());
   }
}
