package javazoom.jl.decoder;

import net.minecraft.enchantment.EnchantmentHelper$1;
import net.minecraft.entity.item.EntityMinecartContainer;

public class DecoderException extends JavaLayerException implements DecoderErrors {
   public int errorcode = 512;
   public EnchantmentHelper$1 __junk4142969644863434569;
   public EntityMinecartContainer __junk3746748321068066914;

   public static String getErrorString(int var0) {
      return "Decoder errorcode " + Integer.toHexString(var0);
   }

   public int getErrorCode() {
      return this.errorcode;
   }

   public DecoderException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public DecoderException(int var1, Throwable var2) {
      this(getErrorString(var1), var2);
      this.errorcode = var1;
   }
}
