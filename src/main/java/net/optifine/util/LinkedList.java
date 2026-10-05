package net.optifine.util;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<LinkedList.Node<T>> {
   public LinkedList.Node<T> first;
   public LinkedList.Node<T> last;
   public int size;

   public void addFirst(LinkedList.Node<T> var1) {
      this.checkNoParent(var1);
      if (this.isEmpty()) {
         this.first = var1;
         this.last = var1;
      } else {
         LinkedList.Node var2 = this.first;
         var1.method_02468(var2);
         var2.method_02460(var1);
         this.first = var1;
      }

      var1.setParent(this);
      this.size++;
   }

   public LinkedList.Node<T> getFirst() {
      return this.first;
   }

   public boolean find(LinkedList.Node<T> var1, LinkedList.Node<T> var2, LinkedList.Node<T> var3) {
      this.checkParent(var2);
      if (var3 != null) {
         this.checkParent(var3);
      }

      LinkedList.Node var4;
      for (var4 = var2; var4 != null && var4 != var3; var4 = var4.getNext()) {
         if (var4 == var1) {
            return true;
         }
      }

      if (var4 != var3) {
         throw new IllegalArgumentException("Sublist is not linked, from: " + var2 + ", to: " + var3);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      StringBuffer var1 = new StringBuffer();

      for (LinkedList.Node var3 : this) {
         if (var1.length() > 0) {
            var1.append(", ");
         }

         var1.append(var3.getItem());
      }

      return "" + this.size + " [" + var1.toString() + "]";
   }

   public boolean contains(LinkedList.Node<T> var1) {
      return var1.parent == this;
   }

   public LinkedList.Node<T> getLast() {
      return this.last;
   }

   public boolean isEmpty() {
      return this.size <= 0;
   }

   public void moveAfter(LinkedList.Node<T> var1, LinkedList.Node<T> var2) {
      this.remove(var2);
      this.addAfter(var1, var2);
   }

   public Iterator<LinkedList.Node<T>> iterator() {
      return new Iterator<LinkedList.Node<T>>() {
         public LinkedList.Node<T> node = LinkedList.this.getFirst();

         @Override
         public boolean hasNext() {
            return this.node != null;
         }

         public LinkedList.Node<T> next() {
            LinkedList.Node var1 = this.node;
            if (this.node != null) {
               this.node = this.node.recoveredField854;
            }

            return var1;
         }

         @Override
         public void remove() {
            throw new UnsupportedOperationException("remove");
         }
      };
   }

   public void checkParent(LinkedList.Node<T> var1) {
      if (var1.parent != this) {
         throw new IllegalArgumentException("Node has different parent, node: " + var1 + ", parent: " + var1.parent + ", this: " + this);
      }
   }

   public void addLast(LinkedList.Node<T> var1) {
      this.checkNoParent(var1);
      if (this.isEmpty()) {
         this.first = var1;
         this.last = var1;
      } else {
         LinkedList.Node var2 = this.last;
         var1.method_02460(var2);
         var2.method_02468(var1);
         this.last = var1;
      }

      var1.setParent(this);
      this.size++;
   }

   public int getSize() {
      return this.size;
   }

   public LinkedList.Node<T> remove(LinkedList.Node<T> var1) {
      this.checkParent(var1);
      LinkedList.Node var2 = var1.getPrev();
      LinkedList.Node var3 = var1.getNext();
      if (var2 != null) {
         var2.method_02468(var3);
      } else {
         this.first = var3;
      }

      if (var3 != null) {
         var3.method_02460(var2);
      } else {
         this.last = var2;
      }

      var1.method_02460(null);
      var1.method_02468(null);
      var1.setParent(null);
      this.size--;
      return var1;
   }

   public void checkNoParent(LinkedList.Node<T> var1) {
      if (var1.parent != null) {
         throw new IllegalArgumentException("Node has different parent, node: " + var1 + ", parent: " + var1.parent + ", this: " + this);
      }
   }

   public void addAfter(LinkedList.Node<T> var1, LinkedList.Node<T> var2) {
      if (var1 == null) {
         this.addFirst(var2);
      } else if (var1 == this.last) {
         this.addLast(var2);
      } else {
         this.checkParent(var1);
         this.checkNoParent(var2);
         LinkedList.Node var3 = var1.getNext();
         var1.method_02468(var2);
         var2.method_02460(var1);
         var3.method_02460(var2);
         var2.method_02468(var3);
         var2.setParent(this);
         this.size++;
      }
   }

   public static class Node<T> {
      public LinkedList.Node<T> recoveredField853;
      public LinkedList<T> parent;
      public LinkedList.Node<T> recoveredField854;
      public T item;

      public Node(T var1) {
         this.item = (T)var1;
      }

      @Override
      public String toString() {
         return "" + this.item;
      }

      public T getItem() {
         return this.item;
      }

      public LinkedList.Node<T> getPrev() {
         return this.recoveredField853;
      }

      public void method_02468(LinkedList.Node<T> var1) {
         this.recoveredField854 = var1;
      }

      public void method_02460(LinkedList.Node<T> var1) {
         this.recoveredField853 = var1;
      }

      public LinkedList.Node<T> getNext() {
         return this.recoveredField854;
      }

      public void setParent(LinkedList<T> var1) {
         this.parent = var1;
      }
   }
}
