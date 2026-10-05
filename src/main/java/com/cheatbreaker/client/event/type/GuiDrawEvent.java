package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.client.gui.ScaledResolution;

public class GuiDrawEvent extends EventBus$Event {
   public ScaledResolution resolution;

   public GuiDrawEvent(ScaledResolution var1) {
      this.resolution = var1;
   }

   public ScaledResolution getResolution() {
      return this.resolution;
   }
}
