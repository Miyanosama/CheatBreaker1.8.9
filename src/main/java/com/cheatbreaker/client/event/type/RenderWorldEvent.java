package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;

public class RenderWorldEvent extends EventBus$Event {
   public float recoveredField2491;

   public float method_00120() {
      return this.recoveredField2491;
   }

   public RenderWorldEvent(float var1) {
      this.recoveredField2491 = var1;
   }
}
