package net.optifine.util;

import java.util.Iterator;
import net.minecraft.crash.CrashReport$1;
import net.minecraft.server.management.UserListBans;
import net.minecraft.util.EntityDamageSource;
import recovered.unidentified.UnidentifiedClass0921;

public class LinkedList<T> {
   public LinkedList$Node<T> first;
   public UnidentifiedClass0921 field_0005;
   public CrashReport$1 field_0002;
   public EntityDamageSource field_0004;
   public LinkedList$Node<T> last;
   public int size;
   public UserListBans field_0006;

   public void addFirst(LinkedList$Node<T> var1) {
      this.checkNoParent(var1);
      if (this.isEmpty()) {
         this.first = var1;
         this.last = var1;
      } else {
         LinkedList$Node var2 = this.first;
         LinkedList$Node.access$000(var1, var2);
         LinkedList$Node.access$100(var2, var1);
         this.first = var1;
      }

      LinkedList$Node.access$200(var1, this);
      this.size++;
   }

   public LinkedList$Node<T> getFirst() {
      return this.first;
   }

   public boolean find(LinkedList$Node<T> var1, LinkedList$Node<T> var2, LinkedList$Node<T> var3) {
      this.checkParent(var2);
      if (var3 != null) {
         this.checkParent(var3);
      }

      LinkedList$Node var4;
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

      for (LinkedList$Node var3 : this) {
         if (var1.length() > 0) {
            var1.append(", ");
         }

         var1.append(var3.getItem());
      }

      return "" + this.size + " [" + var1.toString() + "]";
   }

   public boolean contains(LinkedList$Node<T> var1) {
      return LinkedList$Node.access$300(var1) == this;
   }

   public LinkedList$Node<T> getLast() {
      return this.last;
   }

   public boolean isEmpty() {
      return this.size <= 0;
   }

   public void moveAfter(LinkedList$Node<T> var1, LinkedList$Node<T> var2) {
      this.remove(var2);
      this.addAfter(var1, var2);
   }

   public Iterator<LinkedList$Node<T>> iterator() {
      return new LinkedList$1(this);
   }

   public void checkParent(LinkedList$Node<T> var1) {
      if (LinkedList$Node.access$300(var1) != this) {
         throw new IllegalArgumentException("Node has different parent, node: " + var1 + ", parent: " + LinkedList$Node.access$300(var1) + ", this: " + this);
      }
   }

   public void addLast(LinkedList$Node<T> var1) {
      this.checkNoParent(var1);
      if (this.isEmpty()) {
         this.first = var1;
         this.last = var1;
      } else {
         LinkedList$Node var2 = this.last;
         LinkedList$Node.access$100(var1, var2);
         LinkedList$Node.access$000(var2, var1);
         this.last = var1;
      }

      LinkedList$Node.access$200(var1, this);
      this.size++;
   }

   public int getSize() {
      return this.size;
   }

   public LinkedList$Node<T> remove(LinkedList$Node<T> var1) {
      this.checkParent(var1);
      LinkedList$Node var2 = var1.getPrev();
      LinkedList$Node var3 = var1.getNext();
      if (var2 != null) {
         LinkedList$Node.access$000(var2, var3);
      } else {
         this.first = var3;
      }

      if (var3 != null) {
         LinkedList$Node.access$100(var3, var2);
      } else {
         this.last = var2;
      }

      LinkedList$Node.access$100(var1, null);
      LinkedList$Node.access$000(var1, null);
      LinkedList$Node.access$200(var1, null);
      this.size--;
      return var1;
   }

   public void checkNoParent(LinkedList$Node<T> var1) {
      if (LinkedList$Node.access$300(var1) != null) {
         throw new IllegalArgumentException("Node has different parent, node: " + var1 + ", parent: " + LinkedList$Node.access$300(var1) + ", this: " + this);
      }
   }

   public void addAfter(LinkedList$Node<T> var1, LinkedList$Node<T> var2) {
      if (var1 == null) {
         this.addFirst(var2);
      } else if (var1 == this.last) {
         this.addLast(var2);
      } else {
         this.checkParent(var1);
         this.checkNoParent(var2);
         LinkedList$Node var3 = var1.getNext();
         LinkedList$Node.access$000(var1, var2);
         LinkedList$Node.access$100(var2, var1);
         LinkedList$Node.access$100(var3, var2);
         LinkedList$Node.access$000(var2, var3);
         LinkedList$Node.access$200(var2, this);
         this.size++;
      }
   }
}
