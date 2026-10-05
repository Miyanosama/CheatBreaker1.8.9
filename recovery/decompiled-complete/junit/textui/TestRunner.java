package junit.textui;

import io.netty.channel.udt.nio.NioUdtMessageRendezvousChannel;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.internal.chmv8.ForkJoinTask$1;
import java.io.PrintStream;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.runner.BaseTestRunner;
import junit.runner.StandardTestSuiteLoader;
import junit.runner.TestSuiteLoader;
import net.minecraft.client.particle.EntityDropParticleFX$WaterFactory;
import net.minecraft.crash.CrashReport$6;
import org.java_websocket.framing.TextFrame;
import recovered.unidentified.UnidentifiedClass1163;
import recovered.unidentified.UnidentifiedClass4928;

public class TestRunner extends BaseTestRunner {
   public ResultPrinter fPrinter;
   public EntityDropParticleFX$WaterFactory field_0008;
   public NioUdtMessageRendezvousChannel field_0004;
   public TextFrame field_0007;
   public static int field_0001;
   public HttpVersion field_0002;
   public CrashReport$6 field_0009;
   public ForkJoinTask$1 field_0006;
   public static int field_0003;
   public static int field_0010;
   public UnidentifiedClass1163 field_0000;

   public TestResult createTestResult() {
      return new TestResult();
   }

   public void method_00096(String var1) {
   }

   public void testEnded(String var1) {
   }

   public TestRunner(PrintStream var1) {
      this(new ResultPrinter(var1));
   }

   public void runFailed(String var1) {
      System.err.println(var1);
      System.exit(1);
   }

   public TestRunner() {
      this(System.out);
   }

   public void pause(boolean var1) {
      if (var1) {
         this.fPrinter.printWaitPrompt();

         try {
            System.in.read();
         } catch (Exception var3) {
         }
      }
   }

   public static TestResult run(Test var0) {
      TestRunner var1 = new TestRunner();
      return var1.doRun(var0);
   }

   public TestRunner(ResultPrinter var1) {
      this.fPrinter = var1;
   }

   public TestSuiteLoader getLoader() {
      return new StandardTestSuiteLoader();
   }

   public void setPrinter(ResultPrinter var1) {
      this.fPrinter = var1;
   }

   public TestResult doRun(Test var1, boolean var2) {
      TestResult var3 = this.createTestResult();
      var3.addListener(this.fPrinter);
      long var4 = System.currentTimeMillis();
      var1.run(var3);
      long var6 = System.currentTimeMillis();
      long var8 = var6 - var4;
      this.fPrinter.print(var3, var8);
      this.pause(var2);
      return var3;
   }

   public void testFailed(int var1, Test var2, Throwable var3) {
   }

   public TestResult method_22615(String var1, String var2, boolean var3) {
      Class var4 = this.loadSuiteClass(var1);
      Test var5 = TestSuite.createTest(var4, var2);
      return this.doRun(var5, var3);
   }

   public TestResult start(String[] var1) {
      String var2 = "";
      String var3 = "";
      boolean var4 = false;

      for (int var5 = 0; var5 < var1.length; var5++) {
         if (var1[var5].equals("-wait")) {
            var4 = true;
         } else if (var1[var5].equals("-c")) {
            var2 = this.extractClassName(var1[++var5]);
         } else if (var1[var5].equals("-m")) {
            String var6 = var1[++var5];
            int var7 = var6.lastIndexOf(46);
            var2 = var6.substring(0, var7);
            var3 = var6.substring(var7 + 1);
         } else if (var1[var5].equals("-v")) {
            System.err.println("JUnit " + UnidentifiedClass4928.method_29434() + " by Kent Beck and Erich Gamma");
         } else {
            var2 = var1[var5];
         }
      }

      if (var2.equals("")) {
         throw new Exception("Usage: TestRunner [-wait] testCaseName, where name is the name of the TestCase class");
      } else {
         try {
            if (!var3.equals("")) {
               return this.method_22615(var2, var3, var4);
            } else {
               Test var9 = this.getTest(var2);
               return this.doRun(var9, var4);
            }
         } catch (Exception var8) {
            throw new Exception("Could not create and run test suite: " + var8);
         }
      }
   }

   public static void run(Class var0) {
      run(new TestSuite(var0));
   }

   public TestResult doRun(Test var1) {
      return this.doRun(var1, false);
   }

   public static void runAndWait(Test var0) {
      TestRunner var1 = new TestRunner();
      var1.doRun(var0, true);
   }

   public static void main(String[] var0) {
      TestRunner var1 = new TestRunner();

      try {
         TestResult var2 = var1.start(var0);
         if (!var2.wasSuccessful()) {
            System.exit(1);
         }

         System.exit(0);
      } catch (Exception var3) {
         System.err.println(var3.getMessage());
         System.exit(2);
      }
   }
}
