package net.minecraft.client.particle;

import io.netty.handler.codec.http.DefaultCookie;
import net.minecraft.world.World;

public class EntityFlameFX$Factory implements IParticleFactory {
   public DefaultCookie field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityFlameFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
