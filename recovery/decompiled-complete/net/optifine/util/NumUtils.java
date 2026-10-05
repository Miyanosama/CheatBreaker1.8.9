package net.optifine.util;

import io.netty.handler.codec.http.HttpVersion;
import net.minecraft.client.resources.data.BaseMetadataSectionSerializer;

public class NumUtils {
   public BaseMetadataSectionSerializer field_0000;
   public HttpVersion field_0001;

   public static float limit(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : (var0 > var2 ? var2 : var0);
   }

   public static int mod(int var0, int var1) {
      int var2 = var0 % var1;
      if (var2 < 0) {
         var2 += var1;
      }

      return var2;
   }
}
