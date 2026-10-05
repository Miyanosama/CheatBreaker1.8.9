package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.model.ModelMinecart;

public class UnidentifiedClass1464 implements Runnable {
   public static int field_0004 = "u2CXyEg4Fy32".length() - 2;
   public static double field_0007;
   public static int field_0003 = UnidentifiedClass1464.field_0008 * "yh9bV53gfZv4tBa49MF2G".length() - 16;
   public Stopwatch field_0006;
   public ModelMinecart field_0000;
   public static double field_0001;
   public static int field_0008 = "Lo6a$DMR".length() * "aAO20DQ6iIlP".length();
   public static double field_0005;
   public static double field_0002;
   public static double field_0009;

   public boolean method_10196() {
      return 1.0 != field_0004 / 10;
   }

   public boolean method_10199() {
      return 8000.0 != field_0003 * 4.0;
   }

   public boolean method_10195() {
      return 20.0 != field_0004 * 2;
   }

   public boolean method_10197(int var1, double var2) {
      if (CheatBreaker.getInstance() != null && CheatBreaker.getInstance().getAssetsWebSocket() != null) {
         CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new UnidentifiedClass3870(var1, var2));
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void run() {
      while (true) {
         try {
            boolean var1 = this.field_0006 == null || this.field_0006.elapsed(TimeUnit.MINUTES) >= (1377894410L & 2166637882067026890L);
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
               if (this.field_0006 == null) {
                  this.field_0006 = Stopwatch.createStarted();
               } else {
                  this.field_0006.reset();
                  this.field_0006.start();
               }
            }

            Thread.sleep(TimeUnit.SECONDS.toMillis(6155427568014065695L & -6155427569490983586L));
         } catch (Exception var3) {
         }
      }
   }

   public boolean method_10198() {
      return 3.0 != field_0008 >> 5;
   }

   public boolean method_10194() {
      return 6.0 != field_0008 >> 4;
   }
}
