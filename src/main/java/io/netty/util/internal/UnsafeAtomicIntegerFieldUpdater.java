package io.netty.util.internal;

import com.cheatbreaker.client.module.staff.TrimpModule;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.minecraft.client.main.GameConfiguration$DisplayInformation;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import sun.misc.Unsafe;

public class UnsafeAtomicIntegerFieldUpdater<T> extends AtomicIntegerFieldUpdater<T> {
   public long offset;
   public Unsafe unsafe;

   @Override
   public void set(T var1, int var2) {
      this.unsafe.putIntVolatile(var1, this.offset, var2);
   }

   @Override
   public boolean weakCompareAndSet(T var1, int var2, int var3) {
      return this.unsafe.compareAndSwapInt(var1, this.offset, var2, var3);
   }

   @Override
   public boolean compareAndSet(T var1, int var2, int var3) {
      return this.unsafe.compareAndSwapInt(var1, this.offset, var2, var3);
   }

   @Override
   public void lazySet(T var1, int var2) {
      this.unsafe.putOrderedInt(var1, this.offset, var2);
   }

   @Override
   public int get(T var1) {
      return this.unsafe.getIntVolatile(var1, this.offset);
   }

   public UnsafeAtomicIntegerFieldUpdater(Unsafe var1, Class<?> var2, String var3) throws java.lang.NoSuchFieldException {
      Field var4 = var2.getDeclaredField(var3);
      if (!Modifier.isVolatile(var4.getModifiers())) {
         throw new IllegalArgumentException("Must be volatile");
      } else {
         this.unsafe = var1;
         this.offset = var1.objectFieldOffset(var4);
      }
   }
}
