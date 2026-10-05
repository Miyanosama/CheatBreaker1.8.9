package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.block.BlockStoneBrick$EnumType;
import net.minecraft.client.audio.SoundList$SoundEntry;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$29;

public class Item$5 implements Function<ItemStack, String> {
   public SoundList$SoundEntry field_0000;
   public LogBrokerMonitor$29 field_0001;

   public String apply(ItemStack var1) {
      return BlockStoneBrick$EnumType.byMetadata(var1.getMetadata()).getUnlocalizedName();
   }
}
