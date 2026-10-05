package org.apache.log4j;

import io.netty.handler.codec.spdy.SpdyOrHttpChooser;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.BoundedFIFO;
import org.apache.log4j.spi.LoggingEvent;

public class Dispatcher extends Thread {
   public BoundedFIFO bf;
   public AppenderAttachableImpl aai;
   public boolean interrupted = false;
   public SpdyOrHttpChooser field_0003;
   public AsyncAppender container;

   public void run() {
      while (true) {
         label59: {
            LoggingEvent var1;
            synchronized (this.bf) {
               if (this.bf.length() == 0) {
                  if (this.interrupted) {
                     break label59;
                  }

                  try {
                     this.bf.wait();
                  } catch (InterruptedException var7) {
                     break label59;
                  }
               }

               var1 = this.bf.get();
               if (this.bf.wasFull()) {
                  this.bf.notify();
               }
            }

            synchronized (this.container.aai) {
               if (this.aai != null && var1 != null) {
                  this.aai.appendLoopOnAppenders(var1);
               }
               continue;
            }
         }

         this.aai.removeAllAppenders();
         return;
      }
   }

   public void close() {
      synchronized (this.bf) {
         this.interrupted = true;
         if (this.bf.length() == 0) {
            this.bf.notify();
         }
      }
   }

   public Dispatcher(BoundedFIFO var1, AsyncAppender var2) {
      this.bf = var1;
      this.container = var2;
      this.aai = var2.aai;
      this.setDaemon(true);
      this.setPriority(1);
      this.setName("Dispatcher-" + this.getName());
   }
}
