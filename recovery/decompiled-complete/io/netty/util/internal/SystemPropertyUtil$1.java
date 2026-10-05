package io.netty.util.internal;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesTask;
import java.security.PrivilegedAction;
import org.apache.log4j.config.PropertySetter;

public class SystemPropertyUtil$1 implements PrivilegedAction<String> {
   public PropertySetter __junk4172558568654338746;
   public ConcurrentHashMapV8$MapReduceEntriesTask __junk2562146101509390083;

   public String run() {
      return System.getProperty(this.val$key);
   }

   public SystemPropertyUtil$1(String var1) {
      this.val$key = var1;
      super();
   }
}
