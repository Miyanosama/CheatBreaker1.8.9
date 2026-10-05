package org.java_websocket.handshake;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;

public class HandshakedataImpl1 implements HandshakeBuilder {
   public TreeMap<String, String> map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
   public byte[] content;

   @Override
   public void setContent(byte[] var1) {
      this.content = var1;
   }

   @Override
   public void put(String var1, String var2) {
      this.map.put(var1, var2);
   }

   @Override
   public boolean hasFieldValue(String var1) {
      return this.map.containsKey(var1);
   }

   @Override
   public byte[] getContent() {
      return this.content;
   }

   @Override
   public String getFieldValue(String var1) {
      String var2 = this.map.get(var1);
      return var2 == null ? "" : var2;
   }

   @Override
   public Iterator<String> iterateHttpFields() {
      return Collections.unmodifiableSet(this.map.keySet()).iterator();
   }
}
