package net.minecraft.client.renderer;

import io.netty.channel.embedded.EmbeddedEventLoop;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.client.renderer.entity.layers.LayerSaddle;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.render.VboRegion;
import net.optifine.shaders.ShadersRender;
import org.apache.log4j.lf5.viewer.TrackingAdjustmentListener;
import org.lwjgl.opengl.GL11;

public class VboRenderList extends ChunkRenderContainer {
   public TrackingAdjustmentListener field_0003;
   public double viewEntityY;
   public double viewEntityX;
   public double viewEntityZ;
   public EmbeddedEventLoop field_0006;
   public S34PacketMaps field_0000;
   public LayerSaddle field_0001;

   public void setupArrayPointers() {
      if (Config.isShaders()) {
         ShadersRender.method_06618();
      } else {
         GL11.glVertexPointer(3, 5126, 28, -2377802058442911552L & 69730566L);
         GL11.glColorPointer(4, 5121, 28, 1076888860L & 2266277968051994796L);
         GL11.glTexCoordPointer(2, 5126, 28, -1971714593892857264L & 1971714591889104912L);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.lightmapTexUnit);
         GL11.glTexCoordPointer(2, 5122, 28, 571736088L & 1409418907L);
         OpenGlHelper.setClientActiveTexture(OpenGlHelper.defaultTexUnit);
      }
   }

   public void preRenderRegion(int var1, int var2, int var3) {
      GlStateManager.translate((float)(var1 - this.viewEntityX), (float)(var2 - this.viewEntityY), (float)(var3 - this.viewEntityZ));
   }

   @Override
   public void a(double var1, double var3, double var5) {
      this.viewEntityX = var1;
      this.viewEntityY = var3;
      this.viewEntityZ = var5;
      super.a(var1, var3, var5);
   }

   @Override
   public void renderChunkLayer(EnumWorldBlockLayer var1) {
      if (this.initialized) {
         if (!Config.isRenderRegions()) {
            for (RenderChunk var10 : this.renderChunks) {
               VertexBuffer var11 = var10.getVertexBufferByLayer(var1.ordinal());
               GlStateManager.pushMatrix();
               this.preRenderChunk(var10);
               var10.multModelviewMatrix();
               var11.bindBuffer();
               this.setupArrayPointers();
               var11.drawArrays(7);
               GlStateManager.popMatrix();
            }
         } else {
            int var2 = Integer.MIN_VALUE;
            int var3 = Integer.MIN_VALUE;
            VboRegion var4 = null;

            for (RenderChunk var6 : this.renderChunks) {
               VertexBuffer var7 = var6.getVertexBufferByLayer(var1.ordinal());
               VboRegion var8 = var7.getVboRegion();
               if (var8 != var4 || var2 != var6.regionX || var3 != var6.regionZ) {
                  if (var4 != null) {
                     this.drawRegion(var2, var3, var4);
                  }

                  var2 = var6.regionX;
                  var3 = var6.regionZ;
                  var4 = var8;
               }

               var7.drawArrays(7);
            }

            if (var4 != null) {
               this.drawRegion(var2, var3, var4);
            }
         }

         OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
         GlStateManager.resetColor();
         this.renderChunks.clear();
      }
   }

   public void drawRegion(int var1, int var2, VboRegion var3) {
      GlStateManager.pushMatrix();
      this.preRenderRegion(var1, 0, var2);
      var3.finishDraw(this);
      GlStateManager.popMatrix();
   }
}
