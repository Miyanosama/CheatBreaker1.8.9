package io.netty.util.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import sun.misc.Unsafe;

public class UnsafeAtomicReferenceFieldUpdater<U, M> extends AtomicReferenceFieldUpdater<U, M> {
   public Unsafe unsafe;
   public long offset;

   @Override
   public boolean compareAndSet(U var1, M var2, M var3) {
      return this.unsafe.compareAndSwapObject(var1, this.offset, var2, var3);
   }

   public UnsafeAtomicReferenceFieldUpdater(Unsafe var1, Class<U> var2, String var3) throws java.lang.NoSuchFieldException {
      Field var4 = var2.getDeclaredField(var3);
      if (!Modifier.isVolatile(var4.getModifiers())) {
         throw new IllegalArgumentException("Must be volatile");
      } else {
         this.unsafe = var1;
         this.offset = var1.objectFieldOffset(var4);
      }
   }

   @Override
   public M get(U var1) {
      return (M)this.unsafe.getObjectVolatile(var1, this.offset);
   }

   @Override
   public boolean weakCompareAndSet(U var1, M var2, M var3) {
      return this.unsafe.compareAndSwapObject(var1, this.offset, var2, var3);
   }

   @Override
   public void lazySet(U var1, M var2) {
      this.unsafe.putOrderedObject(var1, this.offset, var2);
   }

   @Override
   public void set(U var1, M var2) {
      this.unsafe.putObjectVolatile(var1, this.offset, var2);
   }
}
