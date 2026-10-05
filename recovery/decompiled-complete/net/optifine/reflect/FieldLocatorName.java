package net.optifine.reflect;

import java.lang.reflect.Field;
import net.minecraft.block.BlockSnow;
import recovered.unidentified.UnidentifiedClass1092;

public class FieldLocatorName implements IFieldLocator {
   public BlockSnow field_0001;
   public ReflectorClass reflectorClass = null;
   public UnidentifiedClass1092 field_0000;
   public String targetFieldName = null;

   public FieldLocatorName(ReflectorClass var1, String var2) {
      this.reflectorClass = var1;
      this.targetFieldName = var2;
   }

   public Field getDeclaredField(Class var1, String var2) {
      Field[] var3 = var1.getDeclaredFields();

      for (int var4 = 0; var4 < var3.length; var4++) {
         Field var5 = var3[var4];
         if (var5.getName().equals(var2)) {
            return var5;
         }
      }

      if (var1 == Object.class) {
         throw new NoSuchFieldException(var2);
      } else {
         return this.getDeclaredField(var1.getSuperclass(), var2);
      }
   }

   @Override
   public Field getField() {
      Class var1 = this.reflectorClass.getTargetClass();
      if (var1 == null) {
         return null;
      } else {
         try {
            Field var2 = this.getDeclaredField(var1, this.targetFieldName);
            var2.setAccessible(true);
            return var2;
         } catch (NoSuchFieldException var3) {
            return null;
         } catch (SecurityException var4) {
            var4.printStackTrace();
            return null;
         } catch (Throwable var5) {
            var5.printStackTrace();
            return null;
         }
      }
   }
}
