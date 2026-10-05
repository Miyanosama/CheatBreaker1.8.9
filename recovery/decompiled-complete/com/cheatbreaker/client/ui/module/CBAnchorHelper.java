package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.type.ToggleSprintModule;
import io.netty.channel.AbstractServerChannel$DefaultServerUnsafe;
import io.netty.channel.udt.nio.NioUdtProvider;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.model.ModelEnderMite;
import net.minecraft.client.shader.ShaderLinkHelper;

public class CBAnchorHelper {
   public ToggleSprintModule field_0002;
   public AbstractServerChannel$DefaultServerUnsafe field_0004;
   public ShaderLinkHelper field_0001;
   public NioUdtProvider field_0003;
   public ModelEnderMite field_0000;

   public static float[] getPositions(AbstractModule var0, float var1, float var2, ScaledResolution var3) {
      float var4 = var3.getScaledWidth();
      float var5 = var3.getScaledHeight();
      CBGuiAnchor var6 = getAnchor(var1, var2, var3);
      float var7 = var0.field_0041 * var0.method_28770();
      float var8 = var0.field_0012 * var0.method_28770();
      float var9 = 0.0F;
      float var10 = 0.0F;
      switch (CBAnchorHelper$1.$SwitchMap$com$cheatbreaker$client$ui$module$CBGuiAnchor[var6.ordinal()]) {
         case 1:
            var9 = var7 / 2.0F;
            var10 = var8 / 2.0F;
            break;
         case 2:
            var9 = var4 / 3.0F - var7 / 2.0F;
            var10 = var8 / 2.0F;
            break;
         case 3:
            var9 = var4 / 6.0F;
            var10 = var8 / 2.0F;
            break;
         case 4:
            var9 = var7 / 2.0F;
            var10 = var5 / 6.0F;
            break;
         case 5:
            var9 = var4 / 3.0F - var7 / 2.0F;
            var10 = var5 / 6.0F;
            break;
         case 6:
            var9 = var4 / 6.0F;
            var10 = var5 / 6.0F;
            break;
         case 7:
            var9 = var7 / 2.0F;
            var10 = var5 / 3.0F - var8 / 2.0F;
            break;
         case 8:
            var9 = var7 / 2.0F;
            var10 = var5 / 3.0F - var8 / 2.0F;
            break;
         case 9:
            var9 = var4 / 6.0F - var7 / 2.0F;
            var10 = var5 / 3.0F - var8 / 2.0F;
            break;
         case 10:
            var9 = var4 / 3.0F - var7 / 2.0F;
            var10 = var5 / 3.0F - var8 / 2.0F;
      }

      return new float[]{var9, var10};
   }

   public static float[] getPositions(CBGuiAnchor var0) {
      float var1 = 0.0F;
      float var2 = 0.0F;
      switch (CBAnchorHelper$1.$SwitchMap$com$cheatbreaker$client$ui$module$CBGuiAnchor[var0.ordinal()]) {
         case 5:
            var1 = -2.0F;
            break;
         case 6:
            var2 = -50.0F;
            var1 = 0.0F;
            break;
         case 7:
            var1 = 2.0F;
            var2 = -34.0F;
      }

      return new float[]{var1, var2};
   }

   public static CBPositionEnum getHorizontalPositionEnum(CBGuiAnchor var0) {
      switch (CBAnchorHelper$1.$SwitchMap$com$cheatbreaker$client$ui$module$CBGuiAnchor[var0.ordinal()]) {
         case 1:
         case 4:
         case 7:
         case 8:
            return CBPositionEnum.LEFT;
         case 2:
         case 5:
         case 9:
         case 10:
            return CBPositionEnum.RIGHT;
         case 3:
         case 6:
            return CBPositionEnum.CENTER;
         default:
            return null;
      }
   }

   public static float[] getPositions(float var0, float var1, ScaledResolution var2) {
      float var3 = var2.getScaledWidth();
      float var4 = var2.getScaledHeight();
      CBGuiAnchor var5 = getAnchor(var0, var1, var2);
      float var6 = 0.0F;
      float var7 = 0.0F;
      switch (CBAnchorHelper$1.$SwitchMap$com$cheatbreaker$client$ui$module$CBGuiAnchor[var5.ordinal()]) {
         case 1:
            var6 = 0.0F;
            var7 = 0.0F;
            break;
         case 2:
            var6 = var3 / 3.0F * 2.0F;
            var7 = 0.0F;
            break;
         case 3:
            var6 = var3 / 3.0F;
            var7 = 0.0F;
            break;
         case 4:
            var6 = 0.0F;
            var7 = var4 / 3.0F;
            break;
         case 5:
            var6 = var3 / 3.0F * 2.0F;
            var7 = var4 / 3.0F;
            break;
         case 6:
            var6 = var3 / 3.0F;
            var7 = var4 / 3.0F;
            break;
         case 7:
            var6 = 0.0F;
            var7 = var4 / 3.0F * 2.0F;
            break;
         case 8:
            var6 = var3 / 3.0F + var3 / 6.0F;
            var7 = var4 / 3.0F * 2.0F;
            break;
         case 9:
            var6 = var3 / 3.0F;
            var7 = var4 / 3.0F * 2.0F;
            break;
         case 10:
            var6 = var3 / 3.0F * 2.0F;
            var7 = var4 / 3.0F * 2.0F;
      }

      return new float[]{var6, var7};
   }

   public static float[] getPositions(CBGuiAnchor var0, ScaledResolution var1, float var2, float var3, float var4) {
      float var5 = 0.0F;
      float var6 = 0.0F;
      var2 *= var4;
      var3 *= var4;
      switch (CBAnchorHelper$1.$SwitchMap$com$cheatbreaker$client$ui$module$CBGuiAnchor[var0.ordinal()]) {
         case 1:
            var5 = 2.0F;
            var6 = 2.0F;
            break;
         case 2:
            var5 = var1.getScaledWidth() - var2 - 2.0F;
            var6 = 2.0F;
            break;
         case 3:
            var5 = var1.getScaledWidth() / 2 - var2 / 2.0F;
            var6 = 2.0F;
            break;
         case 4:
            var5 = 2.0F;
            var6 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case 5:
            var5 = var1.getScaledWidth() - var2;
            var6 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case 6:
            var5 = var1.getScaledWidth() / 2 - var2 / 2.0F;
            var6 = var1.getScaledHeight() / 2 - var3 / 2.0F;
            break;
         case 7:
            var6 = var1.getScaledHeight() - var3 - 2.0F;
            var5 = 2.0F;
            break;
         case 8:
            var5 = var1.getScaledWidth() / 2;
            var6 = var1.getScaledHeight() - var3 - 2.0F;
            break;
         case 9:
            var5 = var1.getScaledWidth() / 2 - var2;
            var6 = var1.getScaledHeight() - var3 - 2.0F;
            break;
         case 10:
            var5 = var1.getScaledWidth() - var2;
            var6 = var1.getScaledHeight() - var3;
      }

      return new float[]{var5, var6};
   }

   public static CBGuiAnchor getAnchor(float var0, float var1, ScaledResolution var2) {
      int var3 = var2.getScaledWidth();
      int var4 = var2.getScaledHeight();
      if (var0 < var3 / 3 && var1 < var4 / 3) {
         return CBGuiAnchor.LEFT_TOP;
      } else if (var0 > var3 / 3 * 2 && var1 < var4 / 3) {
         return CBGuiAnchor.RIGHT_TOP;
      } else if (var1 < var4 / 3) {
         return CBGuiAnchor.MIDDLE_TOP;
      } else if (var0 < var3 / 3 && var1 < var4 / 3 * 2) {
         return CBGuiAnchor.LEFT_MIDDLE;
      } else if (var0 > var3 / 3 * 2 && var1 < var4 / 3 * 2) {
         return CBGuiAnchor.RIGHT_MIDDLE;
      } else if (var1 < var4 / 3 * 2) {
         return CBGuiAnchor.MIDDLE_MIDDLE;
      } else if (var0 < var3 / 3) {
         return CBGuiAnchor.LEFT_BOTTOM;
      } else if (var0 < var3 / 3 * 2) {
         return var0 > var3 / 3 + var3 / 6 ? CBGuiAnchor.MIDDLE_BOTTOM_RIGHT : CBGuiAnchor.MIDDLE_BOTTOM_LEFT;
      } else {
         return CBGuiAnchor.RIGHT_BOTTOM;
      }
   }

   public static CBPositionEnum method_26692(CBGuiAnchor var0) {
      switch (CBAnchorHelper$1.$SwitchMap$com$cheatbreaker$client$ui$module$CBGuiAnchor[var0.ordinal()]) {
         case 1:
         case 2:
         case 3:
            return CBPositionEnum.field_0008;
         case 4:
         case 5:
         case 6:
            return CBPositionEnum.CENTER;
         case 7:
         case 8:
         case 9:
         case 10:
            return CBPositionEnum.field_0006;
         default:
            return null;
      }
   }
}
