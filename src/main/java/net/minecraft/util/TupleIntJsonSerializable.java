package net.minecraft.util;

public class TupleIntJsonSerializable {
   public int integerValue;
   public IJsonSerializable jsonSerializableValue;

   public void setJsonSerializableValue(IJsonSerializable var1) {
      this.jsonSerializableValue = var1;
   }

   public int getIntegerValue() {
      return this.integerValue;
   }

   public void setIntegerValue(int var1) {
      this.integerValue = var1;
   }

   public <T extends IJsonSerializable> T getJsonSerializableValue() {
      return (T)this.jsonSerializableValue;
   }
}
