package net.optifine;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public enum BlockDir {
      DOWN(EnumFacing.DOWN),
      UP(EnumFacing.UP),
      NORTH(EnumFacing.NORTH),
      SOUTH(EnumFacing.SOUTH),
      WEST(EnumFacing.WEST),
      EAST(EnumFacing.EAST),
      NORTH_WEST(EnumFacing.NORTH, EnumFacing.WEST),
      NORTH_EAST(EnumFacing.NORTH, EnumFacing.EAST),
      SOUTH_WEST(EnumFacing.SOUTH, EnumFacing.WEST),
      SOUTH_EAST(EnumFacing.SOUTH, EnumFacing.EAST),
      DOWN_NORTH(EnumFacing.DOWN, EnumFacing.NORTH),
      DOWN_SOUTH(EnumFacing.DOWN, EnumFacing.SOUTH),
      UP_NORTH(EnumFacing.UP, EnumFacing.NORTH),
      UP_SOUTH(EnumFacing.UP, EnumFacing.SOUTH),
      DOWN_WEST(EnumFacing.DOWN, EnumFacing.WEST),
      DOWN_EAST(EnumFacing.DOWN, EnumFacing.EAST),
      UP_WEST(EnumFacing.UP, EnumFacing.WEST),
      UP_EAST(EnumFacing.UP, EnumFacing.EAST);
   public EnumFacing facing2;
   public EnumFacing facing1;
   public static BlockDir[] $VALUES = new BlockDir[]{
      DOWN,
      UP,
      NORTH,
      SOUTH,
      WEST,
      BlockDir.EAST,
      NORTH_WEST,
      NORTH_EAST,
      SOUTH_WEST,
      SOUTH_EAST,
      DOWN_NORTH,
      BlockDir.DOWN_SOUTH,
      UP_NORTH,
      UP_SOUTH,
      DOWN_WEST,
      DOWN_EAST,
      UP_WEST,
      UP_EAST
   };

   public BlockPos offset(BlockPos var1) {
      var1 = var1.a(this.facing1, 1);
      if (this.facing2 != null) {
         var1 = var1.a(this.facing2, 1);
      }

      return var1;
   }

   public int method_03005() {
      int var1 = this.facing1.getFrontOffsetY();
      if (this.facing2 != null) {
         var1 += this.facing2.getFrontOffsetY();
      }

      return var1;
   }

   BlockDir(EnumFacing var3) {
      this.facing1 = var3;
   }

   public int method_03009() {
      int var1 = this.facing1.getFrontOffsetZ();
      if (this.facing2 != null) {
         var1 += this.facing2.getFrontOffsetZ();
      }

      return var1;
   }

   BlockDir(EnumFacing var3, EnumFacing var4) {
      this.facing1 = var3;
      this.facing2 = var4;
   }

   public boolean isDouble() {
      return this.facing2 != null;
   }

   public EnumFacing getFacing2() {
      return this.facing2;
   }

   public int method_03002() {
      int var1 = this.facing1.getFrontOffsetX();
      if (this.facing2 != null) {
         var1 += this.facing2.getFrontOffsetX();
      }

      return var1;
   }

   public EnumFacing getFacing1() {
      return this.facing1;
   }
}
