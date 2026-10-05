package net.minecraft.item;

import com.google.common.base.Function;
import io.netty.channel.ThreadPerChannelEventLoopGroup;
import net.minecraft.block.BlockRedSandstone$EnumType;
import org.apache.log4j.lf5.viewer.LF5SwingUtils;

public class Item$9 implements Function<ItemStack, String> {
   public ThreadPerChannelEventLoopGroup field_0000;
   public LF5SwingUtils field_0001;

   public String apply(ItemStack var1) {
      return BlockRedSandstone$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
