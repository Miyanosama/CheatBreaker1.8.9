package io.netty.util.internal;

import com.cheatbreaker.client.module.staff.TrimpModule;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import net.minecraft.client.main.llIlllIIIIlIlIlllIllIllIl;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.gen.structure.StructureVillagePieces$Path;
import sun.misc.Unsafe;

public class UnsafeAtomicIntegerFieldUpdater<T> extends AtomicIntegerFieldUpdater<T> {
   public llIlllIIIIlIlIlllIllIllIl __junk7386232538336182498;
   public long offset;
   public StructureVillagePieces$Path __junk1439927067305081178;
   public TrimpModule __junk6777243491457976593;
   public Unsafe unsafe;
   public EntityHorse __junk235545387863389079;

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

   public UnsafeAtomicIntegerFieldUpdater(Unsafe var1, Class<?> var2, String var3) {
      Field var4 = var2.getDeclaredField(var3);
      if (!Modifier.isVolatile(var4.getModifiers())) {
         throw new IllegalArgumentException("Must be volatile");
      } else {
         this.unsafe = var1;
         this.offset = var1.objectFieldOffset(var4);
      }
   }
}
