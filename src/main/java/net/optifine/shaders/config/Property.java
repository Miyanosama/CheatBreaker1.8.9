package net.optifine.shaders.config;

import java.util.Properties;
import net.minecraft.src.Config;
import org.apache.commons.lang3.ArrayUtils;

public class Property {
   public String recoveredField1812;
   public String[] userValues;
   public String[] propertyValues;
   public String recoveredField1813;
   public int defaultValue = 0;
   public int value;

   public String method_02960() {
      return this.recoveredField1813;
   }

   public void nextValue(boolean var1) {
      byte var2 = 0;
      int var3 = this.propertyValues.length - 1;
      this.value = Config.limit(this.value, var2, var3);
      if (var1) {
         this.value++;
         if (this.value > var3) {
            this.value = var2;
         }
      } else {
         this.value--;
         if (this.value < var2) {
            this.value = var3;
         }
      }
   }

   @Override
   public String toString() {
      return "" + this.recoveredField1812 + "=" + this.getPropertyValue() + " [" + Config.arrayToString(this.propertyValues) + "], value: " + this.value;
   }

   public boolean loadFrom(Properties var1) {
      this.resetValue();
      if (var1 == null) {
         return false;
      } else {
         String var2 = var1.getProperty(this.recoveredField1812);
         return var2 == null ? false : this.setPropertyValue(var2);
      }
   }

   public boolean setPropertyValue(String var1) {
      if (var1 == null) {
         this.value = this.defaultValue;
         return false;
      } else {
         this.value = ArrayUtils.indexOf(this.propertyValues, var1);
         if (this.value >= 0 && this.value < this.propertyValues.length) {
            return true;
         } else {
            this.value = this.defaultValue;
            return false;
         }
      }
   }

   public void saveTo(Properties var1) {
      if (var1 != null) {
         var1.setProperty(this.method_02959(), this.getPropertyValue());
      }
   }

   public String getPropertyValue() {
      return this.propertyValues[this.value];
   }

   public String method_02959() {
      return this.recoveredField1812;
   }

   public Property(String var1, String[] var2, String var3, String[] var4, int var5) {
      this.recoveredField1812 = null;
      this.propertyValues = null;
      this.recoveredField1813 = null;
      this.userValues = null;
      this.value = 0;
      this.recoveredField1812 = var1;
      this.propertyValues = var2;
      this.recoveredField1813 = var3;
      this.userValues = var4;
      this.defaultValue = var5;
      if (var2.length != var4.length) {
         throw new IllegalArgumentException("Property and user values have different lengths: " + var2.length + " != " + var4.length);
      } else if (var5 >= 0 && var5 < var2.length) {
         this.value = var5;
      } else {
         throw new IllegalArgumentException("Invalid default value: " + var5);
      }
   }

   public String getUserValue() {
      return this.userValues[this.value];
   }

   public void resetValue() {
      this.value = this.defaultValue;
   }

   public int getValue() {
      return this.value;
   }

   public void setValue(int var1) {
      this.value = var1;
      if (this.value < 0 || this.value >= this.propertyValues.length) {
         this.value = this.defaultValue;
      }
   }
}
