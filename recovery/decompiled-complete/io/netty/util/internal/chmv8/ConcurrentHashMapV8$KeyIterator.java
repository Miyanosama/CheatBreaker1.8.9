package io.netty.util.internal.chmv8;

import io.netty.handler.timeout.IdleStateHandler$1;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.optifine.override.ChunkCacheOF;
import org.apache.log4j.AsyncAppender$Dispatcher;

public class ConcurrentHashMapV8$KeyIterator<K, V> extends ConcurrentHashMapV8$BaseIterator<K, V> implements Enumeration<K>, Iterator<K> {
   public AsyncAppender$Dispatcher __junk3584792796147030490;
   public ChunkCacheOF __junk5853256224154923724;
   public IdleStateHandler$1 __junk5429891415089566357;

   @Override
   public K nextElement() {
      return this.next();
   }

   @Override
   public K next() {
      ConcurrentHashMapV8$Node var1 = this.next;
      if (this.next == null) {
         throw new NoSuchElementException();
      } else {
         Object var2 = var1.key;
         this.lastReturned = var1;
         this.advance();
         return (K)var2;
      }
   }

   public ConcurrentHashMapV8$KeyIterator(ConcurrentHashMapV8$Node<K, V>[] var1, int var2, int var3, int var4, ConcurrentHashMapV8<K, V> var5) {
      super(var1, var2, var3, var4, var5);
   }
}
