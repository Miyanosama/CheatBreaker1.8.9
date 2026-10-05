package net.optifine.reflect;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesToIntTask;
import junit.swingui.TestSuitePanel$1;
import recovered.unidentified.UnidentifiedClass0672;

public class ReflectorFields {
   public TestSuitePanel$1 field_0003;
   public int fieldCount;
   public UnidentifiedClass0672 field_0002;
   public ReflectorField[] reflectorFields;
   public Class fieldType;
   public ConcurrentHashMapV8$MapReduceEntriesToIntTask field_0001;
   public ReflectorClass reflectorClass;

   public int getFieldCount() {
      return this.fieldCount;
   }

   public ReflectorFields(ReflectorClass var1, Class var2, int var3) {
      this.reflectorClass = var1;
      this.fieldType = var2;
      if (var1.exists() && var2 != null) {
         this.reflectorFields = new ReflectorField[var3];

         for (int var4 = 0; var4 < this.reflectorFields.length; var4++) {
            this.reflectorFields[var4] = new ReflectorField(var1, var2, var4);
         }
      }
   }

   public ReflectorClass getReflectorClass() {
      return this.reflectorClass;
   }

   public Class getFieldType() {
      return this.fieldType;
   }

   public ReflectorField getReflectorField(int var1) {
      return var1 >= 0 && var1 < this.reflectorFields.length ? this.reflectorFields[var1] : null;
   }
}
