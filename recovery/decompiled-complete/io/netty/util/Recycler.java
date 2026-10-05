package io.netty.util;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import junit.swingui.TestSelector$KeySelectListener;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.entity.Entity$4;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenDesert;
import net.optifine.entity.model.anim.RenderResolverEntity;

public abstract class Recycler<T> {
   public static FastThreadLocal<Map<Recycler$Stack<?>, Recycler$WeakOrderQueue>> DELAYED_RECYCLED;
   public BlockButtonStone __junk1626074836016357966;
   public static int OWN_THREAD_ID = Recycler.ID_GENERATOR.getAndIncrement();
   public int maxCapacity;
   public FastThreadLocal<Recycler$Stack<T>> threadLocal = new Recycler$1(this);
   public static AtomicInteger ID_GENERATOR = new AtomicInteger(Integer.MIN_VALUE);
   public static int DEFAULT_MAX_CAPACITY;
   public BlockPos __junk7203269166424355917;
   public Entity$4 __junk3576212282878390611;
   public RenderResolverEntity __junk2498771055620660196;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(Recycler.class);
   public TestSelector$KeySelectListener __junk3636976543622431560;
   public static int INITIAL_CAPACITY;
   public BlockBookshelf __junk5678931361478257307;
   public BiomeGenDesert __junk2802347354323957321;

   public abstract T newObject(Recycler$Handle var1);

   public Recycler() {
      this(DEFAULT_MAX_CAPACITY);
   }

   public boolean recycle(T var1, Recycler$Handle var2) {
      Recycler$DefaultHandle var3 = (Recycler$DefaultHandle)var2;
      if (Recycler$DefaultHandle.access$200(var3).parent != this) {
         return false;
      } else if (var1 != Recycler$DefaultHandle.access$100(var3)) {
         throw new IllegalArgumentException("o does not belong to handle");
      } else {
         var3.recycle();
         return true;
      }
   }

   public T get() {
      Recycler$Stack var1 = this.threadLocal.get();
      Recycler$DefaultHandle var2 = var1.pop();
      if (var2 == null) {
         var2 = var1.newHandle();
         Recycler$DefaultHandle.access$102(var2, this.newObject(var2));
      }

      return (T)Recycler$DefaultHandle.access$100(var2);
   }

   static {
      int var0 = SystemPropertyUtil.getInt("io.netty.recycler.maxCapacity.default", 0);
      if (var0 <= 0) {
         var0 = 262144;
      }

      DEFAULT_MAX_CAPACITY = var0;
      if (logger.isDebugEnabled()) {
         logger.debug("-Dio.netty.recycler.maxCapacity.default: {}", DEFAULT_MAX_CAPACITY);
      }

      INITIAL_CAPACITY = Math.min(DEFAULT_MAX_CAPACITY, 256);
      DELAYED_RECYCLED = new Recycler$2();
   }

   public Recycler(int var1) {
      this.maxCapacity = Math.max(0, var1);
   }
}
