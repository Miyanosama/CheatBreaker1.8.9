package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class BlockBed extends BlockDirectional {
   public static PropertyEnum<BlockBed.EnumPartType> PART = PropertyEnum.create("part", BlockBed.EnumPartType.class);
   public static PropertyBool OCCUPIED = PropertyBool.create("occupied");

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return var1.getValue(PART) == BlockBed.EnumPartType.HEAD ? null : Items.bed;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.setBedBounds();
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      if (var3.getValue(PART) == BlockBed.EnumPartType.FOOT) {
         super.dropBlockAsItemWithChance(var1, var2, var3, var4, 0);
      }
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var1.D) {
         return true;
      } else {
         if (var3.getValue(PART) != BlockBed.EnumPartType.HEAD) {
            var2 = var2.a(var3.getValue(O));
            var3 = var1.getBlockState(var2);
            if (var3.getBlock() != this) {
               return true;
            }
         }

         if (var1.t.canRespawnHere() && var1.getBiomeGenForCoords(var2) != BiomeGenBase.hell) {
            if (var3.getValue(OCCUPIED)) {
               EntityPlayer var11 = this.getPlayerInBed(var1, var2);
               if (var11 != null) {
                  var4.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied"));
                  return true;
               }

               var3 = var3.withProperty(OCCUPIED, false);
               var1.a(var2, var3, 4);
            }

            EntityPlayer.EnumStatus var12 = var4.trySleep(var2);
            if (var12 == EntityPlayer.EnumStatus.OK) {
               var3 = var3.withProperty(OCCUPIED, true);
               var1.a(var2, var3, 4);
               return true;
            } else {
               if (var12 == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW) {
                  var4.addChatComponentMessage(new ChatComponentTranslation("tile.bed.noSleep"));
               } else if (var12 == EntityPlayer.EnumStatus.NOT_SAFE) {
                  var4.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notSafe"));
               }

               return true;
            }
         } else {
            var1.setBlockToAir(var2);
            BlockPos var9 = var2.a(var3.getValue(O).getOpposite());
            if (var1.getBlockState(var9).getBlock() == this) {
               var1.setBlockToAir(var9);
            }

            var1.newExplosion((Entity)null, var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, 5.0F, true, true);
            return true;
         }
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      EnumFacing var5 = var3.getValue(O);
      if (var3.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         if (var1.getBlockState(var2.a(var5.getOpposite())).getBlock() != this) {
            var1.setBlockToAir(var2);
         }
      } else if (var1.getBlockState(var2.a(var5)).getBlock() != this) {
         var1.setBlockToAir(var2);
         if (!var1.D) {
            this.dropBlockAsItem(var1, var2, var3, 0);
         }
      }
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      if (var1.getValue(PART) == BlockBed.EnumPartType.FOOT) {
         IBlockState var4 = var2.getBlockState(var3.a(var1.getValue(O)));
         if (var4.getBlock() == this) {
            var1 = var1.withProperty(OCCUPIED, var4.getValue(OCCUPIED));
         }
      }

      return var1;
   }

   public BlockBed() {
      super(Material.cloth);
      this.setDefaultState(this.M.getBaseState().withProperty(PART, BlockBed.EnumPartType.FOOT).withProperty(OCCUPIED, false));
      this.setBedBounds();
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(O).getHorizontalIndex();
      if (var1.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         var2 |= 8;
         if (var1.getValue(OCCUPIED)) {
            var2 |= 4;
         }
      }

      return var2;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return Items.bed;
   }

   @Override
   public void onBlockHarvested(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4) {
      if (var4.bA.isCreativeMode && var3.getValue(PART) == BlockBed.EnumPartType.HEAD) {
         BlockPos var5 = var2.a(var3.getValue(O).getOpposite());
         if (var1.getBlockState(var5).getBlock() == this) {
            var1.setBlockToAir(var5);
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   public static boolean hasRoomForPlayer(World var0, BlockPos var1) {
      return World.doesBlockHaveSolidTopSurface(var0, var1.down())
         && !var0.getBlockState(var1).getBlock().getMaterial().isSolid()
         && !var0.getBlockState(var1.up()).getBlock().getMaterial().isSolid();
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, O, PART, OCCUPIED);
   }

   @Override
   public int getMobilityFlag() {
      return 1;
   }

   public static BlockPos getSafeExitLocation(World var0, BlockPos var1, int var2) {
      EnumFacing var3 = var0.getBlockState(var1).getValue(O);
      int var4 = var1.getX();
      int var5 = var1.getY();
      int var6 = var1.getZ();

      for (int var7 = 0; var7 <= 1; var7++) {
         int var8 = var4 - var3.getFrontOffsetX() * var7 - 1;
         int var9 = var6 - var3.getFrontOffsetZ() * var7 - 1;
         int var10 = var8 + 2;
         int var11 = var9 + 2;

         for (int var12 = var8; var12 <= var10; var12++) {
            for (int var13 = var9; var13 <= var11; var13++) {
               BlockPos var14 = new BlockPos(var12, var5, var13);
               if (hasRoomForPlayer(var0, var14)) {
                  if (var2 <= 0) {
                     return var14;
                  }

                  var2--;
               }
            }
         }
      }

      return null;
   }

   public EntityPlayer getPlayerInBed(World var1, BlockPos var2) {
      for (EntityPlayer var4 : var1.j) {
         if (var4.bJ() && var4.playerLocation.equals(var2)) {
            return var4;
         }
      }

      return null;
   }

   public void setBedBounds() {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      EnumFacing var2 = EnumFacing.getHorizontal(var1);
      return (var1 & 8) > 0
         ? this.getDefaultState().withProperty(PART, BlockBed.EnumPartType.HEAD).withProperty(O, var2).withProperty(OCCUPIED, (var1 & 4) > 0)
         : this.getDefaultState().withProperty(PART, BlockBed.EnumPartType.FOOT).withProperty(O, var2);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   public static enum EnumPartType implements IStringSerializable {
      HEAD("head"),
      FOOT("foot");

      public String name;

      @Override
      public String getName() {
         return this.name;
      }

      EnumPartType(String var3) {
         this.name = var3;
      }

      @Override
      public String toString() {
         return this.name;
      }
   }
}
