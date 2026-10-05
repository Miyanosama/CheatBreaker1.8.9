package com.cheatbreaker.client.module;

import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import io.netty.handler.codec.rtsp.RtspObjectDecoder;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.init.Bootstrap$2;
import org.apache.log4j.lf5.util.AdapterLogRecord;

// $VF: synthetic class
public class AbstractModule$1 {
   public GuiTwitchUserMode field_0002;
   public RtspObjectDecoder field_0001;
   public AdapterLogRecord field_0003;
   public Bootstrap$2 field_0000;

   static {
      try {
         field_0004[CBGuiAnchor.LEFT_TOP.ordinal()] = 1;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_0004[CBGuiAnchor.LEFT_MIDDLE.ordinal()] = 2;
      } catch (NoSuchFieldError var9) {
      }

      try {
         field_0004[CBGuiAnchor.LEFT_BOTTOM.ordinal()] = 3;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_0004[CBGuiAnchor.MIDDLE_TOP.ordinal()] = 4;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field_0004[CBGuiAnchor.MIDDLE_MIDDLE.ordinal()] = 5;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_0004[CBGuiAnchor.MIDDLE_BOTTOM_LEFT.ordinal()] = 6;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_0004[CBGuiAnchor.MIDDLE_BOTTOM_RIGHT.ordinal()] = 7;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0004[CBGuiAnchor.RIGHT_TOP.ordinal()] = 8;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0004[CBGuiAnchor.RIGHT_MIDDLE.ordinal()] = 9;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0004[CBGuiAnchor.RIGHT_BOTTOM.ordinal()] = 10;
      } catch (NoSuchFieldError var1) {
      }
   }
}
