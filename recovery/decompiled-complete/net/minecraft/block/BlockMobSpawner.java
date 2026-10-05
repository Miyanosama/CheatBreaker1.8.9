package net.minecraft.block;

import java.util.Random;
import javax.vecmath.GVector;
import javazoom.jl.player.advanced.jlap$InfoListener;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockMobSpawner extends BlockContainer {
   public jlap$InfoListener field_0000;
   public GVector field_0001;

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5);
      int var6 = 15 + var1.s.nextInt(15) + var1.s.nextInt(15);
      this.b(var1, var2, var6);
   }

   public BlockMobSpawner() {
      super(Material.rock);
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public int quantityDropped(Random var1) {
      return 0;
   }

   @Override
   public Item getItemDropped(IBlockState var1, Random var2, int var3) {
      return null;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return null;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityMobSpawner();
   }
}
