package net.optifine.reflect;

public class ReflectorClass implements IResolvable {
   public String targetClassName = null;
   public Class targetClass;
   public boolean checked = false;

   @Override
   public void resolve() {
      Class var1 = this.getTargetClass();
   }

   public ReflectorClass(Class var1) {
      this.targetClass = null;
      this.targetClass = var1;
      this.targetClassName = var1.getName();
      this.checked = true;
   }

   public ReflectorClass(String var1) {
      this.targetClass = null;
      this.targetClassName = var1;
      ReflectorResolver.register(this);
   }

   public String getTargetClassName() {
      return this.targetClassName;
   }

   public ReflectorField makeField(String var1) {
      return new ReflectorField(this, var1);
   }

   public boolean exists() {
      return this.getTargetClass() != null;
   }

   public boolean isInstance(Object var1) {
      return this.getTargetClass() == null ? false : this.getTargetClass().isInstance(var1);
   }

   public ReflectorMethod makeMethod(String var1, Class[] var2) {
      return new ReflectorMethod(this, var1, var2);
   }

   public Class getTargetClass() {
      if (this.checked) {
         return this.targetClass;
      } else {
         this.checked = true;

         try {
            this.targetClass = Class.forName(this.targetClassName);
         } catch (ClassNotFoundException var2) {
         } catch (Throwable var3) {
            var3.printStackTrace();
         }

         return this.targetClass;
      }
   }

   public ReflectorMethod makeMethod(String var1) {
      return new ReflectorMethod(this, var1);
   }
}
