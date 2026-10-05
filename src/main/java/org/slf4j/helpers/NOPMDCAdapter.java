package org.slf4j.helpers;

import java.util.Map;
import org.slf4j.spi.MDCAdapter;

public class NOPMDCAdapter implements MDCAdapter {
   @Override
   public void setContextMap(Map<String, String> var1) {
   }

   @Override
   public void clear() {
   }

   @Override
   public void put(String var1, String var2) {
   }

   @Override
   public Map<String, String> getCopyOfContextMap() {
      return null;
   }

   @Override
   public void remove(String var1) {
   }

   @Override
   public String get(String var1) {
      return null;
   }
}
