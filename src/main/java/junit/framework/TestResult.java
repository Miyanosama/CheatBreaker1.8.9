package junit.framework;

import java.util.Enumeration;
import java.util.Vector;
import junit.framework.TestResult$1;

public class TestResult {
   public Vector fFailures = new Vector();
   public int fRunTests;
   public Vector fErrors = new Vector();
   public boolean fStop;
   public Vector fListeners = new Vector();

   public synchronized void addFailure(Test var1, AssertionFailedError var2) {
      this.fFailures.addElement(new TestFailure(var1, var2));
      Enumeration var3 = this.cloneListeners().elements();

      while (var3.hasMoreElements()) {
         ((TestListener)var3.nextElement()).addFailure(var1, var2);
      }
   }

   public synchronized void addError(Test var1, Throwable var2) {
      this.fErrors.addElement(new TestFailure(var1, var2));
      Enumeration var3 = this.cloneListeners().elements();

      while (var3.hasMoreElements()) {
         ((TestListener)var3.nextElement()).addError(var1, var2);
      }
   }

   public synchronized boolean wasSuccessful() {
      return this.failureCount() == 0 && this.errorCount() == 0;
   }

   public synchronized Vector cloneListeners() {
      return (Vector)this.fListeners.clone();
   }

   public TestResult() {
      this.fRunTests = 0;
      this.fStop = false;
   }

   public synchronized boolean shouldStop() {
      return this.fStop;
   }

   public void runProtected(Test var1, Protectable var2) {
      try {
         var2.protect();
      } catch (AssertionFailedError var4) {
         this.addFailure(var1, var4);
      } catch (ThreadDeath var5) {
         throw var5;
      } catch (Throwable var6) {
         this.addError(var1, var6);
      }
   }

   public synchronized Enumeration errors() {
      return this.fErrors.elements();
   }

   public void run(TestCase var1) {
      this.startTest(var1);
      TestResult$1 var2 = new TestResult$1(this, var1);
      this.runProtected(var1, var2);
      this.endTest(var1);
   }

   public synchronized int errorCount() {
      return this.fErrors.size();
   }

   public synchronized void addListener(TestListener var1) {
      this.fListeners.addElement(var1);
   }

   public synchronized int runCount() {
      return this.fRunTests;
   }

   public void startTest(Test var1) {
      int var2 = var1.j_();
      synchronized (this) {
         this.fRunTests += var2;
      }

      Enumeration var3 = this.cloneListeners().elements();

      while (var3.hasMoreElements()) {
         ((TestListener)var3.nextElement()).startTest(var1);
      }
   }

   public synchronized Enumeration failures() {
      return this.fFailures.elements();
   }

   public synchronized int failureCount() {
      return this.fFailures.size();
   }

   public synchronized void stop() {
      this.fStop = true;
   }

   public synchronized void removeListener(TestListener var1) {
      this.fListeners.removeElement(var1);
   }

   public void endTest(Test var1) {
      Enumeration var2 = this.cloneListeners().elements();

      while (var2.hasMoreElements()) {
         ((TestListener)var2.nextElement()).endTest(var1);
      }
   }
}
