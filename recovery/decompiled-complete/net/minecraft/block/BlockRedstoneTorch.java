package net.minecraft.block;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.channel.nio.NioEventLoop$1;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.java_websocket.server.WebSocketServer$WebSocketWorker$1;

public class BlockRedstoneTorch extends BlockTorch {
   public static Map<World, List<BlockRedstoneTorch$Toggle>> toggles = Maps.newHashMap();
   public boolean field_0003;
   public WebSocketServer$WebSocketWorker$1 field_0000;
   public NioEventLoop$1 field_0001;

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!this.onNeighborChangeInternal(var1, var2, var3) && this.field_0003 == this.shouldBeOff(var1, var2, var3)) {
         var1.scheduleUpdate(var2, this, this.tickRate(var1));
      }
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return this.field_0003 && var3.getValue(FACING) != var4 ? 15 : 0;
   }

   @Override
   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (this.field_0003) {
         double var5 = var2.getX() + 0.5 + (var4.nextDouble() - 0.5) * 0.2;
         double var7 = var2.getY() + 0.7 + (var4.nextDouble() - 0.5) * 0.2;
         double var9 = var2.getZ() + 0.5 + (var4.nextDouble() - 0.5) * 0.2;
         EnumFacing var11 = var3.getValue(FACING);
         if (var11.getAxis().isHorizontal()) {
            EnumFacing var12 = var11.getOpposite();
            double var13 = 0.27;
            var5 += 0.27 * var12.getFrontOffsetX();
            var7 += 0.22;
            var9 += 0.27 * var12.getFrontOffsetZ();
         }

         var1.spawnParticle(EnumParticleTypes.REDSTONE, var5, var7, var9, 0.0, 0.0, 0.0);
      }
   }

   public BlockRedstoneTorch(boolean var1) {
      this.field_0003 = var1;
      this.setTickRandomly(true);
      this.setCreativeTab((CreativeTabs)null);
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      if (this.field_0003) {
         for (EnumFacing var7 : EnumFacing.values()) {
            var1.notifyNeighborsOfStateChange(var2.a(var7), this);
         }
      }
   }

   @Override
   public boolean isAssociatedBlock(Block var1) {
      return var1 == Blocks.unlit_redstone_torch || var1 == Blocks.redstone_torch;
   }

   public boolean shouldBeOff(World var1, BlockPos var2, IBlockState var3) {
      EnumFacing var4 = var3.getValue(FACING).getOpposite();
      return var1.isSidePowered(var2.a(var4), var4);
   }

   @Override
   public int getStrongPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return var4 == EnumFacing.DOWN ? this.getWeakPower(var1, var2, var3, var4) : 0;
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return Item.getItemFromBlock(Blocks.redstone_torch);
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Item.getItemFromBlock(Blocks.redstone_torch);
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      if (this.field_0003) {
         for (EnumFacing var7 : EnumFacing.values()) {
            var1.notifyNeighborsOfStateChange(var2.a(var7), this);
         }
      }
   }

   @Override
   public void randomTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
   }

   @Override
   public int tickRate(World var1) {
      return 2;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      boolean var5 = this.shouldBeOff(var1, var2, var3);
      List var6 = toggles.get(var1);

      while (var6 != null && !var6.isEmpty() && var1.K() - ((BlockRedstoneTorch$Toggle)var6.get(0)).time > (-8937747415557207620L & 2097788L)) {
         var6.remove(0);
      }

      if (this.field_0003) {
         if (var5) {
            var1.a(var2, Blocks.unlit_redstone_torch.getDefaultState().withProperty(FACING, var3.getValue(FACING)), 3);
            if (this.isBurnedOut(var1, var2, true)) {
               var1.playSoundEffect(
                  var2.getX() + 0.5F, var2.getY() + 0.5F, var2.getZ() + 0.5F, "random.fizz", 0.5F, 2.6F + (var1.s.nextFloat() - var1.s.nextFloat()) * 0.8F
               );

               for (int var7 = 0; var7 < 5; var7++) {
                  double var8 = var2.getX() + var4.nextDouble() * 0.6 + 0.2;
                  double var10 = var2.getY() + var4.nextDouble() * 0.6 + 0.2;
                  double var12 = var2.getZ() + var4.nextDouble() * 0.6 + 0.2;
                  var1.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, var8, var10, var12, 0.0, 0.0, 0.0);
               }

               var1.scheduleUpdate(var2, var1.getBlockState(var2).getBlock(), 160);
            }
         }
      } else if (!var5 && !this.isBurnedOut(var1, var2, false)) {
         var1.a(var2, Blocks.redstone_torch.getDefaultState().withProperty(FACING, var3.getValue(FACING)), 3);
      }
   }

   public boolean isBurnedOut(World var1, BlockPos var2, boolean var3) {
      if (!toggles.containsKey(var1)) {
         toggles.put(var1, Lists.newArrayList());
      }

      List var4 = toggles.get(var1);
      if (var3) {
         var4.add(new BlockRedstoneTorch$Toggle(var2, var1.K()));
      }

      int var5 = 0;

      for (int var6 = 0; var6 < var4.size(); var6++) {
         BlockRedstoneTorch$Toggle var7 = (BlockRedstoneTorch$Toggle)var4.get(var6);
         if (var7.pos.equals(var2)) {
            if (++var5 >= 8) {
               return true;
            }
         }
      }

      return false;
   }
}
