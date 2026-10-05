package recovered.unidentified;

import io.netty.handler.codec.socks.SocksRequest;
import io.netty.handler.stream.ChunkedWriteHandler$4;
import java.util.Arrays;
import net.minecraft.village.VillageSiege;
import net.optifine.shaders.MultiTexID;

public class UnidentifiedClass4575 implements CharSequence {
   public VillageSiege field_0003;
   public char[] field_0005;
   public int field_0002;
   public ChunkedWriteHandler$4 field_0004;
   public SocksRequest field_0000;
   public MultiTexID field_0001;

   public UnidentifiedClass4575 method_27548() {
      int var1 = this.length();

      while (var1 > 0 && Character.isWhitespace(this.charAt(var1 - 1))) {
         var1--;
      }

      this.field_0002 = var1;
      return this;
   }

   public int method_27550(int var1) {
      int var2 = this.field_0005.length * 2;
      if (var2 < this.field_0002 + var1) {
         var2 = this.field_0002 + var1;
      }

      return var2;
   }

   @Override
   public String toString() {
      return new String(this.field_0005, 0, this.field_0002);
   }

   public String method_27551(int var1, int var2) {
      return new String(this.field_0005, var1, var2 - var1);
   }

   public void method_27556(int var1, int var2) {
      System.arraycopy(this.field_0005, var2, this.field_0005, var1, this.field_0002 - var2);
      this.field_0002 -= var2 - var1;
   }

   @Override
   public int length() {
      return this.field_0002;
   }

   public UnidentifiedClass4575(char[] var1, int var2) {
      this.field_0005 = var1;
      this.field_0002 = var2;
   }

   public char[] method_27554() {
      return this.field_0005;
   }

   public UnidentifiedClass4575() {
      this(new char[16], 0);
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      char[] var3 = new char[var2 - var1];
      System.arraycopy(this.field_0005, var1, var3, 0, var2 - var1);
      return new UnidentifiedClass4575(var3, var3.length);
   }

   public void method_27555(int var1) {
      if (this.field_0002 + var1 > this.field_0005.length) {
         this.field_0005 = Arrays.copyOf(this.field_0005, this.method_27550(var1));
      }
   }

   public void method_27552(UnidentifiedClass4575 var1) {
      int var2 = var1.length();
      this.method_27555(var2);
      System.arraycopy(var1.field_0005, 0, this.field_0005, this.field_0002, var2);
      this.field_0002 += var2;
   }

   @Override
   public char charAt(int var1) {
      return this.field_0005[var1];
   }

   public void method_27549(char var1) {
      this.method_27555(1);
      this.field_0005[this.field_0002] = var1;
      this.field_0002++;
   }

   public void method_27546(int var1) {
      this.field_0002 = var1;
   }

   public UnidentifiedClass4575(String var1) {
      this(var1.toCharArray(), var1.length());
   }
}
