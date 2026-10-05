package org.apache.log4j.pattern;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;

public class PatternParser {
   public static Class class$org$apache$log4j$pattern$LineSeparatorPatternConverter;
   public static Class class$org$apache$log4j$pattern$LevelPatternConverter;
   public static Class class$org$apache$log4j$pattern$FullLocationPatternConverter;
   public static final int recoveredField3851 = 3;
   public static Class class$org$apache$log4j$pattern$NDCPatternConverter;
   public static Class class$org$apache$log4j$pattern$FileDatePatternConverter;
   public static Class class$org$apache$log4j$pattern$PropertiesPatternConverter;
   public static final int recoveredField3852 = 0;
   public static Class class$org$apache$log4j$pattern$ThreadPatternConverter;
   public static Class class$org$apache$log4j$pattern$DatePatternConverter;
   public static Map PATTERN_LAYOUT_RULES;
   public static Class class$org$apache$log4j$pattern$LineLocationPatternConverter;
   public static final int recoveredField3853 = 5;
   public static Class class$org$apache$log4j$pattern$LoggerPatternConverter;
   public static Class class$org$apache$log4j$pattern$SequenceNumberPatternConverter;
   public static Map FILENAME_PATTERN_RULES;
   public static Class class$org$apache$log4j$pattern$MessagePatternConverter;
   public static Class class$org$apache$log4j$pattern$RelativeTimePatternConverter;
   public static final char recoveredField3854 = 37;
   public static Class class$org$apache$log4j$pattern$MethodLocationPatternConverter;
   public static Class class$org$apache$log4j$pattern$ClassNamePatternConverter;
   public static final int recoveredField3855 = 4;
   public static Class class$org$apache$log4j$pattern$IntegerPatternConverter;
   public static final int recoveredField3856 = 1;
   public static Class class$org$apache$log4j$pattern$ThrowableInformationPatternConverter;
   public static Class class$org$apache$log4j$pattern$FileLocationPatternConverter;

   public static Map getPatternLayoutRules() {
      return PATTERN_LAYOUT_RULES;
   }

   static {
      HashMap var0 = new HashMap(17);
      var0.put(
         "c",
         class$org$apache$log4j$pattern$LoggerPatternConverter == null
            ? (class$org$apache$log4j$pattern$LoggerPatternConverter = class$("org.apache.log4j.pattern.LoggerPatternConverter"))
            : class$org$apache$log4j$pattern$LoggerPatternConverter
      );
      var0.put(
         "logger",
         class$org$apache$log4j$pattern$LoggerPatternConverter == null
            ? (class$org$apache$log4j$pattern$LoggerPatternConverter = class$("org.apache.log4j.pattern.LoggerPatternConverter"))
            : class$org$apache$log4j$pattern$LoggerPatternConverter
      );
      var0.put(
         "C",
         class$org$apache$log4j$pattern$ClassNamePatternConverter == null
            ? (class$org$apache$log4j$pattern$ClassNamePatternConverter = class$("org.apache.log4j.pattern.ClassNamePatternConverter"))
            : class$org$apache$log4j$pattern$ClassNamePatternConverter
      );
      var0.put(
         "class",
         class$org$apache$log4j$pattern$ClassNamePatternConverter == null
            ? (class$org$apache$log4j$pattern$ClassNamePatternConverter = class$("org.apache.log4j.pattern.ClassNamePatternConverter"))
            : class$org$apache$log4j$pattern$ClassNamePatternConverter
      );
      var0.put(
         "d",
         class$org$apache$log4j$pattern$DatePatternConverter == null
            ? (class$org$apache$log4j$pattern$DatePatternConverter = class$("org.apache.log4j.pattern.DatePatternConverter"))
            : class$org$apache$log4j$pattern$DatePatternConverter
      );
      var0.put(
         "date",
         class$org$apache$log4j$pattern$DatePatternConverter == null
            ? (class$org$apache$log4j$pattern$DatePatternConverter = class$("org.apache.log4j.pattern.DatePatternConverter"))
            : class$org$apache$log4j$pattern$DatePatternConverter
      );
      var0.put(
         "F",
         class$org$apache$log4j$pattern$FileLocationPatternConverter == null
            ? (class$org$apache$log4j$pattern$FileLocationPatternConverter = class$("org.apache.log4j.pattern.FileLocationPatternConverter"))
            : class$org$apache$log4j$pattern$FileLocationPatternConverter
      );
      var0.put(
         "file",
         class$org$apache$log4j$pattern$FileLocationPatternConverter == null
            ? (class$org$apache$log4j$pattern$FileLocationPatternConverter = class$("org.apache.log4j.pattern.FileLocationPatternConverter"))
            : class$org$apache$log4j$pattern$FileLocationPatternConverter
      );
      var0.put(
         "l",
         class$org$apache$log4j$pattern$FullLocationPatternConverter == null
            ? (class$org$apache$log4j$pattern$FullLocationPatternConverter = class$("org.apache.log4j.pattern.FullLocationPatternConverter"))
            : class$org$apache$log4j$pattern$FullLocationPatternConverter
      );
      var0.put(
         "L",
         class$org$apache$log4j$pattern$LineLocationPatternConverter == null
            ? (class$org$apache$log4j$pattern$LineLocationPatternConverter = class$("org.apache.log4j.pattern.LineLocationPatternConverter"))
            : class$org$apache$log4j$pattern$LineLocationPatternConverter
      );
      var0.put(
         "line",
         class$org$apache$log4j$pattern$LineLocationPatternConverter == null
            ? (class$org$apache$log4j$pattern$LineLocationPatternConverter = class$("org.apache.log4j.pattern.LineLocationPatternConverter"))
            : class$org$apache$log4j$pattern$LineLocationPatternConverter
      );
      var0.put(
         "m",
         class$org$apache$log4j$pattern$MessagePatternConverter == null
            ? (class$org$apache$log4j$pattern$MessagePatternConverter = class$("org.apache.log4j.pattern.MessagePatternConverter"))
            : class$org$apache$log4j$pattern$MessagePatternConverter
      );
      var0.put(
         "message",
         class$org$apache$log4j$pattern$MessagePatternConverter == null
            ? (class$org$apache$log4j$pattern$MessagePatternConverter = class$("org.apache.log4j.pattern.MessagePatternConverter"))
            : class$org$apache$log4j$pattern$MessagePatternConverter
      );
      var0.put(
         "n",
         class$org$apache$log4j$pattern$LineSeparatorPatternConverter == null
            ? (class$org$apache$log4j$pattern$LineSeparatorPatternConverter = class$("org.apache.log4j.pattern.LineSeparatorPatternConverter"))
            : class$org$apache$log4j$pattern$LineSeparatorPatternConverter
      );
      var0.put(
         "M",
         class$org$apache$log4j$pattern$MethodLocationPatternConverter == null
            ? (class$org$apache$log4j$pattern$MethodLocationPatternConverter = class$("org.apache.log4j.pattern.MethodLocationPatternConverter"))
            : class$org$apache$log4j$pattern$MethodLocationPatternConverter
      );
      var0.put(
         "method",
         class$org$apache$log4j$pattern$MethodLocationPatternConverter == null
            ? (class$org$apache$log4j$pattern$MethodLocationPatternConverter = class$("org.apache.log4j.pattern.MethodLocationPatternConverter"))
            : class$org$apache$log4j$pattern$MethodLocationPatternConverter
      );
      var0.put(
         "p",
         class$org$apache$log4j$pattern$LevelPatternConverter == null
            ? (class$org$apache$log4j$pattern$LevelPatternConverter = class$("org.apache.log4j.pattern.LevelPatternConverter"))
            : class$org$apache$log4j$pattern$LevelPatternConverter
      );
      var0.put(
         "level",
         class$org$apache$log4j$pattern$LevelPatternConverter == null
            ? (class$org$apache$log4j$pattern$LevelPatternConverter = class$("org.apache.log4j.pattern.LevelPatternConverter"))
            : class$org$apache$log4j$pattern$LevelPatternConverter
      );
      var0.put(
         "r",
         class$org$apache$log4j$pattern$RelativeTimePatternConverter == null
            ? (class$org$apache$log4j$pattern$RelativeTimePatternConverter = class$("org.apache.log4j.pattern.RelativeTimePatternConverter"))
            : class$org$apache$log4j$pattern$RelativeTimePatternConverter
      );
      var0.put(
         "relative",
         class$org$apache$log4j$pattern$RelativeTimePatternConverter == null
            ? (class$org$apache$log4j$pattern$RelativeTimePatternConverter = class$("org.apache.log4j.pattern.RelativeTimePatternConverter"))
            : class$org$apache$log4j$pattern$RelativeTimePatternConverter
      );
      var0.put(
         "t",
         class$org$apache$log4j$pattern$ThreadPatternConverter == null
            ? (class$org$apache$log4j$pattern$ThreadPatternConverter = class$("org.apache.log4j.pattern.ThreadPatternConverter"))
            : class$org$apache$log4j$pattern$ThreadPatternConverter
      );
      var0.put(
         "thread",
         class$org$apache$log4j$pattern$ThreadPatternConverter == null
            ? (class$org$apache$log4j$pattern$ThreadPatternConverter = class$("org.apache.log4j.pattern.ThreadPatternConverter"))
            : class$org$apache$log4j$pattern$ThreadPatternConverter
      );
      var0.put(
         "x",
         class$org$apache$log4j$pattern$NDCPatternConverter == null
            ? (class$org$apache$log4j$pattern$NDCPatternConverter = class$("org.apache.log4j.pattern.NDCPatternConverter"))
            : class$org$apache$log4j$pattern$NDCPatternConverter
      );
      var0.put(
         "ndc",
         class$org$apache$log4j$pattern$NDCPatternConverter == null
            ? (class$org$apache$log4j$pattern$NDCPatternConverter = class$("org.apache.log4j.pattern.NDCPatternConverter"))
            : class$org$apache$log4j$pattern$NDCPatternConverter
      );
      var0.put(
         "X",
         class$org$apache$log4j$pattern$PropertiesPatternConverter == null
            ? (class$org$apache$log4j$pattern$PropertiesPatternConverter = class$("org.apache.log4j.pattern.PropertiesPatternConverter"))
            : class$org$apache$log4j$pattern$PropertiesPatternConverter
      );
      var0.put(
         "properties",
         class$org$apache$log4j$pattern$PropertiesPatternConverter == null
            ? (class$org$apache$log4j$pattern$PropertiesPatternConverter = class$("org.apache.log4j.pattern.PropertiesPatternConverter"))
            : class$org$apache$log4j$pattern$PropertiesPatternConverter
      );
      var0.put(
         "sn",
         class$org$apache$log4j$pattern$SequenceNumberPatternConverter == null
            ? (class$org$apache$log4j$pattern$SequenceNumberPatternConverter = class$("org.apache.log4j.pattern.SequenceNumberPatternConverter"))
            : class$org$apache$log4j$pattern$SequenceNumberPatternConverter
      );
      var0.put(
         "sequenceNumber",
         class$org$apache$log4j$pattern$SequenceNumberPatternConverter == null
            ? (class$org$apache$log4j$pattern$SequenceNumberPatternConverter = class$("org.apache.log4j.pattern.SequenceNumberPatternConverter"))
            : class$org$apache$log4j$pattern$SequenceNumberPatternConverter
      );
      var0.put(
         "throwable",
         class$org$apache$log4j$pattern$ThrowableInformationPatternConverter == null
            ? (class$org$apache$log4j$pattern$ThrowableInformationPatternConverter = class$("org.apache.log4j.pattern.ThrowableInformationPatternConverter"))
            : class$org$apache$log4j$pattern$ThrowableInformationPatternConverter
      );
      PATTERN_LAYOUT_RULES = new PatternParser.ReadOnlyMap(var0);
      HashMap var1 = new HashMap(4);
      var1.put(
         "d",
         class$org$apache$log4j$pattern$FileDatePatternConverter == null
            ? (class$org$apache$log4j$pattern$FileDatePatternConverter = class$("org.apache.log4j.pattern.FileDatePatternConverter"))
            : class$org$apache$log4j$pattern$FileDatePatternConverter
      );
      var1.put(
         "date",
         class$org$apache$log4j$pattern$FileDatePatternConverter == null
            ? (class$org$apache$log4j$pattern$FileDatePatternConverter = class$("org.apache.log4j.pattern.FileDatePatternConverter"))
            : class$org$apache$log4j$pattern$FileDatePatternConverter
      );
      var1.put(
         "i",
         class$org$apache$log4j$pattern$IntegerPatternConverter == null
            ? (class$org$apache$log4j$pattern$IntegerPatternConverter = class$("org.apache.log4j.pattern.IntegerPatternConverter"))
            : class$org$apache$log4j$pattern$IntegerPatternConverter
      );
      var1.put(
         "index",
         class$org$apache$log4j$pattern$IntegerPatternConverter == null
            ? (class$org$apache$log4j$pattern$IntegerPatternConverter = class$("org.apache.log4j.pattern.IntegerPatternConverter"))
            : class$org$apache$log4j$pattern$IntegerPatternConverter
      );
      FILENAME_PATTERN_RULES = new PatternParser.ReadOnlyMap(var1);
   }

   public static int extractConverter(char var0, String var1, int var2, StringBuffer var3, StringBuffer var4) {
      var3.setLength(0);
      if (!Character.isUnicodeIdentifierStart(var0)) {
         return var2;
      } else {
         var3.append(var0);

         while (var2 < var1.length() && Character.isUnicodeIdentifierPart(var1.charAt(var2))) {
            var3.append(var1.charAt(var2));
            var4.append(var1.charAt(var2));
            var2++;
         }

         return var2;
      }
   }

   public static int finalizeConverter(char var0, String var1, int var2, StringBuffer var3, FormattingInfo var4, Map var5, Map var6, List var7, List var8) {
      StringBuffer var9 = new StringBuffer();
      var2 = extractConverter(var0, var1, var2, var9, var3);
      String var10 = var9.toString();
      ArrayList var11 = new ArrayList();
      var2 = extractOptions(var1, var2, var11);
      PatternConverter var12 = createConverter(var10, var3, var5, var6, var11);
      if (var12 == null) {
         StringBuffer var13;
         if (var10 != null && var10.length() != 0) {
            var13 = new StringBuffer("Unrecognized conversion specifier [");
            var13.append(var10);
            var13.append("] starting at position ");
         } else {
            var13 = new StringBuffer("Empty conversion specifier starting at position ");
         }

         var13.append(Integer.toString(var2));
         var13.append(" in conversion pattern.");
         LogLog.error(var13.toString());
         var7.add(new LiteralPatternConverter(var3.toString()));
         var8.add(FormattingInfo.getDefault());
      } else {
         var7.add(var12);
         var8.add(var4);
         if (var3.length() > 0) {
            var7.add(new LiteralPatternConverter(var3.toString()));
            var8.add(FormattingInfo.getDefault());
         }
      }

      var3.setLength(0);
      return var2;
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void parse(String var0, List var1, List var2, Map var3, Map var4) {
      if (var0 == null) {
         throw new NullPointerException("pattern");
      } else {
         StringBuffer var5 = new StringBuffer(32);
         int var6 = var0.length();
         byte var7 = 0;
         int var9 = 0;
         FormattingInfo var10 = FormattingInfo.getDefault();

         while (var9 < var6) {
            char var8 = var0.charAt(var9++);
            switch (var7) {
               case 0:
                  if (var9 == var6) {
                     var5.append(var8);
                  } else if (var8 == '%') {
                     switch (var0.charAt(var9)) {
                        case '%':
                           var5.append(var8);
                           var9++;
                           break;
                        default:
                           if (var5.length() != 0) {
                              var1.add(new LiteralPatternConverter(var5.toString()));
                              var2.add(FormattingInfo.getDefault());
                           }

                           var5.setLength(0);
                           var5.append(var8);
                           var7 = 1;
                           var10 = FormattingInfo.getDefault();
                     }
                  } else {
                     var5.append(var8);
                  }
                  break;
               case 1:
                  var5.append(var8);
                  switch (var8) {
                     case '-':
                        var10 = new FormattingInfo(true, var10.getMinLength(), var10.getMaxLength());
                        break;
                     case '.':
                        var7 = 3;
                        break;
                     default:
                        if (var8 >= '0' && var8 <= '9') {
                           var10 = new FormattingInfo(var10.isLeftAligned(), var8 - '0', var10.getMaxLength());
                           var7 = 4;
                        } else {
                           var9 = finalizeConverter(var8, var0, var9, var5, var10, var3, var4, var1, var2);
                           var7 = 0;
                           var10 = FormattingInfo.getDefault();
                           var5.setLength(0);
                        }
                  }
               case 2:
               default:
                  break;
               case 3:
                  var5.append(var8);
                  if (var8 >= '0' && var8 <= '9') {
                     var10 = new FormattingInfo(var10.isLeftAligned(), var10.getMinLength(), var8 - '0');
                     var7 = 5;
                     break;
                  }

                  LogLog.error("Error occured in position " + var9 + ".\n Was expecting digit, instead got char \"" + var8 + "\".");
                  var7 = 0;
                  break;
               case 4:
                  var5.append(var8);
                  if (var8 >= '0' && var8 <= '9') {
                     var10 = new FormattingInfo(var10.isLeftAligned(), var10.getMinLength() * 10 + (var8 - '0'), var10.getMaxLength());
                  } else if (var8 == '.') {
                     var7 = 3;
                  } else {
                     var9 = finalizeConverter(var8, var0, var9, var5, var10, var3, var4, var1, var2);
                     var7 = 0;
                     var10 = FormattingInfo.getDefault();
                     var5.setLength(0);
                  }
                  break;
               case 5:
                  var5.append(var8);
                  if (var8 >= '0' && var8 <= '9') {
                     var10 = new FormattingInfo(var10.isLeftAligned(), var10.getMinLength(), var10.getMaxLength() * 10 + (var8 - '0'));
                  } else {
                     var9 = finalizeConverter(var8, var0, var9, var5, var10, var3, var4, var1, var2);
                     var7 = 0;
                     var10 = FormattingInfo.getDefault();
                     var5.setLength(0);
                  }
            }
         }

         if (var5.length() != 0) {
            var1.add(new LiteralPatternConverter(var5.toString()));
            var2.add(FormattingInfo.getDefault());
         }
      }
   }

   public static int extractOptions(String var0, int var1, List var2) {
      while (var1 < var0.length() && var0.charAt(var1) == '{') {
         int var3 = var0.indexOf(125, var1);
         if (var3 != -1) {
            String var4 = var0.substring(var1 + 1, var3);
            var2.add(var4);
            var1 = var3 + 1;
            continue;
         }
         break;
      }

      return var1;
   }

   public static PatternConverter createConverter(String var0, StringBuffer var1, Map var2, Map var3, List var4) {
      String var5 = var0;
      Object var6 = null;

      for (int var7 = var0.length(); var7 > 0 && var6 == null; var7--) {
         var5 = var5.substring(0, var7);
         if (var2 != null) {
            var6 = var2.get(var5);
         }

         if (var6 == null && var3 != null) {
            var6 = var3.get(var5);
         }
      }

      if (var6 == null) {
         LogLog.error("Unrecognized format specifier [" + var0 + "]");
         return null;
      } else {
         Class var14 = null;
         if (var6 instanceof Class) {
            var14 = (Class)var6;
         } else {
            if (!(var6 instanceof String)) {
               LogLog.warn("Bad map entry for conversion pattern %" + var5 + ".");
               return null;
            }

            try {
               var14 = Loader.loadClass((String)var6);
            } catch (ClassNotFoundException var11) {
               LogLog.warn("Class for conversion pattern %" + var5 + " not found", var11);
               return null;
            }
         }

         try {
            Method var8 = var14.getMethod("newInstance", Class.forName("[Ljava.lang.String;"));
            String[] var16 = new String[var4.size()];
            var16 = (java.lang.String[])var4.toArray(var16);
            Object var10 = var8.invoke(null, var16);
            if (var10 instanceof PatternConverter) {
               var1.delete(0, var1.length() - (var0.length() - var5.length()));
               return (PatternConverter)var10;
            }

            LogLog.warn("Class " + var14.getName() + " does not extend PatternConverter.");
         } catch (Exception var13) {
            LogLog.error("Error creating converter for " + var0, var13);

            try {
               PatternConverter var9 = (PatternConverter)var14.newInstance();
               var1.delete(0, var1.length() - (var0.length() - var5.length()));
               return var9;
            } catch (Exception var12) {
               LogLog.error("Error creating converter for " + var0, var12);
            }
         }

         return null;
      }
   }

   public static Map getFileNamePatternRules() {
      return FILENAME_PATTERN_RULES;
   }

   public static class ReadOnlyMap implements Map {
      public Map map;

      public Set entrySet() {
         return this.map.entrySet();
      }

      public Set keySet() {
         return this.map.keySet();
      }

      public boolean containsKey(Object var1) {
         return this.map.containsKey(var1);
      }

      public void clear() {
         throw new UnsupportedOperationException();
      }

      public Object remove(Object var1) {
         throw new UnsupportedOperationException();
      }

      public ReadOnlyMap(Map var1) {
         this.map = var1;
      }

      public void putAll(Map var1) {
         throw new UnsupportedOperationException();
      }

      public boolean isEmpty() {
         return this.map.isEmpty();
      }

      public int size() {
         return this.map.size();
      }

      public boolean containsValue(Object var1) {
         return this.map.containsValue(var1);
      }

      public Collection values() {
         return this.map.values();
      }

      public Object get(Object var1) {
         return this.map.get(var1);
      }

      public Object put(Object var1, Object var2) {
         throw new UnsupportedOperationException();
      }
   }
}
