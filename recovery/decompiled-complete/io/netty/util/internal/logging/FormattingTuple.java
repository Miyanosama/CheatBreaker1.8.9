package io.netty.util.internal.logging;

import net.minecraft.client.util.JsonException$Entry;
import net.minecraft.item.ItemSaddle;
import net.minecraft.scoreboard.Team;
import recovered.unidentified.UnidentifiedEnum3979;

public class FormattingTuple {
   public Throwable throwable;
   public ItemSaddle __junk6895373364996963640;
   public Object[] argArray;
   public Team __junk1120696072957678970;
   public String message;
   public JsonException$Entry __junk7277382141007753173;
   public UnidentifiedEnum3979 __junk4778455484172510103;
   public static FormattingTuple NULL = new FormattingTuple(null);

   public static Object[] trimmedCopy(Object[] var0) {
      if (var0 != null && var0.length != 0) {
         int var1 = var0.length - 1;
         Object[] var2 = new Object[var1];
         System.arraycopy(var0, 0, var2, 0, var1);
         return var2;
      } else {
         throw new IllegalStateException("non-sensical empty or null argument array");
      }
   }

   public FormattingTuple(String var1, Object[] var2, Throwable var3) {
      this.message = var1;
      this.throwable = var3;
      if (var3 == null) {
         this.argArray = var2;
      } else {
         this.argArray = trimmedCopy(var2);
      }
   }

   public FormattingTuple(String var1) {
      this(var1, null, null);
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   public Object[] getArgArray() {
      return this.argArray;
   }

   public String getMessage() {
      return this.message;
   }
}
