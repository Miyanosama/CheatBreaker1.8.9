package io.netty.util.internal.chmv8;

import io.netty.handler.codec.http.CookieDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.scijava.nativelib.NativeLibraryUtil$Processor;

public class ConcurrentHashMapV8$ValueIterator<K, V> extends ConcurrentHashMapV8$BaseIterator<K, V> implements Enumeration<V>, Iterator<V> {
   public NativeLibraryUtil$Processor __junk3399209319720507318;
   public CookieDecoder __junk8506914528401461165;

   @Override
   public V next() {
      ConcurrentHashMapV8$Node var1 = this.next;
      if (this.next == null) {
         throw new NoSuchElementException();
      } else {
         Object var2 = var1.val;
         this.lastReturned = var1;
         this.advance();
         return (V)var2;
      }
   }

   public ConcurrentHashMapV8$ValueIterator(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
      super(var1, var2, var3, var4, var5);
   }

   @Override
   public V nextElement() {
      return this.next();
   }
}
