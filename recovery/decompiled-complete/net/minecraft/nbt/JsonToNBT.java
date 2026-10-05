package net.minecraft.nbt;

import io.netty.channel.sctp.nio.NioSctpServerChannel;
import java.util.Stack;
import java.util.regex.Pattern;
import net.minecraft.world.gen.structure.StructureComponent$BlockSelector;
import net.optifine.gui.GuiQualitySettingsOF;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonToNBT {
   public GuiQualitySettingsOF field_0002;
   public static Logger logger = LogManager.getLogger();
   public NioSctpServerChannel field_0001;
   public static Pattern field_179273_b = Pattern.compile("\\[[-+\\d|,\\s]+\\]");
   public StructureComponent$BlockSelector field_0000;

   public static String func_150311_c(String var0, boolean var1) {
      if (var1) {
         var0 = var0.trim();
         if (var0.startsWith("{") || var0.startsWith("[")) {
            return var0;
         }
      }

      int var2 = func_150312_a(var0, ':');
      if (var2 != -1) {
         return var0.substring(var2 + 1).trim();
      } else if (var1) {
         return var0;
      } else {
         throw new NBTException("Unable to locate name/value separator for string: " + var0);
      }
   }

   public static JsonToNBT$Any func_179270_a(String var0, boolean var1) {
      String var2 = func_150313_b(var0, var1);
      String var3 = func_150311_c(var0, var1);
      return func_179272_a(var2, var3);
   }

   public static String func_150314_a(String var0, boolean var1) {
      int var2 = func_150312_a(var0, ':');
      int var3 = func_150312_a(var0, ',');
      if (var1) {
         if (var2 == -1) {
            throw new NBTException("Unable to locate name/value separator for string: " + var0);
         }

         if (var3 != -1 && var3 < var2) {
            throw new NBTException("Name error at: " + var0);
         }
      } else if (var2 == -1 || var2 > var3) {
         var2 = -1;
      }

      return func_179269_a(var0, var2);
   }

   public static JsonToNBT$Any func_179272_a(String... var0) {
      return func_150316_a(var0[0], var0[1]);
   }

   public static int func_150310_b(String var0) {
      int var1 = 0;
      boolean var2 = false;
      Stack var3 = new Stack();

      for (int var4 = 0; var4 < var0.length(); var4++) {
         char var5 = var0.charAt(var4);
         if (var5 == '"') {
            if (func_179271_b(var0, var4)) {
               if (!var2) {
                  throw new NBTException("Illegal use of \\\": " + var0);
               }
            } else {
               var2 = !var2;
            }
         } else if (!var2) {
            if (var5 != '{' && var5 != '[') {
               if (var5 == '}' && (var3.isEmpty() || (Character)var3.pop() != '{')) {
                  throw new NBTException("Unbalanced curly brackets {}: " + var0);
               }

               if (var5 == ']' && (var3.isEmpty() || (Character)var3.pop() != '[')) {
                  throw new NBTException("Unbalanced square brackets []: " + var0);
               }
            } else {
               if (var3.isEmpty()) {
                  var1++;
               }

               var3.push(var5);
            }
         }
      }

      if (var2) {
         throw new NBTException("Unbalanced quotation: " + var0);
      } else if (!var3.isEmpty()) {
         throw new NBTException("Unbalanced brackets: " + var0);
      } else {
         if (var1 == 0 && !var0.isEmpty()) {
            var1 = 1;
         }

         return var1;
      }
   }

   public static NBTTagCompound getTagFromJson(String var0) {
      var0 = var0.trim();
      if (!var0.startsWith("{")) {
         throw new NBTException("Invalid tag encountered, expected '{' as first char.");
      } else if (func_150310_b(var0) != 1) {
         throw new NBTException("Encountered multiple top tags, only one expected");
      } else {
         return (NBTTagCompound)func_150316_a("tag", var0).parse();
      }
   }

   public static JsonToNBT$Any func_150316_a(String var0, String var1) {
      var1 = var1.trim();
      if (var1.startsWith("{")) {
         var1 = var1.substring(1, var1.length() - 1);
         JsonToNBT$Compound var8 = new JsonToNBT$Compound(var0);

         while (var1.length() > 0) {
            String var9 = func_150314_a(var1, true);
            if (var9.length() > 0) {
               boolean var11 = false;
               var8.field_150491_b.add(func_179270_a(var9, var11));
            }

            if (var1.length() < var9.length() + 1) {
               break;
            }

            char var12 = var1.charAt(var9.length());
            if (var12 != ',' && var12 != '{' && var12 != '}' && var12 != '[' && var12 != ']') {
               throw new NBTException("Unexpected token '" + var12 + "' at: " + var1.substring(var9.length()));
            }

            var1 = var1.substring(var9.length() + 1);
         }

         return var8;
      } else if (var1.startsWith("[") && !field_179273_b.matcher(var1).matches()) {
         var1 = var1.substring(1, var1.length() - 1);
         JsonToNBT$List var2 = new JsonToNBT$List(var0);

         while (var1.length() > 0) {
            String var3 = func_150314_a(var1, false);
            if (var3.length() > 0) {
               boolean var4 = true;
               var2.field_150492_b.add(func_179270_a(var3, var4));
            }

            if (var1.length() < var3.length() + 1) {
               break;
            }

            char var10 = var1.charAt(var3.length());
            if (var10 != ',' && var10 != '{' && var10 != '}' && var10 != '[' && var10 != ']') {
               throw new NBTException("Unexpected token '" + var10 + "' at: " + var1.substring(var3.length()));
            }

            var1 = var1.substring(var3.length() + 1);
         }

         return var2;
      } else {
         return new JsonToNBT$Primitive(var0, var1);
      }
   }

   public static boolean func_179271_b(String var0, int var1) {
      return var1 > 0 && var0.charAt(var1 - 1) == '\\' && !func_179271_b(var0, var1 - 1);
   }

   public static String func_150313_b(String var0, boolean var1) {
      if (var1) {
         var0 = var0.trim();
         if (var0.startsWith("{") || var0.startsWith("[")) {
            return "";
         }
      }

      int var2 = func_150312_a(var0, ':');
      if (var2 != -1) {
         return var0.substring(0, var2).trim();
      } else if (var1) {
         return "";
      } else {
         throw new NBTException("Unable to locate name/value separator for string: " + var0);
      }
   }

   public static int func_150312_a(String var0, char var1) {
      int var2 = 0;

      for (boolean var3 = true; var2 < var0.length(); var2++) {
         char var4 = var0.charAt(var2);
         if (var4 == '"') {
            if (!func_179271_b(var0, var2)) {
               var3 = !var3;
            }
         } else if (var3) {
            if (var4 == var1) {
               return var2;
            }

            if (var4 == '{' || var4 == '[') {
               return -1;
            }
         }
      }

      return -1;
   }

   public static String func_179269_a(String var0, int var1) {
      Stack var2 = new Stack();
      int var3 = var1 + 1;
      boolean var4 = false;
      boolean var5 = false;
      boolean var6 = false;

      for (int var7 = 0; var3 < var0.length(); var3++) {
         char var8 = var0.charAt(var3);
         if (var8 == '"') {
            if (func_179271_b(var0, var3)) {
               if (!var4) {
                  throw new NBTException("Illegal use of \\\": " + var0);
               }
            } else {
               var4 = !var4;
               if (var4 && !var6) {
                  var5 = true;
               }

               if (!var4) {
                  var7 = var3;
               }
            }
         } else if (!var4) {
            if (var8 != '{' && var8 != '[') {
               if (var8 == '}' && (var2.isEmpty() || (Character)var2.pop() != '{')) {
                  throw new NBTException("Unbalanced curly brackets {}: " + var0);
               }

               if (var8 == ']' && (var2.isEmpty() || (Character)var2.pop() != '[')) {
                  throw new NBTException("Unbalanced square brackets []: " + var0);
               }

               if (var8 == ',' && var2.isEmpty()) {
                  return var0.substring(0, var3);
               }
            } else {
               var2.push(var8);
            }
         }

         if (!Character.isWhitespace(var8)) {
            if (!var4 && var5 && var7 != var3) {
               return var0.substring(0, var7 + 1);
            }

            var6 = true;
         }
      }

      return var0.substring(0, var3);
   }
}
