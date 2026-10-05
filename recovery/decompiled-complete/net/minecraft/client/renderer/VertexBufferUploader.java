package net.minecraft.client.renderer;

import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.src.Config;
import org.apache.log4j.xml.XMLLayout;

public class VertexBufferUploader extends WorldVertexBufferUploader {
   public EntityRabbit field_0001;
   public XMLLayout field_0002;
   public VertexBuffer vertexBuffer = null;

   public void setVertexBuffer(VertexBuffer var1) {
      this.vertexBuffer = var1;
   }

   @Override
   public void draw(WorldRenderer var1) {
      if (var1.getDrawMode() == 7 && Config.isQuadsToTriangles()) {
         var1.quadsToTriangles();
         this.vertexBuffer.setDrawMode(var1.getDrawMode());
      }

      this.vertexBuffer.bufferData(var1.getByteBuffer());
      var1.reset();
   }
}
