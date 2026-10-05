package io.netty.handler.codec.spdy;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import net.minecraft.client.particle.EntityRainFX;

public class DefaultSpdyHeaders$HeaderIterator implements Iterator<Entry<String, String>> {
   public DefaultSpdyHeaders$HeaderEntry current;
   public EntityRainFX __junk2658533144743309501;

   @Override
   public void remove() {
      throw new UnsupportedOperationException();
   }

   public DefaultSpdyHeaders$HeaderIterator(DefaultSpdyHeaders var1) {
      this.this$0 = var1;
      super();
      this.current = DefaultSpdyHeaders.access$100(this.this$0);
   }

   public Entry<String, String> next() {
      this.current = this.current.after;
      if (this.current == DefaultSpdyHeaders.access$100(this.this$0)) {
         throw new NoSuchElementException();
      } else {
         return this.current;
      }
   }

   @Override
   public boolean hasNext() {
      return this.current.after != DefaultSpdyHeaders.access$100(this.this$0);
   }
}
