package net.minecraft.world;

import net.minecraft.entity.player.EntityPlayer$EnumStatus;

public class GameRules$Value {
   public GameRules$ValueType type;
   public int valueInteger;
   public String valueString;
   public boolean valueBoolean;
   public EntityPlayer$EnumStatus field_0000;
   public double valueDouble;

   public boolean getBoolean() {
      return this.valueBoolean;
   }

   public int getInt() {
      return this.valueInteger;
   }

   public String getString() {
      return this.valueString;
   }

   public void setValue(String var1) {
      this.valueString = var1;
      if (var1 != null) {
         if (var1.equals("false")) {
            this.valueBoolean = false;
            return;
         }

         if (var1.equals("true")) {
            this.valueBoolean = true;
            return;
         }
      }

      this.valueBoolean = Boolean.parseBoolean(var1);
      this.valueInteger = this.valueBoolean ? 1 : 0;

      try {
         this.valueInteger = Integer.parseInt(var1);
      } catch (NumberFormatException var4) {
      }

      try {
         this.valueDouble = Double.parseDouble(var1);
      } catch (NumberFormatException var3) {
      }
   }

   public GameRules$ValueType getType() {
      return this.type;
   }

   public GameRules$Value(String var1, GameRules$ValueType var2) {
      this.type = var2;
      this.setValue(var1);
   }
}
