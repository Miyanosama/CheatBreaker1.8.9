package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.achievement.GuiStats$StatsBlock$1;
import recovered.unidentified.UnidentifiedClass3416;

public class GuiDrawEvent extends EventBus$Event {
   public UnidentifiedClass3416 field_0002;
   public ScaledResolution resolution;
   public GuiStats$StatsBlock$1 field_0000;

   public GuiDrawEvent(ScaledResolution var1) {
      this.resolution = var1;
   }

   public ScaledResolution getResolution() {
      return this.resolution;
   }
}
