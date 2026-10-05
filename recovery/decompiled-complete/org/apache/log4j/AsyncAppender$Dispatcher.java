package org.apache.log4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.spi.LoggingEvent;
import recovered.unidentified.UnidentifiedClass4913;

public class AsyncAppender$Dispatcher implements Runnable {
   public ItemStack field_0003;
   public AsyncAppender parent;
   public GuiRepair field_0002;
   public Map discardMap;
   public AppenderAttachableImpl appenders;
   public UnidentifiedClass4913 field_0001;
   public List buffer;
   public ItemPotion field_0004;

   public void run() {
      boolean var1 = true;

      try {
         while (var1) {
            LoggingEvent[] var2 = null;
            synchronized (this.buffer) {
               int var4 = this.buffer.size();

               for (var1 = !this.parent.closed; var4 == 0 && var1; var1 = !this.parent.closed) {
                  this.buffer.wait();
                  var4 = this.buffer.size();
               }

               if (var4 > 0) {
                  var2 = new LoggingEvent[var4 + this.discardMap.size()];
                  this.buffer.toArray(var2);
                  int var5 = var4;
                  Iterator var6 = this.discardMap.values().iterator();

                  while (var6.hasNext()) {
                     var2[var5++] = ((AsyncAppender$DiscardSummary)var6.next()).createEvent();
                  }

                  this.buffer.clear();
                  this.discardMap.clear();
                  this.buffer.notifyAll();
               }
            }

            if (var2 != null) {
               for (int var12 = 0; var12 < var2.length; var12++) {
                  synchronized (this.appenders) {
                     this.appenders.appendLoopOnAppenders(var2[var12]);
                  }
               }
            }
         }
      } catch (InterruptedException var11) {
         Thread.currentThread().interrupt();
      }
   }

   public AsyncAppender$Dispatcher(AsyncAppender var1, List var2, Map var3, AppenderAttachableImpl var4) {
      this.parent = var1;
      this.buffer = var2;
      this.appenders = var4;
      this.discardMap = var3;
   }
}
