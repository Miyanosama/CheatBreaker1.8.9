package io.netty.util.concurrent;

import com.cheatbreaker.client.module.type.CombatCounterModule;
import net.minecraft.client.particle.EntityHeartFX$Factory;
import org.java_websocket.framing.TextFrame;

public class DefaultPromise$1 implements Runnable {
   public EntityHeartFX$Factory __junk5468574009092211749;
   public CombatCounterModule __junk3353187798869648572;
   public TextFrame __junk4841899471642781918;

   @Override
   public void run() {
      DefaultPromise.access$000(this.this$0, this.val$dfl);
      DefaultPromise.access$102(this.this$0, null);
   }

   public DefaultPromise$1(DefaultPromise var1, DefaultFutureListeners var2) {
      this.this$0 = var1;
      this.val$dfl = var2;
      super();
   }
}
