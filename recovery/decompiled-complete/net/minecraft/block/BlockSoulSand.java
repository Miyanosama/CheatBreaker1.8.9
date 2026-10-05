package net.minecraft.block;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$BaseIterator;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenMutated;
import net.optifine.shaders.ShaderPackDefault;

public class BlockSoulSand extends Block {
   public ConcurrentHashMapV8$BaseIterator field_0002;
   public ShaderPackDefault field_0003;
   public EntityAIControlledByPlayer field_0000;
   public BiomeGenMutated field_0001;

   public BlockSoulSand() {
      super(Material.sand, MapColor.brownColor);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity var4) {
      var4.v *= 0.4;
      var4.x *= 0.4;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      float var4 = 0.125F;
      return new AxisAlignedBB(var2.getX(), var2.getY(), var2.getZ(), var2.getX() + 1, var2.getY() + 1 - var4, var2.getZ() + 1);
   }
}
