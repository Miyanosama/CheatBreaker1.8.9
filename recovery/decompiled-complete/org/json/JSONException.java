package org.json;

import net.minecraft.realms.RealmsVertexFormatElement;
import net.minecraft.stats.StatList;

public class JSONException extends RuntimeException {
   public StatList field_0001;
   public RealmsVertexFormatElement field_0002;
   public static long field_0000;

   public JSONException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public JSONException(String var1) {
      super(var1);
   }

   public JSONException(Throwable var1) {
      super(var1.getMessage(), var1);
   }
}
