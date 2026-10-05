package com.jagrosh.discordipc.entities;

public enum DiscordBuild {
      CANARY("//canary.discordapp.com/api"),
      PTB("//ptb.discordapp.com/api"),
      STABLE("//discordapp.com/api"),
      ANY;
   public String recoveredField333;
   public static DiscordBuild[] recoveredField336 = new DiscordBuild[]{
      CANARY, DiscordBuild.PTB, DiscordBuild.STABLE, ANY
   };

   DiscordBuild() {
      this(null);
   }

   public static DiscordBuild method_13354(String var0) {
      for (DiscordBuild var4 : values()) {
         if (var4.recoveredField333 != null && var4.recoveredField333.equals(var0)) {
            return var4;
         }
      }

      return ANY;
   }

   public static DiscordBuild method_13355(String var0) {
      return Enum.valueOf(DiscordBuild.class, var0);
   }

   DiscordBuild(String var3) {
      this.recoveredField333 = var3;
   }
}
