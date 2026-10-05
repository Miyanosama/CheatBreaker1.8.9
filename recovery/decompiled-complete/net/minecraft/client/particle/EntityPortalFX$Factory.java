package net.minecraft.client.particle;

import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$CounterHashCode;
import net.minecraft.world.World;
import org.newsclub.net.unix.AFUNIXSocketImpl$1;
import recovered.unidentified.UnidentifiedClass1350;

public class EntityPortalFX$Factory implements IParticleFactory {
   public UnidentifiedClass1350 field_0001;
   public TooLongFrameException field_0003;
   public ConcurrentHashMapV8$CounterHashCode field_0000;
   public AFUNIXSocketImpl$1 field_0002;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityPortalFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
