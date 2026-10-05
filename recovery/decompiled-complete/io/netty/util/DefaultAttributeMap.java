package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import net.minecraft.world.border.EnumBorderStatus;

public class DefaultAttributeMap implements AttributeMap {
   public static int BUCKET_SIZE;
   public EnumBorderStatus __junk761179949997150281;
   public static AtomicReferenceFieldUpdater<DefaultAttributeMap, AtomicReferenceArray> updater;
   public static int MASK;
   public volatile AtomicReferenceArray<DefaultAttributeMap$DefaultAttribute<?>> attributes;

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
         DefaultAttributeMap$DefaultAttribute var4 = (DefaultAttributeMap$DefaultAttribute)var2.get(var3);
         if (var4 == null) {
            var4 = new DefaultAttributeMap$DefaultAttribute(var1);
            if (var2.compareAndSet(var3, null, var4)) {
               return var4;
            }

            var4 = (DefaultAttributeMap$DefaultAttribute)var2.get(var3);
         }

         synchronized (var4) {
            DefaultAttributeMap$DefaultAttribute var6 = var4;

            while (DefaultAttributeMap$DefaultAttribute.access$000(var6) || DefaultAttributeMap$DefaultAttribute.access$100(var6) != var1) {
               DefaultAttributeMap$DefaultAttribute var7 = DefaultAttributeMap$DefaultAttribute.access$200(var6);
               if (var7 == null) {
                  DefaultAttributeMap$DefaultAttribute var8 = new DefaultAttributeMap$DefaultAttribute(var4, var1);
                  DefaultAttributeMap$DefaultAttribute.access$202(var6, var8);
                  DefaultAttributeMap$DefaultAttribute.access$302(var8, var6);
                  return var8;
               }

               var6 = var7;
            }

            return var6;
         }
      }
   }
}
