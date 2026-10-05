package net.minecraft.client.renderer;

import io.netty.util.internal.EmptyArrays;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.EnumFacing;

public class RenderGlobal$ContainerLocalRenderInformation {
   public RenderChunk renderChunk;
   public EnumFacing facing;
   public int setFacing;
   public EmptyArrays field_0002;

   public void setFacingBit(byte var1, EnumFacing var2) {
      this.setFacing = this.setFacing | var1 | 1 << var2.ordinal();
   }

   public boolean isFacingBit(EnumFacing var1) {
      return (this.setFacing & 1 << var1.ordinal()) > 0;
   }

   public void initialize(EnumFacing var1, int var2) {
      this.facing = var1;
      this.setFacing = var2;
   }

   public RenderGlobal$ContainerLocalRenderInformation(RenderChunk var1, EnumFacing var2, int var3) {
      this.renderChunk = var1;
      this.facing = var2;
      this.setFacing = var3;
   }
}
