package org.apache.log4j;

import com.cheatbreaker.client.ui.fading.FloatFade;
import io.netty.channel.ChannelOutboundBuffer$Entry$1;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.particle.EntityBubbleFX$Factory;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;

public class AsyncAppender extends AppenderSkeleton implements AppenderAttachable {
   public EntityBubbleFX$Factory field_0011;
   public Map discardMap;
   public AppenderAttachableImpl appenders;
   public AppenderAttachableImpl aai;
   public int bufferSize;
   public ChannelOutboundBuffer$Entry$1 field_0008;
   public boolean locationInfo;
   public FloatFade field_0001;
   public List buffer = new ArrayList();
   public Thread dispatcher;
   public boolean blocking;
   public static int field_0000;

   public void close() {
      synchronized (this.buffer) {
         this.closed = true;
         this.buffer.notifyAll();
      }

      try {
         this.dispatcher.join();
      } catch (InterruptedException var5) {
         Thread.currentThread().interrupt();
         LogLog.error("Got an InterruptedException while waiting for the dispatcher to finish.", var5);
      }

      synchronized (this.appenders) {
         Enumeration var2 = this.appenders.getAllAppenders();
         if (var2 != null) {
            while (var2.hasMoreElements()) {
               Object var3 = var2.nextElement();
               if (var3 instanceof Appender) {
                  ((Appender)var3).close();
               }
            }
         }
      }
   }

   public void removeAppender(String var1) {
      synchronized (this.appenders) {
         this.appenders.removeAppender(var1);
      }
   }

   public void removeAppender(Appender var1) {
      synchronized (this.appenders) {
         this.appenders.removeAppender(var1);
      }
   }

   public void setBlocking(boolean var1) {
      synchronized (this.buffer) {
         this.blocking = var1;
         this.buffer.notifyAll();
      }
   }

   public void setLocationInfo(boolean var1) {
      this.locationInfo = var1;
   }

   public void setBufferSize(int var1) {
      if (var1 < 0) {
         throw new NegativeArraySizeException("size");
      } else {
         synchronized (this.buffer) {
            this.bufferSize = var1 < 1 ? 1 : var1;
            this.buffer.notifyAll();
         }
      }
   }

   public Appender getAppender(String var1) {
      synchronized (this.appenders) {
         return this.appenders.getAppender(var1);
      }
   }

   public boolean getBlocking() {
      return this.blocking;
   }

   public boolean isAttached(Appender var1) {
      synchronized (this.appenders) {
         return this.appenders.isAttached(var1);
      }
   }

   public void addAppender(Appender var1) {
      synchronized (this.appenders) {
         this.appenders.addAppender(var1);
      }
   }

   public void removeAllAppenders() {
      synchronized (this.appenders) {
         this.appenders.removeAllAppenders();
      }
   }

   public boolean requiresLayout() {
      return false;
   }

   public AsyncAppender() {
      this.discardMap = new HashMap();
      this.bufferSize = 128;
      this.locationInfo = false;
      this.blocking = true;
      this.appenders = new AppenderAttachableImpl();
      this.aai = this.appenders;
      this.dispatcher = new Thread(new AsyncAppender$Dispatcher(this, this.buffer, this.discardMap, this.appenders));
      this.dispatcher.setDaemon(true);
      this.dispatcher.setName("AsyncAppender-Dispatcher-" + this.dispatcher.getName());
      this.dispatcher.start();
   }

   public Enumeration getAllAppenders() {
      synchronized (this.appenders) {
         return this.appenders.getAllAppenders();
      }
   }

   public boolean getLocationInfo() {
      return this.locationInfo;
   }

   public void append(LoggingEvent var1) {
      if (this.dispatcher != null && this.dispatcher.isAlive() && this.bufferSize > 0) {
         var1.getNDC();
         var1.getThreadName();
         var1.getMDCCopy();
         if (this.locationInfo) {
            var1.getLocationInformation();
         }

         var1.getRenderedMessage();
         var1.getThrowableStrRep();
         synchronized (this.buffer) {
            while (true) {
               int var3 = this.buffer.size();
               if (var3 < this.bufferSize) {
                  this.buffer.add(var1);
                  if (var3 == 0) {
                     this.buffer.notifyAll();
                  }
                  break;
               }

               boolean var4 = true;
               if (this.blocking && !Thread.interrupted() && Thread.currentThread() != this.dispatcher) {
                  try {
                     this.buffer.wait();
                     var4 = false;
                  } catch (InterruptedException var8) {
                     Thread.currentThread().interrupt();
                  }
               }

               if (var4) {
                  String var5 = var1.getLoggerName();
                  AsyncAppender$DiscardSummary var6 = (AsyncAppender$DiscardSummary)this.discardMap.get(var5);
                  if (var6 == null) {
                     var6 = new AsyncAppender$DiscardSummary(var1);
                     this.discardMap.put(var5, var6);
                  } else {
                     var6.add(var1);
                  }
                  break;
               }
            }
         }
      } else {
         synchronized (this.appenders) {
            this.appenders.appendLoopOnAppenders(var1);
         }
      }
   }

   public int getBufferSize() {
      return this.bufferSize;
   }
}
