package net.optifine.render;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.VboRenderList;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.src.Config;
import net.minecraft.util.EnumWorldBlockLayer;
import net.optifine.util.LinkedList;

public class VboRegion {
   public VboRange compactRangeLast;
   public EnumWorldBlockLayer layer = null;
   public IntBuffer bufferIndexVertex;
   public int vertexBytes;
   public int drawMode;
   public int capacity;
   public int glBufferId = OpenGlHelper.glGenBuffers();
   public LinkedList<VboRange> rangeList;
   public IntBuffer bufferCountVertex;
   public int positionTop;
   public int sizeUsed;

   public void bufferData(ByteBuffer var1, VboRange var2) {
      int var3 = var2.getPosition();
      int var4 = var2.getSize();
      int var5 = this.toVertex(var1.limit());
      if (var5 <= 0) {
         if (var3 >= 0) {
            var2.setPosition(-1);
            var2.setSize(0);
            this.rangeList.remove(var2.getNode());
            this.sizeUsed -= var4;
         }
      } else {
         if (var5 > var4) {
            var2.setPosition(this.positionTop);
            var2.setSize(var5);
            this.positionTop += var5;
            if (var3 >= 0) {
               this.rangeList.remove(var2.getNode());
            }

            this.rangeList.addLast(var2.getNode());
         }

         var2.setSize(var5);
         this.sizeUsed += var5 - var4;
         this.checkVboSize(var2.getPositionNext());
         long var6 = this.toBytes(var2.getPosition());
         this.bindBuffer();
         OpenGlHelper.glBufferSubData(OpenGlHelper.GL_ARRAY_BUFFER, var6, var1);
         this.unbindBuffer();
         if (this.positionTop > this.sizeUsed * 11 / 10) {
            this.compactRanges(1);
         }
      }
   }

   public void unbindBuffer() {
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
   }

   public long toBytes(int var1) {
      return (long)var1 * this.vertexBytes;
   }

   public void bindBuffer() {
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.glBufferId);
   }

   public int toVertex(long var1) {
      return (int)(var1 / this.vertexBytes);
   }

   public void deleteGlBuffers() {
      if (this.glBufferId >= 0) {
         OpenGlHelper.glDeleteBuffers(this.glBufferId);
         this.glBufferId = -1;
      }
   }

   public void drawArrays(int var1, VboRange var2) {
      if (this.drawMode != var1) {
         if (this.bufferIndexVertex.position() > 0) {
            throw new IllegalArgumentException("Mixed region draw modes: " + this.drawMode + " != " + var1);
         }

         this.drawMode = var1;
      }

      this.bufferIndexVertex.put(var2.getPosition());
      this.bufferCountVertex.put(var2.getSize());
   }

   public void compactRanges(int var1) {
      if (!this.rangeList.isEmpty()) {
         VboRange var2 = this.compactRangeLast;
         if (var2 == null || !this.rangeList.contains(var2.getNode())) {
            var2 = this.rangeList.getFirst().getItem();
         }

         int var3 = var2.getPosition();
         VboRange var4 = var2.getPrev();
         if (var4 == null) {
            var3 = 0;
         } else {
            var3 = var4.getPositionNext();
         }

         int var5 = 0;

         while (var2 != null && var5 < var1) {
            var5++;
            if (var2.getPosition() == var3) {
               var3 += var2.getSize();
               var2 = var2.getNext();
            } else {
               int var6 = var2.getPosition() - var3;
               if (var2.getSize() <= var6) {
                  this.copyVboData(var2.getPosition(), var3, var2.getSize());
                  var2.setPosition(var3);
                  var3 += var2.getSize();
                  var2 = var2.getNext();
               } else {
                  this.checkVboSize(this.positionTop + var2.getSize());
                  this.copyVboData(var2.getPosition(), this.positionTop, var2.getSize());
                  var2.setPosition(this.positionTop);
                  this.positionTop = this.positionTop + var2.getSize();
                  VboRange var7 = var2.getNext();
                  this.rangeList.remove(var2.getNode());
                  this.rangeList.addLast(var2.getNode());
                  var2 = var7;
               }
            }
         }

         if (var2 == null) {
            this.positionTop = this.rangeList.getLast().getItem().getPositionNext();
         }

         this.compactRangeLast = var2;
      }
   }

   public void finishDraw(VboRenderList var1) {
      this.bindBuffer();
      var1.setupArrayPointers();
      ((Buffer)this.bufferIndexVertex).flip();
      ((Buffer)this.bufferCountVertex).flip();
      GlStateManager.glMultiDrawArrays(this.drawMode, this.bufferIndexVertex, this.bufferCountVertex);
      ((Buffer)this.bufferIndexVertex).limit(this.bufferIndexVertex.capacity());
      ((Buffer)this.bufferCountVertex).limit(this.bufferCountVertex.capacity());
      if (this.positionTop > this.sizeUsed * 11 / 10) {
         this.compactRanges(1);
      }
   }

   public int getPositionTop() {
      return this.positionTop;
   }

   public void expandVbo(int var1) {
      int var2 = this.capacity * 6 / 4;

      while (var2 < var1) {
         var2 = var2 * 6 / 4;
      }

      long var3 = this.toBytes(this.capacity);
      long var5 = this.toBytes(var2);
      int var7 = OpenGlHelper.glGenBuffers();
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, var7);
      OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, var5, OpenGlHelper.GL_STATIC_DRAW);
      Config.checkGlError("Expand VBO");
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, 0);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, this.glBufferId);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, var7);
      OpenGlHelper.glCopyBufferSubData(OpenGlHelper.GL_COPY_READ_BUFFER, OpenGlHelper.GL_COPY_WRITE_BUFFER, 0L, 0L, var3);
      Config.checkGlError("Copy VBO: " + var5);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, 0);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, 0);
      OpenGlHelper.glDeleteBuffers(this.glBufferId);
      this.bufferIndexVertex = Config.createDirectIntBuffer(var2);
      this.bufferCountVertex = Config.createDirectIntBuffer(var2);
      this.glBufferId = var7;
      this.capacity = var2;
   }

   public void checkVboSize(int var1) {
      if (this.capacity < var1) {
         this.expandVbo(var1);
      }
   }

   public VboRegion(EnumWorldBlockLayer var1) {
      this.capacity = 4096;
      this.positionTop = 0;
      this.rangeList = new LinkedList<>();
      this.compactRangeLast = null;
      this.bufferIndexVertex = Config.createDirectIntBuffer(this.capacity);
      this.bufferCountVertex = Config.createDirectIntBuffer(this.capacity);
      this.drawMode = 7;
      this.vertexBytes = DefaultVertexFormats.BLOCK.getNextOffset();
      this.layer = var1;
      this.bindBuffer();
      long var2 = this.toBytes(this.capacity);
      OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, var2, OpenGlHelper.GL_STATIC_DRAW);
      this.unbindBuffer();
   }

   public void checkRanges() {
      int var1 = 0;
      int var2 = 0;

      for (VboRange var3 = this.rangeList.getFirst().getItem(); var3 != null; var3 = var3.getNext()) {
         var1++;
         var2 += var3.getSize();
         if (var3.getPosition() < 0 || var3.getSize() <= 0 || var3.getPositionNext() > this.positionTop) {
            throw new RuntimeException("Invalid range: " + var3);
         }

         VboRange var4 = var3.getPrev();
         if (var4 != null && var3.getPosition() < var4.getPositionNext()) {
            throw new RuntimeException("Invalid range: " + var3);
         }

         VboRange var5 = var3.getNext();
         if (var5 != null && var3.getPositionNext() > var5.getPosition()) {
            throw new RuntimeException("Invalid range: " + var3);
         }
      }

      if (var1 != this.rangeList.getSize()) {
         throw new RuntimeException("Invalid count: " + var1 + " <> " + this.rangeList.getSize());
      } else if (var2 != this.sizeUsed) {
         throw new RuntimeException("Invalid size: " + var2 + " <> " + this.sizeUsed);
      }
   }

   public void copyVboData(int var1, int var2, int var3) {
      long var4 = this.toBytes(var1);
      long var6 = this.toBytes(var2);
      long var8 = this.toBytes(var3);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, this.glBufferId);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, this.glBufferId);
      OpenGlHelper.glCopyBufferSubData(OpenGlHelper.GL_COPY_READ_BUFFER, OpenGlHelper.GL_COPY_WRITE_BUFFER, var4, var6, var8);
      Config.checkGlError("Copy VBO range");
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_READ_BUFFER, 0);
      OpenGlHelper.glBindBuffer(OpenGlHelper.GL_COPY_WRITE_BUFFER, 0);
   }
}
