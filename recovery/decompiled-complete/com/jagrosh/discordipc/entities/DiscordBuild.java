package com.jagrosh.discordipc.entities;

import io.netty.util.internal.TypeParameterMatcher;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;

public enum DiscordBuild {
   field_0003("//canary.discordapp.com/api"),
   field_0006,
   field_0001("//ptb.discordapp.com/api"),
   field_0005("//discordapp.com/api");

   public TypeParameterMatcher field_0004;
   public String field_0007;
   // $VF: synthetic field
   public static DiscordBuild[] field_0000 = new DiscordBuild[]{field_0003, DiscordBuild.field_0001, DiscordBuild.field_0005, field_0006};
   public WorldGenDoublePlant field_0008;
   public EntityAIHurtByTarget field_0002;

   public DiscordBuild() {
      this(null);
   }

   public static DiscordBuild method_13354(String var0) {
      for (DiscordBuild var4 : values()) {
         if (var4.field_0007 != null && var4.field_0007.equals(var0)) {
            return var4;
         }
      }

      return field_0006;
   }

   public static DiscordBuild method_13355(String var0) {
      return Enum.valueOf(DiscordBuild.class, var0);
   }

   public DiscordBuild(String var3) {
      this.field_0007 = var3;
   }
}
