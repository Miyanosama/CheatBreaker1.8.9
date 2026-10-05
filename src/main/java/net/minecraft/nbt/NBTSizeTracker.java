package net.minecraft.nbt;

public class NBTSizeTracker {
   public long read;
   public long max;
   public static NBTSizeTracker INFINITE = new NBTSizeTracker(0L) {
      @Override
      public void read(long var1) {
      }
   };

   public void read(long var1) {
      this.read += var1 / 8L;
      if (this.read > this.max) {
         throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.read + "bytes where max allowed: " + this.max);
      }
   }

   public NBTSizeTracker(long var1) {
      this.max = var1;
   }
}
