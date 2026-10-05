package net.minecraft.init;

import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$WrappedFullHttpRequest;
import io.netty.util.collection.IntObjectHashMap$IteratorImpl;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class Bootstrap$14 extends BehaviorDefaultDispenseItem {
   public IntObjectHashMap$IteratorImpl field_0000;
   public HttpPostRequestEncoder$WrappedFullHttpRequest field_0001;

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      EnumFacing var3 = BlockDispenser.getFacing(var1.getBlockMetadata());
      double var4 = var1.getX() + var3.getFrontOffsetX();
      double var6 = var1.getBlockPos().getY() + 0.2F;
      double var8 = var1.getZ() + var3.getFrontOffsetZ();
      EntityFireworkRocket var10 = new EntityFireworkRocket(var1.getWorld(), var4, var6, var8, var2);
      var1.getWorld().spawnEntityInWorld(var10);
      var2.splitStack(1);
      return var2;
   }

   @Override
   public void playDispenseSound(IBlockSource var1) {
      var1.getWorld().b(1002, var1.getBlockPos(), 0);
   }
}
