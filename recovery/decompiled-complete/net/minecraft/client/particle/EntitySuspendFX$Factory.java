package net.minecraft.client.particle;

import io.netty.util.internal.chmv8.ForkJoinPool$EmptyTask;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.util.MessageSerializer;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatGeneratorInfo;

public class EntitySuspendFX$Factory implements IParticleFactory {
   public ForkJoinPool$EmptyTask field_0001;
   public MovingSoundMinecartRiding field_0003;
   public FlatGeneratorInfo field_0000;
   public MessageSerializer field_0002;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntitySuspendFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
