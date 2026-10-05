package com.cheatbreaker.client.command;

public abstract class CommandMetadata {
   public String[] recoveredField361;
   public String recoveredField362;
   public String recoveredField363;

   public CommandMetadata(String var1, String var2, String[] var3) {
      this.recoveredField362 = var1;
      this.recoveredField363 = var2;
      this.recoveredField361 = var3;
   }

   public String[] method_10953() {
      return this.recoveredField361;
   }

   public String method_10954() {
      return this.recoveredField363;
   }

   public String method_10952() {
      return this.recoveredField362;
   }
}
