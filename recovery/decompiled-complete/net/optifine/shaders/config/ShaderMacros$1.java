package net.optifine.shaders.config;

import io.netty.channel.DefaultChannelPipeline$2;
import net.minecraft.util.Util$EnumOS;

// $VF: synthetic class
public class ShaderMacros$1 {
   public DefaultChannelPipeline$2 field_0000;

   static {
      try {
         $SwitchMap$net$minecraft$util$Util$EnumOS[Util$EnumOS.WINDOWS.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$minecraft$util$Util$EnumOS[Util$EnumOS.OSX.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$minecraft$util$Util$EnumOS[Util$EnumOS.LINUX.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
