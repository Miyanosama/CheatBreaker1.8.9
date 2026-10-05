package junit.framework;

import junit.framework.ComparisonCompactor;

public class ComparisonFailure extends AssertionFailedError {
   public String fActual;
   public static final int recoveredField2299 = 20;
   public static final long recoveredField2300 = 1L;
   public String fExpected;

   public String getMessage() {
      return new ComparisonCompactor(20, this.fExpected, this.fActual).compact(super.getMessage());
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
