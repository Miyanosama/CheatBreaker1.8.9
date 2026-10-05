package org.apache.log4j.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.log4j.helpers.LogLog;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class Log4jEntityResolver implements EntityResolver {
   public static final String recoveredField326 = "-//APACHE//DTD LOG4J 1.2//EN";

   public InputSource resolveEntity(String var1, String var2) {
      if (!var2.endsWith("log4j.dtd") && !"-//APACHE//DTD LOG4J 1.2//EN".equals(var1)) {
         return null;
      } else {
         Class var3 = this.getClass();
         java.io.InputStream var4 = var3.getResourceAsStream("/org/apache/log4j/xml/log4j.dtd");
         if (var4 == null) {
            LogLog.warn("Could not find [log4j.dtd] using [" + var3.getClassLoader() + "] class loader, parsed without DTD.");
            var4 = new ByteArrayInputStream(new byte[0]);
         }

         return new InputSource((InputStream)var4);
      }
   }
}
