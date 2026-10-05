package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.client.gui.ScaledResolution;

public class HudPreviewDrawEvent extends EventBus$Event {
   public ScaledResolution recoveredField2516;

   public HudPreviewDrawEvent(ScaledResolution var1) {
      this.recoveredField2516 = var1;
   }

   public ScaledResolution method_01054() {
      return this.recoveredField2516;
   }
}
