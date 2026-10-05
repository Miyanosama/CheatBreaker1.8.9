package junit.runner;

public class StandardTestSuiteLoader implements TestSuiteLoader {
   public Class load(String var1) {
      return Class.forName(var1);
   }

   public Class reload(Class var1) {
      return var1;
   }
}
