package junit.runner;

public class SimpleTestCollector extends ClassPathTestCollector {
   public boolean isTestClass(String var1) {
      return var1.endsWith(".class") && var1.indexOf(36) < 0 && var1.indexOf("Test") > 0;
   }
}
