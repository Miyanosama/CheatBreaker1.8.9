package org.apache.log4j.helpers;

import org.apache.log4j.spi.LoggingEvent;

public class BoundedFIFO {
   public LoggingEvent[] buf;
   public int first;
   public int next;
   public int numElements = 0;
   public int maxSize;

   public int min(int var1, int var2) {
      return var1 < var2 ? var1 : var2;
   }

   public int length() {
      return this.numElements;
   }

   public boolean isFull() {
      return this.numElements == this.maxSize;
   }

   public boolean wasFull() {
      return this.numElements + 1 == this.maxSize;
   }

   public LoggingEvent get() {
      if (this.numElements == 0) {
         return null;
      } else {
         LoggingEvent var1 = this.buf[this.first];
         this.buf[this.first] = null;
         if (++this.first == this.maxSize) {
            this.first = 0;
         }

         this.numElements--;
         return var1;
      }
   }

   public BoundedFIFO(int var1) {
      this.first = 0;
      this.next = 0;
      if (var1 < 1) {
         throw new IllegalArgumentException("The maxSize argument (" + var1 + ") is not a positive integer.");
      } else {
         this.maxSize = var1;
         this.buf = new LoggingEvent[var1];
      }
   }

   public void put(LoggingEvent var1) {
      if (this.numElements != this.maxSize) {
         this.buf[this.next] = var1;
         if (++this.next == this.maxSize) {
            this.next = 0;
         }

         this.numElements++;
      }
   }

   public int getMaxSize() {
      return this.maxSize;
   }

   public boolean wasEmpty() {
      return this.numElements == 1;
   }

   public synchronized void resize(int var1) {
      if (var1 != this.maxSize) {
         LoggingEvent[] var2 = new LoggingEvent[var1];
         int var3 = this.maxSize - this.first;
         var3 = this.min(var3, var1);
         var3 = this.min(var3, this.numElements);
         System.arraycopy(this.buf, this.first, var2, 0, var3);
         int var4 = 0;
         if (var3 < this.numElements && var3 < var1) {
            int var7 = this.numElements - var3;
            var4 = this.min(var7, var1 - var3);
            System.arraycopy(this.buf, 0, var2, var3, var4);
         }

         this.buf = var2;
         this.maxSize = var1;
         this.first = 0;
         this.numElements = var3 + var4;
         this.next = this.numElements;
         if (this.next == this.maxSize) {
            this.next = 0;
         }
      }
   }
}
