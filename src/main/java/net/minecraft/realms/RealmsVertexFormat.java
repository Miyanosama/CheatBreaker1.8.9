package net.minecraft.realms;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class RealmsVertexFormat {
   public VertexFormat v;

   public RealmsVertexFormat(VertexFormat var1) {
      this.v = var1;
   }

   public int method_24738() {
      return this.v.getColorOffset();
   }

   public void clear() {
      this.v.clear();
   }

   public int method_24734() {
      return this.v.getNextOffset();
   }

   public int method_24743() {
      return this.v.getNormalOffset();
   }

   public List<RealmsVertexFormatElement> getElements() {
      ArrayList var1 = new ArrayList();

      for (VertexFormatElement var3 : this.v.getElements()) {
         var1.add(new RealmsVertexFormatElement(var3));
      }

      return var1;
   }

   public boolean method_24732() {
      return this.v.hasNormal();
   }

   public RealmsVertexFormatElement getElement(int var1) {
      return new RealmsVertexFormatElement(this.v.getElement(var1));
   }

   public int method_24746(int var1) {
      return this.v.getUvOffsetById(var1);
   }

   @Override
   public int hashCode() {
      return this.v.hashCode();
   }

   public int method_24735(int var1) {
      return this.v.getOffset(var1);
   }

   public RealmsVertexFormat addElement(RealmsVertexFormatElement var1) {
      return this.from(this.v.addElement(var1.getVertexFormatElement()));
   }

   public boolean method_24747() {
      return this.v.hasColor();
   }

   public int method_24742() {
      return this.v.getElementCount();
   }

   public VertexFormat getVertexFormat() {
      return this.v;
   }

   public boolean hasUv(int var1) {
      return this.v.hasUvOffset(var1);
   }

   @Override
   public boolean equals(Object var1) {
      return this.v.equals(var1);
   }

   public int method_24748() {
      return this.v.getIntegerSize();
   }

   @Override
   public String toString() {
      return this.v.toString();
   }

   public RealmsVertexFormat from(VertexFormat var1) {
      this.v = var1;
      return this;
   }
}
