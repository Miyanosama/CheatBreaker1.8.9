package recovered.unidentified;

import io.netty.channel.epoll.AbstractEpollChannel$1;
import io.netty.util.internal.ReadOnlyIterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLog$2;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.ContainerBeacon$BeaconSlot;

public class UnidentifiedClass0373 extends Block {
   public ReadOnlyIterator field_0002;
   public BlockNewLog$2 field_0003;
   public AbstractEpollChannel$1 field_0000;
   public ContainerBeacon$BeaconSlot field_0001;

   public UnidentifiedClass0373() {
      super(Material.rock);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.netherrackColor;
   }
}
