package net.optifine.entity.model.anim;

import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGolem;
import net.optifine.ConnectedTexturesCompact$Dir;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public enum RenderEntityParameterFloat implements IExpressionFloat {
   SCALE("scale"),
   SWING_PROGRESS("swing_progress"),
   POS_Y("pos_y"),
   PARTIAL_TICKS("partial_ticks"),
   HEALTH("health"),
   HEAD_YAW("head_yaw"),
   MOVE_STRAFING("move_strafing"),
   LIMB_SWING_SPEED("limb_speed"),
   POS_Z("pos_z"),
   MAX_HEALTH("max_health"),
   HURT_TIME("hurt_time"),
   HEAD_PITCH("head_pitch"),
   REVENGE_TIME("revenge_time"),
   POS_X("pos_x"),
   AGE("age"),
   MOVE_FORWARD("move_forward"),
   LIMB_SWING("limb_swing"),
   IDLE_TIME("idle_time");

   public BlockRedstoneRepeater field_0006;
   public ConnectedTexturesCompact$Dir field_0008;
   public EntityGolem field_0013;
   public String name;
   public static RenderEntityParameterFloat[] VALUES = values();
   public RenderManager renderManager;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   @Override
   public float eval() {
      Render var1 = this.renderManager.renderRender;
      if (var1 == null) {
         return 0.0F;
      } else {
         if (var1 instanceof RendererLivingEntity) {
            RendererLivingEntity var2 = (RendererLivingEntity)var1;
            switch (RenderEntityParameterFloat$1.$SwitchMap$net$optifine$entity$model$anim$RenderEntityParameterFloat[this.ordinal()]) {
               case 12:
                  return var2.field_0007;
               case 13:
                  return var2.field_0010;
               case 14:
                  return var2.field_0000;
               case 15:
                  return var2.field_0012;
               case 16:
                  return var2.field_0001;
               case 17:
                  return var2.field_0004;
               default:
                  EntityLivingBase var3 = var2.renderEntity;
                  if (var3 == null) {
                     return 0.0F;
                  }

                  switch (RenderEntityParameterFloat$1.$SwitchMap$net$optifine$entity$model$anim$RenderEntityParameterFloat[this.ordinal()]) {
                     case 1:
                        return var3.getHealth();
                     case 2:
                        return var3.au;
                     case 3:
                        return var3.bh();
                     case 4:
                        return var3.getMaxHealth();
                     case 5:
                        return var3.ba;
                     case 6:
                        return var3.aZ;
                     case 7:
                        return (float)var3.s;
                     case 8:
                        return (float)var3.t;
                     case 9:
                        return (float)var3.u;
                     case 10:
                        return var3.getRevengeTimer();
                     case 11:
                        return var3.getSwingProgress(var2.field_0013);
                  }
            }
         }

         return 0.0F;
      }
   }

   public String getName() {
      return this.name;
   }

   public static RenderEntityParameterFloat parse(String var0) {
      if (var0 == null) {
         return null;
      } else {
         for (int var1 = 0; var1 < VALUES.length; var1++) {
            RenderEntityParameterFloat var2 = VALUES[var1];
            if (var2.getName().equals(var0)) {
               return var2;
            }
         }

         return null;
      }
   }

   public RenderEntityParameterFloat(String var3) {
      this.name = var3;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }
}
