package io.netty.util;

import com.cheatbreaker.client.ui.overlay.element.DraggableElement;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.UnsafeAtomicReferenceFieldUpdater;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import junit.swingui.TestSelector;
import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.BlockButtonStone;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEndermite;
import net.minecraft.client.renderer.entity.RenderRabbit;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.optifine.entity.model.anim.RenderResolverEntity;
import net.optifine.reflect.ReflectorField;
import org.apache.log4j.helpers.QuietWriter;
import org.newsclub.net.unix.AFUNIXSocketImpl;
import net.minecraft.util.Cartesian;
import net.minecraft.world.gen.layer.GenLayer$1;
import com.cheatbreaker.client.util.render.PerspectiveController;

public abstract class Recycler<T> {
   public static FastThreadLocal<Map<Recycler.Stack<?>, Recycler.WeakOrderQueue>> DELAYED_RECYCLED;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(Recycler.class);
   public int maxCapacity;
   public FastThreadLocal<Recycler.Stack<T>> threadLocal = new FastThreadLocal<Recycler.Stack<T>>() {

      public Recycler.Stack<T> initialValue() {
         return new Recycler.Stack<>(Recycler.this, Thread.currentThread(), Recycler.this.maxCapacity);
      }
   };
   public static AtomicInteger ID_GENERATOR = new AtomicInteger(Integer.MIN_VALUE);
   public static int DEFAULT_MAX_CAPACITY;
   public static int OWN_THREAD_ID = Recycler.ID_GENERATOR.getAndIncrement();
   public static int INITIAL_CAPACITY;

   public abstract T newObject(Recycler.Handle var1);

   public Recycler() {
      this(DEFAULT_MAX_CAPACITY);
   }

   public boolean recycle(T var1, Recycler.Handle var2) {
      Recycler.DefaultHandle var3 = (Recycler.DefaultHandle)var2;
      if (var3.stack.parent != this) {
         return false;
      } else if (var1 != var3.value) {
         throw new IllegalArgumentException("o does not belong to handle");
      } else {
         var3.recycle();
         return true;
      }
   }

   public T get() {
      Recycler.Stack var1 = this.threadLocal.get();
      Recycler.DefaultHandle var2 = var1.pop();
      if (var2 == null) {
         var2 = var1.newHandle();
         var2.value = this.newObject(var2);
      }

      return (T)var2.value;
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
      DELAYED_RECYCLED = new FastThreadLocal<Map<Recycler.Stack<?>, Recycler.WeakOrderQueue>>() {

         public Map<Recycler.Stack<?>, Recycler.WeakOrderQueue> initialValue() {
            return new WeakHashMap<>();
         }
      };
   }

   public Recycler(int var1) {
      this.maxCapacity = Math.max(0, var1);
   }

   public static final class DefaultHandle implements Recycler.Handle {
      public Object value;
      public Recycler.Stack<?> stack;
      public int recycleId;
      public int lastRecycledId;

      public void recycle() {
         Thread var1 = Thread.currentThread();
         if (var1 == this.stack.thread) {
            this.stack.push(this);
         } else {
            Map var2 = Recycler.DELAYED_RECYCLED.get();
            Recycler.WeakOrderQueue var3 = (Recycler.WeakOrderQueue)var2.get(this.stack);
            if (var3 == null) {
               var2.put(this.stack, var3 = new Recycler.WeakOrderQueue(this.stack, var1));
            }

            var3.add(this);
         }
      }

      public DefaultHandle(Recycler.Stack<?> var1) {
         this.stack = var1;
      }
   }

   public interface Handle {
   }

   public static final class Stack<T> {
      public Recycler<T> parent;
      public Recycler.WeakOrderQueue cursor;
      public int maxCapacity;
      public Thread thread;
      public Recycler.WeakOrderQueue prev;
      public int size;
      public volatile Recycler.WeakOrderQueue head;
      public Recycler.DefaultHandle[] elements;

      public Recycler.DefaultHandle newHandle() {
         return new Recycler.DefaultHandle(this);
      }

      public boolean scavenge() {
         if (this.scavengeSome()) {
            return true;
         } else {
            this.prev = null;
            this.cursor = this.head;
            return false;
         }
      }

      public void push(Recycler.DefaultHandle var1) {
         if ((var1.recycleId | var1.lastRecycledId) != 0) {
            throw new IllegalStateException("recycled already");
         } else {
            var1.recycleId = var1.lastRecycledId = Recycler.OWN_THREAD_ID;
            int var2 = this.size;
            if (var2 == this.elements.length) {
               if (var2 == this.maxCapacity) {
                  return;
               }

               this.elements = Arrays.copyOf(this.elements, var2 << 1);
            }

            this.elements[var2] = var1;
            this.size = var2 + 1;
         }
      }

      public Stack(Recycler<T> var1, Thread var2, int var3) {
         this.parent = var1;
         this.thread = var2;
         this.maxCapacity = var3;
         this.elements = new Recycler.DefaultHandle[Recycler.INITIAL_CAPACITY];
      }

      public Recycler.DefaultHandle pop() {
         int var1 = this.size;
         if (var1 == 0) {
            if (!this.scavenge()) {
               return null;
            }

            var1 = this.size;
         }

         Recycler.DefaultHandle var2 = this.elements[--var1];
         if (var2.lastRecycledId != var2.recycleId) {
            throw new IllegalStateException("recycled multiple times");
         } else {
            var2.recycleId = 0;
            var2.lastRecycledId = 0;
            this.size = var1;
            return var2;
         }
      }

      public boolean scavengeSome() {
         boolean var1 = false;
         Recycler.WeakOrderQueue var2 = this.cursor;
         Recycler.WeakOrderQueue var3 = this.prev;

         while (var2 != null) {
            if (var2.transfer(this)) {
               var1 = true;
               break;
            }

            Recycler.WeakOrderQueue var4 = var2.next;
            if (var2.owner.get() == null) {
               if (var2.hasFinalData()) {
                  while (var2.transfer(this)) {
                  }
               }

               if (var3 != null) {
                  var3.next = var4;
               }
            } else {
               var3 = var2;
            }

            var2 = var4;
         }

         this.prev = var3;
         this.cursor = var2;
         return var1;
      }
   }

   public static final class WeakOrderQueue {
      public WeakReference<Thread> owner;
      public Recycler.WeakOrderQueue next;
      public int id = Recycler.ID_GENERATOR.getAndIncrement();
      public static final int LINK_CAPACITY = 16;
      public Recycler.WeakOrderQueue.Link tail;
      public Recycler.WeakOrderQueue.Link head;

      public boolean hasFinalData() {
         return this.tail.readIndex != this.tail.get();
      }

      public boolean transfer(Recycler.Stack<?> var1) {
         Recycler.WeakOrderQueue.Link var2 = this.head;
         if (var2 == null) {
            return false;
         } else {
            if (var2.readIndex == 16) {
               if (var2.next == null) {
                  return false;
               }

               this.head = var2 = var2.next;
            }

            int var3 = var2.readIndex;
            int var4 = var2.get();
            if (var3 == var4) {
               return false;
            } else {
               int var5 = var4 - var3;
               if (var1.size + var5 > var1.elements.length) {
                  var1.elements = Arrays.copyOf(var1.elements, (var1.size + var5) * 2);
               }

               Recycler.DefaultHandle[] var6 = var2.elements;
               Recycler.DefaultHandle[] var7 = var1.elements;

               int var8;
               for (var8 = var1.size; var3 < var4; var6[var3++] = null) {
                  Recycler.DefaultHandle var9 = var6[var3];
                  if (var9.recycleId == 0) {
                     var9.recycleId = var9.lastRecycledId;
                  } else if (var9.recycleId != var9.lastRecycledId) {
                     throw new IllegalStateException("recycled already");
                  }

                  var9.stack = var1;
                  var7[var8++] = var9;
               }

               var1.size = var8;
               if (var4 == 16 && var2.next != null) {
                  this.head = var2.next;
               }

               var2.readIndex = var4;
               return true;
            }
         }
      }

      public void add(Recycler.DefaultHandle var1) {
         var1.lastRecycledId = this.id;
         Recycler.WeakOrderQueue.Link var2 = this.tail;
         int var3;
         if ((var3 = var2.get()) == 16) {
            this.tail = var2 = var2.next = new Recycler.WeakOrderQueue.Link();
            var3 = var2.get();
         }

         var2.elements[var3] = var1;
         var1.stack = null;
         var2.lazySet(var3 + 1);
      }

      public WeakOrderQueue(Recycler.Stack<?> var1, Thread var2) {
         this.head = this.tail = new Recycler.WeakOrderQueue.Link();
         this.owner = new WeakReference<>(var2);
         synchronized (var1) {
            this.next = var1.head;
            var1.head = this;
         }
      }

      public static final class Link extends AtomicInteger {
         public int readIndex;
         public Recycler.WeakOrderQueue.Link next;
         public Recycler.DefaultHandle[] elements = new Recycler.DefaultHandle[16];

         public Link() {
         }
      }
   }
}
