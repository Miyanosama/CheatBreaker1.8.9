package junit.framework;

import net.minecraft.network.play.client.C0FPacketConfirmTransaction;
import recovered.unidentified.UnidentifiedClass0064;
import recovered.unidentified.UnidentifiedClass3870;

public class ComparisonFailure extends AssertionFailedError {
   public C0FPacketConfirmTransaction field_0000;
   public String fActual;
   public static int field_0004;
   public static long field_0003;
   public String fExpected;
   public UnidentifiedClass3870 field_0005;

   public String getMessage() {
      return new UnidentifiedClass0064(20, this.fExpected, this.fActual).method_00588(super.getMessage());
   }

   public String method_10573() {
      return this.fExpected;
   }

   public ComparisonFailure(String var1, String var2, String var3) {
      super(var1);
      this.fExpected = var2;
      this.fActual = var3;
   }

   public String method_10574() {
      return this.fActual;
   }
}
