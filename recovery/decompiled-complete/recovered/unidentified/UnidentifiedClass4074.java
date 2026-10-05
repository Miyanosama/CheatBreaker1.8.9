package recovered.unidentified;

import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;
import io.netty.util.concurrent.DefaultPromise;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.renderer.entity.RenderSlime;

public class UnidentifiedClass4074 extends FloatFade {
   public MinMaxFade field_0003;
   public MinMaxFade field_0002;
   public DefaultPromise field_0004;
   public UnidentifiedClass1449 field_0005;
   public RenderSlime field_0000;
   public EntityHeartFX field_0001;

   @Override
   public void method_20200() {
      super.method_20200();
      if (!this.field_0002.method_21217()) {
         this.field_0002.method_20200();
      }
   }

   public float method_24497() {
      if (!this.field_0002.method_21217()) {
         this.field_0002.method_20200();
      }

      if (this.field_0002.method_21233()) {
         return Math.max(this.field_0002.method_21227(), 0.15F);
      } else if (this.method_21229() <= this.field_0003.method_21240()) {
         if (!this.field_0003.method_21217()) {
            this.field_0003.method_20200();
         }

         return 1.0F - 0.85F * this.field_0003.method_21227();
      } else {
         return 1.0F;
      }
   }

   public UnidentifiedClass4074(UnidentifiedClass1449 var1, long var2) {
      super((long)((float)var2));
      this.field_0005 = var1;
      this.field_0002 = new MinMaxFade(Math.min((long)Math.min((float)var2 * 0.2F, 3000.0F), 806438364L & 4661808601411884541L));
      this.field_0003 = new MinMaxFade(Math.min((long)((float)var2 * 0.4F), 5064618450887644104L & -5064618451749659768L));
   }

   public boolean method_24498() {
      return this.field_0002.method_21210();
   }
}
