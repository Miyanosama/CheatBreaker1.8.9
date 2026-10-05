package net.optifine.util;

public class CounterInt {
   public int value;
   public int startValue;

   public CounterInt(int var1) {
      this.startValue = var1;
      this.value = var1;
   }

   public synchronized int nextValue() {
      return this.value++;
   }

   public synchronized void reset() {
      this.value = this.startValue;
   }

   public int getValue() {
      return this.value;
   }
}
