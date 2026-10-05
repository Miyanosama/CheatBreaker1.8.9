package org.apache.log4j.helpers;

import java.util.Hashtable;

public class ThreadLocalMap extends InheritableThreadLocal {
   public Object childValue(Object var1) {
      Hashtable var2 = (Hashtable)var1;
      return var2 != null ? var2.clone() : null;
   }
}
