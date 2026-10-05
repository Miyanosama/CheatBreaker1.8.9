package com.cheatbreaker.client.util.dash;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.util.dash.Station;
import java.time.Duration;
import java.time.LocalDateTime;

public class StationRefreshThread extends Thread {
   @Override
   public void run() {
      while (true) {
         try {
            Station var1;
            if ((var1 = CheatBreaker.getInstance().getRadioManager().getCurrentStation()) != null
               && var1.method_05594() != null
               && Duration.between(var1.method_05594(), LocalDateTime.now()).toMillis() / 1000L >= var1.getDuration() + 2) {
               var1.getData();
               Thread.sleep(4000L);
            }

            Thread.sleep(1000L);
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }
   }
}
