package net.minecraft.world;

import java.util.Set;
import java.util.TreeMap;
import net.minecraft.nbt.NBTTagCompound;

public class GameRules {
   public TreeMap<String, GameRules.Value> theGameRules = new TreeMap<>();

   public String[] getRules() {
      Set var1 = this.theGameRules.keySet();
      return (java.lang.String[])var1.toArray(new String[var1.size()]);
   }

   public boolean areSameType(String var1, GameRules.ValueType var2) {
      GameRules.Value var3 = this.theGameRules.get(var1);
      return var3 != null && (var3.getType() == var2 || var2 == GameRules.ValueType.ANY_VALUE);
   }

   public NBTTagCompound writeToNBT() {
      NBTTagCompound var1 = new NBTTagCompound();

      for (String var3 : this.theGameRules.keySet()) {
         GameRules.Value var4 = this.theGameRules.get(var3);
         var1.setString(var3, var4.getString());
      }

      return var1;
   }

   public void setOrCreateGameRule(String var1, String var2) {
      GameRules.Value var3 = this.theGameRules.get(var1);
      if (var3 != null) {
         var3.setValue(var2);
      } else {
         this.addGameRule(var1, var2, GameRules.ValueType.ANY_VALUE);
      }
   }

   public boolean hasRule(String var1) {
      return this.theGameRules.containsKey(var1);
   }

   public void addGameRule(String var1, String var2, GameRules.ValueType var3) {
      this.theGameRules.put(var1, new GameRules.Value(var2, var3));
   }

   public String getString(String var1) {
      GameRules.Value var2 = this.theGameRules.get(var1);
      return var2 != null ? var2.getString() : "";
   }

   public void readFromNBT(NBTTagCompound var1) {
      for (String var3 : var1.getKeySet()) {
         String var4 = var1.getString(var3);
         this.setOrCreateGameRule(var3, var4);
      }
   }

   public boolean getBoolean(String var1) {
      GameRules.Value var2 = this.theGameRules.get(var1);
      return var2 != null ? var2.getBoolean() : false;
   }

   public GameRules() {
      this.addGameRule("doFireTick", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("mobGriefing", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("keepInventory", "false", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doMobSpawning", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doMobLoot", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doTileDrops", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doEntityDrops", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("commandBlockOutput", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("naturalRegeneration", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("doDaylightCycle", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("logAdminCommands", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("showDeathMessages", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("randomTickSpeed", "3", GameRules.ValueType.NUMERICAL_VALUE);
      this.addGameRule("sendCommandFeedback", "true", GameRules.ValueType.BOOLEAN_VALUE);
      this.addGameRule("reducedDebugInfo", "false", GameRules.ValueType.BOOLEAN_VALUE);
   }

   public int getInt(String var1) {
      GameRules.Value var2 = this.theGameRules.get(var1);
      return var2 != null ? var2.getInt() : 0;
   }

   public static class Value {
      public GameRules.ValueType type;
      public int valueInteger;
      public String valueString;
      public boolean valueBoolean;
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

      public GameRules.ValueType getType() {
         return this.type;
      }

      public Value(String var1, GameRules.ValueType var2) {
         this.type = var2;
         this.setValue(var1);
      }
   }

   public static enum ValueType {
      ANY_VALUE,
      BOOLEAN_VALUE,
      NUMERICAL_VALUE;
      // $VF: synthetic field
      public static GameRules.ValueType[] $VALUES = new GameRules.ValueType[]{
         ANY_VALUE, GameRules.ValueType.BOOLEAN_VALUE, GameRules.ValueType.NUMERICAL_VALUE
      };
   }
}
