package io.netty.channel.group;

import io.netty.channel.Channel;
import net.minecraft.client.renderer.entity.RenderItem$6;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

public class ChannelMatchers$CompositeMatcher implements ChannelMatcher {
   public RenderItem$6 __junk8427127056642483337;
   public ChannelMatcher[] matchers;
   public WebsocketNotConnectedException __junk2578896412207713990;

   public ChannelMatchers$CompositeMatcher(ChannelMatcher... var1) {
      this.matchers = var1;
   }

   @Override
   public boolean matches(Channel var1) {
      for (ChannelMatcher var5 : this.matchers) {
         if (!var5.matches(var1)) {
            return false;
         }
      }

      return true;
   }
}
