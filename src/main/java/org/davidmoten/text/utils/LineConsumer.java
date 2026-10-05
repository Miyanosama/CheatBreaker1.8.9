package org.davidmoten.text.utils;

public interface LineConsumer {
   void method_09732(char[] var1, int var2, int var3) throws java.io.IOException ;

   default void method_27155(String var1) throws java.io.IOException {
      char[] var2 = var1.toCharArray();
      this.method_09732(var2, 0, var2.length);
   }

   void method_09731() throws java.io.IOException ;
}
