package net.optifine.shaders.uniform;

import io.netty.handler.codec.socks.SocksCmdResponse$1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.EnchantmentWaterWorker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.biome.BiomeGenMushroomIsland;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public enum ShaderParameterBool implements IExpressionBool {
   IS_ALIVE("is_alive"),
   IS_RIDDEN("is_ridden"),
   IS_IN_LAVA("is_in_lava"),
   IS_SNEAKING("is_sneaking"),
   IS_SPRINTING("is_sprinting"),
   IS_ON_GROUND("is_on_ground"),
   IS_BURNING("is_burning"),
   IS_GLOWING("is_glowing"),
   IS_IN_WATER("is_in_water"),
   IS_INVISIBLE("is_invisible"),
   IS_WET("is_wet"),
   IS_RIDING("is_riding"),
   IS_HURT("is_hurt"),
   IS_CHILD("is_child");
   public SocksCmdResponse$1 field_0009;
   public static ShaderParameterBool[] VALUES = values();
   public RenderManager renderManager;
   public EnchantmentWaterWorker field_0013;
   // $VF: synthetic field
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
   public String name;
   public BiomeGenMushroomIsland field_0011;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }

   public ShaderParameterBool(String var3) {
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
         switch (ShaderParameterBool$1.$SwitchMap$net$optifine$shaders$uniform$ShaderParameterBool[this.ordinal()]) {
            case 1:
               return var2.isEntityAlive();
            case 2:
               return var2.isBurning();
            case 3:
               return var2.o_();
            case 4:
               return var2.au > 0;
            case 5:
               return var2.ab();
            case 6:
               return var2.V();
            case 7:
               return var2.isInvisible();
            case 8:
               return var2.C;
            case 9:
               return var2.l != null;
            case 10:
               return var2.au();
            case 11:
               return var2.isSneaking();
            case 12:
               return var2.isSprinting();
            case 13:
               return var2.U();
         }
      }

      return false;
   }
}
