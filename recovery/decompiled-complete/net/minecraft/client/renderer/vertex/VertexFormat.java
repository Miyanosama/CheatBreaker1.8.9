package net.minecraft.client.renderer.vertex;

import com.google.common.collect.Lists;
import io.netty.channel.epoll.Epoll;
import java.util.List;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VertexFormat {
   public CategoryNodeEditor$5 field_0004;
   public int colorElementOffset;
   public List<Integer> uvOffsetsById;
   public static Logger LOGGER = LogManager.getLogger();
   public Epoll field_0000;
   public int nextOffset;
   public List<Integer> offsets;
   public int normalElementOffset;
   public List<VertexFormatElement> elements = Lists.newArrayList();

   public VertexFormat() {
      this.offsets = Lists.newArrayList();
      this.nextOffset = 0;
      this.colorElementOffset = -1;
      this.uvOffsetsById = Lists.newArrayList();
      this.normalElementOffset = -1;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         VertexFormat var2 = (VertexFormat)var1;
         return this.nextOffset != var2.nextOffset ? false : (!this.elements.equals(var2.elements) ? false : this.offsets.equals(var2.offsets));
      } else {
         return false;
      }
   }

   public VertexFormatElement getElement(int var1) {
      return this.elements.get(var1);
   }

   public VertexFormat(VertexFormat var1) {
      this();

      for (int var2 = 0; var2 < var1.getElementCount(); var2++) {
         this.addElement(var1.getElement(var2));
      }

      this.nextOffset = var1.getNextOffset();
   }

   public int getUvOffsetById(int var1) {
      return this.uvOffsetsById.get(var1);
   }

   @Override
   public int hashCode() {
      int var1 = this.elements.hashCode();
      var1 = 31 * var1 + this.offsets.hashCode();
      return 31 * var1 + this.nextOffset;
   }

   public int getNextOffset() {
      return this.nextOffset;
   }

   public int getOffset(int var1) {
      return this.offsets.get(var1);
   }

   public int getIntegerSize() {
      return this.getNextOffset() / 4;
   }

   public boolean hasUvOffset(int var1) {
      return this.uvOffsetsById.size() - 1 >= var1;
   }

   public boolean hasPosition() {
      int var1 = 0;

      for (int var2 = this.elements.size(); var1 < var2; var1++) {
         VertexFormatElement var3 = this.elements.get(var1);
         if (var3.isPositionElement()) {
            return true;
         }
      }

      return false;
   }

   public void clear() {
      this.elements.clear();
      this.offsets.clear();
      this.colorElementOffset = -1;
      this.uvOffsetsById.clear();
      this.normalElementOffset = -1;
      this.nextOffset = 0;
   }

   public boolean hasNormal() {
      return this.normalElementOffset >= 0;
   }

   public int getColorOffset() {
      return this.colorElementOffset;
   }

   public int getNormalOffset() {
      return this.normalElementOffset;
   }

   public int getElementCount() {
      return this.elements.size();
   }

   public List<VertexFormatElement> getElements() {
      return this.elements;
   }

   public VertexFormat addElement(VertexFormatElement var1) {
      if (var1.isPositionElement() && this.hasPosition()) {
         LOGGER.warn("VertexFormat error: Trying to add a position VertexFormatElement when one already exists, ignoring.");
         return this;
      } else {
         this.elements.add(var1);
         this.offsets.add(this.nextOffset);
         switch (VertexFormat$1.field_0000[var1.getUsage().ordinal()]) {
            case 1:
               this.normalElementOffset = this.nextOffset;
               break;
            case 2:
               this.colorElementOffset = this.nextOffset;
               break;
            case 3:
               this.uvOffsetsById.add(var1.getIndex(), this.nextOffset);
         }

         this.nextOffset = this.nextOffset + var1.getSize();
         return this;
      }
   }

   public boolean hasColor() {
      return this.colorElementOffset >= 0;
   }

   @Override
   public String toString() {
      String var1 = "format: " + this.elements.size() + " elements: ";

      for (int var2 = 0; var2 < this.elements.size(); var2++) {
         var1 = var1 + this.elements.get(var2).toString();
         if (var2 != this.elements.size() - 1) {
            var1 = var1 + " ";
         }
      }

      return var1;
   }
}
