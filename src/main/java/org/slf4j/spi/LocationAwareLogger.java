package org.slf4j.spi;

import org.slf4j.Logger;
import org.slf4j.Marker;

public interface LocationAwareLogger extends Logger {
   int recoveredField3201 = 30;
   int recoveredField3202 = 0;
   int recoveredField3203 = 10;
   int recoveredField3204 = 40;
   int recoveredField3205 = 20;

   void log(Marker var1, String var2, int var3, String var4, Object[] var5, Throwable var6);
}
