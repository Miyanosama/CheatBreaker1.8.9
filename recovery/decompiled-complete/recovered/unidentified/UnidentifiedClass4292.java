package recovered.unidentified;

import junit.framework.Test;
import junit.swingui.TestRunner;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import org.apache.log4j.helpers.PatternParser$ClassNamePatternConverter;
import org.apache.log4j.lf5.viewer.LogFactor5ErrorDialog$1;

public class UnidentifiedClass4292 extends Thread {
   public S0DPacketCollectItem field_0002;
   public Test field_0004;
   public TestRunner field_0001;
   public PatternParser$ClassNamePatternConverter field_0003;
   public LogFactor5ErrorDialog$1 field_0000;

   public UnidentifiedClass4292(TestRunner var1, String var2, Test var3) {
      super(var2);
      this.field_0001 = var1;
      this.field_0004 = var3;
   }

   public void run() {
      TestRunner.access$9(this.field_0001, this.field_0004);
      TestRunner.method_00059(this.field_0001, "Running...");
      long var1 = System.currentTimeMillis();
      this.field_0004.run(TestRunner.access$1(this.field_0001));
      if (TestRunner.access$1(this.field_0001).shouldStop()) {
         TestRunner.method_00067(this.field_0001, "Stopped");
      } else {
         long var3 = System.currentTimeMillis();
         long var5 = var3 - var1;
         TestRunner.method_00059(this.field_0001, "Finished: " + this.field_0001.elapsedTimeAsString(var5) + " seconds");
      }

      this.field_0001.method_00048(this.field_0004);
      TestRunner.access$13(this.field_0001, TestRunner.access$12(this.field_0001), "Run");
      TestRunner.method_00068(this.field_0001, null);
      System.gc();
   }
}
