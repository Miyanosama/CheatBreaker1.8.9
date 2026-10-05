package net.minecraft.client.renderer;

import net.optifine.SmartAnimations;

public class Tessellator {
   public WorldRenderer worldRenderer;
   public static Tessellator instance = new Tessellator(2097152);
   public WorldVertexBufferUploader vboUploader = new WorldVertexBufferUploader();

   public Tessellator(int var1) {
      this.worldRenderer = new WorldRenderer(var1);
   }

   public WorldRenderer getWorldRenderer() {
      return this.worldRenderer;
   }

   public static Tessellator getInstance() {
      return instance;
   }

   public void draw() {
      if (this.worldRenderer.animatedSprites != null) {
         SmartAnimations.spritesRendered(this.worldRenderer.animatedSprites);
      }

      this.worldRenderer.finishDrawing();
      this.vboUploader.draw(this.worldRenderer);
   }
}
