package io.netty.channel.group;

import io.netty.channel.Channel;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.handler.codec.serialization.ClassLoaderClassResolver;
import io.netty.handler.codec.spdy.DefaultSpdySynStreamFrame;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.shader.ShaderLoader;
import net.minecraft.command.CommandStats;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.optifine.util.IteratorCache;
import org.java_websocket.exceptions.WebsocketNotConnectedException;
import org.slf4j.helpers.NOPLogger;
import com.cheatbreaker.client.websocket.client.WSPacketClientPlayerJoin;
import net.minecraft.world.gen.layer.GenLayerShore;

public class ChannelMatchers {
   public static ChannelMatcher ALL_MATCHER = new ChannelMatcher() {

      @Override
      public boolean matches(Channel var1) {
         return true;
      }
   };
   public static ChannelMatcher SERVER_CHANNEL_MATCHER = isInstanceOf(ServerChannel.class);
   public static ChannelMatcher NON_SERVER_CHANNEL_MATCHER = isNotInstanceOf(ServerChannel.class);

   public static ChannelMatcher isInstanceOf(Class<? extends Channel> var0) {
      return new ChannelMatchers.ClassMatcher(var0);
   }

   public static ChannelMatcher is(Channel var0) {
      return new ChannelMatchers.InstanceMatcher(var0);
   }

   public static ChannelMatcher compose(ChannelMatcher... var0) {
      if (var0.length < 1) {
         throw new IllegalArgumentException("matchers must at least contain one element");
      } else {
         return (ChannelMatcher)(var0.length == 1 ? var0[0] : new ChannelMatchers.CompositeMatcher(var0));
      }
   }

   public static ChannelMatcher isServerChannel() {
      return SERVER_CHANNEL_MATCHER;
   }

   public static ChannelMatcher invert(ChannelMatcher var0) {
      return new ChannelMatchers.InvertMatcher(var0);
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

   public static final class ClassMatcher implements ChannelMatcher {
      public Class<? extends Channel> clazz;

      @Override
      public boolean matches(Channel var1) {
         return this.clazz.isInstance(var1);
      }

      public ClassMatcher(Class<? extends Channel> var1) {
         this.clazz = var1;
      }
   }

   public static final class CompositeMatcher implements ChannelMatcher {
      public ChannelMatcher[] matchers;

      public CompositeMatcher(ChannelMatcher... var1) {
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

   public static final class InstanceMatcher implements ChannelMatcher {
      public Channel channel;

      public InstanceMatcher(Channel var1) {
         this.channel = var1;
      }

      @Override
      public boolean matches(Channel var1) {
         return this.channel == var1;
      }
   }

   public static final class InvertMatcher implements ChannelMatcher {
      public ChannelMatcher matcher;

      public InvertMatcher(ChannelMatcher var1) {
         this.matcher = var1;
      }

      @Override
      public boolean matches(Channel var1) {
         return !this.matcher.matches(var1);
      }
   }
}
