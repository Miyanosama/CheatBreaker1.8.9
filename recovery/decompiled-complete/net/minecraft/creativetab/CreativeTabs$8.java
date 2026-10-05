package net.minecraft.creativetab;

import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.optifine.model.ListQuadsOverlay;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$7;

public class CreativeTabs$8 extends CreativeTabs {
   public CategoryNodeEditor$7 field_0001;
   public AbstractNioChannel$AbstractNioUnsafe field_0000;
   public ListQuadsOverlay field_0002;

   @Override
   public Item getTabIconItem() {
      return Items.lava_bucket;
   }

   public CreativeTabs$8(int var1, String var2) {
      super(var1, var2);
   }
}
