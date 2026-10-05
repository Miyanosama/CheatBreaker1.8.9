package recovered.unidentified;

import java.net.SocketException;

public class UnidentifiedClass1587 extends SocketException {
   public String field_0000;
   public static long field_0001;

   public UnidentifiedClass1587(String var1) {
      this(var1, (String)null);
   }

   public UnidentifiedClass1587(String var1, String var2) {
      super(var1);
      this.field_0000 = var2;
   }

   public UnidentifiedClass1587(String var1, Throwable var2) {
      this(var1, (String)null);
      this.initCause(var2);
   }

   @Override
   public String toString() {
      return this.field_0000 == null ? super.toString() : super.toString() + " (socket: " + this.field_0000 + ")";
   }
}
