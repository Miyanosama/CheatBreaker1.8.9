package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachMappingTask;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.client.renderer.GlStateManager$TexGenState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySkull;
import org.apache.log4j.helpers.CyclicBuffer;

public class BlockSkull$1 implements Predicate<BlockWorldState> {
   public GlStateManager$TexGenState field_0001;
   public ConcurrentHashMapV8$ForEachMappingTask field_0002;
   public CyclicBuffer field_0000;

   public boolean apply(BlockWorldState var1) {
      return var1.getBlockState() != null
         && var1.getBlockState().getBlock() == Blocks.skull
         && var1.getTileEntity() instanceof TileEntitySkull
         && ((TileEntitySkull)var1.getTileEntity()).getSkullType() == 1;
   }
}
