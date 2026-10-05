package org.apache.log4j;

public class NameValue {
   public String value;
   public String key;

   public NameValue(String var1, String var2) {
      this.key = var1;
      this.value = var2;
   }

   public String toString() {
      return this.key + "=" + this.value;
   }
}
