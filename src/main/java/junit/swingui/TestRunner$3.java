package junit.swingui;

import java.util.Enumeration;
import junit.framework.Test;
import junit.swingui.TestRunView;
import junit.swingui.TestRunner;

public class TestRunner$3 implements Runnable {
   public Test recoveredField1034;
   public TestRunner recoveredField1035;

   public void run() {
      Enumeration var1 = TestRunner.access$4(this.recoveredField1035).elements();

      while (var1.hasMoreElements()) {
         TestRunView var2 = (TestRunView)var1.nextElement();
         var2.runFinished(this.recoveredField1034, TestRunner.access$1(this.recoveredField1035));
      }
   }

   public TestRunner$3(TestRunner var1, Test var2) {
      this.recoveredField1035 = var1;
      this.recoveredField1034 = var2;
   }
}
