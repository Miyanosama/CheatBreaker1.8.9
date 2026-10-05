package com.cheatbreaker.client.emote.type;

public enum FlossPose {
      LEFT_TO_RIGHT,
      RIGHT_TO_LEFT,
      RIGHT_TO_BACK,
      LEFT_TO_BACK;

   public static FlossPose[] recoveredField125 = new FlossPose[]{
      LEFT_TO_RIGHT, RIGHT_TO_LEFT, RIGHT_TO_BACK, LEFT_TO_BACK
   };

   public static FlossPose method_08178(String var0) {
      return Enum.valueOf(FlossPose.class, var0);
   }
}
