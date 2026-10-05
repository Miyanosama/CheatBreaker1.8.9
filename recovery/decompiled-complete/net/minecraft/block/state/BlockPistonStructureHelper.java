package net.minecraft.block.state;

import com.google.common.collect.Lists;
import io.netty.handler.codec.spdy.SpdyHeaderBlockZlibDecoder;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Teleporter$PortalPosition;
import net.minecraft.world.World;
import net.optifine.EmissiveTextures;

public class BlockPistonStructureHelper {
   public List<BlockPos> toMove = Lists.newArrayList();
   public SpdyHeaderBlockZlibDecoder field_0007;
   public Teleporter$PortalPosition field_0003;
   public BlockPos pistonPos;
   public BlockPos blockToMove;
   public InternalLoggerFactory field_0001;
   public EnumFacing moveDirection;
   public EmissiveTextures field_0005;
   public World world;
   public List<BlockPos> toDestroy = Lists.newArrayList();

   public List<BlockPos> getBlocksToMove() {
      return this.toMove;
   }

   public boolean canMove() {
      this.toMove.clear();
      this.toDestroy.clear();
      Block var1 = this.world.getBlockState(this.blockToMove).getBlock();
      if (!BlockPistonBase.canPush(var1, this.world, this.blockToMove, this.moveDirection, false)) {
         if (var1.getMobilityFlag() != 1) {
            return false;
         } else {
            this.toDestroy.add(this.blockToMove);
            return true;
         }
      } else if (!this.func_177251_a(this.blockToMove)) {
         return false;
      } else {
         for (int var2 = 0; var2 < this.toMove.size(); var2++) {
            BlockPos var3 = this.toMove.get(var2);
            if (this.world.getBlockState(var3).getBlock() == Blocks.slime_block && !this.func_177250_b(var3)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean func_177250_b(BlockPos var1) {
      for (EnumFacing var5 : EnumFacing.values()) {
         if (var5.getAxis() != this.moveDirection.getAxis() && !this.func_177251_a(var1.a(var5))) {
            return false;
         }
      }

      return true;
   }

   public BlockPistonStructureHelper(World var1, BlockPos var2, EnumFacing var3, boolean var4) {
      this.world = var1;
      this.pistonPos = var2;
      if (var4) {
         this.moveDirection = var3;
         this.blockToMove = var2.a(var3);
      } else {
         this.moveDirection = var3.getOpposite();
         this.blockToMove = var2.a(var3, 2);
      }
   }

   public List<BlockPos> getBlocksToDestroy() {
      return this.toDestroy;
   }

   public boolean func_177251_a(BlockPos var1) {
      Block var2 = this.world.getBlockState(var1).getBlock();
      if (var2.getMaterial() == Material.air) {
         return true;
      } else if (!BlockPistonBase.canPush(var2, this.world, var1, this.moveDirection, false)) {
         return true;
      } else if (var1.equals(this.pistonPos)) {
         return true;
      } else if (this.toMove.contains(var1)) {
         return true;
      } else {
         int var3 = 1;
         if (var3 + this.toMove.size() > 12) {
            return false;
         } else {
            while (var2 == Blocks.slime_block) {
               BlockPos var4 = var1.a(this.moveDirection.getOpposite(), var3);
               var2 = this.world.getBlockState(var4).getBlock();
               if (var2.getMaterial() == Material.air
                  || !BlockPistonBase.canPush(var2, this.world, var4, this.moveDirection, false)
                  || var4.equals(this.pistonPos)) {
                  break;
               }

               if (++var3 + this.toMove.size() > 12) {
                  return false;
               }
            }

            int var11 = 0;

            for (int var5 = var3 - 1; var5 >= 0; var5--) {
               this.toMove.add(var1.a(this.moveDirection.getOpposite(), var5));
               var11++;
            }

            int var12 = 1;

            while (true) {
               BlockPos var6 = var1.a(this.moveDirection, var12);
               int var7 = this.toMove.indexOf(var6);
               if (var7 > -1) {
                  this.func_177255_a(var11, var7);

                  for (int var8 = 0; var8 <= var7 + var11; var8++) {
                     BlockPos var9 = this.toMove.get(var8);
                     if (this.world.getBlockState(var9).getBlock() == Blocks.slime_block && !this.func_177250_b(var9)) {
                        return false;
                     }
                  }

                  return true;
               }

               var2 = this.world.getBlockState(var6).getBlock();
               if (var2.getMaterial() == Material.air) {
                  return true;
               }

               if (!BlockPistonBase.canPush(var2, this.world, var6, this.moveDirection, true) || var6.equals(this.pistonPos)) {
                  return false;
               }

               if (var2.getMobilityFlag() == 1) {
                  this.toDestroy.add(var6);
                  return true;
               }

               if (this.toMove.size() >= 12) {
                  return false;
               }

               this.toMove.add(var6);
               var11++;
               var12++;
            }
         }
      }
   }

   public void func_177255_a(int var1, int var2) {
      ArrayList var3 = Lists.newArrayList();
      ArrayList var4 = Lists.newArrayList();
      ArrayList var5 = Lists.newArrayList();
      var3.addAll(this.toMove.subList(0, var2));
      var4.addAll(this.toMove.subList(this.toMove.size() - var1, this.toMove.size()));
      var5.addAll(this.toMove.subList(var2, this.toMove.size() - var1));
      this.toMove.clear();
      this.toMove.addAll(var3);
      this.toMove.addAll(var4);
      this.toMove.addAll(var5);
   }
}
