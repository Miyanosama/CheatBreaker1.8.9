package junit.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class TestCase extends Assert implements Test {
   public String fName;

   public String getName() {
      return this.fName;
   }

   public TestResult createResult() {
      return new TestResult();
   }

   public TestResult run() {
      TestResult var1 = this.createResult();
      this.run(var1);
      return var1;
   }

   public void runBare() throws java.lang.Throwable {
      Throwable var1 = null;
      this.setUp();

      try {
         this.runTest();
      } catch (Throwable var11) {
         var1 = var11;
      } finally {
         try {
            this.tearDown();
         } catch (Throwable var12) {
            if (var1 == null) {
               var1 = var12;
            }
         }
      }

      if (var1 != null) {
         throw var1;
      }
   }

   public int j_() {
      return 1;
   }

   public String toString() {
      return this.getName() + "(" + this.getClass().getName() + ")";
   }

   public TestCase() {
      this.fName = null;
   }

   public void setUp() {
   }

   public void run(TestResult var1) {
      var1.run(this);
   }

   public void setName(String var1) {
      this.fName = var1;
   }

   public TestCase(String var1) {
      this.fName = var1;
   }

   public void tearDown() {
   }

   public void runTest() throws java.lang.Throwable {
      assertNotNull(this.fName);
      Method var1 = null;

      try {
         var1 = this.getClass().getMethod(this.fName, (Class<?>[])null);
      } catch (NoSuchMethodException var5) {
         fail("Method \"" + this.fName + "\" not found");
      }

      if (!Modifier.isPublic(var1.getModifiers())) {
         fail("Method \"" + this.fName + "\" should be public");
      }

      try {
         var1.invoke(this, new Class[0]);
      } catch (InvocationTargetException var3) {
         var3.fillInStackTrace();
         throw var3.getTargetException();
      } catch (IllegalAccessException var4) {
         var4.fillInStackTrace();
         throw var4;
      }
   }
}
