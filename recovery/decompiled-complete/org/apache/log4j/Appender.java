package org.apache.log4j;

import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;

public interface Appender {
   void setLayout(Layout var1);

   Filter getFilter();

   ErrorHandler getErrorHandler();

   Layout getLayout();

   void setName(String var1);

   void addFilter(Filter var1);

   void doAppend(LoggingEvent var1);

   void close();

   boolean requiresLayout();

   void setErrorHandler(ErrorHandler var1);

   void clearFilters();

   String getName();
}
