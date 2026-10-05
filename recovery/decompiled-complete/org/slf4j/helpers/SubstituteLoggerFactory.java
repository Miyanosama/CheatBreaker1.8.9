package org.slf4j.helpers;

import com.cheatbreaker.client.config.GlobalSettings;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.SubstituteLoggingEvent;

public class SubstituteLoggerFactory implements ILoggerFactory {
   public boolean postInitialization = false;
   public Map<String, SubstituteLogger> loggers = new HashMap<>();
   public LinkedBlockingQueue<SubstituteLoggingEvent> eventQueue = new LinkedBlockingQueue<>();
   public GlobalSettings field_0003;
   public FingerprintTrustManagerFactory field_0000;

   public List<String> getLoggerNames() {
      return new ArrayList<>(this.loggers.keySet());
   }

   public List<SubstituteLogger> getLoggers() {
      return new ArrayList<>(this.loggers.values());
   }

   public void postInitialization() {
      this.postInitialization = true;
   }

   @Override
   public synchronized Logger getLogger(String var1) {
      SubstituteLogger var2 = this.loggers.get(var1);
      if (var2 == null) {
         var2 = new SubstituteLogger(var1, this.eventQueue, this.postInitialization);
         this.loggers.put(var1, var2);
      }

      return var2;
   }

   public LinkedBlockingQueue<SubstituteLoggingEvent> getEventQueue() {
      return this.eventQueue;
   }

   public void clear() {
      this.loggers.clear();
      this.eventQueue.clear();
   }
}
