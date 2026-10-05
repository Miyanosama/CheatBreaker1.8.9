package net.optifine.shaders;

import java.util.Iterator;
import javazoom.jl.decoder.BitstreamException;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.init.Bootstrap$2;
import net.minecraft.inventory.ContainerEnchantment$2;
import net.minecraft.util.BlockPos;
import net.optifine.BlockPosM;

public class IteratorRenderChunks implements Iterator<RenderChunk> {
   public Bootstrap$2 field_0003;
   public BitstreamException field_0005;
   public Iterator3d Iterator3d;
   public BlockPosM posBlock = new BlockPosM(0, 0, 0);
   public ContainerEnchantment$2 field_0000;
   public ViewFrustum viewFrustum;

   public RenderChunk next() {
      BlockPos var1 = this.Iterator3d.next();
      this.posBlock.setXyz(var1.getX() << 4, var1.getY() << 4, var1.getZ() << 4);
      return this.viewFrustum.getRenderChunk(this.posBlock);
   }

   public IteratorRenderChunks(ViewFrustum var1, BlockPos var2, BlockPos var3, int var4, int var5) {
      this.viewFrustum = var1;
      this.Iterator3d = new Iterator3d(var2, var3, var4, var5);
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   @Override
   public boolean hasNext() {
      return this.Iterator3d.hasNext();
   }
}
