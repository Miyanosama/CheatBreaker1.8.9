package net.minecraft.init;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockTNT;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk$3;
import org.java_websocket.SSLSocketChannel;
import recovered.unidentified.UnidentifiedClass1216;

public class Bootstrap$4 extends BehaviorDefaultDispenseItem {
   public UnidentifiedClass1216 field_0001;
   public S02PacketChat field_0003;
   public Chunk$3 field_0000;
   public boolean field_150839_b = true;
   public SSLSocketChannel field_0002;

   @Override
   public ItemStack dispenseStack(IBlockSource var1, ItemStack var2) {
      World var3 = var1.getWorld();
      BlockPos var4 = var1.getBlockPos().a(BlockDispenser.getFacing(var1.getBlockMetadata()));
      if (var3.isAirBlock(var4)) {
         var3.setBlockState(var4, Blocks.fire.getDefaultState());
         if (var2.attemptDamageItem(1, var3.s)) {
            var2.stackSize = 0;
         }
      } else if (var3.getBlockState(var4).getBlock() == Blocks.tnt) {
         Blocks.tnt.onBlockDestroyedByPlayer(var3, var4, Blocks.tnt.getDefaultState().withProperty(BlockTNT.EXPLODE, true));
         var3.setBlockToAir(var4);
      } else {
         this.field_150839_b = false;
      }

      return var2;
   }

   @Override
   public void playDispenseSound(IBlockSource var1) {
      if (this.field_150839_b) {
         var1.getWorld().b(1000, var1.getBlockPos(), 0);
      } else {
         var1.getWorld().b(1001, var1.getBlockPos(), 0);
      }
   }
}
