package org.davidmoten.text.utils;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class WordWrap$Builder {
   public boolean recoveredField1196;
   public String recoveredField1197;
   public Set<Character> recoveredField1198;
   public Reader recoveredField1199;
   public boolean recoveredField1200;
   public boolean recoveredField1201;
   public Number recoveredField1202 = 80;
   public Function<? super CharSequence, ? extends Number> recoveredField1203 = WordWrap.method_05901();

   public void method_29694(File var1) {
      this.method_29695(var1, StandardCharsets.UTF_8);
   }

   public WordWrap$Builder method_29698(String var1) {
      Set var2 = WordWrap.method_05920(var1);
      this.recoveredField1198.removeAll(var2);
      return this;
   }

   public String method_29693() {
      try (StringWriter var1 = new StringWriter()) {
         this.method_29696(var1);
         return var1.toString();
      } catch (IOException var15) {
         throw new RuntimeException(var15);
      }
   }

   public WordWrap$Builder method_29700(Set<Character> var1) {
      this.recoveredField1198 = var1;
      return this;
   }

   public WordWrap$Builder method_29703(boolean var1) {
      this.recoveredField1201 = var1;
      return this;
   }

   public List<String> method_29705() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      boolean[] var3 = new boolean[1];
      this.method_29702(new WordWrap$Builder$1(this, var3, var2, var1));
      if (var3[0]) {
         var1.add(var2.toString());
      }

      return var1;
   }

   public WordWrap$Builder method_29697(Number var1) {
      Preconditions.checkArgument(var1.doubleValue() > 0.0);
      this.recoveredField1202 = var1;
      return this;
   }

   public WordWrap$Builder method_29701(Function<? super CharSequence, ? extends Number> var1) {
      this.recoveredField1203 = var1;
      return this;
   }

   public WordWrap$Builder(Reader var1, boolean var2) {
      this.recoveredField1198 = WordWrap.recoveredField3020;
      this.recoveredField1197 = "\n";
      this.recoveredField1201 = true;
      this.recoveredField1196 = true;
      this.recoveredField1199 = var1;
      this.recoveredField1200 = var2;
   }

   public void method_29706(String var1) {
      this.method_29694(new File(var1));
   }

   public void method_29702(LineConsumer var1) {
      try {
         WordWrap.method_05908(
            this.recoveredField1199,
            var1,
            this.recoveredField1202,
            this.recoveredField1203,
            this.recoveredField1198,
            this.recoveredField1201,
            this.recoveredField1196
         );
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      } finally {
         if (this.recoveredField1200) {
            WordWrap.method_05906(this.recoveredField1199);
         }
      }
   }

   public void method_29696(Writer var1) {
      try {
         WordWrap.method_05907(
            this.recoveredField1199,
            var1,
            this.recoveredField1197,
            this.recoveredField1202,
            this.recoveredField1203,
            this.recoveredField1198,
            this.recoveredField1201,
            this.recoveredField1196
         );
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      } finally {
         if (this.recoveredField1200) {
            WordWrap.method_05906(this.recoveredField1199);
         }
      }
   }

   public void method_29699(String var1, Charset var2) {
      this.method_29695(new File(var1), var2);
   }

   public WordWrap$Builder method_29692(String var1) {
      Set var2 = WordWrap.method_05920(var1);
      this.recoveredField1198.addAll(var2);
      return this;
   }

   public WordWrap$Builder method_29707(boolean var1) {
      this.recoveredField1196 = var1;
      return this;
   }

   public void method_29695(File var1, Charset var2) {
      try (OutputStreamWriter var3 = new OutputStreamWriter(new FileOutputStream(var1), var2)) {
         this.method_29696(var3);
      } catch (IOException var16) {
         throw new RuntimeException(var16);
      }
   }

   public WordWrap$Builder method_29704(String var1) {
      this.recoveredField1197 = var1;
      return this;
   }

   public WordWrap$Builder method_29691(String var1) {
      return this.method_29700(WordWrap.method_05920(var1));
   }
}
