package net.minecraft.client.renderer;

import io.netty.channel.DefaultChannelPipeline$HeadContext;
import net.minecraft.block.BlockEventData;
import net.optifine.SmartAnimations;
import net.optifine.expr.FunctionFloat$1;

public class Tessellator {
   public DefaultChannelPipeline$HeadContext field_0003;
   public WorldRenderer worldRenderer;
   public BlockEventData field_0002;
   public static Tessellator instance = new Tessellator(2097152);
   public WorldVertexBufferUploader vboUploader = new WorldVertexBufferUploader();
   public FunctionFloat$1 field_0001;

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
