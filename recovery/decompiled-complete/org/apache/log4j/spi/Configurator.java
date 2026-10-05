package org.apache.log4j.spi;

import java.io.InputStream;
import java.net.URL;

public interface Configurator {
   String field_0000 = "inherited";
   String field_0001 = "null";

   void doConfigure(InputStream var1, LoggerRepository var2);

   void doConfigure(URL var1, LoggerRepository var2);
}
