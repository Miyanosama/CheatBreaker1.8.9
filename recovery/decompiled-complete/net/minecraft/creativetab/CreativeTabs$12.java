package net.minecraft.creativetab;

import io.netty.buffer.CompositeByteBuf$Component;
import io.netty.channel.socket.oio.OioDatagramChannel;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item$16;
import net.minecraft.tileentity.TileEntityDaylightDetector;

public class CreativeTabs$12 extends CreativeTabs {
   public CompositeByteBuf$Component field_0002;
   public TileEntityDaylightDetector field_0000;
   public OioDatagramChannel field_0003;
   public Item$16 field_0001;

   public CreativeTabs$12(int var1, String var2) {
      super(var1, var2);
   }

   @Override
   public Item getTabIconItem() {
      return Items.golden_sword;
   }
}
