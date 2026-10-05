package com.jagrosh.discordipc.entities;

public class User {
   public String recoveredField3187;
   public String recoveredField3188;
   public String recoveredField3189;
   public long recoveredField3190;

   public String method_13391() {
      return this.recoveredField3187;
   }

   public String method_13394() {
      return "https://discordapp.com/assets/" + this.method_13395() + ".png";
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof User)) {
         return false;
      } else {
         User var2 = (User)var1;
         return this == var2 || this.recoveredField3190 == var2.recoveredField3190;
      }
   }

   public String method_13388() {
      return this.method_13386() == null ? this.method_13394() : this.method_13386();
   }

   public boolean method_13393() {
      return false;
   }

   @Override
   public int hashCode() {
      return Long.hashCode(this.recoveredField3190);
   }

   public String method_13385() {
      return "<@" + this.recoveredField3190 + '>';
   }

   public String method_13386() {
      return this.method_13396() == null
         ? null
         : "https://cdn.discordapp.com/avatars/" + this.method_13384() + "/" + this.method_13396() + (this.method_13396().startsWith("a_") ? ".gif" : ".png");
   }

   public String method_13395() {
      return User$DefaultAvatar.values()[Integer.parseInt(this.method_13391()) % User$DefaultAvatar.values().length].toString();
   }

   public String method_13392() {
      return this.recoveredField3189;
   }

   public long method_13387() {
      return this.recoveredField3190;
   }

   public User(String var1, String var2, long var3, String var5) {
      this.recoveredField3189 = var1;
      this.recoveredField3187 = var2;
      this.recoveredField3190 = var3;
      this.recoveredField3188 = var5;
   }

   @Override
   public String toString() {
      return "U:" + this.method_13392() + '(' + this.recoveredField3190 + ')';
   }

   public String method_13396() {
      return this.recoveredField3188;
   }

   public String method_13384() {
      return Long.toString(this.recoveredField3190);
   }
}
