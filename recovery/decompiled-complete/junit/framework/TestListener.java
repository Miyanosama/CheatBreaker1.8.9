package junit.framework;

public interface TestListener {
   void endTest(Test var1);

   void startTest(Test var1);

   void addFailure(Test var1, AssertionFailedError var2);

   void addError(Test var1, Throwable var2);
}
