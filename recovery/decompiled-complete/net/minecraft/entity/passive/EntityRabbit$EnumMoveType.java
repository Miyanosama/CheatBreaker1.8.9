package net.minecraft.entity.passive;

import io.netty.channel.group.ChannelGroupException;
import io.netty.handler.codec.sctp.SctpInboundByteStreamHandler;
import net.minecraft.entity.effect.EntityWeatherEffect;

public enum EntityRabbit$EnumMoveType {
   ATTACK(2.0F, 0.7F, 7, 8),
   HOP(0.8F, 0.2F, 20, 10),
   SPRINT(1.75F, 0.4F, 1, 8),
   STEP(1.0F, 0.45F, 14, 14),
   NONE(0.0F, 0.0F, 30, 1);
   public int field_180085_i;
   public int duration;
   // $VF: synthetic field
   public static EntityRabbit$EnumMoveType[] $VALUES = new EntityRabbit$EnumMoveType[]{
      EntityRabbit$EnumMoveType.NONE,
      EntityRabbit$EnumMoveType.HOP,
      EntityRabbit$EnumMoveType.STEP,
      EntityRabbit$EnumMoveType.SPRINT,
      EntityRabbit$EnumMoveType.ATTACK
   };
   public float speed;
   public float field_180077_g;
   public ChannelGroupException field_0012;
   public SctpInboundByteStreamHandler field_0000;
   public EntityWeatherEffect field_0006;

   public EntityRabbit$EnumMoveType(float var3, float var4, int var5, int var6) {
      this.speed = var3;
      this.field_180077_g = var4;
      this.duration = var5;
      this.field_180085_i = var6;
   }

   public int func_180073_d() {
      return this.field_180085_i;
   }

   public int getDuration() {
      return this.duration;
   }

   public float func_180074_b() {
      return this.field_180077_g;
   }

   public float getSpeed() {
      return this.speed;
   }
}
