package net.minecraft.world.biome;

import io.netty.channel.AbstractChannelHandlerContext$1;
import io.netty.handler.codec.socks.SocksInitResponseDecoder$1;
import net.minecraft.client.particle.EntityFishWakeFX;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;

public class BiomeGenEnd extends BiomeGenBase {
   public AbstractChannelHandlerContext$1 field_0000;
   public SocksInitResponseDecoder$1 field_0002;
   public EntityFishWakeFX field_0001;

   @Override
   public int getSkyColorByTemp(float var1) {
      return 0;
   }

   public BiomeGenEnd(int var1) {
      super(var1);
      this.at.clear();
      this.au.clear();
      this.av.clear();
      this.aw.clear();
      this.at.add(new BiomeGenBase$SpawnListEntry(EntityEnderman.class, 10, 4, 4));
      this.ak = Blocks.dirt.getDefaultState();
      this.al = Blocks.dirt.getDefaultState();
      this.as = new BiomeEndDecorator();
   }
}
