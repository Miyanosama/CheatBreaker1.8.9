package net.optifine.entity.model.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public enum RenderEntityParameterBool implements IExpressionBool {
      IS_ALIVE("is_alive"),
      IS_BURNING("is_burning"),
      IS_CHILD("is_child"),
      IS_GLOWING("is_glowing"),
      IS_HURT("is_hurt"),
      IS_IN_LAVA("is_in_lava"),
      IS_IN_WATER("is_in_water"),
      IS_INVISIBLE("is_invisible"),
      IS_ON_GROUND("is_on_ground"),
      IS_RIDDEN("is_ridden"),
      IS_RIDING("is_riding"),
      IS_SNEAKING("is_sneaking"),
      IS_SPRINTING("is_sprinting"),
      IS_WET("is_wet");

   public RenderManager renderManager;
   public String name;
   public static RenderEntityParameterBool[] $VALUES = new RenderEntityParameterBool[]{
      IS_ALIVE,
      IS_BURNING,
      IS_CHILD,
      IS_GLOWING,
      IS_HURT,
      IS_IN_LAVA,
      IS_IN_WATER,
      IS_INVISIBLE,
      IS_ON_GROUND,
      IS_RIDDEN,
      IS_RIDING,
      IS_SNEAKING,
      IS_SPRINTING,
      IS_WET
   };
   public static RenderEntityParameterBool[] VALUES = values();

   public static RenderEntityParameterBool parse(String var0) {
      if (var0 == null) {
         return null;
      } else {
         for (int var1 = 0; var1 < VALUES.length; var1++) {
            RenderEntityParameterBool var2 = VALUES[var1];
            if (var2.getName().equals(var0)) {
               return var2;
            }
         }

         return null;
      }
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }

   @Override
   public boolean eval() {
      Render var1 = this.renderManager.renderRender;
      if (var1 == null) {
         return false;
      } else {
         if (var1 instanceof RendererLivingEntity) {
            RendererLivingEntity var2 = (RendererLivingEntity)var1;
            EntityLivingBase var3 = var2.renderEntity;
            if (var3 == null) {
               return false;
            }

            switch (this) {
               case IS_ALIVE:
                  return var3.isEntityAlive();
               case IS_BURNING:
                  return var3.isBurning();
               case IS_CHILD:
                  return var3.o_();
               case IS_HURT:
                  return var3.au > 0;
               case IS_IN_LAVA:
                  return var3.ab();
               case IS_IN_WATER:
                  return var3.V();
               case IS_INVISIBLE:
                  return var3.isInvisible();
               case IS_ON_GROUND:
                  return var3.C;
               case IS_RIDDEN:
                  return var3.l != null;
               case IS_RIDING:
                  return var3.au();
               case IS_SNEAKING:
                  return var3.isSneaking();
               case IS_SPRINTING:
                  return var3.isSprinting();
               case IS_WET:
                  return var3.U();
            }
         }

         return false;
      }
   }

   RenderEntityParameterBool(String var3) {
      this.name = var3;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }

   public String getName() {
      return this.name;
   }
}
