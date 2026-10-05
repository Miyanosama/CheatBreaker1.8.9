package net.minecraft.client.renderer;

import io.netty.channel.MultithreadEventLoopGroup;
import java.nio.Buffer;
import java.nio.IntBuffer;
import net.minecraft.client.player.inventory.ContainerLocalMenu;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.border.EnumBorderStatus;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0921;

public class RenderList extends ChunkRenderContainer {
   public double viewEntityZ;
   public double viewEntityX;
   public ContainerLocalMenu field_0002;
   public IntBuffer bufferLists = GLAllocation.createDirectIntBuffer(16);
   public UnidentifiedClass0921 field_0007;
   public MultithreadEventLoopGroup field_0000;
   public double viewEntityY;
   public EnumBorderStatus field_0004;

   @Override
   public void renderChunkLayer(EnumWorldBlockLayer var1) {
      if (this.initialized) {
         if (!Config.isRenderRegions()) {
            for (RenderChunk var9 : this.renderChunks) {
               ListedRenderChunk var10 = (ListedRenderChunk)var9;
               GlStateManager.pushMatrix();
               this.preRenderChunk(var9);
               GL11.glCallList(var10.getDisplayList(var1, var10.getCompiledChunk()));
               GlStateManager.popMatrix();
            }
         } else {
            int var2 = Integer.MIN_VALUE;
            int var3 = Integer.MIN_VALUE;

            for (RenderChunk var5 : this.renderChunks) {
               ListedRenderChunk var6 = (ListedRenderChunk)var5;
               if (var2 != var5.regionX || var3 != var5.regionZ) {
                  if (this.bufferLists.position() > 0) {
                     this.drawRegion(var2, var3, this.bufferLists);
                  }

                  var2 = var5.regionX;
                  var3 = var5.regionZ;
               }

               if (this.bufferLists.position() >= this.bufferLists.capacity()) {
                  IntBuffer var7 = GLAllocation.createDirectIntBuffer(this.bufferLists.capacity() * 2);
                  ((Buffer)this.bufferLists).flip();
                  var7.put(this.bufferLists);
                  this.bufferLists = var7;
               }

               this.bufferLists.put(var6.getDisplayList(var1, var6.getCompiledChunk()));
            }

            if (this.bufferLists.position() > 0) {
               this.drawRegion(var2, var3, this.bufferLists);
            }
         }

         if (Config.isMultiTexture()) {
            GlStateManager.bindCurrentTexture();
         }

         GlStateManager.resetColor();
         this.renderChunks.clear();
      }
   }

   @Override
   public void a(double var1, double var3, double var5) {
      this.viewEntityX = var1;
      this.viewEntityY = var3;
      this.viewEntityZ = var5;
      super.a(var1, var3, var5);
   }

   public void drawRegion(int var1, int var2, IntBuffer var3) {
      GlStateManager.pushMatrix();
      this.preRenderRegion(var1, 0, var2);
      ((Buffer)var3).flip();
      GlStateManager.callLists(var3);
      ((Buffer)var3).clear();
      GlStateManager.popMatrix();
   }

   public void preRenderRegion(int var1, int var2, int var3) {
      GlStateManager.translate((float)(var1 - this.viewEntityX), (float)(var2 - this.viewEntityY), (float)(var3 - this.viewEntityZ));
   }
}
