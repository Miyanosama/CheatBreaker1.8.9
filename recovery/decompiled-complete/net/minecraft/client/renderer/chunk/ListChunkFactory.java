package net.minecraft.client.renderer.chunk;

import net.minecraft.block.BlockBeacon$1;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass0631;

public class ListChunkFactory implements IRenderChunkFactory {
   public BlockBeacon$1 field_0000;
   public UnidentifiedClass0631 field_0001;

   @Override
   public RenderChunk makeRenderChunk(World var1, RenderGlobal var2, BlockPos var3, int var4) {
      return new ListedRenderChunk(var1, var2, var3, var4);
   }
}
