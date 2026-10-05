package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.util.dash.Station;
import java.util.LinkedList;
import java.util.Queue;

public class StationDataQueueThread extends Thread {
   public Queue<Station> recoveredField1325 = new LinkedList<>();

   public void method_19837(Station var1) {
      synchronized (this.recoveredField1325) {
         this.recoveredField1325.offer(var1);
         this.recoveredField1325.notify();
      }
   }

   @Override
   public void run() {
      try {
         while (true) {
            synchronized (this.recoveredField1325) {
               this.recoveredField1325.wait();
               Station var2 = this.recoveredField1325.poll();
               if (var2 != null) {
                  var2.getData();
               }
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }
}
