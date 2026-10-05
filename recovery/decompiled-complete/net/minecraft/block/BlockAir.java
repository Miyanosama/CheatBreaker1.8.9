package net.minecraft.block;

import io.netty.buffer.ByteBufUtil;
import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer$EnumChatVisibility;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import org.apache.log4j.MDC;

public class BlockAir extends Block {
   public BlockPortal$Size field_0002;
   public ByteBufUtil field_0003;
   public static Map mapOriginalOpacity = new IdentityHashMap();
   public EntityPlayer$EnumChatVisibility field_0001;
   public MDC field_0004;

   @Override
   public boolean isReplaceable(World var1, BlockPos var2) {
      return true;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public int getRenderType() {
      return -1;
   }

   @Override
   public boolean canCollideCheck(IBlockState var1, boolean var2) {
      return false;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public static void setLightOpacity(Block var0, int var1) {
      if (!mapOriginalOpacity.containsKey(var0)) {
         mapOriginalOpacity.put(var0, var0.lightOpacity);
      }

      var0.lightOpacity = var1;
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
   }

   public static void restoreLightOpacity(Block var0) {
      if (mapOriginalOpacity.containsKey(var0)) {
         int var1 = (Integer)mapOriginalOpacity.get(var0);
         setLightOpacity(var0, var1);
      }
   }

   public BlockAir() {
      super(Material.air);
   }
}
