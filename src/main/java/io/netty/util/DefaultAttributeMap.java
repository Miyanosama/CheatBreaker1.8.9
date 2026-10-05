package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import net.minecraft.world.border.EnumBorderStatus;

public class DefaultAttributeMap implements AttributeMap {
   public static final int BUCKET_SIZE = 4;
   public static AtomicReferenceFieldUpdater<DefaultAttributeMap, AtomicReferenceArray> updater;
   public static final int MASK = 3;
   public volatile AtomicReferenceArray<DefaultAttributeMap.DefaultAttribute<?>> attributes;

   static {
      AtomicReferenceFieldUpdater var0 = PlatformDependent.newAtomicReferenceFieldUpdater(DefaultAttributeMap.class, "attributes");
      if (var0 == null) {
         var0 = AtomicReferenceFieldUpdater.newUpdater(DefaultAttributeMap.class, AtomicReferenceArray.class, "attributes");
      }

      updater = var0;
   }

   public static int index(AttributeKey<?> var0) {
      return var0.id() & 3;
   }

   @Override
   public <T> Attribute<T> attr(AttributeKey<T> var1) {
      if (var1 == null) {
         throw new NullPointerException("key");
      } else {
         AtomicReferenceArray var2 = this.attributes;
         if (var2 == null) {
            var2 = new AtomicReferenceArray(4);
            if (!updater.compareAndSet(this, null, var2)) {
               var2 = this.attributes;
            }
         }

         int var3 = index(var1);
         DefaultAttributeMap.DefaultAttribute var4 = (DefaultAttributeMap.DefaultAttribute)var2.get(var3);
         if (var4 == null) {
            var4 = new DefaultAttributeMap.DefaultAttribute(var1);
            if (var2.compareAndSet(var3, null, var4)) {
               return var4;
            }

            var4 = (DefaultAttributeMap.DefaultAttribute)var2.get(var3);
         }

         synchronized (var4) {
            DefaultAttributeMap.DefaultAttribute var6 = var4;

            while (var6.removed || var6.key != var1) {
               DefaultAttributeMap.DefaultAttribute var7 = var6.next;
               if (var7 == null) {
                  DefaultAttributeMap.DefaultAttribute var8 = new DefaultAttributeMap.DefaultAttribute(var4, var1);
                  var6.next = var8;
                  var8.prev = var6;
                  return var8;
               }

               var6 = var7;
            }

            return var6;
         }
      }
   }

   public static final class DefaultAttribute<T> extends AtomicReference<T> implements Attribute<T> {
      public DefaultAttributeMap.DefaultAttribute<?> head;
      public AttributeKey<T> key;
      public static final long serialVersionUID = -2661411462200283011L;
      public DefaultAttributeMap.DefaultAttribute<?> prev;
      public DefaultAttributeMap.DefaultAttribute<?> next;
      public volatile boolean removed;

      @Override
      public void remove() {
         this.removed = true;
         this.set(null);
         this.remove0();
      }

      @Override
      public T getAndRemove() {
         this.removed = true;
         Object var1 = this.getAndSet(null);
         this.remove0();
         return (T)var1;
      }

      @Override
      public AttributeKey<T> key() {
         return this.key;
      }

      public DefaultAttribute(AttributeKey<T> var1) {
         this.head = this;
         this.key = var1;
      }

      @Override
      public T setIfAbsent(T var1) {
         while (!this.compareAndSet(null, (T)var1)) {
            Object var2 = this.get();
            if (var2 != null) {
               return (T)var2;
            }
         }

         return null;
      }

      public void remove0() {
         synchronized (this.head) {
            if (this.prev != null) {
               this.prev.next = this.next;
               if (this.next != null) {
                  this.next.prev = this.prev;
               }
            }
         }
      }

      public DefaultAttribute(DefaultAttributeMap.DefaultAttribute<?> var1, AttributeKey<T> var2) {
         this.head = var1;
         this.key = var2;
      }
   }
}
