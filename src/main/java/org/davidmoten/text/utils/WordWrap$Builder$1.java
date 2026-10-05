package org.davidmoten.text.utils;

import org.davidmoten.text.utils.LineConsumer;

import org.davidmoten.text.utils.WordWrap$Builder;

import java.util.List;

public class WordWrap$Builder$1 implements LineConsumer {
   public StringBuilder recoveredField2932;
   public List recoveredField2933;
   public WordWrap$Builder recoveredField2934;
   public boolean[] recoveredField2935;

   @Override
   public void method_09732(char[] var1, int var2, int var3) throws java.io.IOException {
      this.recoveredField2935[0] = true;
      this.recoveredField2932.append(var1, var2, var3);
   }

   @Override
   public void method_09731() throws java.io.IOException {
      this.recoveredField2933.add(this.recoveredField2932.toString());
      this.recoveredField2932.setLength(0);
      this.recoveredField2935[0] = false;
   }

   public WordWrap$Builder$1(WordWrap$Builder var1, boolean[] var2, StringBuilder var3, List var4) {
      this.recoveredField2934 = var1;
      this.recoveredField2935 = var2;
      this.recoveredField2932 = var3;
      this.recoveredField2933 = var4;
   }
}
