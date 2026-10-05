package io.netty.channel.group;

import io.netty.channel.Channel;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.optifine.util.IteratorCache;

public class ChannelMatchers$InvertMatcher implements ChannelMatcher {
   public ChannelMatcher matcher;
   public ShapelessRecipes __junk2055782596443920000;
   public IteratorCache __junk5718987618165134435;

   public ChannelMatchers$InvertMatcher(ChannelMatcher var1) {
      this.matcher = var1;
   }

   @Override
   public boolean matches(Channel var1) {
      return !this.matcher.matches(var1);
   }
}
