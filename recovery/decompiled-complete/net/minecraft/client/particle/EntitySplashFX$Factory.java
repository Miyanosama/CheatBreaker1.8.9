package net.minecraft.client.particle;

import io.netty.buffer.WrappedByteBuf;
import io.netty.channel.group.DefaultChannelGroupFuture$1;
import io.netty.channel.socket.oio.OioDatagramChannel;
import net.minecraft.command.CommandResultStats$1;
import net.minecraft.init.Bootstrap$3;
import net.minecraft.nbt.JsonToNBT$Compound;
import net.minecraft.world.World;

public class EntitySplashFX$Factory implements IParticleFactory {
   public WrappedByteBuf field_0003;
   public OioDatagramChannel field_0005;
   public DefaultChannelGroupFuture$1 field_0002;
   public JsonToNBT$Compound field_0004;
   public CommandResultStats$1 field_0000;
   public Bootstrap$3 field_0001;
   public EntitySuspendFX$Factory field_0006;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntitySplashFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
