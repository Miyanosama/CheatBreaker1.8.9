package com.jagrosh.discordipc.entities;

import net.minecraft.world.EnumSkyBlock;
import net.optifine.entity.model.ModelAdapterMagmaCube;

public class User {
   public ModelAdapterMagmaCube field_0003;
   public EnumSkyBlock field_0005;
   public String field_0002;
   public String field_0004;
   public String field_0000;
   public long field_0001;

   public String method_13391() {
      return this.field_0002;
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
         return this == var2 || this.field_0001 == var2.field_0001;
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
      return Long.hashCode(this.field_0001);
   }

   public String method_13385() {
      return "<@" + this.field_0001 + '>';
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
      return this.field_0000;
   }

   public long method_13387() {
      return this.field_0001;
   }

   public User(String var1, String var2, long var3, String var5) {
      this.field_0000 = var1;
      this.field_0002 = var2;
      this.field_0001 = var3;
      this.field_0004 = var5;
   }

   @Override
   public String toString() {
      return "U:" + this.method_13392() + '(' + this.field_0001 + ')';
   }

   public String method_13396() {
      return this.field_0004;
   }

   public String method_13384() {
      return Long.toString(this.field_0001);
   }
}
