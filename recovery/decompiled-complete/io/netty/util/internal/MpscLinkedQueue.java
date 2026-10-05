package io.netty.util.internal;

import com.cheatbreaker.client.module.type.TNTTimerModule;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MpscLinkedQueue<E> extends MpscLinkedQueueTailRef<E> implements Queue<E> {
   public static long serialVersionUID;
   public long p36;
   public TNTTimerModule __junk4260583567284831613;
   public long p02;
   public long p32;
   public long p06;
   public long p01;
   public long p34;
   public long p00;
   public long p37;
   public long p35;
   public long p33;
   public long p31;
   public long p05;
   public long p07;
   public long p30;
   public long p04;
   public long p03;

   public MpscLinkedQueueNode<E> peekNode() {
      MpscLinkedQueueNode var1;
      do {
         var1 = this.headRef();
         MpscLinkedQueueNode var2 = var1.next();
         if (var2 != null) {
            return var2;
         }
      } while (var1 != this.tailRef());

      return null;
   }

   @Override
   public boolean contains(Object var1) {
      for (MpscLinkedQueueNode var2 = this.peekNode(); var2 != null; var2 = var2.next()) {
         if (var2.value() == var1) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean isEmpty() {
      return this.peekNode() == null;
   }

   @Override
   public int size() {
      int var1 = 0;

      for (MpscLinkedQueueNode var2 = this.peekNode(); var2 != null; var2 = var2.next()) {
         var1++;
      }

      return var1;
   }

   @Override
   public void clear() {
      while (this.poll() != null) {
      }
   }

   @Override
   public boolean offer(E var1) {
      if (var1 == null) {
         throw new NullPointerException("value");
      } else {
         Object var2;
         if (var1 instanceof MpscLinkedQueueNode) {
            var2 = (MpscLinkedQueueNode)var1;
            ((MpscLinkedQueueNode)var2).setNext(null);
         } else {
            var2 = new MpscLinkedQueue$DefaultNode<>(var1);
         }

         MpscLinkedQueueNode var3 = this.getAndSetTailRef((MpscLinkedQueueNode<E>)var2);
         var3.setNext((MpscLinkedQueueNode)var2);
         return true;
      }
   }

   @Override
   public boolean addAll(Collection<? extends E> var1) {
      if (var1 == null) {
         throw new NullPointerException("c");
      } else if (var1 == this) {
         throw new IllegalArgumentException("c == this");
      } else {
         boolean var2 = false;

         for (Object var4 : var1) {
            this.add((E)var4);
            var2 = true;
         }

         return var2;
      }
   }

   @Override
   public E element() {
      Object var1 = this.peek();
      if (var1 != null) {
         return (E)var1;
      } else {
         throw new NoSuchElementException();
      }
   }

   public void readObject(ObjectInputStream var1) {
      var1.defaultReadObject();
      MpscLinkedQueue$DefaultNode var2 = new MpscLinkedQueue$DefaultNode(null);
      this.setHeadRef(var2);
      this.setTailRef(var2);

      while (true) {
         Object var3 = var1.readObject();
         if (var3 == null) {
            return;
         }

         this.add((E)var3);
      }
   }

   @Override
   public boolean removeAll(Collection<?> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Object[] toArray() {
      Object[] var1 = new Object[this.size()];
      Iterator var2 = this.iterator();

      for (int var3 = 0; var3 < var1.length; var3++) {
         if (!var2.hasNext()) {
            return Arrays.copyOf(var1, var3);
         }

         var1[var3] = var2.next();
      }

      return var1;
   }

   @Override
   public E poll() {
      MpscLinkedQueueNode var1 = this.peekNode();
      if (var1 == null) {
         return null;
      } else {
         MpscLinkedQueueNode var2 = this.headRef();
         this.lazySetHeadRef(var1);
         var2.unlink();
         return (E)var1.clearMaybe();
      }
   }

   @Override
   public boolean remove(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public E peek() {
      MpscLinkedQueueNode var1 = this.peekNode();
      return (E)(var1 == null ? null : var1.value());
   }

   @Override
   public Iterator<E> iterator() {
      return new MpscLinkedQueue$1(this);
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      int var2 = this.size();
      Object[] var3;
      if (var1.length >= var2) {
         var3 = var1;
      } else {
         var3 = (Object[])Array.newInstance(var1.getClass().getComponentType(), var2);
      }

      Iterator var4 = this.iterator();

      for (int var5 = 0; var5 < var3.length; var5++) {
         if (!var4.hasNext()) {
            if (var1 == var3) {
               var3[var5] = null;
               return (T[])var3;
            }

            if (var1.length < var5) {
               return (T[])Arrays.copyOf(var3, var5);
            }

            System.arraycopy(var3, 0, var1, 0, var5);
            if (var1.length > var5) {
               var1[var5] = null;
            }

            return (T[])var1;
         }

         var3[var5] = var4.next();
      }

      return (T[])var3;
   }

   @Override
   public E remove() {
      Object var1 = this.poll();
      if (var1 != null) {
         return (E)var1;
      } else {
         throw new NoSuchElementException();
      }
   }

   @Override
   public boolean add(E var1) {
      if (this.offer((E)var1)) {
         return true;
      } else {
         throw new IllegalStateException("queue full");
      }
   }

   public void writeObject(ObjectOutputStream var1) {
      var1.defaultWriteObject();

      for (Object var3 : this) {
         var1.writeObject(var3);
      }

      var1.writeObject(null);
   }

   @Override
   public boolean retainAll(Collection<?> var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean containsAll(Collection<?> var1) {
      for (Object var3 : var1) {
         if (!this.contains(var3)) {
            return false;
         }
      }

      return true;
   }

   public MpscLinkedQueue() {
      MpscLinkedQueue$DefaultNode var1 = new MpscLinkedQueue$DefaultNode(null);
      this.setHeadRef(var1);
      this.setTailRef(var1);
   }
}
