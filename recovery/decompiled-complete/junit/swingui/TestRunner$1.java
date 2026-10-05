package junit.swingui;

import io.netty.buffer.PoolArena$DirectArena;
import junit.framework.Test;
import net.minecraft.client.stream.NullStream;
import net.minecraft.world.chunk.NibbleArray;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$22;
import recovered.unidentified.UnidentifiedClass4292;

public class TestRunner$1 implements Runnable {
   public NibbleArray field_0004;
   public Throwable val$t;
   public TestRunner this$0;
   public Test val$test;
   public UnidentifiedClass4292 field_0000;
   public LogBrokerMonitor$22 field_0001;
   public NullStream field_0008;
   public PoolArena$DirectArena field_0005;
   public int val$status;

   public void run() {
      switch (this.val$status) {
         case 1:
            TestRunner.access$0(this.this$0).setErrorValue(TestRunner.access$1(this.this$0).errorCount());
            TestRunner.access$2(this.this$0, this.val$test, this.val$t);
            break;
         case 2:
            TestRunner.access$0(this.this$0).setFailureValue(TestRunner.access$1(this.this$0).failureCount());
            TestRunner.access$2(this.this$0, this.val$test, this.val$t);
      }
   }

   public TestRunner$1(TestRunner var1, int var2, Test var3, Throwable var4) {
      this.this$0 = var1;
      this.val$status = var2;
      this.val$test = var3;
      this.val$t = var4;
   }
}
