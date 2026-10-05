package net.optifine.util;

import io.netty.channel.nio.AbstractNioByteChannel$1;
import io.netty.util.internal.chmv8.ForkJoinTask$ExceptionNode;
import io.netty.util.internal.logging.MessageFormatter;
import recovered.unidentified.UnidentifiedClass0366;

public class LinkedList$Node<T> {
   public LinkedList$Node<T> field_0003;
   public LinkedList<T> parent;
   public UnidentifiedClass0366 field_0002;
   public MessageFormatter field_0005;
   public LinkedList$Node<T> field_0000;
   public AbstractNioByteChannel$1 field_0001;
   public T item;
   public ForkJoinTask$ExceptionNode field_0004;

   public LinkedList$Node(T var1) {
      this.item = (T)var1;
   }

   @Override
   public String toString() {
      return "" + this.item;
   }

   public T getItem() {
      return this.item;
   }

   public LinkedList$Node<T> getPrev() {
      return this.field_0003;
   }

   public void method_02468(LinkedList$Node<T> var1) {
      this.field_0000 = var1;
   }

   public void method_02460(LinkedList$Node<T> var1) {
      this.field_0003 = var1;
   }

   public LinkedList$Node<T> getNext() {
      return this.field_0000;
   }

   public void setParent(LinkedList<T> var1) {
      this.parent = var1;
   }
}
