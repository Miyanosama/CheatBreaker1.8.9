package com.cheatbreaker.client.event;

import com.cheatbreaker.client.event.EventBus$Event;

public class CancellableEvent extends EventBus$Event {
   public boolean recoveredField3748 = false;

   public boolean method_10232() {
      return this.recoveredField3748;
   }

   public void method_10233(boolean var1) {
      this.recoveredField3748 = var1;
   }
}
