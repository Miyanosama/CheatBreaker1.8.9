package net.optifine.entity.model.anim;

import net.minecraft.block.BlockObsidian;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public enum RenderEntityParameterBool implements IExpressionBool {
   IS_SPRINTING("is_sprinting"),
   IS_IN_LAVA("is_in_lava"),
   IS_INVISIBLE("is_invisible"),
   IS_RIDING("is_riding"),
   IS_ON_GROUND("is_on_ground"),
   IS_BURNING("is_burning"),
   IS_WET("is_wet"),
   IS_GLOWING("is_glowing"),
   IS_ALIVE("is_alive"),
   IS_RIDDEN("is_ridden"),
   IS_CHILD("is_child"),
   IS_IN_WATER("is_in_water"),
   IS_SNEAKING("is_sneaking"),
   IS_HURT("is_hurt");

   public RenderManager renderManager;
   public String name;
   public EntityAILookAtTradePlayer field_0016;
   public BlockObsidian field_0000;
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

            switch (RenderEntityParameterBool$1.$SwitchMap$net$optifine$entity$model$anim$RenderEntityParameterBool[this.ordinal()]) {
               case 1:
                  return var3.isEntityAlive();
               case 2:
                  return var3.isBurning();
               case 3:
                  return var3.o_();
               case 4:
                  return var3.au > 0;
               case 5:
                  return var3.ab();
               case 6:
                  return var3.V();
               case 7:
                  return var3.isInvisible();
               case 8:
                  return var3.C;
               case 9:
                  return var3.l != null;
               case 10:
                  return var3.au();
               case 11:
                  return var3.isSneaking();
               case 12:
                  return var3.isSprinting();
               case 13:
                  return var3.U();
            }
         }

         return false;
      }
   }

   public RenderEntityParameterBool(String var3) {
      this.name = var3;
      this.renderManager = Minecraft.getMinecraft().getRenderManager();
   }

   public String getName() {
      return this.name;
   }
}
