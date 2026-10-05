package net.minecraft.init;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.world.World;
import net.optifine.ConnectedTexturesCompact;
import net.optifine.http.HttpRequest;

public class Bootstrap$10 extends BehaviorProjectileDispense {
   public ConnectedTexturesCompact field_0001;
   public HttpRequest field_0000;

   @Override
   public IProjectile getProjectileEntity(World var1, IPosition var2) {
      return new EntitySnowball(var1, var2.getX(), var2.getY(), var2.getZ());
   }
}
