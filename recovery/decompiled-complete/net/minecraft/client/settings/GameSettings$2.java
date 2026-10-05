package net.minecraft.client.settings;

import io.netty.buffer.ReadOnlyByteBuf;
import io.netty.util.ResourceLeakException;
import net.minecraft.block.BlockWall$EnumType;

// $VF: synthetic class
public class GameSettings$2 {
   public ReadOnlyByteBuf field_0001;
   public BlockWall$EnumType field_0003;
   public ResourceLeakException field_0002;

   static {
      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.INVERT_MOUSE.ordinal()] = 1;
      } catch (NoSuchFieldError var18) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.VIEW_BOBBING.ordinal()] = 2;
      } catch (NoSuchFieldError var17) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.ANAGLYPH.ordinal()] = 3;
      } catch (NoSuchFieldError var16) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.FBO_ENABLE.ordinal()] = 4;
      } catch (NoSuchFieldError var15) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.CHAT_COLOR.ordinal()] = 5;
      } catch (NoSuchFieldError var14) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.CHAT_LINKS.ordinal()] = 6;
      } catch (NoSuchFieldError var13) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.CHAT_LINKS_PROMPT.ordinal()] = 7;
      } catch (NoSuchFieldError var12) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.SNOOPER_ENABLED.ordinal()] = 8;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.USE_FULLSCREEN.ordinal()] = 9;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.ENABLE_VSYNC.ordinal()] = 10;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.USE_VBO.ordinal()] = 11;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.TOUCHSCREEN.ordinal()] = 12;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.STREAM_SEND_METADATA.ordinal()] = 13;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.FORCE_UNICODE_FONT.ordinal()] = 14;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.BLOCK_ALTERNATIVES.ordinal()] = 15;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.REDUCED_DEBUG_INFO.ordinal()] = 16;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.ENTITY_SHADOWS.ordinal()] = 17;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$minecraft$client$settings$GameSettings$Options[GameSettings$Options.REALMS_NOTIFICATIONS.ordinal()] = 18;
      } catch (NoSuchFieldError var1) {
      }
   }
}
