package recovered.unidentified;

import com.cheatbreaker.client.ui.element.module.ModuleListElement;
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

public class UnidentifiedClass4984 {
   public boolean field_0004;
   public String field_0007;
   public Set<Character> field_0003;
   public Reader field_0006;
   public boolean field_0000;
   public boolean field_0001;
   public Number field_0008 = 80;
   public Function<? super CharSequence, ? extends Number> field_0005 = UnidentifiedClass0882.method_05901();
   public ModuleListElement field_0002;

   public void method_29694(File var1) {
      this.method_29695(var1, StandardCharsets.UTF_8);
   }

   public UnidentifiedClass4984 method_29698(String var1) {
      Set var2 = UnidentifiedClass0882.method_05920(var1);
      this.field_0003.removeAll(var2);
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

   public UnidentifiedClass4984 method_29700(Set<Character> var1) {
      this.field_0003 = var1;
      return this;
   }

   public UnidentifiedClass4984 method_29703(boolean var1) {
      this.field_0001 = var1;
      return this;
   }

   public List<String> method_29705() {
      ArrayList var1 = new ArrayList();
      StringBuilder var2 = new StringBuilder();
      boolean[] var3 = new boolean[1];
      this.method_29702(new UnidentifiedClass1421(this, var3, var2, var1));
      if (var3[0]) {
         var1.add(var2.toString());
      }

      return var1;
   }

   public UnidentifiedClass4984 method_29697(Number var1) {
      Preconditions.checkArgument(var1.doubleValue() > 0.0);
      this.field_0008 = var1;
      return this;
   }

   public UnidentifiedClass4984 method_29701(Function<? super CharSequence, ? extends Number> var1) {
      this.field_0005 = var1;
      return this;
   }

   public UnidentifiedClass4984(Reader var1, boolean var2) {
      this.field_0003 = UnidentifiedClass0882.field_0001;
      this.field_0007 = "\n";
      this.field_0001 = true;
      this.field_0004 = true;
      this.field_0006 = var1;
      this.field_0000 = var2;
   }

   public void method_29706(String var1) {
      this.method_29694(new File(var1));
   }

   public void method_29702(UnidentifiedInterface4518 var1) {
      try {
         UnidentifiedClass0882.method_05908(this.field_0006, var1, this.field_0008, this.field_0005, this.field_0003, this.field_0001, this.field_0004);
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      } finally {
         if (this.field_0000) {
            UnidentifiedClass0882.method_05906(this.field_0006);
         }
      }
   }

   public void method_29696(Writer var1) {
      try {
         UnidentifiedClass0882.method_05907(
            this.field_0006, var1, this.field_0007, this.field_0008, this.field_0005, this.field_0003, this.field_0001, this.field_0004
         );
      } catch (IOException var6) {
         throw new RuntimeException(var6);
      } finally {
         if (this.field_0000) {
            UnidentifiedClass0882.method_05906(this.field_0006);
         }
      }
   }

   public void method_29699(String var1, Charset var2) {
      this.method_29695(new File(var1), var2);
   }

   public UnidentifiedClass4984 method_29692(String var1) {
      Set var2 = UnidentifiedClass0882.method_05920(var1);
      this.field_0003.addAll(var2);
      return this;
   }

   public UnidentifiedClass4984 method_29707(boolean var1) {
      this.field_0004 = var1;
      return this;
   }

   public void method_29695(File var1, Charset var2) {
      try (OutputStreamWriter var3 = new OutputStreamWriter(new FileOutputStream(var1), var2)) {
         this.method_29696(var3);
      } catch (IOException var16) {
         throw new RuntimeException(var16);
      }
   }

   public UnidentifiedClass4984 method_29704(String var1) {
      this.field_0007 = var1;
      return this;
   }

   public UnidentifiedClass4984 method_29691(String var1) {
      return this.method_29700(UnidentifiedClass0882.method_05920(var1));
   }
}
