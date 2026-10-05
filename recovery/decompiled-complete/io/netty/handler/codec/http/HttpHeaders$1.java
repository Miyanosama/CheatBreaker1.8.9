package io.netty.handler.codec.http;

import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.command.CommandTitle;

public class HttpHeaders$1 extends HttpHeaders {
   public CommandTitle __junk8239687452697008018;
   public ByteToMessageDecoder __junk6972641306058342306;

   @Override
   public Set<String> names() {
      return Collections.emptySet();
   }

   @Override
   public HttpHeaders clear() {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public HttpHeaders add(String var1, Iterable<?> var2) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public HttpHeaders add(String var1, Object var2) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public boolean contains(String var1) {
      return false;
   }

   @Override
   public Iterator<Entry<String, String>> iterator() {
      return this.entries().iterator();
   }

   @Override
   public List<Entry<String, String>> entries() {
      return Collections.emptyList();
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
   public boolean isEmpty() {
      return true;
   }

   @Override
   public HttpHeaders set(String var1, Iterable<?> var2) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public HttpHeaders set(String var1, Object var2) {
      throw new UnsupportedOperationException("read only");
   }

   @Override
   public HttpHeaders remove(String var1) {
      throw new UnsupportedOperationException("read only");
   }
}
