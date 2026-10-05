package com.cheatbreaker.client.module.staff;

import com.cheatbreaker.client.event.type.CollisionEvent;

import com.cheatbreaker.client.module.staff.StaffModule;

public class NoClipModule extends StaffModule {
   public void method_24325(CollisionEvent var1) {
      var1.method_10233(true);
   }

   public NoClipModule() {
      super("noclip");
      this.method_28828(true);
      this.method_28820(CollisionEvent.class, this::method_24325);
   }
}
