package io.netty.channel.group;

import io.netty.channel.AbstractChannelHandlerContext$6;
import io.netty.channel.Channel;
import io.netty.handler.codec.serialization.ClassLoaderClassResolver;
import io.netty.handler.codec.spdy.DefaultSpdySynStreamFrame;
import net.minecraft.block.BlockOre;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import org.slf4j.helpers.NOPLogger;

public class ChannelMatchers$InstanceMatcher implements ChannelMatcher {
   public BlockOre __junk2756539083848580272;
   public ClassLoaderClassResolver __junk3187091441678885775;
   public Channel channel;
   public DefaultSpdySynStreamFrame __junk8022426694967874351;
   public AbstractChannelHandlerContext$6 __junk2902900854742803984;
   public NOPLogger __junk4361323988770112922;
   public GenLayerAddMushroomIsland __junk4625743120883906524;

   public ChannelMatchers$InstanceMatcher(Channel var1) {
      this.channel = var1;
   }

   @Override
   public boolean matches(Channel var1) {
      return this.channel == var1;
   }
}
