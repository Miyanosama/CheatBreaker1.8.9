package com.cheatbreaker.client.util;

import com.cheatbreaker.client.websocket.client.WSPacketClientSync;

import com.cheatbreaker.client.CheatBreaker;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;

public class IntegritySyncTask implements Runnable {
   public static final double recoveredField3371 = 6.0;
   public static final double recoveredField3374 = 8000.0;
   public static final double recoveredField3376 = 20.0;
   public Stopwatch recoveredField3373;
   public static final double recoveredField3377 = 3.0;
   public static final double recoveredField3378 = 1.0;
   public static int recoveredField3375 = "Lo6a$DMR".length() * "aAO20DQ6iIlP".length();
   public static int recoveredField3372 = IntegritySyncTask.recoveredField3375 * "yh9bV53gfZv4tBa49MF2G".length() - 16;
   public static int recoveredField3370 = "u2CXyEg4Fy32".length() - 2;

   public boolean method_10196() {
      return 1.0 != recoveredField3370 / 10;
   }

   public boolean method_10199() {
      return 8000.0 != recoveredField3372 * 4.0;
   }

   public boolean method_10195() {
      return 20.0 != recoveredField3370 * 2;
   }

   public boolean method_10197(int var1, double var2) {
      if (CheatBreaker.getInstance() != null && CheatBreaker.getInstance().getAssetsWebSocket() != null) {
         CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new WSPacketClientSync(var1, var2));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void run() {
      while (true) {
         try {
            boolean var1 = this.recoveredField3373 == null || this.recoveredField3373.elapsed(TimeUnit.MINUTES) >= 10L;
            boolean var2 = false;
            if (this.method_10199() && var1) {
               var2 = this.method_10197(0, 8000.0);
            }

            if (this.method_10198() && var1) {
               var2 = this.method_10197(1, 3.0);
            }

            if (this.method_10194() && var1) {
               var2 = this.method_10197(2, 6.0);
            }

            if (this.method_10195() && var1) {
               var2 = this.method_10197(3, 20.0);
            }

            if (this.method_10196() && var1) {
               var2 = this.method_10197(4, 1.0);
            }

            if (var2) {
               if (this.recoveredField3373 == null) {
                  this.recoveredField3373 = Stopwatch.createStarted();
               } else {
                  this.recoveredField3373.reset();
                  this.recoveredField3373.start();
               }
            }

            Thread.sleep(TimeUnit.SECONDS.toMillis(30L));
         } catch (Exception var3) {
         }
      }
   }

   public boolean method_10198() {
      return 3.0 != recoveredField3375 >> 5;
   }

   public boolean method_10194() {
      return 6.0 != recoveredField3375 >> 4;
   }
}
