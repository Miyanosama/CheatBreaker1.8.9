package net.minecraft.inventory;

import io.netty.channel.socket.nio.NioDatagramChannelConfig;
import io.netty.handler.codec.spdy.SpdyOrHttpChooser$1;
import net.minecraft.creativetab.CreativeTabs$2;
import net.minecraft.dispenser.PositionImpl;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagEnd;
import net.minecraft.world.gen.MapGenBase;

public class ContainerBeacon$BeaconSlot extends Slot {
   public NioDatagramChannelConfig field_0003;
   public NBTTagEnd field_0005;
   public SpdyOrHttpChooser$1 field_0002;
   public PositionImpl field_0004;
   public CreativeTabs$2 field_0000;
   public MapGenBase field_0006;

   public ContainerBeacon$BeaconSlot(ContainerBeacon var1, IInventory var2, int var3, int var4, int var5) {
      this.field_82876_a = var1;
      super(var2, var3, var4, var5);
   }

   @Override
   public int getSlotStackLimit() {
      return 1;
   }

   @Override
   public boolean isItemValid(ItemStack var1) {
      return var1 == null
         ? false
         : var1.getItem() == Items.emerald || var1.getItem() == Items.diamond || var1.getItem() == Items.gold_ingot || var1.getItem() == Items.iron_ingot;
   }
}
