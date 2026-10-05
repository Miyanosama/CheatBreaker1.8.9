package junit.runner;

public class StandardTestSuiteLoader implements TestSuiteLoader {
   public Class load(String var1) throws java.lang.ClassNotFoundException {
      return Class.forName(var1);
   }

   public Class reload(Class var1) throws java.lang.ClassNotFoundException {
      return var1;
   }
}
