package io.netty.util;

import java.util.concurrent.atomic.AtomicReference;

public class DefaultAttributeMap$DefaultAttribute<T> extends AtomicReference<T> implements Attribute<T> {
   public DefaultAttributeMap$DefaultAttribute<?> head;
   public AttributeKey<T> key;
   public static long serialVersionUID;
   public DefaultAttributeMap$DefaultAttribute<?> prev;
   public DefaultAttributeMap$DefaultAttribute<?> next;
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

   public DefaultAttributeMap$DefaultAttribute(AttributeKey<T> var1) {
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

   public DefaultAttributeMap$DefaultAttribute(DefaultAttributeMap$DefaultAttribute<?> var1, AttributeKey<T> var2) {
      this.head = var1;
      this.key = var2;
   }
}
