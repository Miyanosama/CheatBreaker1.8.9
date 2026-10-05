package io.netty.handler.codec.http;

import com.cheatbreaker.client.module.type.PackDisplayModule;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker00;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import net.minecraft.block.BlockTorch$1;
import recovered.unidentified.UnidentifiedClass1113;

public class DefaultHttpHeaders$HeaderIterator implements Iterator<Entry<String, String>> {
   public PackDisplayModule __junk3311143454587697024;
   public UnidentifiedClass1113 __junk639443759407677006;
   public WebSocketClientHandshaker00 __junk8416534386546674279;
   public BlockTorch$1 __junk2656385022925010653;
   public DefaultHttpHeaders$HeaderEntry current;

   @Override
   public boolean hasNext() {
      return this.current.after != DefaultHttpHeaders.access$100(this.this$0);
   }

   public Entry<String, String> next() {
      this.current = this.current.after;
      if (this.current == DefaultHttpHeaders.access$100(this.this$0)) {
         throw new NoSuchElementException();
      } else {
         return this.current;
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException();
   }

   public DefaultHttpHeaders$HeaderIterator(DefaultHttpHeaders var1) {
      this.this$0 = var1;
      super();
      this.current = DefaultHttpHeaders.access$100(this.this$0);
   }
}
