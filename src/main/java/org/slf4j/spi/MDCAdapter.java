package org.slf4j.spi;

import java.util.Map;

public interface MDCAdapter {
   String get(String var1);

   void remove(String var1);

   void put(String var1, String var2);

   Map<String, String> getCopyOfContextMap();

   void clear();

   void setContextMap(Map<String, String> var1);
}
