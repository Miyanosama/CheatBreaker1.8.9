package net.minecraft.dispenser;

import io.netty.handler.codec.spdy.SpdySessionHandler$1;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityEnderChest;

public class IBehaviorDispenseItem$1 implements IBehaviorDispenseItem {
   public SpdySessionHandler$1 field_0000;
   public TileEntityEnderChest field_0001;

   @Override
   public ItemStack dispense(IBlockSource var1, ItemStack var2) {
      return var2;
   }
}
