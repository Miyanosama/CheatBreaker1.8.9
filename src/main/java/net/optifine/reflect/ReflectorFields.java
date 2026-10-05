package net.optifine.reflect;

public class ReflectorFields {
   public int fieldCount;
   public ReflectorField[] reflectorFields;
   public Class fieldType;
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
