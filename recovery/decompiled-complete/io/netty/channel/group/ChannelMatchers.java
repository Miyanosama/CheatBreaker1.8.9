package io.netty.channel.group;

import io.netty.channel.AbstractChannelHandlerContext$14;
import io.netty.channel.Channel;
import io.netty.channel.ServerChannel;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory$2;
import net.minecraft.client.renderer.GlStateManager$BlendState;
import net.minecraft.client.renderer.entity.RenderArrow;
import recovered.unidentified.UnidentifiedClass3153;

public class ChannelMatchers {
   public AbstractChannelHandlerContext$14 __junk1489564344430205120;
   public static ChannelMatcher SERVER_CHANNEL_MATCHER = isInstanceOf(ServerChannel.class);
   public static ChannelMatcher NON_SERVER_CHANNEL_MATCHER = isNotInstanceOf(ServerChannel.class);
   public RenderArrow __junk8998311120433482884;
   public UnidentifiedClass3153 __junk8832128875581843393;
   public FingerprintTrustManagerFactory$2 __junk4463156977308669194;
   public static ChannelMatcher ALL_MATCHER = new ChannelMatchers$1();
   public GlStateManager$BlendState __junk2367849589382360316;

   public static ChannelMatcher isInstanceOf(Class<? extends Channel> var0) {
      return new ChannelMatchers$ClassMatcher(var0);
   }

   public static ChannelMatcher is(Channel var0) {
      return new ChannelMatchers$InstanceMatcher(var0);
   }

   public static ChannelMatcher compose(ChannelMatcher... var0) {
      if (var0.length < 1) {
         throw new IllegalArgumentException("matchers must at least contain one element");
      } else {
         return (ChannelMatcher)(var0.length == 1 ? var0[0] : new ChannelMatchers$CompositeMatcher(var0));
      }
   }

   public static ChannelMatcher isServerChannel() {
      return SERVER_CHANNEL_MATCHER;
   }

   public static ChannelMatcher invert(ChannelMatcher var0) {
      return new ChannelMatchers$InvertMatcher(var0);
   }

   public static ChannelMatcher isNonServerChannel() {
      return NON_SERVER_CHANNEL_MATCHER;
   }

   public static ChannelMatcher isNotInstanceOf(Class<? extends Channel> var0) {
      return invert(isInstanceOf(var0));
   }

   public static ChannelMatcher isNot(Channel var0) {
      return invert(is(var0));
   }

   public static ChannelMatcher all() {
      return ALL_MATCHER;
   }
}
