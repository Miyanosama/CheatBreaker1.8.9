package com.jagrosh.discordipc.entities;

public enum User$DefaultAvatar {
      BLURPLE("6debd47ed13483642cf09e832ed0bc1b"),
      GREY("322c936a8c8be1b803cd94861bdfa868"),
      GREEN("dd4dbc0016779df1378e7812eabaa04d"),
      ORANGE("0e291f67c9274a1abdddeb3fd919cbaa"),
      RED("1cbd08c76f8af6dddce02c5138971129");
   public String recoveredField2831;
   public static User$DefaultAvatar[] recoveredField2834 = new User$DefaultAvatar[]{
      User$DefaultAvatar.BLURPLE,
      User$DefaultAvatar.GREY,
      GREEN,
      User$DefaultAvatar.ORANGE,
      RED
   };

   public static User$DefaultAvatar method_13400(String var0) {
      return Enum.valueOf(User$DefaultAvatar.class, var0);
   }

   User$DefaultAvatar(String var3) {
      this.recoveredField2831 = var3;
   }

   @Override
   public String toString() {
      return this.recoveredField2831;
   }
}
