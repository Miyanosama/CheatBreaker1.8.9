package net.optifine.shaders.config;

import net.optifine.Lang;

public class PropertyDefaultTrueFalse extends Property {
   public static String[] PROPERTY_VALUES = new String[]{"default", "true", "false"};
   public static String[] USER_VALUES = new String[]{"Default", "ON", "OFF"};

   public PropertyDefaultTrueFalse(String var1, String var2, int var3) {
      super(var1, PROPERTY_VALUES, var2, USER_VALUES, var3);
   }

   public boolean isFalse() {
      return this.getValue() == 2;
   }

   public boolean isDefault() {
      return this.getValue() == 0;
   }

   public boolean isTrue() {
      return this.getValue() == 1;
   }

   @Override
   public String getUserValue() {
      return this.isDefault() ? Lang.getDefault() : (this.isTrue() ? Lang.getOn() : (this.isFalse() ? Lang.getOff() : super.getUserValue()));
   }
}
