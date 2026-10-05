package org.apache.log4j.spi;

import java.util.Enumeration;
import org.apache.log4j.Appender;

public interface AppenderAttachable {
   boolean isAttached(Appender var1);

   void removeAppender(Appender var1);

   void removeAllAppenders();

   void addAppender(Appender var1);

   Appender getAppender(String var1);

   void removeAppender(String var1);

   Enumeration getAllAppenders();
}
