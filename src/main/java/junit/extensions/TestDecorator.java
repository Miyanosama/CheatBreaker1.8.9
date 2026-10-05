package junit.extensions;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestResult;

public class TestDecorator extends Assert implements Test {
   public Test fTest;

   public TestDecorator(Test var1) {
      this.fTest = var1;
   }

   public String toString() {
      return this.fTest.toString();
   }

   public int j_() {
      return this.fTest.j_();
   }

   public void basicRun(TestResult var1) {
      this.fTest.run(var1);
   }

   public void run(TestResult var1) {
      this.basicRun(var1);
   }

   public Test getTest() {
      return this.fTest;
   }
}
