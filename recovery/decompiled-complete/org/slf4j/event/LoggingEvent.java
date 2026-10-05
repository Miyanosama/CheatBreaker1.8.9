package org.slf4j.event;

import org.slf4j.Marker;

public interface LoggingEvent {
   Level getLevel();

   String method_07030();

   Object[] getArgumentArray();

   Marker getMarker();

   long getTimeStamp();

   String method_07025();

   String method_07031();

   Throwable getThrowable();
}
