package net.minecraft.client.particle;

import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.nio.NioSocketChannel$NioSocketChannelConfig;
import javazoom.jl.converter.RiffFile$RiffChunkHeader;
import net.minecraft.entity.EntityList$EntityEggInfo;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityBreakingFX$SlimeFactory implements IParticleFactory {
   public EntityList$EntityEggInfo field_0001;
   public RiffFile$RiffChunkHeader field_0003;
   public NioSctpServerChannel field_0000;
   public NioSocketChannel$NioSocketChannelConfig field_0002;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityBreakingFX(var2, var3, var5, var7, Items.slime_ball);
   }
}
