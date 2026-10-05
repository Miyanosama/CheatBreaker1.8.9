package io.netty.util.internal.logging;

import java.util.logging.Logger;
import junit.swingui.TestRunner$2;

public class JdkLoggerFactory extends InternalLoggerFactory {

   @Override
   public InternalLogger newInstance(String var1) {
      return new JdkLogger(Logger.getLogger(var1));
   }
}
