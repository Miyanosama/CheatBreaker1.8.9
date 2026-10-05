package net.minecraft.block;

import io.netty.channel.ChannelOutboundBuffer$Entry;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiCreateFlatWorld;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockBarrier extends Block {
   public S1EPacketRemoveEntityEffect field_0001;
   public GuiCreateFlatWorld field_0002;
   public ChannelOutboundBuffer$Entry field_0000;

   public BlockBarrier() {
      super(Material.barrier);
      this.setBlockUnbreakable();
      this.setResistance(6000001.0F);
      this.disableStats();
      this.translucent = true;
   }

   @Override
   public float getAmbientOcclusionLightValue() {
      return 1.0F;
   }

   @Override
   public int getRenderType() {
      return -1;
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }
}
