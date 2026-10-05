package io.netty.channel.group;

import io.netty.channel.Channel;
import io.netty.channel.MultithreadEventLoopGroup;
import recovered.unidentified.UnidentifiedClass5091;

public class ChannelMatchers$ClassMatcher implements ChannelMatcher {
   public MultithreadEventLoopGroup __junk2266969108669770645;
   public UnidentifiedClass5091 __junk1390829253176743173;
   public Class<? extends Channel> clazz;

   @Override
   public boolean matches(Channel var1) {
      return this.clazz.isInstance(var1);
   }

   public ChannelMatchers$ClassMatcher(Class<? extends Channel> var1) {
      this.clazz = var1;
   }
}
