package org.davidmoten.text.utils;

import org.davidmoten.text.utils.LineConsumer;

import java.io.Writer;

public class WordWrap$1 implements LineConsumer {
   public String recoveredField977;
   public Writer recoveredField978;

   @Override
   public void method_09731() throws java.io.IOException {
      this.recoveredField978.write(this.recoveredField977);
   }

   @Override
   public void method_27155(String var1) throws java.io.IOException {
      this.recoveredField978.write(var1);
   }

   public WordWrap$1(Writer var1, String var2) {
      this.recoveredField978 = var1;
      this.recoveredField977 = var2;
   }

   @Override
   public void method_09732(char[] var1, int var2, int var3) throws java.io.IOException {
      this.recoveredField978.write(var1, var2, var3);
   }
}
