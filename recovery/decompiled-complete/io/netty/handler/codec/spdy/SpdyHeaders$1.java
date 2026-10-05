package io.netty.handler.codec.spdy;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.util.ChatComponentStyle$2;

public class SpdyHeaders$1 extends SpdyHeaders {
   public SpdyHttpEncoder __junk6079666175373785513;
   public ChatComponentStyle$2 __junk423527794338613458;

   @Override
   public SpdyHeaders remove(String var1) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public SpdyHeaders add(String var1, Object var2) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public SpdyHeaders clear() {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public String get(String var1) {
      return null;
   }

   @Override
   public List<String> getAll(String var1) {
      return Collections.emptyList();
   }

   @Override
   public SpdyHeaders add(String var1, Iterable<?> var2) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public List<Entry<String, String>> entries() {
      return Collections.emptyList();
   }

   @Override
   public Set<String> names() {
      return Collections.emptySet();
   }

   @Override
   public SpdyHeaders set(String var1, Iterable<?> var2) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public Iterator<Entry<String, String>> iterator() {
      return this.entries().iterator();
   }

   @Override
   public boolean contains(String var1) {
      return false;
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   @Override
   public SpdyHeaders set(String var1, Object var2) {
      throw new UnsupportedOperationException("read only");
   }
}
