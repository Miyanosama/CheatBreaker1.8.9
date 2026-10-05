package com.cheatbreaker.client.config;

import io.netty.channel.FixedRecvByteBufAllocator;
import net.optifine.shaders.gui.GuiButtonShaderOption;

public enum SettingsDetailLevel {
   field_0003,
   field_0000,
   field_0001;
   public FixedRecvByteBufAllocator field_0005;
   public GuiButtonShaderOption field_0002;
   // $VF: synthetic field
   public static SettingsDetailLevel[] field_0004 = new SettingsDetailLevel[]{SettingsDetailLevel.field_0000, field_0003, SettingsDetailLevel.field_0001};

   public static SettingsDetailLevel method_29794(String var0) {
      return Enum.valueOf(SettingsDetailLevel.class, var0);
   }
}
