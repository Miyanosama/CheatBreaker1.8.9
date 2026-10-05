package junit.extensions;

import junit.framework.Test;
import junit.framework.TestResult;
import net.minecraft.client.shader.ShaderLoader;
import net.optifine.player.PlayerItemsLayer;
import org.apache.log4j.NDC$DiagnosticContext;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$9;

public class ActiveTestSuite$1 extends Thread {
   public TestResult val$result;
   public ActiveTestSuite this$0;
   public ShaderLoader field_0002;
   public NDC$DiagnosticContext field_0004;
   public LogBrokerMonitor$9 field_0000;
   public PlayerItemsLayer field_0001;
   public Test val$test;

   public ActiveTestSuite$1(ActiveTestSuite var1, Test var2, TestResult var3) {
      this.this$0 = var1;
      this.val$test = var2;
      this.val$result = var3;
   }

   public void run() {
      try {
         this.val$test.run(this.val$result);
      } finally {
         this.this$0.method_10853();
      }
   }
}
