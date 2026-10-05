package net.optifine.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import net.minecraft.client.shader.ShaderDefault;
import net.optifine.Log;

public class ReflectorMethod implements IResolvable {
   public ReflectorClass reflectorClass = null;
   public String targetMethodName = null;
   public boolean checked;
   public ShaderDefault field_0004;
   public Class[] targetMethodParameterTypes = null;
   public Method targetMethod;

   public void callVoid(Object... var1) {
      Reflector.callVoid(this, var1);
   }

   public Object call(Object... var1) {
      return Reflector.call(this, var1);
   }

   public static Method getMethod(Class var0, String var1, Class[] var2) {
      Method[] var3 = var0.getDeclaredMethods();

      for (int var4 = 0; var4 < var3.length; var4++) {
         Method var5 = var3[var4];
         if (var5.getName().equals(var1)) {
            Class[] var6 = var5.getParameterTypes();
            if (Reflector.matchesTypes(var2, var6)) {
               return var5;
            }
         }
      }

      return null;
   }

   public Class getReturnType() {
      Method var1 = this.getTargetMethod();
      return var1 == null ? null : var1.getReturnType();
   }

   public Object call(Object var1) {
      return Reflector.call(this, var1);
   }

   public float callFloat(Object... var1) {
      return Reflector.callFloat(this, var1);
   }

   public double callDouble(Object... var1) {
      return Reflector.callDouble(this, var1);
   }

   @Override
   public void resolve() {
      Method var1 = this.getTargetMethod();
   }

   public boolean callBoolean(Object var1) {
      return Reflector.callBoolean(this, var1);
   }

   public int callInt(Object... var1) {
      return Reflector.callInt(this, var1);
   }

   public Method getTargetMethod() {
      if (this.checked) {
         return this.targetMethod;
      } else {
         this.checked = true;
         Class var1 = this.reflectorClass.getTargetClass();
         if (var1 == null) {
            return null;
         } else {
            try {
               if (this.targetMethodParameterTypes == null) {
                  Method[] var2 = getMethods(var1, this.targetMethodName);
                  if (var2.length <= 0) {
                     return null;
                  }

                  if (var2.length > 1) {
                     Log.warn("(Reflector) More than one method found: " + var1.getName() + "." + this.targetMethodName);

                     for (int var3 = 0; var3 < var2.length; var3++) {
                        Method var4 = var2[var3];
                        Log.warn("(Reflector)  - " + var4);
                     }

                     return null;
                  }

                  this.targetMethod = var2[0];
               } else {
                  this.targetMethod = getMethod(var1, this.targetMethodName, this.targetMethodParameterTypes);
               }

               if (this.targetMethod == null) {
                  return null;
               } else {
                  this.targetMethod.setAccessible(true);
                  return this.targetMethod;
               }
            } catch (Throwable var5) {
               var5.printStackTrace();
               return null;
            }
         }
      }
   }

   public ReflectorMethod(ReflectorClass var1, String var2, Class[] var3) {
      this.checked = false;
      this.targetMethod = null;
      this.reflectorClass = var1;
      this.targetMethodName = var2;
      this.targetMethodParameterTypes = var3;
      ReflectorResolver.register(this);
   }

   public int callInt(Object var1) {
      return Reflector.callInt(this, var1);
   }

   public ReflectorMethod(ReflectorClass var1, String var2) {
      this(var1, var2, (Class[])null);
   }

   public String callString1(Object var1) {
      return Reflector.callString(this, var1);
   }

   public float callFloat(Object var1) {
      return Reflector.callFloat(this, var1);
   }

   public double callDouble(Object var1) {
      return Reflector.callDouble(this, var1);
   }

   public static Method[] getMethods(Class var0, String var1) {
      ArrayList var2 = new ArrayList();
      Method[] var3 = var0.getDeclaredMethods();

      for (int var4 = 0; var4 < var3.length; var4++) {
         Method var5 = var3[var4];
         if (var5.getName().equals(var1)) {
            var2.add(var5);
         }
      }

      return var2.toArray(new Method[var2.size()]);
   }

   public boolean callBoolean(Object... var1) {
      return Reflector.callBoolean(this, var1);
   }

   public boolean exists() {
      return this.checked ? this.targetMethod != null : this.getTargetMethod() != null;
   }

   public String callString(Object... var1) {
      return Reflector.callString(this, var1);
   }

   public void deactivate() {
      this.checked = true;
      this.targetMethod = null;
   }
}
