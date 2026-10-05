package net.minecraft.client.renderer;

import java.util.concurrent.Callable;
import net.minecraft.entity.monster.EntityBlaze;
import net.optifine.util.TimedEvent;
import org.lwjgl.input.Mouse;
import recovered.unidentified.UnidentifiedClass3311;

public class EntityRenderer$3 implements Callable<String> {
   public TimedEvent field_0003;
   public UnidentifiedClass3311 field_0005;
   public EntityBlaze field_0004;

   public EntityRenderer$3(EntityRenderer var1, int var2, int var3) {
      this.this$0 = var1;
      this.val$k1 = var2;
      this.val$l1 = var3;
      super();
   }

   public String call() {
      return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", this.val$k1, this.val$l1, Mouse.getX(), Mouse.getY());
   }
}
