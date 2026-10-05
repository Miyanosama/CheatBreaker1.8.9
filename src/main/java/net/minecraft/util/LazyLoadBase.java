package net.minecraft.util;

public abstract class LazyLoadBase<T> {
   public T value;
   public boolean isLoaded = false;

   public abstract T load();

   public T getValue() {
      if (!this.isLoaded) {
         this.isLoaded = true;
         this.value = this.load();
      }

      return this.value;
   }
}
