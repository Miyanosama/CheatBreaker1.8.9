package net.optifine;

import net.optifine.Lagometer;

public class Lagometer$TimerNano {
   public long recoveredField2793;
   public long recoveredField2794 = 0L;

   public void method_30328() {
      if (Lagometer.active && this.recoveredField2794 != 0L) {
         this.recoveredField2793 = this.recoveredField2793 + (System.nanoTime() - this.recoveredField2794);
         this.recoveredField2794 = 0L;
      }
   }

   public Lagometer$TimerNano() {
      this.recoveredField2793 = 0L;
   }

   // $VF: synthetic method
   public static void method_30329(Lagometer$TimerNano var0) {
      var0.method_30330();
   }

   public void method_30330() {
      this.recoveredField2793 = 0L;
      this.recoveredField2794 = 0L;
   }

   public void method_30327() {
      if (Lagometer.active && this.recoveredField2794 == 0L) {
         this.recoveredField2794 = System.nanoTime();
      }
   }
}
