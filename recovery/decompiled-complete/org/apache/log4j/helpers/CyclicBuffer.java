package org.apache.log4j.helpers;

import net.minecraft.client.resources.model.ModelBakery$2;
import net.minecraft.tileentity.TileEntityDropper;
import net.minecraft.util.IntHashMap$Entry;
import org.apache.log4j.spi.LoggingEvent;

public class CyclicBuffer {
   public IntHashMap$Entry field_0003;
   public int first;
   public int last;
   public TileEntityDropper field_0005;
   public int maxSize;
   public int numElems;
   public ModelBakery$2 field_0007;
   public LoggingEvent[] ea;

   public void resize(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Negative array size [" + var1 + "] not allowed.");
      } else if (var1 != this.numElems) {
         LoggingEvent[] var2 = new LoggingEvent[var1];
         int var3 = var1 < this.numElems ? var1 : this.numElems;

         for (int var4 = 0; var4 < var3; var4++) {
            var2[var4] = this.ea[this.first];
            this.ea[this.first] = null;
            if (++this.first == this.numElems) {
               this.first = 0;
            }
         }

         this.ea = var2;
         this.first = 0;
         this.numElems = var3;
         this.maxSize = var1;
         if (var3 == var1) {
            this.last = 0;
         } else {
            this.last = var3;
         }
      }
   }

   public int length() {
      return this.numElems;
   }

   public LoggingEvent get() {
      LoggingEvent var1 = null;
      if (this.numElems > 0) {
         this.numElems--;
         var1 = this.ea[this.first];
         this.ea[this.first] = null;
         if (++this.first == this.maxSize) {
            this.first = 0;
         }
      }

      return var1;
   }

   public CyclicBuffer(int var1) {
      if (var1 < 1) {
         throw new IllegalArgumentException("The maxSize argument (" + var1 + ") is not a positive integer.");
      } else {
         this.maxSize = var1;
         this.ea = new LoggingEvent[var1];
         this.first = 0;
         this.last = 0;
         this.numElems = 0;
      }
   }

   public void add(LoggingEvent var1) {
      this.ea[this.last] = var1;
      if (++this.last == this.maxSize) {
         this.last = 0;
      }

      if (this.numElems < this.maxSize) {
         this.numElems++;
      } else if (++this.first == this.maxSize) {
         this.first = 0;
      }
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public LoggingEvent get(int var1) {
      return var1 >= 0 && var1 < this.numElems ? this.ea[(this.first + var1) % this.maxSize] : null;
   }
}
