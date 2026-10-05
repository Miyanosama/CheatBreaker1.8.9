package com.cheatbreaker.client.event.type;

import com.cheatbreaker.client.event.EventPhase;

import com.cheatbreaker.client.event.EventBus$Event;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;

public class PlayerModelRenderEvent extends EventBus$Event {
   public ModelPlayer recoveredField3420;
   public float recoveredField3421;
   public AbstractClientPlayer recoveredField3422;
   public EventPhase recoveredField3423;

   public EventPhase method_23156() {
      return this.recoveredField3423;
   }

   public PlayerModelRenderEvent(EventPhase var1, AbstractClientPlayer var2, ModelPlayer var3, float var4) {
      this.recoveredField3423 = var1;
      this.recoveredField3422 = var2;
      this.recoveredField3420 = var3;
      this.recoveredField3421 = var4;
   }

   public ModelPlayer method_23158() {
      return this.recoveredField3420;
   }

   public AbstractClientPlayer method_23155() {
      return this.recoveredField3422;
   }

   public float method_23157() {
      return this.recoveredField3421;
   }
}
