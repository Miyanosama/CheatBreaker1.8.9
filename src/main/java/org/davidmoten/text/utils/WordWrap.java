package org.davidmoten.text.utils;

import org.davidmoten.text.utils.CharSequenceConcatRightTrim;

import org.davidmoten.text.utils.StringBuilder2;

import org.davidmoten.text.utils.LineConsumer;

import org.davidmoten.text.utils.WordWrap$Builder;

import com.google.common.base.Preconditions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import org.apache.commons.io.input.CharSequenceReader;

public class WordWrap {
   public static final String recoveredField3019 = "\"'\u2018\u2019\u201c\u201d?./!,;:_";
   public static final String recoveredField3021 = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
   public static Set<Character> recoveredField3020 = method_05912("\"'‘’“”?./!,;:_");
   public static Function<CharSequence, Number> recoveredField3018 = var0 -> var0.length();

   public static WordWrap$Builder method_05903(File var0, Charset var1) {
      try {
         return method_05909(new BufferedReader(new InputStreamReader(new FileInputStream(var0), var1)), true);
      } catch (FileNotFoundException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void method_05906(Reader var0) {
      try {
         var0.close();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   // $VF: synthetic method
   public static Function method_05901() {
      return recoveredField3018;
   }

   public static void method_05918(StringBuilder2 var0, StringBuilder2 var1) {
      var0.method_27552(var1);
      var1.method_27546(0);
   }

   public static void method_05908(
      Reader var0,
      LineConsumer var1,
      Number var2,
      Function<? super CharSequence, ? extends Number> var3,
      Set<Character> var4,
      boolean var5,
      boolean var6
   ) throws java.io.IOException {
      StringBuilder2 var7 = new StringBuilder2();
      StringBuilder2 var8 = new StringBuilder2();
      CharSequence var9 = method_05911(var7, var8);
      double var10 = var2.doubleValue();
      boolean var12 = false;
      boolean var13 = false;
      boolean var14 = false;

      while (true) {
         int var15 = var0.read();
         if (var15 == -1) {
            if (var7.length() > 0) {
               String var18 = var7.toString() + var8;
               if (var12) {
                  var18 = method_05900(var18);
               }

               var1.method_27155(var18);
            } else {
               if (var12) {
                  method_05917(var8);
               }

               if (!method_05922(var8)) {
                  var1.method_09732(var8.method_27554(), 0, var8.length());
               }
            }

            return;
         }

         char var16 = (char)var15;
         var13 = Character.isLetter(var16) || var4.contains(var16);
         if (var16 == '\n') {
            var7.method_27552(var8);
            if (method_05914(var3, var7, var10)) {
               var7.method_27548();
            }

            if (!method_05922(var7)) {
               var1.method_09732(var7.method_27554(), 0, var7.length());
            }

            var1.method_09731();
            var8.method_27546(0);
            var7.method_27546(0);
            var12 = false;
         } else if (var16 != '\r') {
            if (var13 && !var14) {
               var8.method_27549(var16);
               if (var12 && var7.length() == 0) {
                  method_05917(var8);
               }

               if (method_05914(var3, var9, var10)) {
                  if (var7.length() > 0) {
                     method_05915(var1, var7);
                     method_05917(var8);
                     if (method_05914(var3, var8, var10)) {
                        if (var6) {
                           method_05916(var1, var8, var5);
                        } else {
                           var12 = true;
                        }
                     } else {
                        var12 = true;
                     }
                  } else if (var6) {
                     method_05916(var1, var8, var5);
                  } else {
                     var12 = true;
                  }
               }
            } else {
               if (var8.length() > 0 && !method_05922(var8)) {
                  method_05918(var7, var8);
                  if (var12) {
                     method_05917(var7);
                  }
               }

               var8.method_27549(var16);
               if (method_05914(var3, var9, var10)) {
                  Preconditions.checkArgument(
                     var7.length() > 0, "line length was zero. If this happens please contribute unit test that provokes this failure to the project!"
                  );
                  if (!method_05922(var7)) {
                     method_05915(var1, var7);
                  } else {
                     var7.method_27546(0);
                  }

                  var12 = true;
               }
            }
         }

         var14 = method_05902(var16) && !var4.contains(var16);
      }
   }

   public static Set<Character> method_05912(String var0) {
      HashSet var1 = new HashSet();

      for (int var2 = 0; var2 < var0.length(); var2++) {
         var1.add(var0.charAt(var2));
      }

      return var1;
   }

   public static boolean method_05902(char var0) {
      return "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~".indexOf(var0) != -1;
   }

   public static WordWrap$Builder method_05913(String var0, Charset var1) {
      return new WordWrap$Builder(new BufferedReader(new InputStreamReader(WordWrap.class.getResourceAsStream(var0), var1)), true);
   }

   public static WordWrap$Builder method_05904(InputStream var0) {
      return method_05905(var0, StandardCharsets.UTF_8);
   }

   public static WordWrap$Builder method_05923(String var0) {
      return method_05913(var0, StandardCharsets.UTF_8);
   }

   public static void method_05907(
      Reader var0,
      Writer var1,
      String var2,
      Number var3,
      Function<? super CharSequence, ? extends Number> var4,
      Set<Character> var5,
      boolean var6,
      boolean var7
   ) throws java.io.IOException {
      WordWrap$1 var8 = new WordWrap$1(var1, var2);
      method_05908(var0, var8, var3, var4, var5, var6, var7);
   }

   public static void method_05917(StringBuilder2 var0) {
      int var1 = 0;

      while (var1 < var0.length() && Character.isWhitespace(var0.charAt(var1))) {
         var1++;
      }

      if (var1 < var0.length() && var1 > 0) {
         var0.method_27556(0, var1);
      }
   }

   public static boolean method_05922(CharSequence var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (!Character.isWhitespace(var0.charAt(var1))) {
            return false;
         }
      }

      return true;
   }

   public static WordWrap$Builder method_05921(Reader var0) {
      return method_05909(var0, false);
   }

   public static WordWrap$Builder method_05899(CharSequence var0) {
      return method_05909(new BufferedReader(new CharSequenceReader(var0)), true);
   }

   public static CharSequence method_05911(CharSequence var0, CharSequence var1) {
      return new CharSequenceConcatRightTrim(var0, var1);
   }

   public static WordWrap$Builder method_05909(Reader var0, boolean var1) {
      return new WordWrap$Builder(var0, var1);
   }

   public static CharSequence method_05919(CharSequence var0) {
      int var1 = var0.length();

      while (var1 > 0 && Character.isWhitespace(var0.charAt(var1 - 1))) {
         var1--;
      }

      return var1 != var0.length() ? var0.subSequence(0, var1) : var0;
   }

   public static String method_05900(String var0) {
      StringBuilder2 var1 = new StringBuilder2(var0);
      method_05917(var1);
      return var1.toString();
   }

   public static WordWrap$Builder method_05905(InputStream var0, Charset var1) {
      return method_05921(new BufferedReader(new InputStreamReader(var0, var1)));
   }

   // $VF: synthetic method
   public static Set method_05920(String var0) {
      return method_05912(var0);
   }

   public static boolean method_05914(Function<? super CharSequence, ? extends Number> var0, CharSequence var1, double var2) {
      return ((Number)var0.apply(var1)).doubleValue() > var2;
   }

   public static void method_05915(LineConsumer var0, StringBuilder2 var1) throws java.io.IOException {
      var0.method_09732(var1.method_27554(), 0, var1.length());
      var0.method_09731();
      var1.method_27546(0);
   }

   public static void method_05916(LineConsumer var0, StringBuilder2 var1, boolean var2) throws java.io.IOException {
      String var3;
      if (var2 && var1.length() > 2 && !method_05922(var3 = var1.method_27551(0, var1.length() - 2))) {
         var0.method_27155(var3);
         var0.method_27155("-");
         var0.method_09731();
         var1.method_27556(0, var1.length() - 2);
      } else {
         String var4 = var1.method_27551(0, var1.length() - 1);
         if (!method_05922(var4)) {
            var0.method_27155(var4);
         }

         var0.method_09731();
         var1.method_27556(0, var1.length() - 1);
      }
   }
}
