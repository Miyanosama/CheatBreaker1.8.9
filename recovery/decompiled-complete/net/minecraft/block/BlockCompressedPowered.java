package net.minecraft.block;

import io.netty.handler.stream.ChunkedWriteHandler$3;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.server.S45PacketTitle$Type;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.chunk.storage.ChunkLoader$AnvilConverterData;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Throne;
import org.slf4j.helpers.Util$ClassContextSecurityManager;

public class BlockCompressedPowered extends Block {
   public S45PacketTitle$Type field_0002;
   public StructureNetherBridgePieces$Throne field_0003;
   public ChunkLoader$AnvilConverterData field_0000;
   public ChunkedWriteHandler$3 field_0001;
   public Util$ClassContextSecurityManager field_0004;

   @Override
   public int getWeakPower(IBlockAccess var1, BlockPos var2, IBlockState var3, EnumFacing var4) {
      return 15;
   }

   @Override
   public boolean canProvidePower() {
      return true;
   }

   public BlockCompressedPowered(Material var1, MapColor var2) {
      super(var1, var2);
   }
}
