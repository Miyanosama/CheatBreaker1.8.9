package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventBus$Event;

public class EmoteMouseInputEvent extends EventBus$Event {
   public boolean recoveredField811;
   public int recoveredField812;

   public EmoteMouseInputEvent(int var1, boolean var2) {
      this.recoveredField812 = var1;
      this.recoveredField811 = var2;
   }

   public boolean method_21079() {
      return this.recoveredField811;
   }

   public int method_21080() {
      return this.recoveredField812;
   }
}
