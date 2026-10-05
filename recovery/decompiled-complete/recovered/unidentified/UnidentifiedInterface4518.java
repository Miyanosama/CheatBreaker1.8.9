package recovered.unidentified;

public interface UnidentifiedInterface4518 {
   void method_09732(char[] var1, int var2, int var3);

   default void method_27155(String var1) {
      char[] var2 = var1.toCharArray();
      this.method_09732(var2, 0, var2.length);
   }

   void method_09731();
}
