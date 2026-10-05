package io.netty.util.internal.logging;

import java.util.logging.Logger;
import junit.swingui.TestRunner$2;

public class JdkLoggerFactory extends InternalLoggerFactory {
   public TestRunner$2 __junk6828401994308354657;

   @Override
   public InternalLogger newInstance(String var1) {
      return new JdkLogger(Logger.getLogger(var1));
   }
}
