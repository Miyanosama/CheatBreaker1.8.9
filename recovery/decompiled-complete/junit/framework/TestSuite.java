package junit.framework;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Vector;
import net.minecraft.client.stream.ChatController$1;
import recovered.unidentified.UnidentifiedClass0156;

public class TestSuite implements Test {
   public ChatController$1 field_0002;
   public Vector fTests = new Vector(10);
   public static Class field_0000;
   public String fName;
   public static Class field_0004;

   public void run(TestResult var1) {
      Enumeration var2 = this.tests();

      while (var2.hasMoreElements() && !var1.shouldStop()) {
         Test var3 = (Test)var2.nextElement();
         this.runTest(var3, var1);
      }
   }

   public String toString() {
      return this.getName() != null ? this.getName() : super.toString();
   }

   public void runTest(Test var1, TestResult var2) {
      var1.run(var2);
   }

   public static String exceptionToString(Throwable var0) {
      StringWriter var1 = new StringWriter();
      PrintWriter var2 = new PrintWriter(var1);
      var0.printStackTrace(var2);
      return var1.toString();
   }

   public TestSuite(String var1) {
      this.setName(var1);
   }

   public TestSuite(Class var1, String var2) {
      this(var1);
      this.setName(var2);
   }

   public static Test createTest(Class var0, String var1) {
      Constructor var2;
      try {
         var2 = getTestConstructor(var0);
      } catch (NoSuchMethodException var8) {
         return warning("Class " + var0.getName() + " has no public constructor TestCase(String name) or TestCase()");
      }

      Object var3;
      try {
         if (var2.getParameterTypes().length == 0) {
            var3 = var2.newInstance();
            if (var3 instanceof TestCase) {
               ((TestCase)var3).setName(var1);
            }
         } else {
            var3 = var2.newInstance(var1);
         }
      } catch (InstantiationException var5) {
         return warning("Cannot instantiate test case: " + var1 + " (" + exceptionToString(var5) + ")");
      } catch (InvocationTargetException var6) {
         return warning("Exception in constructor: " + var1 + " (" + exceptionToString(var6.getTargetException()) + ")");
      } catch (IllegalAccessException var7) {
         return warning("Cannot access test case: " + var1 + " (" + exceptionToString(var7) + ")");
      }

      return (Test)var3;
   }

   public Test testAt(int var1) {
      return (Test)this.fTests.elementAt(var1);
   }

   public void setName(String var1) {
      this.fName = var1;
   }

   public TestSuite(Class[] var1, String var2) {
      this(var1);
      this.setName(var2);
   }

   public void addTestSuite(Class var1) {
      this.addTest(new TestSuite(var1));
   }

   public void addTest(Test var1) {
      this.fTests.addElement(var1);
   }

   public static Class method_24118(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }

   public static Test warning(String var0) {
      return new UnidentifiedClass0156("warning", var0);
   }

   public int j_() {
      int var1 = 0;
      Enumeration var2 = this.tests();

      while (var2.hasMoreElements()) {
         Test var3 = (Test)var2.nextElement();
         var1 += var3.j_();
      }

      return var1;
   }

   public int testCount() {
      return this.fTests.size();
   }

   public static Constructor getTestConstructor(Class var0) {
      Class[] var1 = new Class[]{field_0004 == null ? (field_0004 = method_24118("java.lang.String")) : field_0004};

      try {
         return var0.getConstructor(var1);
      } catch (NoSuchMethodException var3) {
         return var0.getConstructor();
      }
   }

   public TestSuite() {
   }

   public String getName() {
      return this.fName;
   }

   public boolean method_24114(Method var1) {
      String var2 = var1.getName();
      Class[] var3 = var1.getParameterTypes();
      Class var4 = var1.getReturnType();
      return var3.length == 0 && var2.startsWith("test") && var4.equals(void.class);
   }

   public Enumeration tests() {
      return this.fTests.elements();
   }

   public TestSuite(Class[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         this.addTest(new TestSuite(var1[var2]));
      }
   }

   public TestSuite(Class var1) {
      this.fName = var1.getName();

      try {
         getTestConstructor(var1);
      } catch (NoSuchMethodException var6) {
         this.addTest(warning("Class " + var1.getName() + " has no public constructor TestCase(String name) or TestCase()"));
         return;
      }

      if (!Modifier.isPublic(var1.getModifiers())) {
         this.addTest(warning("Class " + var1.getName() + " is not public"));
      } else {
         Class var2 = var1;

         for (Vector var3 = new Vector();
            (field_0000 == null ? (field_0000 = method_24118("junit.framework.Test")) : field_0000).isAssignableFrom(var2);
            var2 = var2.getSuperclass()
         ) {
            Method[] var4 = var2.getDeclaredMethods();

            for (int var5 = 0; var5 < var4.length; var5++) {
               this.addTestMethod(var4[var5], var3, var1);
            }
         }

         if (this.fTests.size() == 0) {
            this.addTest(warning("No tests found in " + var1.getName()));
         }
      }
   }

   public boolean method_24119(Method var1) {
      return this.method_24114(var1) && Modifier.isPublic(var1.getModifiers());
   }

   public void addTestMethod(Method var1, Vector var2, Class var3) {
      String var4 = var1.getName();
      if (!var2.contains(var4)) {
         if (!this.method_24119(var1)) {
            if (this.method_24114(var1)) {
               this.addTest(warning("Test method isn't public: " + var1.getName()));
            }
         } else {
            var2.addElement(var4);
            this.addTest(createTest(var3, var4));
         }
      }
   }
}
