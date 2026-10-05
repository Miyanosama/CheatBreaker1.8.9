package junit.runner;

public class ReloadingTestSuiteLoader implements TestSuiteLoader {
   public TestCaseClassLoader createLoader() {
      return new TestCaseClassLoader();
   }

   public Class reload(Class var1) throws java.lang.ClassNotFoundException {
      return this.createLoader().loadClass(var1.getName(), true);
   }

   public Class load(String var1) throws java.lang.ClassNotFoundException {
      return this.createLoader().loadClass(var1, true);
   }
}
