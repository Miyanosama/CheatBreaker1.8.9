package com.cheatbreaker.client;

public enum BuildBranch {
      DEVELOPMENT("?"),
      BETA("beta"),
      MASTER("master"),
      UNKNOWN("unknown");

   public String recoveredField1343;
   public static BuildBranch[] recoveredField1344 = new BuildBranch[]{DEVELOPMENT, BETA, MASTER, UNKNOWN};

   BuildBranch(String var3) {
      this.recoveredField1343 = var3;
   }

   public boolean method_06000(BuildBranch var1) {
      return var1.ordinal() >= this.ordinal();
   }

   public static BuildBranch method_06001(String var0) {
      for (BuildBranch var4 : values()) {
         if (var4.method_05999().equalsIgnoreCase(var0)) {
            return var4;
         }
      }

      return UNKNOWN;
   }

   public String method_05999() {
      return this.recoveredField1343;
   }

   public static BuildBranch method_06002(String var0) {
      return Enum.valueOf(BuildBranch.class, var0);
   }
}
