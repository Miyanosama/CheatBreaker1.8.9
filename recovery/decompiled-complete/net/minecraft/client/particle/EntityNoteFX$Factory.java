package net.minecraft.client.particle;

import io.netty.channel.rxtx.DefaultRxtxChannelConfig;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.world.World;
import org.apache.log4j.spi.ThrowableInformation;

public class EntityNoteFX$Factory implements IParticleFactory {
   public C0CPacketInput field_0001;
   public DefaultRxtxChannelConfig field_0002;
   public ThrowableInformation field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityNoteFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
