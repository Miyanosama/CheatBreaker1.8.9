package io.netty.util.internal;

import java.util.Iterator;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.resources.ResourcePackFileNotFoundException;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog$1;

public class ReadOnlyIterator<T> implements Iterator<T> {
   public ResourcePackFileNotFoundException __junk5992956436189304504;
   public LogFactor5InputDialog$1 __junk6525576133264007364;
   public BlockDirectional __junk99491801546621900;
   public Iterator<? extends T> iterator;

   @Override
   public T next() {
      return (T)this.iterator.next();
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("read-only");
   }

   @Override
   public boolean hasNext() {
      return this.iterator.hasNext();
   }

   public ReadOnlyIterator(Iterator<? extends T> var1) {
      if (var1 == null) {
         throw new NullPointerException("iterator");
      } else {
         this.iterator = var1;
      }
   }
}
