package net.minecraft.nbt;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import io.netty.util.internal.chmv8.ForkJoinPool$1;
import java.util.regex.Pattern;

public class JsonToNBT$Primitive extends JsonToNBT$Any {
   public static Pattern BYTE = Pattern.compile("[-+]?[0-9]+[b|B]");
   public static Pattern DOUBLE_UNTYPED = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
   public static Pattern LONG = Pattern.compile("[-+]?[0-9]+[l|L]");
   public static Pattern SHORT = Pattern.compile("[-+]?[0-9]+[s|S]");
   public static Pattern INTEGER = Pattern.compile("[-+]?[0-9]+");
   public static Splitter SPLITTER = Splitter.on(',').omitEmptyStrings();
   public ForkJoinPool$1 field_0009;
   public static Pattern FLOAT = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[f|F]");
   public String jsonValue;
   public static Pattern DOUBLE = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+[d|D]");

   @Override
   public NBTBase parse() {
      try {
         if (DOUBLE.matcher(this.jsonValue).matches()) {
            return new NBTTagDouble(Double.parseDouble(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
         }

         if (FLOAT.matcher(this.jsonValue).matches()) {
            return new NBTTagFloat(Float.parseFloat(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
         }

         if (BYTE.matcher(this.jsonValue).matches()) {
            return new NBTTagByte(Byte.parseByte(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
         }

         if (LONG.matcher(this.jsonValue).matches()) {
            return new NBTTagLong(Long.parseLong(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
         }

         if (SHORT.matcher(this.jsonValue).matches()) {
            return new NBTTagShort(Short.parseShort(this.jsonValue.substring(0, this.jsonValue.length() - 1)));
         }

         if (INTEGER.matcher(this.jsonValue).matches()) {
            return new NBTTagInt(Integer.parseInt(this.jsonValue));
         }

         if (DOUBLE_UNTYPED.matcher(this.jsonValue).matches()) {
            return new NBTTagDouble(Double.parseDouble(this.jsonValue));
         }

         if (this.jsonValue.equalsIgnoreCase("true") || this.jsonValue.equalsIgnoreCase("false")) {
            return new NBTTagByte((byte)(Boolean.parseBoolean(this.jsonValue) ? 1 : 0));
         }
      } catch (NumberFormatException var6) {
         this.jsonValue = this.jsonValue.replaceAll("\\\\\"", "\"");
         return new NBTTagString(this.jsonValue);
      }

      if (this.jsonValue.startsWith("[") && this.jsonValue.endsWith("]")) {
         String var7 = this.jsonValue.substring(1, this.jsonValue.length() - 1);
         String[] var8 = (String[])Iterables.toArray(SPLITTER.split(var7), String.class);

         try {
            int[] var3 = new int[var8.length];

            for (int var4 = 0; var4 < var8.length; var4++) {
               var3[var4] = Integer.parseInt(var8[var4].trim());
            }

            return new NBTTagIntArray(var3);
         } catch (NumberFormatException var5) {
            return new NBTTagString(this.jsonValue);
         }
      } else {
         if (this.jsonValue.startsWith("\"") && this.jsonValue.endsWith("\"")) {
            this.jsonValue = this.jsonValue.substring(1, this.jsonValue.length() - 1);
         }

         this.jsonValue = this.jsonValue.replaceAll("\\\\\"", "\"");
         StringBuilder var1 = new StringBuilder();

         for (int var2 = 0; var2 < this.jsonValue.length(); var2++) {
            if (var2 < this.jsonValue.length() - 1 && this.jsonValue.charAt(var2) == '\\' && this.jsonValue.charAt(var2 + 1) == '\\') {
               var1.append('\\');
               var2++;
            } else {
               var1.append(this.jsonValue.charAt(var2));
            }
         }

         return new NBTTagString(var1.toString());
      }
   }

   public JsonToNBT$Primitive(String var1, String var2) {
      this.a = var1;
      this.jsonValue = var2;
   }
}
