package org.apache.log4j.jmx;

import java.lang.reflect.Method;

public class MethodUnion {
   public Method readMethod;
   public Method writeMethod;

   public MethodUnion(Method var1, Method var2) {
      this.readMethod = var1;
      this.writeMethod = var2;
   }
}
