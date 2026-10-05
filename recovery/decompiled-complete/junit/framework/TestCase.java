package junit.framework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.network.login.server.S02PacketLoginSuccess;

public abstract class TestCase extends Assert implements Test {
   public String fName;
   public ModelManager field_0001;
   public S02PacketLoginSuccess field_0002;

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

   public void method_25956() {
      Throwable var1 = null;
      this.method_25962();

      try {
         this.runTest();
      } catch (Throwable var11) {
         var1 = var11;
      } finally {
         try {
            this.method_25959();
         } catch (Throwable var12) {
            if (var1 == null) {
               ;
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

   public void method_25962() {
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

   public void method_25959() {
   }

   public void runTest() {
      method_03720(this.fName);
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
