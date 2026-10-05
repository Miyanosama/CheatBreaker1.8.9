package io.netty.handler.traffic;

import io.netty.handler.codec.ReplayingDecoder;
import java.util.concurrent.TimeUnit;

public class TrafficCounter$TrafficMonitoringTask implements Runnable {
   public ReplayingDecoder __junk8936274024019391885;
   public AbstractTrafficShapingHandler trafficShapingHandler1;
   public TrafficCounter counter;

   public TrafficCounter$TrafficMonitoringTask(AbstractTrafficShapingHandler var1, TrafficCounter var2) {
      this.trafficShapingHandler1 = var1;
      this.counter = var2;
   }

   @Override
   public void run() {
      if (this.counter.monitorActive.get()) {
         long var1 = System.currentTimeMillis();
         this.counter.resetAccounting(var1);
         if (this.trafficShapingHandler1 != null) {
            this.trafficShapingHandler1.doAccounting(this.counter);
         }

         TrafficCounter.access$002(
            this.counter, TrafficCounter.access$100(this.counter).schedule(this, this.counter.checkInterval.get(), TimeUnit.MILLISECONDS)
         );
      }
   }
}
