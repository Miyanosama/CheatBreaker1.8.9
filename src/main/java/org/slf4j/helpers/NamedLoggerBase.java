package org.slf4j.helpers;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NamedLoggerBase implements Serializable, Logger {
   public static final long recoveredField3863 = 7535258609338176893L;
   public String name;

   public Object readResolve() {
      return LoggerFactory.getLogger(this.getName());
   }

   @Override
   public String getName() {
      return this.name;
   }
}
