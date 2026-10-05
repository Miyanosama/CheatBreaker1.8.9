package javazoom.jl.decoder;

public class BitstreamException extends JavaLayerException implements BitstreamErrors {
   public int errorcode = 256;

   public int getErrorCode() {
      return this.errorcode;
   }

   public static String getErrorString(int var0) {
      return "Bitstream errorcode " + Integer.toHexString(var0);
   }

   public BitstreamException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public BitstreamException(int var1, Throwable var2) {
      this(getErrorString(var1), var2);
      this.errorcode = var1;
   }
}
