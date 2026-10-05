package net.optifine.config;

import com.cheatbreaker.client.ui.overlay.element.ConsoleElement;
import java.util.Arrays;
import java.util.regex.Pattern;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.src.Config;
import net.optifine.util.StrUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class NbtTagValue {
   public static int field_0008;
   public int valueFormat;
   public ConsoleElement field_0007;
   public static Pattern PATTERN_HEX_COLOR = Pattern.compile("^#[0-9a-f]{6}+$");
   public String name;
   public static String field_0003;
   public static int field_0018;
   public static int field_0012;
   public boolean negative;
   public static int field_0019;
   public int type;
   public static String field_0009;
   public static int field_0011;
   public String value;
   public String[] parents = null;
   public static String field_0016;
   public static String field_0000;
   public static String field_0005;
   public static int field_0010;
   public static int field_0015;

   public boolean matches(NBTTagCompound var1) {
      return this.negative ? !this.matchesCompound(var1) : this.matchesCompound(var1);
   }

   public boolean matchesCompound(NBTTagCompound var1) {
      if (var1 == null) {
         return false;
      } else {
         Object var2 = var1;

         for (int var3 = 0; var3 < this.parents.length; var3++) {
            String var4 = this.parents[var3];
            var2 = getChildTag((NBTBase)var2, var4);
            if (var2 == null) {
               return false;
            }
         }

         if (this.name.equals("*")) {
            return this.matchesAnyChild((NBTBase)var2);
         } else {
            var2 = getChildTag((NBTBase)var2, this.name);
            return var2 == null ? false : this.matchesBase((NBTBase)var2);
         }
      }
   }

   public NbtTagValue(String var1, String var2) {
      this.name = null;
      this.negative = false;
      this.type = 0;
      this.value = null;
      this.valueFormat = 0;
      String[] var3 = Config.tokenize(var1, ".");
      this.parents = Arrays.copyOfRange(var3, 0, var3.length - 1);
      this.name = var3[var3.length - 1];
      if (var2.startsWith("!")) {
         this.negative = true;
         var2 = var2.substring(1);
      }

      if (var2.startsWith("pattern:")) {
         this.type = 1;
         var2 = var2.substring("pattern:".length());
      } else if (var2.startsWith("ipattern:")) {
         this.type = 2;
         var2 = var2.substring("ipattern:".length()).toLowerCase();
      } else if (var2.startsWith("regex:")) {
         this.type = 3;
         var2 = var2.substring("regex:".length());
      } else if (var2.startsWith("iregex:")) {
         this.type = 4;
         var2 = var2.substring("iregex:".length()).toLowerCase();
      } else {
         this.type = 0;
      }

      var2 = StringEscapeUtils.unescapeJava(var2);
      if (this.type == 0 && PATTERN_HEX_COLOR.matcher(var2).matches()) {
         this.valueFormat = 1;
      }

      this.value = var2;
   }

   @Override
   public String toString() {
      StringBuffer var1 = new StringBuffer();

      for (int var2 = 0; var2 < this.parents.length; var2++) {
         String var3 = this.parents[var2];
         if (var2 > 0) {
            var1.append(".");
         }

         var1.append(var3);
      }

      if (var1.length() > 0) {
         var1.append(".");
      }

      var1.append(this.name);
      var1.append(" = ");
      var1.append(this.value);
      return var1.toString();
   }

   public boolean matchesPattern(String var1, String var2) {
      return StrUtils.equalsMask(var1, var2, '*', '?');
   }

   public boolean matchesValue(String var1) {
      if (var1 == null) {
         return false;
      } else {
         switch (this.type) {
            case 0:
               return var1.equals(this.value);
            case 1:
               return this.matchesPattern(var1, this.value);
            case 2:
               return this.matchesPattern(var1.toLowerCase(), this.value);
            case 3:
               return this.matchesRegex(var1, this.value);
            case 4:
               return this.matchesRegex(var1.toLowerCase(), this.value);
            default:
               throw new IllegalArgumentException("Unknown NbtTagValue type: " + this.type);
         }
      }
   }

   public static NBTBase getChildTag(NBTBase var0, String var1) {
      if (var0 instanceof NBTTagCompound) {
         NBTTagCompound var4 = (NBTTagCompound)var0;
         return var4.getTag(var1);
      } else if (var0 instanceof NBTTagList) {
         NBTTagList var2 = (NBTTagList)var0;
         if (var1.equals("count")) {
            return new NBTTagInt(var2.tagCount());
         } else {
            int var3 = Config.parseInt(var1, -1);
            return var3 >= 0 && var3 < var2.tagCount() ? var2.get(var3) : null;
         }
      } else {
         return null;
      }
   }

   public boolean matchesRegex(String var1, String var2) {
      return var1.matches(var2);
   }

   public boolean matchesAnyChild(NBTBase var1) {
      if (var1 instanceof NBTTagCompound) {
         NBTTagCompound var2 = (NBTTagCompound)var1;

         for (String var4 : var2.getKeySet()) {
            NBTBase var5 = var2.getTag(var4);
            if (this.matchesBase(var5)) {
               return true;
            }
         }
      }

      if (var1 instanceof NBTTagList) {
         NBTTagList var6 = (NBTTagList)var1;
         int var7 = var6.tagCount();

         for (int var8 = 0; var8 < var7; var8++) {
            NBTBase var9 = var6.get(var8);
            if (this.matchesBase(var9)) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean matchesBase(NBTBase var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = getNbtString(var1, this.valueFormat);
         return this.matchesValue(var2);
      }
   }

   public static String getNbtString(NBTBase var0, int var1) {
      if (var0 == null) {
         return null;
      } else if (var0 instanceof NBTTagString) {
         NBTTagString var8 = (NBTTagString)var0;
         return var8.getString();
      } else if (var0 instanceof NBTTagInt) {
         NBTTagInt var7 = (NBTTagInt)var0;
         return var1 == 1 ? "#" + StrUtils.fillLeft(Integer.toHexString(var7.getInt()), 6, '0') : Integer.toString(var7.getInt());
      } else if (var0 instanceof NBTTagByte) {
         NBTTagByte var6 = (NBTTagByte)var0;
         return Byte.toString(var6.getByte());
      } else if (var0 instanceof NBTTagShort) {
         NBTTagShort var5 = (NBTTagShort)var0;
         return Short.toString(var5.getShort());
      } else if (var0 instanceof NBTTagLong) {
         NBTTagLong var4 = (NBTTagLong)var0;
         return Long.toString(var4.getLong());
      } else if (var0 instanceof NBTTagFloat) {
         NBTTagFloat var3 = (NBTTagFloat)var0;
         return Float.toString(var3.getFloat());
      } else if (var0 instanceof NBTTagDouble) {
         NBTTagDouble var2 = (NBTTagDouble)var0;
         return Double.toString(var2.getDouble());
      } else {
         return var0.toString();
      }
   }
}
