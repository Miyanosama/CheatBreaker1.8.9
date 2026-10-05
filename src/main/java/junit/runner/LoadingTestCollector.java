package junit.runner;

import java.lang.reflect.Modifier;
import junit.framework.TestSuite;

public class LoadingTestCollector extends ClassPathTestCollector {
   public TestCaseClassLoader fLoader = new TestCaseClassLoader();
   public static Class class$0;

   public boolean hasPublicConstructor(Class var1) {
      try {
         TestSuite.getTestConstructor(var1);
         return true;
      } catch (NoSuchMethodException var3) {
         return false;
      }
   }

   public boolean isTestClass(Class var1) throws java.lang.ClassNotFoundException {
      return this.hasSuiteMethod(var1)
         ? true
         : (class$0 == null ? (class$0 = class$("junit.framework.Test")) : class$0).isAssignableFrom(var1)
            && Modifier.isPublic(var1.getModifiers())
            && this.hasPublicConstructor(var1);
   }

   public static Class class$(String var0) throws java.lang.ClassNotFoundException {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }

   public boolean hasSuiteMethod(Class var1) {
      try {
         var1.getMethod("suite");
         return true;
      } catch (Exception var3) {
         return false;
      }
   }

   public boolean isTestClass(String var1) {
      try {
         if (var1.endsWith(".class")) {
            Class var2 = this.classFromFile(var1);
            return var2 != null && this.isTestClass(var2);
         }
      } catch (ClassNotFoundException var3) {
      } catch (NoClassDefFoundError var4) {
      }

      return false;
   }

   public Class classFromFile(String var1) throws java.lang.ClassNotFoundException {
      String var2 = this.classNameFromFile(var1);
      return !this.fLoader.isExcluded(var2) ? this.fLoader.loadClass(var2, false) : null;
   }
}
