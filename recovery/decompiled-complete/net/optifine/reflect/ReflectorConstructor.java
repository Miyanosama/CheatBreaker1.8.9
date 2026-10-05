package net.optifine.reflect;

import java.lang.reflect.Constructor;
import net.optifine.util.CacheLocalByte;
import net.optifine.util.StrUtils;
import recovered.unidentified.UnidentifiedClass3953;

public class ReflectorConstructor implements IResolvable {
   public UnidentifiedClass3953 field_0003;
   public boolean checked;
   public Constructor targetConstructor;
   public StrUtils field_0004;
   public ReflectorClass reflectorClass = null;
   public Class[] parameterTypes = null;
   public CacheLocalByte field_0006;

   @Override
   public void resolve() {
      Constructor var1 = this.getTargetConstructor();
   }

   public static Constructor findConstructor(Class var0, Class[] var1) {
      Constructor[] var2 = var0.getDeclaredConstructors();

      for (int var3 = 0; var3 < var2.length; var3++) {
         Constructor var4 = var2[var3];
         Class[] var5 = var4.getParameterTypes();
         if (Reflector.matchesTypes(var1, var5)) {
            return var4;
         }
      }

      return null;
   }

   public void deactivate() {
      this.checked = true;
      this.targetConstructor = null;
   }

   public boolean exists() {
      return this.checked ? this.targetConstructor != null : this.getTargetConstructor() != null;
   }

   public Object newInstance(Object... var1) {
      return Reflector.newInstance(this, var1);
   }

   public Constructor getTargetConstructor() {
      if (this.checked) {
         return this.targetConstructor;
      } else {
         this.checked = true;
         Class var1 = this.reflectorClass.getTargetClass();
         if (var1 == null) {
            return null;
         } else {
            try {
               this.targetConstructor = findConstructor(var1, this.parameterTypes);
               if (this.targetConstructor == null) {
               }

               if (this.targetConstructor != null) {
                  this.targetConstructor.setAccessible(true);
               }
            } catch (Throwable var3) {
               var3.printStackTrace();
            }

            return this.targetConstructor;
         }
      }
   }

   public ReflectorConstructor(ReflectorClass var1, Class[] var2) {
      this.checked = false;
      this.targetConstructor = null;
      this.reflectorClass = var1;
      this.parameterTypes = var2;
      ReflectorResolver.register(this);
   }
}
