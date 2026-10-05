package net.optifine.shaders.uniform;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public enum ShaderParameterBool implements IExpressionBool {
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
   public static ShaderParameterBool[] $VALUES = new ShaderParameterBool[]{
      IS_ALIVE,
      ShaderParameterBool.IS_BURNING,
      ShaderParameterBool.IS_CHILD,
      ShaderParameterBool.IS_GLOWING,
      ShaderParameterBool.IS_HURT,
      IS_IN_LAVA,
      ShaderParameterBool.IS_IN_WATER,
      ShaderParameterBool.IS_INVISIBLE,
      ShaderParameterBool.IS_ON_GROUND,
      IS_RIDDEN,
      ShaderParameterBool.IS_RIDING,
      IS_SNEAKING,
      IS_SPRINTING,
      ShaderParameterBool.IS_WET
   };
   public RenderManager renderManager;
   public static ShaderParameterBool[] VALUES = values();
   public String name;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }

   ShaderParameterBool(String var3) {
      this.name = var3;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }

   public String getName() {
      return this.name;
   }

   public static ShaderParameterBool parse(String var0) {
      if (var0 == null) {
         return null;
      } else {
         for (int var1 = 0; var1 < VALUES.length; var1++) {
            ShaderParameterBool var2 = VALUES[var1];
            if (var2.getName().equals(var0)) {
               return var2;
            }
         }

         return null;
      }
   }

   @Override
   public boolean eval() {
      Entity var1 = Minecraft.getMinecraft().getRenderViewEntity();
      if (var1 instanceof EntityLivingBase) {
         EntityLivingBase var2 = (EntityLivingBase)var1;
         switch (this) {
            case IS_ALIVE:
               return var2.isEntityAlive();
            case IS_BURNING:
               return var2.isBurning();
            case IS_CHILD:
               return var2.o_();
            case IS_HURT:
               return var2.au > 0;
            case IS_IN_LAVA:
               return var2.ab();
            case IS_IN_WATER:
               return var2.V();
            case IS_INVISIBLE:
               return var2.isInvisible();
            case IS_ON_GROUND:
               return var2.C;
            case IS_RIDDEN:
               return var2.l != null;
            case IS_RIDING:
               return var2.au();
            case IS_SNEAKING:
               return var2.isSneaking();
            case IS_SPRINTING:
               return var2.isSprinting();
            case IS_WET:
               return var2.U();
         }
      }

      return false;
   }
}
