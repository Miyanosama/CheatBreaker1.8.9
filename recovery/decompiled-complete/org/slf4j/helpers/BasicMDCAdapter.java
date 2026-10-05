package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.slf4j.spi.MDCAdapter;

public class BasicMDCAdapter implements MDCAdapter {
   public InheritableThreadLocal<Map<String, String>> inheritableThreadLocal = new BasicMDCAdapter$1(this);

   @Override
   public void setContextMap(Map<String, String> var1) {
      this.inheritableThreadLocal.set(new HashMap<>(var1));
   }

   @Override
   public void remove(String var1) {
      Map var2 = this.inheritableThreadLocal.get();
      if (var2 != null) {
         var2.remove(var1);
      }
   }

   @Override
   public String get(String var1) {
      Map var2 = this.inheritableThreadLocal.get();
      return var2 != null && var1 != null ? (String)var2.get(var1) : null;
   }

   public Set<String> getKeys() {
      Map var1 = this.inheritableThreadLocal.get();
      return var1 != null ? var1.keySet() : null;
   }

   @Override
   public void clear() {
      Map var1 = this.inheritableThreadLocal.get();
      if (var1 != null) {
         var1.clear();
         this.inheritableThreadLocal.remove();
      }
   }

   @Override
   public Map<String, String> getCopyOfContextMap() {
      Map var1 = this.inheritableThreadLocal.get();
      return var1 != null ? new HashMap<>(var1) : null;
   }

   @Override
   public void put(String var1, String var2) {
      if (var1 == null) {
         throw new IllegalArgumentException("key cannot be null");
      } else {
         Object var3 = this.inheritableThreadLocal.get();
         if (var3 == null) {
            var3 = new HashMap();
            this.inheritableThreadLocal.set((Map<String, String>)var3);
         }

         var3.put(var1, var2);
      }
   }
}
