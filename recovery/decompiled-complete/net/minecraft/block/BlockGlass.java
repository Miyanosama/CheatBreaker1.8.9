package net.minecraft.block;

import com.cheatbreaker.client.nethandler.client.PacketVoiceMute;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityLargeExplodeFX$Factory;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockGlass extends BlockBreakable {
   public EntityLargeExplodeFX$Factory field_0000;
   public ItemFishingRod field_0002;
   public PacketVoiceMute field_0001;

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public boolean canSilkHarvest() {
      return true;
   }

   public BlockGlass(Material var1, boolean var2) {
      super(var1, var2);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int quantityDropped(Random var1) {
      return 0;
   }
}
