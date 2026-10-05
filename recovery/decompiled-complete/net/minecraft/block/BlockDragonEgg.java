package net.minecraft.block;

import com.cheatbreaker.client.ui.util.font.CBFont;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundHandler$3;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget$Sorter;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.log4j.pattern.NDCPatternConverter;

public class BlockDragonEgg extends Block {
   public EntityAINearestAttackableTarget$Sorter field_0002;
   public NDCPatternConverter field_0003;
   public SoundHandler$3 field_0000;
   public CBFont field_0001;

   public void checkFall(World var1, BlockPos var2) {
      if (BlockFalling.canFallInto(var1, var2.down()) && var2.getY() >= 0) {
         byte var3 = 32;
         if (!BlockFalling.fallInstantly && var1.isAreaLoaded(var2.add(-var3, -var3, -var3), var2.add((int)var3, (int)var3, (int)var3))) {
            var1.spawnEntityInWorld(new EntityFallingBlock(var1, var2.getX() + 0.5F, var2.getY(), var2.getZ() + 0.5F, this.getDefaultState()));
         } else {
            var1.setBlockToAir(var2);
            BlockPos var4 = var2;

            while (BlockFalling.canFallInto(var1, var4) && var4.getY() > 0) {
               var4 = var4.down();
            }

            if (var4.getY() > 0) {
               var1.a(var4, this.getDefaultState(), 2);
            }
         }
      }
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      this.teleport(var1, var2);
      return true;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      this.checkFall(var1, var2);
   }

   public void teleport(World var1, BlockPos var2) {
      IBlockState var3 = var1.getBlockState(var2);
      if (var3.getBlock() == this) {
         for (int var4 = 0; var4 < 1000; var4++) {
            BlockPos var5 = var2.add(var1.s.nextInt(16) - var1.s.nextInt(16), var1.s.nextInt(8) - var1.s.nextInt(8), var1.s.nextInt(16) - var1.s.nextInt(16));
            if (var1.getBlockState(var5).getBlock().J == Material.air) {
               if (var1.D) {
                  for (int var6 = 0; var6 < 128; var6++) {
                     double var7 = var1.s.nextDouble();
                     float var9 = (var1.s.nextFloat() - 0.5F) * 0.2F;
                     float var10 = (var1.s.nextFloat() - 0.5F) * 0.2F;
                     float var11 = (var1.s.nextFloat() - 0.5F) * 0.2F;
                     double var12 = var5.getX() + (var2.getX() - var5.getX()) * var7 + (var1.s.nextDouble() - 0.5) * 1.0 + 0.5;
                     double var14 = var5.getY() + (var2.getY() - var5.getY()) * var7 + var1.s.nextDouble() * 1.0 - 0.5;
                     double var16 = var5.getZ() + (var2.getZ() - var5.getZ()) * var7 + (var1.s.nextDouble() - 0.5) * 1.0 + 0.5;
                     var1.spawnParticle(EnumParticleTypes.PORTAL, var12, var14, var16, var9, var10, var11);
                  }
               } else {
                  var1.a(var5, var3, 2);
                  var1.setBlockToAir(var2);
               }

               return;
            }
         }
      }
   }

   @Override
   public void onBlockClicked(World var1, BlockPos var2, EntityPlayer var3) {
      this.teleport(var1, var2);
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      var1.scheduleUpdate(var2, this, this.tickRate(var1));
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return null;
   }

   public BlockDragonEgg() {
      super(Material.dragonEgg, MapColor.blackColor);
      this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return true;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int tickRate(World var1) {
      return 5;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      var1.scheduleUpdate(var2, this, this.tickRate(var1));
   }
}
