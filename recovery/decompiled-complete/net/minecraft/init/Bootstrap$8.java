package net.minecraft.init;

import io.netty.handler.ssl.util.SimpleTrustManagerFactory$1;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForwardingNode;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.optifine.shaders.BlockAliases;
import org.apache.log4j.PropertyWatchdog;

public class Bootstrap$8 extends BehaviorDefaultDispenseItem {
   public SimpleTrustManagerFactory$1 field_0002;
   public ConcurrentHashMapV8$ForwardingNode field_0004;
   public PropertyWatchdog field_0000;
   public BlockAliases field_0005;
   public boolean field_179241_b = true;
   public GenLayerRiverMix field_0001;

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      World var3 = var1.getWorld();
      BlockPos var4 = var1.getBlockPos().a(BlockDispenser.getFacing(var1.getBlockMetadata()));
      BlockPumpkin var5 = (BlockPumpkin)Blocks.pumpkin;
      if (var3.isAirBlock(var4) && var5.canDispenserPlace(var3, var4)) {
         if (!var3.D) {
            var3.a(var4, var5.getDefaultState(), 3);
         }

         var2.stackSize--;
      } else {
         this.field_179241_b = false;
      }

      return var2;
   }

   @Override
   public void playDispenseSound(IBlockSource var1) {
      if (this.field_179241_b) {
         var1.getWorld().b(1000, var1.getBlockPos(), 0);
      } else {
         var1.getWorld().b(1001, var1.getBlockPos(), 0);
      }
   }
}
