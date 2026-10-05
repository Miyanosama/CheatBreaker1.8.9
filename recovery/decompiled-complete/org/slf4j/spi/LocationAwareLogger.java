package org.slf4j.spi;

import org.slf4j.Logger;
import org.slf4j.Marker;

public interface LocationAwareLogger extends Logger {
   int field_0004 = 30;
   int field_0002 = 0;
   int field_0003 = 10;
   int field_0000 = 40;
   int field_0001 = 20;

   void log(Marker var1, String var2, int var3, String var4, Object[] var5, Throwable var6);
}
