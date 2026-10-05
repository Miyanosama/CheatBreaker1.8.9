package com.jagrosh.discordipc.entities;

import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.util.internal.chmv8.ForkJoinPool$Submitter;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.ai.EntityAIFleeSun;

public enum User$DefaultAvatar {
   field_0004("dd4dbc0016779df1378e7812eabaa04d"),
   field_0002("1cbd08c76f8af6dddce02c5138971129"),
   field_0003("322c936a8c8be1b803cd94861bdfa868"),
   field_0010("6debd47ed13483642cf09e832ed0bc1b"),
   field_0000("0e291f67c9274a1abdddeb3fd919cbaa");
   public String field_0005;
   public EntityAIFleeSun field_0008;
   public LineBasedFrameDecoder field_0007;
   public ForkJoinPool$Submitter field_0001;
   // $VF: synthetic field
   public static User$DefaultAvatar[] field_0009 = new User$DefaultAvatar[]{
      User$DefaultAvatar.field_0010, User$DefaultAvatar.field_0003, field_0004, User$DefaultAvatar.field_0000, field_0002
   };
   public PlayerControllerMP field_0006;

   public static User$DefaultAvatar method_13400(String var0) {
      return Enum.valueOf(User$DefaultAvatar.class, var0);
   }

   public User$DefaultAvatar(String var3) {
      this.field_0005 = var3;
   }

   @Override
   public String toString() {
      return this.field_0005;
   }
}
