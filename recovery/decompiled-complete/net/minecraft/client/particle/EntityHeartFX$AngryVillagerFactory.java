package net.minecraft.client.particle;

import io.netty.handler.codec.spdy.DefaultSpdyHeadersFrame;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AbstractReferenceCounted;
import net.minecraft.world.World;

public class EntityHeartFX$AngryVillagerFactory implements IParticleFactory {
   public AbstractReferenceCounted field_0001;
   public LoggingHandler field_0002;
   public DefaultSpdyHeadersFrame field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      EntityHeartFX var16 = new EntityHeartFX(var2, var3, var5 + 0.5, var7, var9, var11, var13);
      var16.k(81);
      var16.b(1.0F, 1.0F, 1.0F);
      return var16;
   }
}
